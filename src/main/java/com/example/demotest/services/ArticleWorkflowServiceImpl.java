package com.example.demotest.services;

import com.example.demotest.entity.Article;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleWorkflowServiceImpl implements ArticleWorkflowService {


    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;


    @Override
    public Article startProcess(Article article) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("author", article.getAuthor());
        variables.put("title", article.getTitle());
        variables.put("articleBody", article.getArticleBody());
        variables.put("status", "new");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("articleReview", variables);
        article.setArticleProcessID(UUID.fromString(processInstance.getId()));
        return article;
    }

    @Override
    public List<Article> getTasksForAssignee(String assignee) {
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateGroup(assignee)
                .list();
        return tasks.stream().map(task -> {
                    Map<String, Object> variables = taskService.getVariables(task.getId());
                    return new Article((UUID.fromString(
                            task.getProcessInstanceId())),
                            UUID.fromString(task.getId()),
                            (String) variables.get("author"), (String) variables.get("title"),
                            (String) variables.get("articleBody"), (String) variables.get("status"));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void decide(String id, String decision) {
        Task task = taskService.createTaskQuery()
                .processInstanceId(id)
                .includeProcessVariables()
                .singleResult();
        String taskId = task.getId();

        Map<String, Object> variables = new HashMap<>();
        variables.put("status", decision);
        taskService.complete(task.getId(), variables);

    }
}

