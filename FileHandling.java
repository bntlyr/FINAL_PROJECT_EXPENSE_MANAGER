import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FileHandling {
    public void createFile(String fileName) {
        try {
            File ACCOUNTS = new File(fileName);
            if (ACCOUNTS.createNewFile()) {
                System.out.println("File Created: " + ACCOUNTS.getName());
            } else {
                System.out.println("File already Created");
            }
        } catch (IOException e) {
            System.out.println("\nERROR OCCURED...");
        }
    }

    public void writeFile(String fileName, String userName, String password, String lastName, String firstName,
            String address,
            String phoneNumber, double allowance, double expenditures, double savings) {
        try {
            // Open the file in append mode
            FileWriter ACCOUNTS = new FileWriter(fileName, true);
            HashMap<String, String> accountCredentials = new HashMap<>();
            accountCredentials.put(userName, password);
            ACCOUNTS.append(accountCredentials + " " + lastName + " "
                    + firstName + " " + address + " " + phoneNumber + " " + allowance + " " + expenditures + " "
                    + savings + "\n");
            ACCOUNTS.close();
        } catch (IOException e) {
            System.out.println("\nERROR OCCURRED...");
        }
    }

    public void readFile(String fileName, ArrayList<Accounts> accountsList) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.split(" ");

                // Extract username and password from the first part
                String[] credentials = parts[0].substring(1, parts[0].length() - 1).split("=");

                // Create a HashMap for credentials
                HashMap<String, String> credentialsMap = new HashMap<>();
                credentialsMap.put(credentials[0], credentials[1]);

                // Use a List to store the remaining parts
                List<String> accountInfo = Arrays.asList(parts).subList(1, parts.length);

                // Parse the last three indices as doubles
                double allowance = Double.parseDouble(accountInfo.get(4)); // Assuming parts[5] is allowance
                double expenditures = Double.parseDouble(accountInfo.get(5)); // Assuming parts[6] is expenditures
                double savings = Double.parseDouble(accountInfo.get(6)); // Assuming parts[7] is savings

                // Create an Accounts object and add it to the list
                Accounts account = new Accounts(
                        credentialsMap, // userName=password
                        accountInfo.get(0), // parts[1] is the lastname
                        accountInfo.get(1), // parts[2] is the firstname
                        accountInfo.get(2), // parts[3] is the address
                        accountInfo.get(3), // parts[4] is the phone number
                        allowance,
                        expenditures,
                        savings);
                accountsList.add(account);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void updateFile(ArrayList<Accounts> accountsList) {
        try {
            File ACCOUNTS = new File("accounts.txt");
            File TEMPACCOUNTS = new File("accounts1.txt");
            FileWriter updater = new FileWriter(TEMPACCOUNTS, true);
            Iterator<Accounts> it = accountsList.iterator();
            while (it.hasNext()) {
                updater.append(accountsList.toString());
            }
            ACCOUNTS.delete();
            TEMPACCOUNTS.renameTo(ACCOUNTS);
            updater.close();
        } catch (IOException e) {
            System.out.println("\nERROR OCCURRED...");
        }
    }
}