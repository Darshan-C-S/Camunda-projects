package com.example.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        String url = "http://localhost:8080/engine-rest/task";
        Connection.Response response = Jsoup.connect(url).ignoreContentType(true).execute();
        try {
            Document doc = response.parse();
            String data = doc.text();
            execution.setVariable("data", data);
        }catch (IOException e){
            throw new Exception("Error Retriving data from the rest end point"+ e.getMessage());
        }
    }
    // String url = (String) execution.getVariable("");
//        Document doc = Jsoup.connect(url).get();
//        execution.setVariable("html", doc.html());
}
