public class CurrencyConverterAdapter implements CurrencyAdapter{

    private final CurrencyConverter currencyConverter = new CurrencyConverter();

    @Override
    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (fromCurrency.equals("USD")){
            return currencyConverter.convertFromUSD(toCurrency, amount);
        } else if (toCurrency.equals("USD")) {
            return currencyConverter.convertToUSD(fromCurrency, amount);
        } else if (fromCurrency.equals("EUR")) {
            return currencyConverter.convertFromUSD("GBP",
                    currencyConverter.convertToUSD("EUR",amount)
            );
        } else {
            return currencyConverter.convertFromUSD("EUR",
                    currencyConverter.convertToUSD("GBP",amount)
            );
        }
    }
}
