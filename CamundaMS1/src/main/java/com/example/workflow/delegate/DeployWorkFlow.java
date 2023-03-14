package com.example.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class DeployWorkFlow implements JavaDelegate {
    @Override

    public void execute(DelegateExecution execution) throws Exception {
//            String filePath = "C:/Users/Darshan CS-3121/Desktop/Camunda Projects/diagram_1FeedPet.bpmn";
//            String deploymentName = "My Deployment Name";
//
//            byte[] bpmnBytes = Files.readAllBytes(Paths.get(filePath));
//            System.out.println(bpmnBytes);
//
//            MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
//            bodyMap.add("deployment-name", deploymentName);
//            bodyMap.add("enable-duplicate-filtering", "true");
//            bodyMap.add("deployment-source", "Camunda Modeler");
//            bodyMap.add("data", new String(bpmnBytes) );//, MediaType.APPLICATION_OCTET_STREAM_VALUE);
//            bodyMap.add("resource", new File(filePath).getName());
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
//
//            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
//
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/engine-rest/deployment/create", HttpMethod.POST, requestEntity, String.class);
//
//            if (response.getStatusCode() != HttpStatus.OK) {
//                throw new RuntimeException("Failed to deploy process: " + response.getBody());
//            }
//        }
//
//    }
    }
}