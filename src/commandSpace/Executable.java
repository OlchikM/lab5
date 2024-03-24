package commandSpace;

import exceptions.ExitProgram;
import exceptions.InvalidArguments;

public interface Executable {
    void execute(String args) throws InvalidArguments, ExitProgram;
}
