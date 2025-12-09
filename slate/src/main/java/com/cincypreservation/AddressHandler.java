package com.cincypreservation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to populate the searchable address list from a json file. Uses GSON for easy json parsing. Maintains the addresses.json file with a
 * list of all addresses added to the application.
 */
public class AddressHandler {
    private static final String FILE = "addresses.json";
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