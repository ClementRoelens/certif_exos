public class ModernHouseBuilder implements HouseBuilder{
    private House house = new House();

    @Override
    public void setFloorNumber() {
        house.setFloorNumber(3);
    }

    @Override
    public void setRoomNumber() {
        house.setRoomNumber(8);
    }

    @Override
    public void setMatter() {
        house.setMatter("PVC");
    }

    @Override
    public House build() {
        return house;
    }
}
