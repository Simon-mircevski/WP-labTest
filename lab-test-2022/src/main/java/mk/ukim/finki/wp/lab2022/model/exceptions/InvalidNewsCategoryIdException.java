package mk.ukim.finki.wp.lab2022.model.exceptions;

public class InvalidNewsCategoryIdException extends RuntimeException {
    public InvalidNewsCategoryIdException(){
        super("Invalid news category");
    }
}
