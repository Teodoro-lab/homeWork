package bank.controller;

import java.util.ArrayList;
import bank.model.BankTextReader;
import java.util.Hashtable;

public class BankTextController {

    /**
     * @param Filename the name of the file containing the information of the
     *                 customers
     * @return A table/matrix of the data recovered from the file
     */
    public String[][] getTable(String Filename) {

        String accounts;
        String[] fields;

        BankTextReader bankBuilder = new BankTextReader();
        ArrayList<String> strCustomerLst = bankBuilder.bankReader(Filename);

        String[][] tableOfCustomers = new String[strCustomerLst.size()][4];

        for (int i = 0; i < strCustomerLst.size(); i++) {
            customerInfo = strCustomerLst.get(i);
            fields = customerInfo.split(",");

            accounts = fields[3];

            // reads all the IDAccount and store it in a single element of the table
            int j;
            for (j = 6; j < fields.length; j++) {
                accounts = accounts + ", " + fields[j];
                j = j + 2;
            }

            // load the data in the table
            tableOfCustomers[i][3] = accounts;
            for (j = 0; j < 3; j++) {
                tableOfCustomers[i][j] = fields[j];
            }
        }
        return tableOfCustomers;
    }

    public String[] getHeader() {
        String[] header = {
                "ID",
                "First Name",
                "Last Name",
                "Accounts"
        };
        return header;
    }

    /*
     * Recibe un hashTable y busca una key, si existe, regresa una tabla con
     * la informacion del resultado de la busqueda. Si no existe, regresa
     * un apuntador null
     */
    public String[][] getCustomerTableFromHash(String key, Hashtable<String, String> customerHash) {
        String[][] customerTable = { { " ", " ", " ", " " } };

        if (customerHash.containsKey(key)) {
            String customerInfo;
            String accounts;
            String[] fields;

            // Recibe la información del cliente del .txt correspondiente
            customerInfo = customerHash.get(key);
            // y lo divide en los elementos necesarios para mostrarlo en una tabla
            fields = customerInfo.split(",");
            // colocandolos en la matriz customerTable
            int j;
            for (j = 0; j < 3; j++) {
                customerTable[0][j] = fields[j];
            }

            // acomoda todos los IDAccount en un solo elemento de la matriz
            accounts = fields[3];
            for (j = 6; j < fields.length; j++) {
                accounts = accounts + ", " + fields[j];
                j = j + 2;
            }
            customerTable[0][3] = accounts;

        } else {
            customerTable = null;
        }
        return customerTable;
    }
}
