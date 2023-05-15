package com.cepsearch.conections;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.cepsearch.models.AddressRecord;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CepApi {
    private final String baseUrl;
    private final Gson gson;

    public CepApi(String baseUrl) {
        this.baseUrl = baseUrl;
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .setLenient()
                .create();
    }

    public AddressRecord getAddress(String cep) throws Exception {
        if (cep.length() != 8) {
            throw new IllegalArgumentException(" Invalid Cep!");
        }

        String url = this.baseUrl + cep + "/json";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(java.net.URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);
        if (body.contains("erro")) {
            throw new Exception("Cep not found!");
        }
        return this.gson.fromJson(body, AddressRecord.class);
    }
}
