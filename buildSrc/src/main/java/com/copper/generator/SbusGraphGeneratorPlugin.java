package com.copper.generator;

import com.copper.generator.models.ServiceToServicesRequest;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

public class SbusGraphGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(@NotNull Project project)
    {
        project.getTasks().register("SbusGeneratorAll", SbusGraphGeneratorTask.class, task -> {
            task.setGroup("reporting");
            task.setFunc(ServiceToServicesRequest::getTo);
        });
        project.getTasks().register("SbusGeneratorRequests", SbusGraphGeneratorTask.class, task -> {
            task.setGroup("reporting");
            task.setFunc(ServiceToServicesRequest::getRequestTo);
        });
        project.getTasks().register("SbusGeneratorCommands", SbusGraphGeneratorTask.class, task -> {
            task.setGroup("reporting");
            task.setFunc(ServiceToServicesRequest::getCommandTo);
        });
        project.getTasks().register("SbusGeneratorEvents", SbusGraphGeneratorTask.class, task -> {
            task.setGroup("reporting");
            task.setFunc(ServiceToServicesRequest::getEventTo);
        });
    }
}
