package commandSpace.commands;

import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.FileModeException;
import exceptions.InvalidArguments;
import managers.CollectionManager;
import exceptions.NoSuchId;
import models.Vehicle;
import models.forms.VehicleForm;


public class Update extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public Update(CollectionManager collectionManager, Console console) {
        super("update", "id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram {
        if (args.isBlank()) throw new InvalidArguments();
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExistById(id)) throw new NoSuchId();
            console.println("Начало создания экземпляра Vehicle...");
            collectionManager.editById(id, new VehicleForm(console).build());
            console.println("Экземпляр Vehicle успешно создан!");
        } catch (NoSuchId e) {
            console.printError("Нет элемента с таким id");
        } catch (FileModeException e) { //InvalidForm |
            console.printError("Поля невалидны => экземпляр не создан");
        } catch (NumberFormatException e) {
            console.printError("id должен быть числом типа long");
        }
    }
}
