package com.copper.generator;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.jetbrains.annotations.NotNull;

public class SbusGraphGeneratorPlugin implements Plugin<Project> {

    @Override
    public void apply(@NotNull Project project)
    {
        project.getTasks().register("SbusGeneratorPlugin", SbusGraphGeneratorTask.class, task -> {
            task.setGroup("reporting");
        });
    }
}
