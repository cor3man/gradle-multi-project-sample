package com.copper.generator.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceToServicesRequest {
    private String serviceName;
    private List<String> eventTo = new ArrayList<>();
    private List<String> requestTo = new ArrayList<>();
    private List<String> commandTo = new ArrayList<>();
    private List<String> to;

    public ServiceToServicesRequest(){}

    public ServiceToServicesRequest(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void ListServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public List<String> getTo()
    {
        to = Stream.of(requestTo, commandTo, eventTo)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return to;
    }

    public List<String> getEventTo()
    {
        return eventTo;
    }

    public List<String> getRequestTo()
    {
        return requestTo;
    }

    public List<String> getCommandTo()
    {
        return commandTo;
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
