package tree.bfs;

import java.util.*;

public class ChatbotFlowChecker {

    // Adjacency map to store the directed graph
    private final Map<String, List<String>> adjacencyList;

    public ChatbotFlowChecker() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a directed flow (edge) from source step to destination step.
     * @param source The starting step name.
     * @param destination The next step name.
     */
    public void addFlow(String source, String destination) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.get(source).add(destination);
    }

    /**
     * Determines if a flow (path) exists between a start and an end step using BFS.
     * @param startStep The name of the starting step.
     * @param endStep The name of the destination step.
     * @return true if a path exists, false otherwise.
     */
    public boolean hasFlow(String startStep, String endStep) {
        // Base case: if start and end are the same, a path trivially exists
        if (startStep.equals(endStep)) {
            return true;
        }

        // Queue for BFS traversal
        Queue<String> queue = new LinkedList<>();
        // Set to keep track of visited steps to prevent cycles and redundant visits
        Set<String> visited = new HashSet<>();

        queue.add(startStep);
        visited.add(startStep);

        while (!queue.isEmpty()) {
            String currentStep = queue.poll();

            // Get all adjacent steps (neighbors)
            List<String> neighbors = adjacencyList.getOrDefault(currentStep, Collections.emptyList());

            for (String neighbor : neighbors) {
                if (neighbor.equals(endStep)) {
                    // Destination found
                    return true;
                }
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // If the queue is empty and the destination was not reached
        return false;
    }

    public static void main(String[] args) {
        ChatbotFlowChecker graph = new ChatbotFlowChecker();

        // Define the chatbot flow steps as directed edges
        graph.addFlow("Start", "CollectInfo");
        graph.addFlow("CollectInfo", "VerifyInfo");
        graph.addFlow("VerifyInfo", "ProcessPayment");
        graph.addFlow("ProcessPayment", "ConfirmOrder");
        graph.addFlow("CollectInfo", "AskForHelp"); // Alternative flow from CollectInfo
        graph.addFlow("AskForHelp", "ConnectToAgent");
        graph.addFlow("ConfirmOrder", "End");
        graph.addFlow("ConnectToAgent", "End");

        // Example 1: Check flow from "Start" to "End"
        String start1 = "Start";
        String end1 = "End";
        System.out.println("Is there a flow from \"" + start1 + "\" to \"" + end1 + "\"? " + graph.hasFlow(start1, end1)); // Output: true

        // Example 2: Check flow from "VerifyInfo" to "ConnectToAgent"
        String start2 = "VerifyInfo";
        String end2 = "ConnectToAgent";
        System.out.println("Is there a flow from \"" + start2 + "\" to \"" + end2 + "\"? " + graph.hasFlow(start2, end2)); // Output: true

        // Example 3: Check flow from "ProcessPayment" to "AskForHelp" (no direct or indirect flow back)
        String start3 = "ProcessPayment";
        String end3 = "AskForHelp";
        System.out.println("Is there a flow from \"" + start3 + "\" to \"" + end3 + "\"? " + graph.hasFlow(start3, end3)); // Output: false
    }
}
