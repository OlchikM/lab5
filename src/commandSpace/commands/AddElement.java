package commandSpace.commands;

import commandSpace.Console;
import exceptions.FileModeException;
import exceptions.InvalidArguments;
import exceptions.InvalidForm;
import managers.CollectionManager;
import models.forms.VehicleForm;

public class AddElement extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    public AddElement(CollectionManager collectionManager, Console console){
        super("add", "добавить элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String args) throws InvalidArguments {
        if (!args.isBlank()) throw new InvalidArguments();
        try{
            console.println("Начало создания экземпляра Vehicle...");
            collectionManager.addElement(new VehicleForm(console).build());
            console.println("Экземпляр Vehicle успешно создан!");
        } catch (FileModeException e) { //InvalidForm
            console.printError("Поля невалидны => экземпляр не создан");
        }
    }
}
