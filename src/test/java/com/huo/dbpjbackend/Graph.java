package com.huo.dbpjbackend;

import java.util.*;

public class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(String node) {
        if (!adjacencyList.containsKey(node)) {
            adjacencyList.put(node, new ArrayList<>());
        }
    }

    public void addEdge(String from, String to) {
        addNode(from);
        addNode(to);
        adjacencyList.get(from).add(to);
    }

    public List<List<String>> findPath(String from) {
        List<List<String>> paths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        findPathRecursive(from, from, paths, currentPath, visited);
        return paths;
    }

    private void findPathRecursive(String start, String current, List<List<String>> paths,
                                   List<String> currentPath, Set<String> visited) {
        visited.add(current);
        currentPath.add(current);

        for (String neighbor : adjacencyList.get(current)) {
            if (neighbor.equals(start) && !currentPath.isEmpty()) {
                currentPath.add(neighbor);
                paths.add(new ArrayList<>(currentPath));
                currentPath.remove(currentPath.size() - 1);
            } else if (!visited.contains(neighbor)) {
                findPathRecursive(start, neighbor, paths, currentPath, visited);
            }
        }

        currentPath.remove(currentPath.size() - 1);
        visited.remove(current);
    }

    public List<List<String>> searchPath(){
        List<List<String>> AllPaths = new ArrayList<>();
        List<String> currentPath = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            if (values!=null){
                currentPath.add(key);

            }

        }
        return null;
    }




    public static void main(String[] args) {
//        List<String> fromList = Arrays.asList("A", "B","B", "C","D","F");
//        List<String> toList = Arrays.asList("B", "C","D","D","A","G");

        List<String> fromList = Arrays.asList("A","B","A","C");
        List<String> toList = Arrays.asList("B","A","C","A");

        Graph graph = new Graph();
        for (int i = 0; i < fromList.size(); i++) {
            graph.addEdge(fromList.get(i), toList.get(i));
        }
        System.out.println(""+graph.adjacencyList);

        System.out.println(""+graph.findPath(fromList.get(0)));
//        System.out.println(""+graph.findPath("G"));

    }
}

