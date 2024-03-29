package commandSpace.commands;
import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import managers.CollectionManager;

import java.util.Objects;
import java.util.stream.Collectors;

public class FilterGreaterThanFuelConsumption extends Command{
    private final Console console;
    private final CollectionManager collectionManager;
    public FilterGreaterThanFuelConsumption(Console console, CollectionManager collectionManager){
        super("filter_greater_than_fuel_consumption", "fuelConsumption",
                "вывести элементы значение поля fuelConsumption (float) которых больше заданного");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram{
        if (args.isBlank()) throw new InvalidArguments();
        try {
            float valueMark = Float.parseFloat(args.trim());
            console.println("Элементы со значением поля fuelConsumption, большим " + valueMark + ":");
            console.println(collectionManager.getCollection().stream()
                    .filter(Objects :: nonNull)
                    .filter(i -> i.getFuelConsumption() > valueMark)
                    .map(Object::toString)
                    .collect(Collectors.joining("\n")));

        } catch(NumberFormatException e){
            console.printError("Аргумент должен быть числом типа float");
        }
    }

}
