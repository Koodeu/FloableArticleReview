package com.example.demotest.controller;


import com.example.demotest.entity.Article;
import com.example.demotest.services.ArticleWorkflowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {


    @Autowired
    ArticleWorkflowServiceImpl articleWorkflowServiceImpl;

    @PostMapping("/submitArticle")
    public Article submitNewArticle(@RequestBody Article article) {
        return articleWorkflowServiceImpl.startProcess(article);
    }

    @GetMapping("/tasks")
    public List<Article> getTasks(@RequestParam String assignee) {
        return articleWorkflowServiceImpl.getTasksForAssignee(assignee);
    }

    @PostMapping("/decision")
    public void decide(@RequestParam String articleProcessId, String decision) {
        articleWorkflowServiceImpl.decide(articleProcessId, decision);
    }

    @GetMapping("/hello")
    public String print() {
        return "hello";
    }

    @GetMapping("/newtasks")
    public List<Article> getNewTasks() {
        return articleWorkflowServiceImpl.listOfNewArticles();
    }

    @GetMapping("/author")
    public List<Article> fetchArticlesByAuthor(@RequestParam String authorName){
        return articleWorkflowServiceImpl.listOfArticlesByGivenAuthor(authorName);
    }


}
