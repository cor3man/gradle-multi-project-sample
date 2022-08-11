package com.copper.generator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Struct {
    private String name;
    private List<String> requests = new ArrayList<>();
    private List<String> subscribes = new ArrayList<>();
    private List<String> commands = new ArrayList<>();;
    private List<String> events = new ArrayList<>();;

    public Struct()
    {
    }

    public Struct(String name, List<String> requests, List<String> subscribes, List<String> commands, List<String> events)
    {
        this.name = name;
        this.requests = requests;
        this.subscribes = subscribes;
        this.commands = commands;
        this.events = events;
    }

    @Override
    public String toString()
    {
        return "Struct{" +
                "name='" + name + '\'' +
                ", requests=" + requests +
                ", subscribes=" + subscribes +
                ", commands=" + commands +
                ", events=" + events +
                '}';
    }

    @JsonProperty("ClassName")
    public String getName()
    {
        return name;
    }

    @JsonProperty("ClassName")
    public void setName(String name)
    {
        this.name = name;
    }

    @JsonProperty("request")
    public List<String> getRequests()
    {
        return requests;
    }

    @JsonProperty("request")
    public void setRequests(List<String> requests)
    {
        this.requests = requests;
    }

    @JsonProperty("subscribe")
    public List<String> getSubscribes()
    {
        return subscribes;
    }

    @JsonProperty("subscribe")
    public void setSubscribes(List<String> subscribes)
    {
        this.subscribes = subscribes;
    }

    @JsonProperty("command")
    public List<String> getCommands()
    {
        return commands;
    }

    @JsonProperty("command")
    public void setCommands(List<String> commands)
    {
        this.commands = commands;
    }

    @JsonProperty("event")
    public List<String> getEvents()
    {
        return events;
    }

    @JsonProperty("event")
    public void setEvents(List<String> events)
    {
        this.events = events;
    }
}
