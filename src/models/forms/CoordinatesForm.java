package models.forms;

import commandSpace.*;
import exceptions.FileModeException;
import exceptions.InvalidForm;
import models.Coordinates;

import static java.lang.Float.NaN;

public class CoordinatesForm extends Form{
    private final Printable console;
    private final Inputable scanner;
    public CoordinatesForm(Printable console) {
        if (Console.getFileMode()) {
            this.console = new BlankConsole();
            this.scanner = new ExecuteFileSpace();
        } else {
            this.console = console;
            this.scanner = new ConsoleInput();
        }
    }
        private Long askX() throws InvalidForm {
            while (true) {
                console.println("Введите координату 'x' (тип long): ");
                String inputLine = "";
                try {
                    inputLine = scanner.nextLine().trim();
                } catch (NullPointerException e){
                    throw new InvalidForm();
                }
                try{
                   return  Long.parseLong(inputLine);
                } catch (NumberFormatException e){
                    console.printError("'x' должно быть числом типа int");
                    if (Console.getFileMode()) throw  new FileModeException();
            }
        }
    }
    private float askY() throws InvalidForm {
        while (true) {
            console.println("Введите координату 'y' (тип float): ");
            String inputLine = "";
            try {
                inputLine = scanner.nextLine().trim();
            } catch (NullPointerException e){
                throw new InvalidForm();
            }
            try{
                Float inputline = Float.parseFloat(inputLine);
                if (Float.isNaN(inputline) || Float.isInfinite(inputline)){
                    throw new NumberFormatException();
                } else {
                    return  Float.parseFloat(inputLine);
                }
            } catch (NumberFormatException e){
                console.printError("'y' должно быть числом типа float");
                if (Console.getFileMode()) throw  new FileModeException();
            }
        }
    }
    @Override
    public Coordinates build() throws InvalidForm{
        return new Coordinates(askX(), askY());
    }

}
