public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
        super("Un âge ne peut être négatif ou nul");
    }
}
