package gianmarte.u5w3d5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("L'id" + id + "non e' stato trovato");
    }
}
