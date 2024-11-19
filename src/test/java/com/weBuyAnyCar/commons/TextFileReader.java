package com.weBuyAnyCar.commons;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.weBuyAnyCar.commons.PropertyReader;

public class TextFileReader extends PropertyReader {

    public static List<String> retrieveRegNumbers(String filePath, String regex) {
        List<String> regNumbers = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append(" ");
            }

            Matcher matcher = pattern.matcher(content.toString());
            while (matcher.find()) {
                String regNumber = matcher.group().trim().replaceAll("\\s+", "");
                regNumbers.add(matcher.group());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regNumbers;
    }

    public static Map<String, CarDetailsHolder> retrieveCarDetails (String filePath) {
        Map<String, CarDetailsHolder> carDetailsHolderMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String regNo = parts[0].trim();
                String make = parts[1].trim();
                String model = parts[2].trim();
                String year = parts[3].trim();

                carDetailsHolderMap.put(regNo, new CarDetailsHolder(regNo, make, model, year));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carDetailsHolderMap;
    }
}