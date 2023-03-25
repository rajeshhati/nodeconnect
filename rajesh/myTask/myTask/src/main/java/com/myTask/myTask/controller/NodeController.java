
package com.myTask.myTask.controller;


import com.myTask.myTask.Entity.Node;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class NodeController {

    // Initialize an empty HashMap to represent the graph
    private Map<Integer, Node> graph = new HashMap<>();
    @PostMapping("/nodes/join")
    public Map<String, Object> connectNodes(@RequestBody Map<String, Integer> payload) {
        int node1 = payload.get("node1");
        int node2 = payload.get("node2");

        // Check if the nodes already exist in the graph
        if (!graph.containsKey(node1)) {
            graph.put(node1, new Node(node1));
        }
        if (!graph.containsKey(node2)) {
            graph.put(node2, new Node(node2));
        }

        // Connect the nodes to each other
        graph.get(node1).connect(graph.get(node2));
        graph.get(node2).connect(graph.get(node1));

        // Return a success message
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", String.format("Nodes %d and %d are now connected.", node1, node2));
        return response;
    }

    @GetMapping("/nodes/isConnected")
    public Map<String, Boolean> areNodesConnected(@RequestParam int node1, @RequestParam int node2) {
        // Check if the nodes exist in the graph
//        if (!graph.containsKey(node1) || !graph.containsKey(node2)) {
//            throw new IllegalArgumentException("Node %d not found in the graph.");
//        }

        // Perform a depth-first search to check if there is a path between the nodes
        boolean isConnected = dfs(node1, node2, new HashSet<>());

        // Return a boolean indicating if the nodes are connected
        Map<String, Boolean> response = new HashMap<>();
        response.put("isConnected", isConnected);
        return response;
    }
    private boolean dfs(int node1, int node2, Set<Integer> visited) {
        // Mark the current node as visited
        visited.add(node1);

        // If the current node is the target node, return true
        if (node1 == node2) {
            return true;
        }
        // Recursively visit all neighbors of the current node
        for (Node neighbor : graph.get(node1).getNeighbors()) {
            if (!visited.contains(neighbor.getId())) {
                if (dfs(neighbor.getId(), node2, visited)) {
                    return true;
                }
            }
        }
        // If no path was found, return false
        return false;
    }
}

