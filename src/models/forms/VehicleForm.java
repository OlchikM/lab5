package models.forms;

import commandSpace.*;
import exceptions.FileModeException;
import exceptions.InvalidForm;
import models.Coordinates;
import models.Vehicle;
import models.VehicleType;

import java.util.Date;

public class VehicleForm extends Form<Vehicle> {
    private final Printable console;
    private final Inputable scanner;

    public VehicleForm(Printable console) {
        if (Console.getFileMode()) {
            this.console = new BlankConsole();
            this.scanner = new ExecuteFileSpace();
        } else {
            this.console = console; //execute file bug
            this.scanner = new ConsoleInput();
        }
    }

    private String askName() throws InvalidForm {
        while (true) {
            console.println("Введите имя:");
            String name = scanner.nextLine().trim();
            if (name.isEmpty() || name == null) {
                console.printError("Имя не может быть пустым");
                if (Console.getFileMode()) throw new FileModeException();
            } else return name;
        }
    }

    private Coordinates askCoordinates() throws InvalidForm{
        return new CoordinatesForm(console).build();
    }

    private float askEnginePower() throws InvalidForm {
        while (true) {
            console.println("Введите мощность двигателя (тип float > 0):");
            String inputLine = "";
            try {
                inputLine = scanner.nextLine().trim();
            } catch (NullPointerException e){
                throw new InvalidForm();
            }
            try {
                float enginePower = Float.parseFloat(inputLine);
                if (enginePower <= 0) {
                    console.printError("Мощность двигателя не может быть отрицательной или равной 0");
                    continue;
                }
                return enginePower;
            } catch (NumberFormatException e) {
                console.printError("Мощность двигателя должна быть числом типа float");
                if (Console.getFileMode()) throw new FileModeException();
            }
        }
    }

    private Long askCapacity() throws InvalidForm {
        while (true) {
            console.println("Введите вместимость (тип long > 0):");
            String inputLine = "";
            try {
                inputLine = scanner.nextLine().trim();
            } catch (NullPointerException e){
                throw new InvalidForm();
            }
            try {
                long capacity = Long.parseLong(inputLine);
                if (capacity <= 0) {
                    console.printError("Вместимость не может быть отрицательной или равной 0");
                    continue;
                }
                return capacity;
            } catch (NumberFormatException e) {
                console.printError("Вместимость должна быть числом типа long");
                if (Console.getFileMode()) throw new FileModeException();
            }
        }
    }

    private float askFuelConsumption() throws InvalidForm {
        while (true) {
            console.println("Введите потребление топлива (тип float > 0):");
            String inputLine = "";
            try {
               inputLine = scanner.nextLine().trim();
            } catch (NullPointerException e){
                throw new InvalidForm();
            }
            try {
                float fuelConsumption = Float.parseFloat(inputLine);
                if (fuelConsumption <= 0) {
                    console.printError("Потребление топлива не может быть отрицательной или равной 0");
                    continue;
                }
                return fuelConsumption;
            } catch (NumberFormatException e) {
                console.printError("Потребление топлива должно быть числом типа float");
                if (Console.getFileMode()) throw new FileModeException();
            }
        }
    }

    private VehicleType askVehicleType() throws InvalidForm{
        return new VehicleTypeForm(console).build();
    }

    @Override
    public Vehicle build() throws InvalidForm {
        return new Vehicle(
                askName(),
                askCoordinates(),
                new Date(),
                askEnginePower(),
                askCapacity(),
                askFuelConsumption(),
                askVehicleType()

        );

    }
}
