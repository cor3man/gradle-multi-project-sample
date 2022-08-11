package com.copper.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.TaskAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.copper.generator.models.*;

@CacheableTask
public class SbusGraphGeneratorTask extends DefaultTask {
    @TaskAction
    public void perform()
    {
        System.out.println("Hi from Sbus plugin");
        Set<Project> projects = getProject().getSubprojects();

        List<Struct> prgStructs = projects.stream().map(pr -> {
            Struct prgStruct = new Struct();
            prgStruct.setName(pr.getName());

            List<Struct> structs = readProjectJsons(pr).stream().map(this::convertJsonFileToStruct).collect(Collectors.toList());
            structs.forEach(System.out::println);

            structs.forEach(s -> {
                prgStruct.getCommands().addAll(s.getCommands());
                prgStruct.getEvents().addAll(s.getEvents());
                prgStruct.getRequests().addAll(s.getRequests());
                prgStruct.getSubscribes().addAll(s.getSubscribes());
            });
            return prgStruct;
        }).collect(Collectors.toList());
        System.out.println("--------- Structs per project");
        prgStructs.forEach(System.out::println);
    }

    private List<Path> readProjectJsons(Project project)
    {
        try (Stream<Path> paths = Files.walk(Paths.get(project.getBuildDir().getPath() + "/ccplugin"))) {
            return paths.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Struct convertJsonFileToStruct(Path path)
    {
        Struct struct = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            struct = mapper.readValue(path.toFile(), Struct.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return struct;
    }
}
