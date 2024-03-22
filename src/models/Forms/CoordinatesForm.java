package models.Forms;

import commandSpace.*;
import exceptions.FileModeException;
import models.Coordinates;

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
        private long askX(){
            while (true) {
                console.println("Введите координату 'x' (тип long): ");
                String inputLine = scanner.nextLine().trim();
                try{
                   return  Long.parseLong(inputLine);
                } catch (NumberFormatException e){
                    console.printError("'x' должно быть числом типа int");
                    if (Console.getFileMode()) throw  new FileModeException();
            }
        }
    }
    private float askY(){
        while (true) {
            console.println("Введите координату 'y' (тип float): ");
            String inputLine = scanner.nextLine().trim();
            try{
                return  Float.parseFloat(inputLine);
            } catch (NumberFormatException e){
                console.printError("'y' должно быть числом типа float");
                if (Console.getFileMode()) throw  new FileModeException();
            }
        }
    }
    @Override
    public Coordinates build(){
        return new Coordinates(askX(), askX());
    }

}
