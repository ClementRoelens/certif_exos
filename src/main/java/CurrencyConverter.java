public class CurrencyConverter {
    public double convertToUSD(String currency, double amount) {
        return (currency.equals("EUR")) ? amount*1.05 : amount*0.83;
    }

    public double convertFromUSD(String currency, double amount) {
        return (currency.equals("EUR")) ? amount/1.05 : amount/0.83;
    }
}
