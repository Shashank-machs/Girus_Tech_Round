package Alien_Dictionary;

import java.util.*;
public class Alien_Dict {

    public String findOrder(String[] words) {
        // Step 1: Initialize graph and in-degree map
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        // Initialize all chars in the graph
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        // Step 2: Build the graph by comparing adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i+1];

            // invalid if w1 longer than w2 but w2 is prefix of w1
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";

            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                char parent = w1.charAt(j);
                char child = w2.charAt(j);
                if (parent != child) {
                    // Add edge parent -> child
                    if (!graph.get(parent).contains(child)) {
                        graph.get(parent).add(child);
                        inDegree.put(child, inDegree.get(child) + 1);
                    }
                    break;
                }
            }
        }

        // Step 3:  sort
        Queue<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) queue.offer(c);
        }

        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char curr = queue.poll();
            order.append(curr);
            for (char nei : graph.get(curr)) {
                inDegree.put(nei, inDegree.get(nei) - 1);
                if (inDegree.get(nei) == 0) queue.offer(nei);
            }
        }

        // If order length differs from total unique chars, cycle exists
        if (order.length() != graph.size()) return "";

        return order.toString();
    }

    // ---------- Test Cases ----------
    public static void main(String[] args) {
        Alien_Dict alienDict = new Alien_Dict();

        // Test Case 1
        String[] words1 = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println("Order: " + alienDict.findOrder(words1));
        // o/p: wertf

        // Test Case 2:
        String[] words2 = {"abc", "ab"};
        System.out.println("Order: " + alienDict.findOrder(words2));
        // o/p: (empty string)

        // Test Case 3: Simple order
        String[] words3 = {"z", "x"};
        System.out.println("Order: " + alienDict.findOrder(words3));
        // o/p: zx

    }
}

