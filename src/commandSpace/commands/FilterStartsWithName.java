package commandSpace.commands;

import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import managers.CollectionManager;

import java.util.Objects;

public class FilterStartsWithName extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    public FilterStartsWithName(Console console, CollectionManager collectionManager){
        super("filter_starts_with_name", "name", "вывести  элементы значение поля name которых начинается с заданной подстроки");
    this.console = console;
    this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram {
        if (args.isBlank()) throw new InvalidArguments();
        String name = args;
        console.println("Элементы значение поля name которых начинается с " + name + ":");
        collectionManager.getCollection().stream()
                .filter(Objects :: nonNull)
                .filter(i -> i.getName().startsWith(name))
                .forEach(i -> console.println(i.getName()));
        }
}
