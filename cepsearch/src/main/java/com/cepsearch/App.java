package com.cepsearch;

import java.util.Scanner;

import com.cepsearch.conections.CepApi;
import com.cepsearch.managers.AddressManager;
import com.cepsearch.models.Menu;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        CepApi cep = new CepApi("https://viacep.com.br/ws/");
        AddressManager adressManager = new AddressManager(cep);
        String userChoice = "";
        String search = "";
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting()
                .setLenient()
                .create();

        prompt: while (true) {
            if (userChoice.equals("")) {
                System.out.println(menu);
                userChoice = scanner.nextLine();
            }

            if (userChoice.equals("1")) {
                System.out.println("\nType the CEP: ");
                search = scanner.nextLine();

                adressManager.addAddress(search);
            }

            else if (userChoice.equals("2")) {
                break;
            } else {
                System.out.println("\nInvalid option!");
                System.out.println("Please, press enter to continue!");
                scanner.nextLine();
                continue;
            }

            while (true) {
                System.out.println("\n Do you want to search another CEP? (y/n)");
                userChoice = scanner.nextLine().toLowerCase();
                if (userChoice.equals("y")) {
                    userChoice = "1";
                    continue prompt;
                } else if (userChoice.equals("n")) {
                    break prompt;
                } else {
                    System.out.println("\nInvalid option!");
                    System.out.println("Please, press enter to continue!");
                    scanner.nextLine();
                    continue;
                }
            }

        }
        adressManager.createJsonFile();
        System.out.println("Thank you for using CEP Search!");
    }
}
