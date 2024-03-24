package models.forms;

import commandSpace.*;
import exceptions.FileModeException;
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
    public VehicleType build(){
        while (true){
            console.println("Возможные варианты транспортрных средств:");
            console.println(VehicleType.list());
            console.println("Введите транспортное средство");
            String inputLine = scanner.nextLine().trim();
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
