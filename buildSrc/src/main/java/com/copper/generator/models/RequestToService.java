package com.copper.generator.models;

import java.util.HashSet;
import java.util.Set;

public class RequestToService {
    private String serviceName;
    private Set<String> to = new HashSet<>(); // todo: TreeSet ?

    public RequestToService(String serviceName, Set<String> to)
    {
        this.serviceName = serviceName;
        this.to = to;
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
        return to;
    }

    public void setTo(Set<String> to)
    {
        this.to = to;
    }

    @Override
    public String toString()
    {
        return "RequestToService{" +
                "serviceName='" + serviceName + '\'' +
                ", to=" + to +
                '}';
    }
}
