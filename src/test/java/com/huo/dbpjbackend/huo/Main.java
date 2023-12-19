//package com.huo.dbpjbackend.huo;
//
//import java.util.*;
//
//public class Main {
//    List<String> from = Arrays.asList("0", "1");
//    List<String> to = Arrays.asList("1", "2");
//
//    List<Map<String, String>> nodes = new ArrayList<>();
//    List<Map<String, String>> edges = new ArrayList<>();
//
//    for(int i = 0; i < from.size(); i++) {
//            String fromNode = from.get(i);
//            String toNode = to.get(i);
//
//            Map<String, String> node = new HashMap<>();
//            node.put("id", fromNode);
//            node.put("label", fromNode);
//            nodes.add(node);
//
//            Map<String, String> edge = new HashMap<>();
//            edge.put("source", fromNode);
//            edge.put("target", toNode);
//            edges.add(edge);
//        }
//
//        Map<String, Object> data = new HashMap<>();
//            data.put("nodes", nodes);
//            data.put("edges", edges);
//}