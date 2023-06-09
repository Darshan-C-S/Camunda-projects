package com.example.workflow.delegate;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder; 

import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class DeployFileDelegateWorkflow implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        try {
            // Get the XML workflow from the process variable
            String xmlWorkflow = (String) execution.getVariable("Output_Attachment_Xml_Workflow");

            // Get the attachment name from the process variable
            String attachmentName = (String) execution.getVariable("AttachmentName");

            // Extract the filename without the extension
            String filenameWithoutExtension = attachmentName.substring(0, attachmentName.lastIndexOf("."));

            // Deploy the XML workflow with the extracted filename
            InputStream inputStream = new ByteArrayInputStream(xmlWorkflow.getBytes());
            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
            DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
            Deployment deployment = deploymentBuilder
                    .addInputStream(filenameWithoutExtension + ".bpmn", inputStream)
                    .deploy();

            // Store the deployment ID for future reference if needed
            String deploymentId = deployment.getId();
            execution.setVariable("deploymentId", deploymentId);

            // Log deployment success or perform any other post-deployment actions
            System.out.println("Workflow deployed successfully. Deployment ID: " + deploymentId);
        } catch (Exception e) {
            // Handle deployment failure
            e.printStackTrace();
            throw new RuntimeException("Failed to deploy workflow: " + e.getMessage());
        }
    }
}
