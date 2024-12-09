import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Stylo", 4.57, 100),
                new Product("Ecran", 449.99, 0),
                new Product("Pistolet nerf", 17D,30),
                new Product("Dé",0.49,500)
        );
        ProductFilter findAvailableProducts = product -> product.getQuantity() > 0;
        ProductFilter findProductsContainingE = p -> p.getName().toLowerCase().contains("e");
        ProductTransform increasePriceByTen = p -> new Product(p.getName(), p.getPrice() + 10, p.getQuantity());
        ProductTransform decreasePriceByTen = p -> new Product(p.getName(), p.getPrice() - 10, p.getQuantity());
        ProductTransform increaseStockByTen = p -> new Product(p.getName(), p.getPrice(), p.getQuantity() + 10);
        ProductTransform decreaseStockByTen = p -> new Product(p.getName(), p.getPrice(), p.getQuantity() - 10);
        ProductTransform addPrefix = p -> new Product("PROMO_".concat(p.getName()), p.getPrice(), p.getQuantity());
        ProductOperation multipleOperations = p -> {
            Product newProduct;
            newProduct = increaseStockByTen.transform(p);
            newProduct = decreasePriceByTen.transform(newProduct);
            newProduct = addPrefix.transform(newProduct);
            return newProduct;
        };


        System.out.println("Lise de base : ");
        products.forEach(System.out::println);
        System.out.println("Affichage des produits en stock");
        products.forEach(p -> {
            if (findAvailableProducts.filter(p)){
                System.out.println(p);
            }
        });
        System.out.println("Affichage des produits dont le nom contient la lettre E");
        products.forEach(p -> {
            if (findProductsContainingE.filter(p)){
                System.out.println(p);
            }
        });
        Product productExampleOne = products.getFirst();
        System.out.println("Prix de " + productExampleOne.getName() + " avant augmentation : " + productExampleOne.getPrice());
        productExampleOne = increasePriceByTen.transform(productExampleOne);
        System.out.println("Prix de " + productExampleOne.getName() + " après augmentation : " + productExampleOne.getPrice());
        Product productExampleTwo = products.get(2);
        System.out.println("Prix de " + productExampleTwo.getName() + " avant réduction : " + productExampleTwo.getPrice());
        productExampleTwo = decreasePriceByTen.transform(productExampleTwo);
        System.out.println("Prix de " + productExampleTwo.getName() + " après réduction : " + productExampleTwo.getPrice());
        Product productExampleThree = products.get(1);
        System.out.println("Quantité de " + productExampleThree.getName() + " avant restockage : " + productExampleThree.getQuantity());
        productExampleThree = increaseStockByTen.transform(productExampleThree);
        System.out.println("Quantité de " + productExampleThree.getName() + " après restockage : " + productExampleThree.getQuantity());
        Product productExampleFour = products.get(3);
        System.out.println("Quantité de " + productExampleFour.getName() + " avant déstockage : " + productExampleFour.getQuantity());
        productExampleFour = decreaseStockByTen.transform(productExampleFour);
        System.out.println("Quantité de " + productExampleFour.getName() + " après déstockage : " + productExampleFour.getQuantity());
        Product productExampleFive = products.get(1);
        System.out.println("Produit avant multiples modifications : " + productExampleFive);
        productExampleFive = multipleOperations.execute(productExampleFive);
        System.out.println("Produit après multiples modifications : " + productExampleFive);
    }
}
