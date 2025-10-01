package com.adel.explorer.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;

public class CompletableFutureSearchStrategy implements SearchStrategy {
    @Override
    public void search(File folder, String searchWord) {
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("No .txt files found in the folder.");
            return;
        }
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (File file : files) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    List<String> lines = Files.readAllLines(file.toPath());
                    for (int i = 0; i < lines.size(); i++) {
                        String line = lines.get(i);
                        if (line.contains(searchWord)) {
                            System.out.printf("%s Line %d: %s\n", file.getName(), i + 1, line);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }
            });
            futures.add(future);
        }
        for (CompletableFuture<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("  Error in async task: " + e.getMessage());
            }
        }
    }
}
