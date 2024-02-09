package ru.netology.Alyoshka;

import com.google.gson.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public static String readString(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static List<Employee> jsonToList(String json) {
        JSONParser parser = new JSONParser();
        List<Employee> employeeList = new ArrayList<>();
        try {
            Object obj = parser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("employee");
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (int i = 0; i < jsonArray.size(); i++) {
                Employee employee = gson.fromJson(jsonArray.get(i).toString(), Employee.class);
                employeeList.add(employee);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}