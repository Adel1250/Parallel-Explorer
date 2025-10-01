package com.adel.explorer.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class SequentialSearchStrategy implements SearchStrategy {
    @Override
    public void search(File folder, String searchWord) {
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("No .txt files found in the folder.");
            return;
        }
        for (File file : files) {
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    if (line.contains(searchWord)) {
                        System.out.printf("%s Line %d: %s\n", file.getName(), i + 1, line);
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }
}

