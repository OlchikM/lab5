package commandSpace.commands;

import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.FileModeException;
import exceptions.InvalidArguments;
import exceptions.InvalidForm;
import managers.CollectionManager;
import models.Vehicle;
import models.forms.VehicleForm;

import java.util.Objects;

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
            console.println("Значение текущего максимального элемента: " + collectionManager.getMaxPrice());
            console.println("Начало создания экземпляра Vehicle...");
            Vehicle vehicle = new VehicleForm(console).build();
            console.println("Экземпляр Vehicle успешно создан!");
            if (vehicle.compareTo(collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .max(Vehicle::compareTo)
                    .orElse(null)) >= 1){
                collectionManager.addElement(vehicle);
                console.println("Объект успешно добавлен");
            } else {
                console.println("Новый элемент меньше максимального");
            }
        } catch (FileModeException | InvalidForm e) { // | InvalidForm e) {
            console.printError("Поля невалидны => экземпляр не создан");
        }

    }
}
