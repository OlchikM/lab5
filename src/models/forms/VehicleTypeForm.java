package models.forms;

import commandSpace.*;
import exceptions.FileModeException;
import exceptions.InvalidForm;
import models.VehicleType;

import java.util.Locale;

public class VehicleTypeForm extends Form<VehicleType>{
    private final Printable console;
    private final Inputable scanner;


    public VehicleTypeForm(Printable console) {
        if (Console.getFileMode()) {
            this.console = new BlankConsole();
            this.scanner = new ExecuteFileSpace();
        } else {
            this.console = console;
            this.scanner = new ConsoleInput();
        }
    }
    @Override
    public VehicleType build() throws InvalidForm {
        while (true){
            console.println("Возможные варианты транспортрных средств:");
            console.println(VehicleType.list());
            String inputLine = "";
            console.println("Введите транспортное средство");
            try {
                inputLine = scanner.nextLine().trim();
            } catch (NullPointerException e){
                throw new InvalidForm();
            }
            if (inputLine.isEmpty()) {
                console.printError("Транспортное средство не может быть null");
            } else {
                try {
                    return VehicleType.valueOf(inputLine.toUpperCase(Locale.ROOT));
                } catch (IllegalArgumentException e) {
                    console.printError("Такого транспортного средства нет в списке");
                    if (Console.getFileMode()) throw new FileModeException();
                }
            }

        }
    }
}
