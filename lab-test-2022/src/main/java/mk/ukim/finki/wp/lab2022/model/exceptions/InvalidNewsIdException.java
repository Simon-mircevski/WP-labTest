package mk.ukim.finki.wp.lab2022.model.exceptions;

public class InvalidNewsIdException extends RuntimeException {
    public InvalidNewsIdException(){
        super("Invalid news ID");
    }
}
