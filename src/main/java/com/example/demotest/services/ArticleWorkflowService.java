package com.example.demotest.services;

import com.example.demotest.entity.Article;

import java.util.List;

public interface ArticleWorkflowService {
    Article startProcess(Article article);

    List<Article> getTasksForAssignee(String assignee);

    void decide(String taskId, String decision);

}
