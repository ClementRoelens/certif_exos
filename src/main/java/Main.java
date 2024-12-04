public class Main {
    public static void main(String[] args) {
        CurrencyConverterAdapter converter = new CurrencyConverterAdapter();
        System.out.println("30 EUR = " + converter.convert("EUR","GBP",30) + " GBP");
        System.out.println("30 EUR = " + converter.convert("EUR","USD",30) + " USD");
        System.out.println("30 GBP = " + converter.convert("GBP","USD",30) + " USD");
        System.out.println("30 USD = " + converter.convert("USD","GBP",30) + " GBP");
        System.out.println("30 USD = " + converter.convert("USD","EUR",30) + " EUR");
        System.out.println("30 GBP = " + converter.convert("GBP","EUR",30) + " EUR");

    }
}
