package com.adel.explorer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestFileGenerator {
    private static final int NO_OF_FILES = 2000;
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
        int fileWithWord = new Random().nextInt(NO_OF_FILES) + 1;
        int lineWithWord = new Random().nextInt(50) + 1;

        for (int i = 1; i <= NO_OF_FILES; i++) {
            File file = new File(dir, "test" + i + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int j = 1; j <= 50; j++) {
                    if (i == fileWithWord && j == lineWithWord) {
                        writer.write("This is line " + j + " with the word " + searchWord + ".\n");
                    } else {
                        writer.write("This is a regular line number " + j + ".\n");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error writing to file: " + file.getName() + ", " + e.getMessage());
            }
        }

        System.out.println(NO_OF_FILES + " test files generated in directory: " + dir.getAbsolutePath());
        System.out.println("The word '" + searchWord + "' is in file: test"
                + fileWithWord + ".txt, line: " + lineWithWord);
    }
}
