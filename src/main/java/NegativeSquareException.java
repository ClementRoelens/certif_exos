public class NegativeSquareException extends RuntimeException {
    public NegativeSquareException() {
        super("Un carré ne peut pas être négatif dans l'ensemble des nombres réels");
    }
}
