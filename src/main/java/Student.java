public record Student(String name, int age) {
    @Override
    public String toString() {
        return name + ", " + age + " ans";
    }
}
