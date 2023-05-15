package com.cepsearch.managers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cepsearch.conections.CepApi;
import com.cepsearch.models.AddressRecord;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddressManager {
    private final CepApi cepApi;
    private final List<AddressRecord> addresses;
    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setPrettyPrinting()
            .setLenient()
            .create();

    public AddressManager(CepApi cepApi) {
        this.cepApi = cepApi;
        this.addresses = new ArrayList<>();
    }

    public void addAddress(String cep) throws Exception {
        try {
            AddressRecord address = this.cepApi.getAddress(cep);
            this.addresses.add(address);
        } catch (Exception e) {
            System.out.println("Error while adding the address!" + e.getMessage());
        }
    }

    public void createJsonFile() {
        try (FileWriter write = new FileWriter("cep.json")) {
            write.write(gson.toJson(this.addresses));
            write.close();

        } catch (IOException e) {
            System.out.println("Error while creating the file!" + e.getMessage());
        }

    }
}