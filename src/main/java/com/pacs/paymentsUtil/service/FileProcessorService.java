package com.pacs.paymentsUtil.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessorService {
    public String placeFileInLocation(String path, String value, String fileName) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder, fileName);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(value);
            System.out.println("String successfully written to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
        return "File placed successfully";
    }
}
