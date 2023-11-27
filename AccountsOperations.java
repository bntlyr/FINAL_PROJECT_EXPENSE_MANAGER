import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class AccountsOperations {
    // this is for adding, removing, updating accounts
    ArrayList<Accounts> accountsList = new ArrayList<>();

    public void addAccounts(String userName, String password, String lastName, String firstName,
            String address,
            String phoneNumber, double allowance, double expenditures, double savings) {
        HashMap<String, String> accountCredentials = new HashMap<>();
        accountCredentials.put(userName, password);
        accountsList.add(new Accounts(accountCredentials, lastName, firstName, address, phoneNumber, allowance,
                expenditures, savings));

        // store in textFile "accounts.txt"
        FileHandling storeAccounts = new FileHandling();
        storeAccounts.writeFile("accounts.txt", userName, password, lastName, firstName, address, phoneNumber,
                allowance, expenditures, savings);
    }

    public Accounts validateAccounts(String userName, String password) {
        FileHandling readAccounts = new FileHandling();
        readAccounts.readFile("accounts.txt", accountsList);
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put(userName, password);
        Iterator<Accounts> it = accountsList.iterator();
        while (it.hasNext()) {
            Accounts account = it.next();
            if (account.getAccountCredentials().equals(credentials)) {
                System.out.println("Account Successfully Logged-in.");
                return account; // Return the logged-in user
            }
        }
        System.out.println("Account does not exist.");
        return null; // Return null if login fails
    }

    public ArrayList<Accounts> getAccounts() {
        return accountsList;
    }
}