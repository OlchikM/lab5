package commandSpace.commands;

import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import exceptions.NoSuchId;
import managers.CollectionManager;

public class RemoveById extends Command{
    private final CollectionManager collectionManager;
    private final Console console;
    public RemoveById(CollectionManager collectionManager, Console console){
        super("remove_by_id", "id","удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram{
        if (args.isBlank()) throw new InvalidArguments();
        try{
            long id = Long.parseLong(args.trim());
            if (!collectionManager.checkExistById(id)) throw new NoSuchId();
            collectionManager.removeElement(collectionManager.getById(id));
            console.println("Экземпляр успешно удален!");
        } catch (NoSuchId e){
            console.printError("Нет элемента с таким id");
        } catch (NumberFormatException e){
            console.printError("id должен быть числом типа long");
        }
    }
}
