package commandSpace.commands;

import commandSpace.Console;
import exceptions.InvalidArguments;
import managers.CollectionManager;

public class Show extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    public Show(CollectionManager collectionManager, Console console){
        super("show", "вывести в стандратный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    @Override
    public void execute(String args) throws InvalidArguments {
        if (!args.isBlank()) throw new InvalidArguments();
        if (collectionManager.getCollection() == null){
            console.printError("Коллекция в сессии не инициализирована");
            return;
        }
        console.println(collectionManager.toString());
    }
}
