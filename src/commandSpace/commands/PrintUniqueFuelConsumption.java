package commandSpace.commands;

import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import managers.CollectionManager;
import models.Vehicle;

import java.util.Objects;

public class PrintUniqueFuelConsumption extends Command {
    private final Console console;
    private final CollectionManager collectionManager;
    public PrintUniqueFuelConsumption(Console console, CollectionManager collectionManager){
        super("print_unique_fuelConsumption", "вывести уникальные значения поля fuelConsumption всех элементов коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram{
        if (!args.isBlank()) throw new InvalidArguments();
        console.println("Уникальные значения поля fuelConsumption элементов коллекции:");
        collectionManager.getCollection().stream()
                .filter(Objects::nonNull)
                .map(Vehicle::getFuelConsumption)
                .distinct()
                .forEach(x -> console.println(String.valueOf(x)));
    }

}
