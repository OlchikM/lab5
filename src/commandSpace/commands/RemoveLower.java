package commandSpace.commands;

import exceptions.InvalidArguments;
import managers.CollectionManager;
import commandSpace.Console;
import models.Vehicle;

import java.util.EmptyStackException;
import java.util.Objects;

public class RemoveLower extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    public RemoveLower(Console console, CollectionManager collectionManager){
        super("remove_lower", "valueOfElement", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String args) throws InvalidArguments{
        if (args.isBlank()) throw new InvalidArguments();
            float value = Float.parseFloat(args.trim());
            for (Vehicle i : collectionManager.getCollection()) {
                if (i != null) {
                    if (i.getPrice() < value) {
                        collectionManager.removeElement(i);
                    }
                }
            }
            console.println("Элементы успешно удален");
    }

}
