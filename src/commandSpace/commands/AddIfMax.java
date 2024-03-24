package commandSpace.commands;

import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import managers.CollectionManager;

public class AddIfMax extends Command{
    public final CollectionManager collectionManager;
    public final Console console;
    public AddIfMax(CollectionManager collectionManager, Console console){
        super("add_if_max", "{element}", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram {
        if (!args.isBlank()) throw new InvalidArguments();
        try {
            console.println("Значение элемента вычисляется, как сумма вместимости, мощности двигателя, потребления топлива");

        }

    }
}
