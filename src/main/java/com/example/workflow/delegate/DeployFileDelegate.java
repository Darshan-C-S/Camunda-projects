package com.example.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DeployFileDelegate implements JavaDelegate {
    public void execute(DelegateExecution execution) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        String deploymentUrl = "http://localhost:8080/engine-rest/deployment/create";
        String filePath = "C:\\Users\\Darshan CS-3121\\Desktop\\Camunda Projects\\user1Task1.bpmn";
        String fileName = "user1Task1.bpmn";

        MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("deployment-name", "MyDeployment");
        requestMap.add("enable-duplicate-filtering", "true");

        // Replace the path with the location of your BPMN file
       // byte[] bpmnBytes = Files.readAllBytes(Paths.get("C:\\Users\\Darshan CS-3121\\Desktop\\Camunda Projects\\user1Task1.bpmn"));
        byte[] bpmnBytes = Files.readAllBytes(Paths.get(filePath));
        requestMap.add("file", new ByteArrayResource(bpmnBytes) {
            @Override
            public String getFilename() {
                //return "user1Task1.bpmn";
                return fileName;

            }
        });

        restTemplate.postForLocation(deploymentUrl, requestMap);
    }
}
