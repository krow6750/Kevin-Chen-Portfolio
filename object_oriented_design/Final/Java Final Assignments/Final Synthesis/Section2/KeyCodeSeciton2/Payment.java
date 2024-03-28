public abstract class Payment implements PaymentMethod {
    protected String name;
    protected String cardNumber;
    protected String expirationDate;
    protected String cvv;
    protected String email;
    
    public Payment(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public abstract boolean processPayment(double amount) throws PaymentException;
}
