package com.copper.generator;

import com.copper.generator.models.ServiceToServicesRequest;
import guru.nidi.graphviz.attribute.GraphAttr;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DependencyGraphGenerator {

    private final Set<ServiceToServicesRequest> reqToServs;

    public DependencyGraphGenerator(Set<ServiceToServicesRequest> reqToServs)
    {
        this.reqToServs = reqToServs;
    }

    public MutableGraph generateGraph()
    {
        MutableGraph mutableGraph = Factory.mutGraph("G").setDirected(true).graphAttrs().add(GraphAttr.dpi(100));

        List<MutableNode> mutableNodes = reqToServs.stream().map(rts -> {
            List<MutableNode> to = rts.getTo().stream().map(Factory::mutNode).collect(Collectors.toList());
            return Factory.mutNode(rts.getServiceName()).addLink(to);
        }).collect(Collectors.toList());

        mutableGraph.add(mutableNodes);

        return mutableGraph;
    }
}
