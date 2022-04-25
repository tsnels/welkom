package be.vdab.welkom.exceptions;

public class RepositoryException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public RepositoryException(Exception cause) {
        super(cause);
    }
}
