package com.example.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Named;

@Named("helloWorld")
@Service
public  class HelloWorldDeligate implements JavaDelegate {
    @Override
    public void execute (DelegateExecution deligateExicution){
        System.out.println("hello World");
        System.out.println("Hello Venkat");
    }
}
