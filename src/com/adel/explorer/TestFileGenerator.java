package com.adel.explorer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestFileGenerator {
    private static final int NO_OF_FILES = 2000;
    private static final int LINES_PER_FILE = 50;
    private static final double FILE_PROBABILITY = 0.002;

    public static void main(String[] args) {
        String dirPath = args.length > 0 ? args[0] : "test-files";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                System.out.println("Failed to create directory: " + dir.getAbsolutePath());
                return;
            }
        }

        String searchWord = "mentorship";
        Random random = new Random();
        int totalFilesWithWord = 0;

        for (int i = 1; i <= NO_OF_FILES; i++) {
            File file = new File(dir, "test" + i + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                boolean insertWord = random.nextDouble() < FILE_PROBABILITY; // decide if this file will contain the word
                int lineWithWord = insertWord ? random.nextInt(LINES_PER_FILE) + 1 : -1;

                if (insertWord) {
                    totalFilesWithWord++;
                    System.out.println("Word '" + searchWord + "' inserted in file: "
                            + file.getName() + " at line " + lineWithWord);
                }

                for (int j = 1; j <= LINES_PER_FILE; j++) {
                    if (j == lineWithWord) {
                        writer.write("This is line " + j + " with the word " + searchWord + ".\n");
                    } else {
                        writer.write("This is a regular line number " + j + ".\n");
                    }
                }

            } catch (IOException e) {
                System.out.println("Error writing to file: " + file.getName() + ", " + e.getMessage());
            }
        }

        System.out.println("\n" + NO_OF_FILES + " test files generated in directory: " + dir.getAbsolutePath());
        System.out.println("The word '" + searchWord + "' appeared in " + totalFilesWithWord + " files.");
    }
}
