package com.example.workflow.delegate;

import okhttp3.*;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.io.File;
import java.nio.file.Files;

public class DeployProcessDefinitionDelegate implements JavaDelegate {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json");

    private static final MediaType XML_MEDIA_TYPE = MediaType.parse("application/xml");

    private static final MediaType BPMN_MEDIA_TYPE = MediaType.parse("application/bpmn");

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Read the process definition file from the local file system
        String filePath = (String) execution.getVariable("diagram_1FeedPet.bpmn");
        byte[] fileContent = Files.readAllBytes(new File(filePath).toPath());

        // Get the Camunda REST API endpoint URL from the process engine configuration
        String camundaRestUrl = (String) execution.getVariable("http://localhost:8080/engine-rest");

        // Create a POST request to deploy the process definition
        RequestBody requestBody = RequestBody.create(fileContent, BPMN_MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(camundaRestUrl + "/deployment/create")
                .post(requestBody)
                .build();

        // Execute the request and handle the response
        try (Response response = HTTP_CLIENT.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to deploy process definition: " + response.body().string());
            }
        }
    }

}
