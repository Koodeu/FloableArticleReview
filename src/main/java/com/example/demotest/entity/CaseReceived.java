package com.example.demotest.entity;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class CaseReceived implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("Article has been received to review.");
    }
}
