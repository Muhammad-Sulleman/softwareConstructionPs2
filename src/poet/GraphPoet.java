package poet;

import graph.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
/**
 * A graph-based poetry generator.
 */
public class GraphPoet {

    private final Graph<String> wordGraph = Graph.empty();

    /**
     * Create a GraphPoet using a corpus file.
     *
     * @param corpus corpus file used to build the word graph
     * @throws IOException if reading the corpus fails
     */
    public GraphPoet(File corpus) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(corpus))) {
            String line;
            while ((line = reader.readLine()) != null) {
                addWordsToGraph(line);
            }
        }
    }

    /**
     * Builds the graph by adding edges between consecutive words.
     */
    private void addWordsToGraph(String line) {
        String[] words = line.toLowerCase().split("\\s+"); // Split on whitespace
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int weight = wordGraph.targets(word1).getOrDefault(word2, 0) + 1; // Increment weight
            wordGraph.addEdge(word1, word2, weight);
        }
    }

    /**
     * Generates a poem by inserting bridge words between input words.
     *
     * @param input text to transform into a poem
     * @return transformed poem
     */
    public String poem(String input) {
        String[] words = input.split("\\s+");
        StringBuilder poem = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i].toLowerCase();
            String word2 = words[i + 1].toLowerCase();

            // Add the first word
            poem.append(words[i]).append(" ");

            // Find the bridge word (if any)
            String bridge = findBridgeWord(word1, word2);
            if (bridge != null) {
                poem.append(bridge).append(" ");
            }
        }

        // Add the last word
        poem.append(words[words.length - 1]);

        return poem.toString();
    }

    /**
     * Finds a bridge word between two words based on graph weights.
     */
    private String findBridgeWord(String word1, String word2) {
        int maxWeight = 0;
        String bridge = null;

        for (String candidate : wordGraph.targets(word1).keySet()) {
            if (wordGraph.sources(word2).containsKey(candidate)) {
                int weight = wordGraph.targets(word1).get(candidate)
                        + wordGraph.sources(word2).get(candidate);
                if (weight > maxWeight) {
                    maxWeight = weight;
                    bridge = candidate;
                }
            }
        }

        return bridge;
    }
}
