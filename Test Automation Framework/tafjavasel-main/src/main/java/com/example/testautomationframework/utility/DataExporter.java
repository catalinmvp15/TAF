package com.example.testautomationframework.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class DataExporter {

    private DataExporter() {
        // Private constructor to hide the implicit public one
    }

    // Save data to a CSV file
    public static void saveToCSV(List<Map<String, String>> data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            // Write header
            writer.append("Product Name,Price\n");
            for (Map<String, String> entry : data) {
                writer.append(entry.get("name")).append(",").append(entry.get("price")).append("\n");
            }
            System.out.println("✅ Data saved to CSV: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save data to a JSON file
    public static void saveToJSON(List<Map<String, String>> data, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(filename), data);
            System.out.println("✅ Data saved to JSON: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
