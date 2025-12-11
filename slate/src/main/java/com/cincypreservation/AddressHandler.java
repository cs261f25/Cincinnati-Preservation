package com.cincypreservation;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddressHandler {
    private static final String FILE = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "addresses.json";
    private static final Gson gson = new Gson();

    //init address list for searching
    public List<String> loadAddresses() throws Exception {
        File file = new File(FILE);

        // If file doesn't exist yet, return empty list
        if (!file.exists()) {
            return new ArrayList<>();
        }

        FileReader reader = new FileReader(file);
        return gson.fromJson(reader, new TypeToken<List<String>>(){}.getType());
    }

    //save address list to file
    public void saveAddresses(List<String> addresses) throws Exception {
        FileWriter writer = new FileWriter(FILE);
        gson.toJson(addresses, writer);
        writer.close();
    }

    //add new address
    public void addAddress(String newAddress) throws Exception {
        List<String> addresses = loadAddresses();
    
        if (!addresses.contains(newAddress)) {
            addresses.add(newAddress);
            saveAddresses(addresses);
        }
    }
}