package commandSpace.commands;

import commandSpace.Console;
import exceptions.InvalidArguments;
import managers.CollectionManager;

public class Clear extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    public Clear(CollectionManager collectionManager, Console console){
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    @Override
    public void execute(String args) throws InvalidArguments{
        if (!args.isBlank()) throw new InvalidArguments();
        collectionManager.clear();
        console.println("Коллекция очищена");
    }
}
