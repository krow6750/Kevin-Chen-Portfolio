public class DebitCardPayment extends Payment {
    public DebitCardPayment(String name, String cardNumber, String expirationDate, String cvv) {
        super(name);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
    
    @Override
    public boolean processPayment(double amount) throws PaymentException {
        String cardNumberPattern = "^\\d{16}$";
        String expirationDatePattern = "^(0[1-9]|1[0-2])/\\d{2}$";
        String cvvPattern = "^\\d{3}$";
        
        if (!cardNumber.matches(cardNumberPattern)) {
            throw new PaymentException("Invalid debit card number");
        }
        
        if (!expirationDate.matches(expirationDatePattern)) {
            throw new PaymentException("Invalid expiration date");
        }
        
        if (!cvv.matches(cvvPattern)) {
            throw new PaymentException("Invalid CVV code");
        }
        
        System.out.println("Debit card payment processed successfully");
        return true;
    }
}
