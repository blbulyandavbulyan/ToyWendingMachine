package org.blbulyandavbulyan;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String toyFileName = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals("--toy-file")) {
                    if (i + 1 < args.length)
                        toyFileName = args[i + 1];
                }
            }
            if (toyFileName == null) {
                System.err.println("Вы не задали входной CSV файл с игрушками!");
                System.exit(-1);
            }
            var toyVendingMachine = new ToyVendingMachine();
            CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(toyFileName)));
            for (String[] line : csvReader) {
                if (line.length != 3)
                    throw new RuntimeException("Количество столбцов в csv файле должно быть 3!");
                String title = line[0];
                long amount = Long.parseLong(line[1]);
                double dropFrequency = Double.parseDouble(line[2]);
                toyVendingMachine.addToy(title, amount, dropFrequency);
            }
            Scanner scanner = new Scanner(System.in);
            while (toyVendingMachine.hasToy()){
                System.out.print("Хотите игрушку ?(yes/no): ");
                if(scanner.nextLine().equalsIgnoreCase("yes"))
                    System.out.println(toyVendingMachine.getRandomToy());
                else break;
            }
            if(!toyVendingMachine.hasToy())
                System.out.println("Игрушки закончились :(");
        } catch (FileNotFoundException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}