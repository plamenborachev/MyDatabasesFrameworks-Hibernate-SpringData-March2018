package p11OnlineRadioDatabase.exeptions;

public class InvalidSongSecondsException extends InvalidSongLengthException {

    public InvalidSongSecondsException(String message) {
        super(message);
    }
}