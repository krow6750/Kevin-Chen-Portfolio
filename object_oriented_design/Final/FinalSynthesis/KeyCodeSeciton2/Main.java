import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter payment method (credit/debit/PayPal): ");
        String paymentMethod = scanner.nextLine();
        
        PaymentMethod method;
        
        switch (paymentMethod) {
            case "credit":
                System.out.println("Enter name on credit card: ");
                String creditCardName = scanner.nextLine();
                
                System.out.println("Enter credit card number: ");
                String creditCardNumber = scanner.nextLine();
                
                System.out.println("Enter expiration date (mm/yy): ");
                String creditCardExpirationDate = scanner.nextLine();
                
                System.out.println("Enter CVV code: ");
                String creditCardCVV = scanner.nextLine();
                
                method = new CreditCardPayment(creditCardName, creditCardNumber, creditCardExpirationDate, creditCardCVV);
                break;
                
            case "debit":
                System.out.println("Enter name on debit card: ");
                String debitCardName = scanner.nextLine();
                
                System.out.println("Enter debit card number: ");
                String debitCardNumber = scanner.nextLine();
                
                System.out.println("Enter expiration date (mm/yy): ");
                String debitCardExpirationDate = scanner.nextLine();
                
                System.out.println("Enter CVV code: ");
                String debitCardCVV = scanner.nextLine();
                
                method = new DebitCardPayment(debitCardName, debitCardNumber, debitCardExpirationDate, debitCardCVV);
                break;
                
            case "PayPal":
                System.out.println("Enter name on PayPal account: ");
                String payPalName = scanner.nextLine();
                
                System.out.println("Enter PayPal email address: ");
                String payPalEmail = scanner.nextLine();
                
                method = new PayPalPayment(payPalName, payPalEmail);
                break;
                
            default:
                System.out.println("Invalid payment method");
                return;
        }
        
        System.out.println("Enter payment amount: ");
        double amount = scanner.nextDouble();
        
        PaymentProcessor processor = new PaymentProcessor();
        
        try {
            processor.processPayment(method, amount);
        } catch (PaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        scanner.close();
    }
}
