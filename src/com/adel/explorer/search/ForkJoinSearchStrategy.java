package com.adel.explorer.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinSearchStrategy implements SearchStrategy {
    private static class SearchTask extends RecursiveAction {
        private final File[] files;
        private final String searchWord;
        private final int start, end;
        private static final int THRESHOLD = 2;

        SearchTask(File[] files, String searchWord, int start, int end) {
            this.files = files;
            this.searchWord = searchWord;
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= THRESHOLD) {
                for (int i = start; i < end; i++) {
                    File file = files[i];
                    try {
                        List<String> lines = Files.readAllLines(file.toPath());
                        for (int j = 0; j < lines.size(); j++) {
                            String line = lines.get(j);
                            if (line.contains(searchWord)) {
                                System.out.printf("%s Line %d: %s\n", file.getName(), j + 1, line);
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading file: " + e.getMessage());
                    }
                }
            } else {
                int mid = (start + end) / 2;
                invokeAll(
                        new SearchTask(files, searchWord, start, mid),
                        new SearchTask(files, searchWord, mid, end)
                );
            }
        }
    }

    @Override
    public void search(File folder, String searchWord) {
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
        if (files == null || files.length == 0) {
            System.out.println("No .txt files found in the folder.");
            return;
        }
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new SearchTask(files, searchWord, 0, files.length));
    }
}

