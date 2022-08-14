package com.copper.generator.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ServiceToServicesRequest {
    private String serviceName;
    private Set<String> eventTo = new HashSet<>();
    private Set<String> requestTo = new HashSet<>();
    private Set<String> commandTo = new HashSet<>();

    private Set<String> to;

    public ServiceToServicesRequest(){}

    public ServiceToServicesRequest(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public Set<String> getTo()
    {
        to = new HashSet<>();
        to.addAll(requestTo);
        to.addAll(commandTo);
        to.addAll(eventTo);

        return requestTo;
    }

    public void setTo(Set<String> to)
    {
        this.to = to;
    }



    public Set<String> getEventTo()
    {
        return eventTo;
    }

    public void setEventTo(Set<String> eventTo)
    {
        this.eventTo = eventTo;
    }

    public Set<String> getRequestTo()
    {
        return requestTo;
    }

    public void setRequestTo(Set<String> requestTo)
    {
        this.requestTo = requestTo;
    }

    public Set<String> getCommandTo()
    {
        return commandTo;
    }

    public void setCommandTo(Set<String> commandTo)
    {
        this.commandTo = commandTo;
    }


    @Override
    public String toString()
    {
        return "ServiceToServicesRequest{" +
                "serviceName='" + serviceName + '\'' +
                ", eventTo=" + eventTo +
                ", requestTo=" + requestTo +
                ", commandTo=" + commandTo +
                ", to=" + getTo() +
                '}';
    }
}
