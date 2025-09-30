package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlDataReader {
    public List<String> read(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            URL url = new URL(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line); // зберігаємо рядок як є
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }
}
