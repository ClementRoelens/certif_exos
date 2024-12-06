public class House {
    private int floorNumber;
    private int roomNumber;
    private String matter;

    public House() {
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    @Override
    public String toString() {
        return "House{" +
                "floorNumber=" + floorNumber +
                ", roomNumber=" + roomNumber +
                ", matter='" + matter + '\'' +
                '}';
    }
}
