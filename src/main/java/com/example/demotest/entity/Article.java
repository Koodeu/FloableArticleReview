package com.example.demotest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.flowable.task.api.Task;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Article {



    private UUID articleId;
    private String author;
    private String title;
    private String articleBody;

    public Article(Task task) {
        Map<String , Object> articleVariables = task.getProcessVariables();
        this.articleId = UUID.fromString(task.getProcessInstanceId());
        this.author = (String) articleVariables.get("author");
        this.title = (String) articleVariables.get("title");
        this.articleBody = (String) articleVariables.get("articleBody");
    }

}
