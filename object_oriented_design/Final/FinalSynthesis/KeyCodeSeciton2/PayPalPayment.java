public class PayPalPayment extends Payment {
    public PayPalPayment(String name, String email) {
        super(name);
        this.email = email;
    }
    
    @Override
    public boolean processPayment(double amount) throws PaymentException {
        String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        
        if (!email.matches(emailPattern)) {
            throw new PaymentException("Invalid PayPal email address");
        }
        
        System.out.println("PayPal payment processed successfully");
        return true;
    }
}
