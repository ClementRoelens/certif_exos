public class Main {
    public static void main(String[] args) {
        TradHouseBuilder tradHouseBuilder = new TradHouseBuilder();
        tradHouseBuilder.setFloorNumber();
        tradHouseBuilder.setRoomNumber();
        tradHouseBuilder.setMatter();
        House tradHouse = tradHouseBuilder.build();

        System.out.println(tradHouse);

        ModernHouseBuilder modernHouseBuilder = new ModernHouseBuilder();
        modernHouseBuilder.setFloorNumber();
        modernHouseBuilder.setRoomNumber();
        modernHouseBuilder.setMatter();
        House modernHouse = modernHouseBuilder.build();
        System.out.println(modernHouse);
    }
}
