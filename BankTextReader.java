package bank.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class BankTextReader {

    private ArrayList<String> bankCustomers;

    /**
     * @param fileName the name of the file containing the information of the
     *                 customers
     * @return A List of Strings where each string is the info of a customer
     */
    public ArrayList<String> bankReader(String fileName) {
        try {
            Scanner sc = new Scanner(new FileReader(fileName));
            ArrayList<String> list = new ArrayList<>();

            while (sc.hasNext()) {
                list.add(sc.next());
            }

            sc.close();
            bankCustomers = list;

        } catch (Exception exc) {
            System.err.print(exc);
        }
        return bankCustomers;
    }

    // Funcion que recibe el nombre de un archivo, lo pasa a bankReader para
    // convertir el ArrayList a un HashTable
    public Hashtable<String, String> bankHashReader(String filename) {
        ArrayList<String> strCustomerLst = bankReader(filename);
        Hashtable<String, String> table = new Hashtable<>();
        String[] fields;
        String key;

        for (String customerInfo : strCustomerLst) {
            fields = customerInfo.split(",", 4);

            // Key es el FirstName + LastName
            key = fields[1] + fields[2];
            table.put(key, customerInfo);
        }
        return table;
    }
}
