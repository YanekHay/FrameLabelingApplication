package core;

import java.util.Scanner;

public class FileLoader {
    private Scanner scanner;

    public FileLoader() {
        scanner = new Scanner(System.in);
    }

    public void loadFiles() {
        System.out.println("Please enter the file path(s) to upload (separated by commas):");
        String input = scanner.nextLine();
        String[] filePaths = input.split(",");

        for (String filePath : filePaths) {
            // Perform file loading logic here
            System.out.println("Loading file: " + filePath);
        }
    }

    public static void main(String[] args) {
        FileLoader fileLoader = new FileLoader();
        fileLoader.loadFiles();
    }
}