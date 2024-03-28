package commandSpace.commands;

import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import managers.CommandManager;
import managers.FileManager;

public class Save extends Command{
    private final FileManager fileManager;
    private final Console console;
    public Save(Console console, FileManager fileManager){
        super("save", "сохранить коллекцию в файл");
        this.console = console;
        this.fileManager = fileManager;
    }
    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram{
        if (!args.isBlank()) throw new InvalidArguments();
        fileManager.saveObjects();
        console.println("Объекты сохранены успешно");
    }
}
