package commandSpace.commands;

import exceptions.ExitProgram;

public class Exit extends Command{
    public Exit(){
        super("exit", "завершить программу (без соханения в файл");
    }
    @Override
    public void execute(String args) throws ExitProgram{
        throw new ExitProgram();
    }
}
