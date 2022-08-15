package com.copper.generator;

import com.copper.generator.models.ServiceToServicesRequest;
import guru.nidi.graphviz.attribute.GraphAttr;
import guru.nidi.graphviz.model.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DependencyGraphGenerator {

    private final List<ServiceToServicesRequest> reqToServs;

    public DependencyGraphGenerator(List<ServiceToServicesRequest> reqToServs)
    {
        this.reqToServs = reqToServs;
    }

    public MutableGraph generateGraph(Function<ServiceToServicesRequest, Collection<String>> func)
    {
        MutableGraph mutableGraph = Factory.mutGraph("G").setDirected(true).graphAttrs().add(GraphAttr.dpi(100));

        List<MutableNode> mutableNodes = reqToServs.stream().map(rts -> {
            Collection<String> tos = func.apply(rts); //todo: change to Set if needed
            //Collection<String> tos = new HashSet<>(func.apply(rts)); //todo: change to Set if needed
            List<Node> to = tos.stream().map(Factory::node).collect(Collectors.toList());
            return Factory.mutNode(rts.getServiceName()).addLink(to);
        }).collect(Collectors.toList());

        mutableGraph.add(mutableNodes);

        return mutableGraph;
    }
}
