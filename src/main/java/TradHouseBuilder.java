public class TradHouseBuilder implements HouseBuilder{
    private House house =  new House();

    @Override
    public void setFloorNumber() {
        house.setFloorNumber(2);
    }

    @Override
    public void setRoomNumber() {
        house.setRoomNumber(4);
    }

    @Override
    public void setMatter() {
        house.setMatter("Bois");
    }

    @Override
    public House build() {
        return house;
    }
}
