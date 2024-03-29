package exceptions;

import java.io.IOException;

public class NoSuchCommand extends IOException {
    public NoSuchCommand(String s) {
        super(s);
    }
}
