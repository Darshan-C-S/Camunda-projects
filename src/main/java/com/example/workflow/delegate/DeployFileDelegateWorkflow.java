package com.example.workflow.delegate;

import org.apache.commons.io.IOUtils;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;

public class DeployFileDelegateWorkflow {
    @Value("${bpmn.filename}")
    private String bpmnFilename;

    public void execute(DelegateExecution execution) throws Exception {
        try {
            TaskService taskService = execution.getProcessEngineServices().getTaskService();
            Task currentTask = taskService.createTaskQuery().processInstanceId(execution.getProcessInstanceId()).singleResult();

            // Retrieve the BPMN model of the process definition containing both tasks
            String processDefinitionId = currentTask.getProcessDefinitionId();
            InputStream bpmnModelStream = execution.getProcessEngineServices().getRepositoryService().getProcessModel(processDefinitionId);

            // Deploy the BPMN model using the REST API
            RestTemplate restTemplate = new RestTemplate();
            String deploymentUrl = "http://localhost:8090/engine-rest/deployment/create";

            MultiValueMap<String, Object> requestMap = new LinkedMultiValueMap<>();
            requestMap.add("deployment-name", "MyDeployment");
            requestMap.add("enable-duplicate-filtering", "true");

            // Add the BPMN model as a file to the deployment request
            byte[] bpmnBytes = IOUtils.toByteArray(bpmnModelStream);
            requestMap.add("file", new ByteArrayResource(bpmnBytes) {
                @Override
                public String getFilename() {
                    return bpmnFilename;
                }
            });

            restTemplate.postForLocation(deploymentUrl, requestMap);
        } catch (RestClientException e) {
            // Handle the REST client exception
            // You can log the error or throw a custom exception, or perform any other error handling logic
            e.printStackTrace();
            throw new RuntimeException("Failed to deploy BPMN model: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions
            // You can log the error or throw a custom exception, or perform any other error handling logic
            e.printStackTrace();
            throw new RuntimeException("An error occurred during BPMN deployment: " + e.getMessage());
        }
    }
}