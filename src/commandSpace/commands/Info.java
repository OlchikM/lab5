package commandSpace.commands;

import commandSpace.Console;
import exceptions.InvalidArguments;
import managers.CollectionManager;

public class Info extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public Info(CollectionManager collectionManager, Console console){
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.collectionManager = collectionManager;
        this.console = console;
    }
    @Override
    public void execute(String args) throws InvalidArguments{
        if (!args.isBlank()) throw new InvalidArguments();
        String lastInitTime = (collectionManager.getLastInitTime() == null) ? "коллекция в сессии не инициализирована"
                : collectionManager.getLastInitTime();
        String lastSaveTime = (collectionManager.getLastSaveTime() == null) ? "-" :
                collectionManager.getLastSaveTime();
        StringBuilder resultInfo  = new StringBuilder();
        resultInfo.append("*** Сведения о коллекции ***" + "\n").
                append("Тип " + collectionManager.getCollectionType() + "\n")
                .append("Количество элементов: " + collectionManager.getSize() + "\n")
                .append("Дата последней инициализации " + lastInitTime + "\n")
                .append("Дата последнего изменения: " + lastSaveTime + "\n");
        console.println(resultInfo.toString());
    }
}
