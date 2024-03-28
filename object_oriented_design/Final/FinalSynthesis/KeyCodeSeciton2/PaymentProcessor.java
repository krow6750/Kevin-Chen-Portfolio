public class PaymentProcessor {
    public boolean processPayment(PaymentMethod paymentMethod, double amount)
            throws PaymentException {
        boolean result = paymentMethod.processPayment(amount);
        if (result) {
            System.out.println("Payment processed successfully using " + paymentMethod.getName());
        }
        return result;
    }
}