
import java.util.HashMap;

public class Accounts {
    private HashMap<String, String> accountCredentials = new HashMap<>();
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private double allowance;
    private double expenditures;
    private double savings;

    // constructor
    public Accounts(HashMap<String, String> accountCredentials, String firstName, String lastName, String address,
            String phoneNumber, double allowance, double expenditures, double savings) {
        this.accountCredentials = accountCredentials;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.allowance = allowance;
        this.expenditures = expenditures;
        this.savings = savings;
    }

    // getters
    protected HashMap<String, String> getAccountCredentials() {
        return accountCredentials;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAllowance() {
        return allowance;
    }

    public double getSavings() {
        return savings;
    }

    public double getExpenditures() {
        return expenditures;
    }
}