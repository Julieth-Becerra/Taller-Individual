package com.example.demo.services;

import com.example.demo.entities.Agent;
import com.example.demo.entities.Singer;
import com.example.demo.repositories.AgentRepository;
import com.example.demo.repositories.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;
    @Autowired
    private SingerRepository singerRepository;

    public List<Agent> findAll(){
        return agentRepository.findAll();
    }

    public Agent findById(Integer id){
        Optional<Agent> optional = agentRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }


    public Agent saveAgent(Agent agent, Integer singerId) {
        Optional<Singer> optionalSinger = singerRepository.findById(singerId);
        if (optionalSinger.isPresent()) {
            Singer singer = optionalSinger.get();
            agent.setSinger(singer);
            singer.setAgent(agent);
            return agentRepository.save(agent);
        } else {
            throw new RuntimeException("Singer with ID " + singerId + " not found.");
        }
    }

    public void deleteAgent(Agent agent) {
        if (agent.getSinger() != null) {
            throw new RuntimeException("Cannot delete agent because it is associated with a singer.");
        }
        agentRepository.delete(agent);
    }

    public Agent updateAgent(Agent updatedAgent) {
        Optional<Agent> optional = agentRepository.findById(updatedAgent.getId());
        if (optional.isPresent()) {
            Agent existingAgent = optional.get();
            existingAgent.setName(updatedAgent.getName());
            existingAgent.setLastName(updatedAgent.getLastName());
            return agentRepository.save(existingAgent);
        }
        return null;
    }

    public Singer getSingerByAgentId(Integer agentId) {
        Optional<Agent> optionalAgent = agentRepository.findById(agentId);
        if (optionalAgent.isPresent()) {
            return optionalAgent.get().getSinger();
        } else {
            throw new RuntimeException("Agent with ID " + agentId + " not found.");
        }
    }
}
