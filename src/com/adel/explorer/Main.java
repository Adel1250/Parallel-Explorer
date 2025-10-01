package com.adel.explorer;

import com.adel.explorer.search.CompletableFutureSearchStrategy;
import com.adel.explorer.search.ForkJoinSearchStrategy;
import com.adel.explorer.search.SearchStrategy;
import com.adel.explorer.search.SequentialSearchStrategy;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String folderPath = "test-files";
        String searchWord = "mentorship";

        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Invalid folder path.");
            return;
        }

        SearchStrategy[] strategies = {
                new SequentialSearchStrategy(),
                new ForkJoinSearchStrategy(),
                new CompletableFutureSearchStrategy()
        };
        String[] strategyNames = {"Sequential", "Fork/Join", "CompletableFuture"};

        for (int i = 0; i < strategies.length; i++) {
            System.out.println("\n--- " + strategyNames[i] + " Search ---");
            long start = System.nanoTime();
            strategies[i].search(folder, searchWord);
            long end = System.nanoTime();
            double durationMs = (end - start) / 1_000_000.0;
            System.out.printf("%s Search took %.2f ms\n", strategyNames[i], durationMs);
        }
    }
}