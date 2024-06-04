import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class User_account {
    protected Map<String, Map<String, String>> Userdata = new LinkedHashMap<>();

    protected User_account() {
        Map<String, String> User2136010031 = new LinkedHashMap<>();
        User2136010031.put("Name", "Jeevitha A");
        User2136010031.put("AccountNo", "2136010031");
        User2136010031.put("Balance", "25000");
        User2136010031.put("Password", "jeevi0031");

        Map<String, String> User2136010032 = new LinkedHashMap<>();
        User2136010032.put("Name", "Swetha J");
        User2136010032.put("AccountNo", "2136010032");
        User2136010032.put("Balance", "20000");
        User2136010032.put("Password", "swe0032");

        Userdata.put("2136010031", User2136010031);
        Userdata.put("2136010032", User2136010032);
    }

    public boolean verify_account(String accno, String pw) {
        Map<String, String> User = Userdata.get(accno);
        return User != null && User.get("Password").equals(pw);
    }
}

public class ATM_System extends User_account {
    static User_account data = new User_account();
    static String accno;
    static String pw;
    static Scanner scanner = new Scanner(System.in);

    protected static void check_balance() {
        Map<String, String> User = data.Userdata.get(accno);
        if (User != null) {
            System.out.println("Your Balance: Rs." + User.get("Balance"));
        }
    }

    protected static void withdraw() {
        Map<String, String> User = data.Userdata.get(accno);
        if (User != null) {
            int balance = Integer.parseInt(User.get("Balance"));
            System.out.print("Enter Your Amount: ");
            int amount = scanner.nextInt();
            if (amount > balance) {
                System.out.println("Insufficient Balance...");
                return;
            }
            balance -= amount;
            User.put("Balance", String.valueOf(balance));
            System.out.println("\nSuccessfully Withdrawn\nYour Balance: Rs." + balance);
        }
    }

    protected static void deposit() {
        Map<String, String> User = data.Userdata.get(accno);
        if (User != null) {
            int balance = Integer.parseInt(User.get("Balance"));
            System.out.print("Enter Your Amount: ");
            int amount = scanner.nextInt();
            if (amount < 0) {
                System.out.println("Invalid amount...");
                return;
            }
            balance += amount;
            User.put("Balance", String.valueOf(balance));
            System.out.println("\nSuccessfully Deposited\nYour Balance: Rs." + balance);
        }
    }

    protected static void perform_operations() {
        boolean run = true;
        while (run) {
            System.out.println("\nSelect Option:\n 1. Check balance\n 2. Withdraw\n 3. Deposit\n 4. Exit");
            int opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    check_balance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    run = false;
                    System.out.println("Thank you for using GV ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Enter correct option");
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Welcome to GV ATM\nEnter Your Account No: ");
        accno = scanner.next();
        System.out.print("Enter Password: ");
        pw = scanner.next();
        // Verify account
        if (data.verify_account(accno, pw)) {
            perform_operations();
        } else {
            System.out.println("Account No or Password are Incorrect");
        }
    }
}
       
           
       
