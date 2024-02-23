package com.example.demo.controllers;


import com.example.demo.entities.Agent;
import com.example.demo.entities.Singer;
import com.example.demo.responses.ResponseHandler;
import com.example.demo.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class agentController {
    @Autowired
    AgentService agentService;

    @GetMapping("/agents")
    public ResponseEntity<Object> findAllAgents() {

        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, agentService.findAll());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/agents/{id}")
    public ResponseEntity<Object> findAgentById(@PathVariable Integer id) {
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, agentService.findById(id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PostMapping("/agents/{singerId}")
    public ResponseEntity<Object> addAgent(@RequestBody Agent agent, @PathVariable Integer singerId) {
        try {
            Agent savedAgent = agentService.saveAgent(agent, singerId);
            return ResponseHandler.generateResponse("Agent added successfully", HttpStatus.CREATED, savedAgent);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/agents/{id}")
    public ResponseEntity<Object> deleteAgent(@PathVariable Integer id) {
        try {
            Agent agent = agentService.findById(id);
            if (agent != null){

                agentService.deleteAgent(agent);

                return ResponseHandler.generateResponse("Success Agent", HttpStatus.ACCEPTED, agent);

            }
            return ResponseHandler.generateResponse("Success Agent", HttpStatus.NOT_FOUND, null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/agents/{id}")
    public ResponseEntity<Object> updateAgent(@PathVariable Integer id, @RequestBody Agent agent) {
        try {
            Agent updatedAgent = agentService.updateAgent(agent);
            if (updatedAgent != null) {
                return ResponseHandler.generateResponse("Agent updated successfully", HttpStatus.OK, updatedAgent);
            } else {
                return ResponseHandler.generateResponse("Agent not found", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/agents/{id}/singer")
    public ResponseEntity<Object> getSingerByAgentId(@PathVariable Integer id) {
        try {
            Singer singer = agentService.getSingerByAgentId(id);
            return ResponseHandler.generateResponse("Singer retrieved successfully", HttpStatus.OK, singer);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
