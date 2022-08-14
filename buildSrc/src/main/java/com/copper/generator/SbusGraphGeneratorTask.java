package com.copper.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.nidi.graphviz.model.MutableGraph;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.copper.generator.models.*;

@CacheableTask
public class SbusGraphGeneratorTask extends DefaultTask {

    private List<Struct> requestsPerService;

    @TaskAction
    public void perform()
    {
        collectRequestsPerService();

        System.out.println("--------- Structs per service");
        requestsPerService.forEach(System.out::println);

        Set<ServiceToServicesRequest> servToServReqs = new HashSet<>();

        requestsPerService.forEach(prgStruct -> {
            ServiceToServicesRequest stsr = new ServiceToServicesRequest(prgStruct.getName());
            prgStruct.getRequests().forEach(request -> { //function?
                requestsPerService.forEach(prgS -> {
                    if (prgS.getSubscribes().contains(request)) stsr.getRequestTo().add(prgS.getName());
                });
            });
            prgStruct.getCommands().forEach(request -> { //function?
                requestsPerService.forEach(prgS -> {
                    if (prgS.getSubscribes().contains(request)) stsr.getCommandTo().add(prgS.getName());
                });
            });
            prgStruct.getEvents().forEach(request -> { //function?
                requestsPerService.forEach(prgS -> {
                    if (prgS.getSubscribes().contains(request)) stsr.getEventTo().add(prgS.getName());
                });
            });
            servToServReqs.add(stsr);
        });

        System.out.println("-------------Request to Service");
        servToServReqs.forEach(System.out::println);

        DependencyGraphGenerator generator = new DependencyGraphGenerator(servToServReqs);
        MutableGraph graph = generator.generateGraph();
        System.out.println(graph);
        saveDot(graph);

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

    private void collectRequestsPerService(){
        requestsPerService = getProject().getSubprojects().stream().map(pr -> {
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
    }

    private void saveDot(MutableGraph mutableGraph)
    {
        File dir = new File(getProject().getBuildDir().getPath() + "/reporting");
        dir.mkdirs();
        File file = new File(dir.getPath() + "/report.dot");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mutableGraph.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
