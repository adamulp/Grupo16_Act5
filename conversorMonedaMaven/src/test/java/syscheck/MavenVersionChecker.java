package syscheck;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MavenVersionChecker {
    public static void main(String[] args) {
        try {
            Process process = new ProcessBuilder("mvn", "-version").start();

            
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.toLowerCase().contains("apache maven")) {
                        System.out.println("Maven Version: " + line);
                        break; 
                    }
                }
            }

            
            process.waitFor();
        } catch (Exception e) {
            System.err.println("Failed to check Maven version: " + e.getMessage());
        }
    }
}
