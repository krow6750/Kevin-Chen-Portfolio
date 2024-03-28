public interface PaymentMethod {
    boolean processPayment(double amount) throws PaymentException;
    String getName();
}
