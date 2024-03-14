package models.Forms;

import commandSpace.BlankConsole;
import commandSpace.Console;
import commandSpace.Inputable;
import commandSpace.Printable;

public class CoordinatesForm extends Form{
    private final Printable console;
    private final Inputable scanner;
    public CoordinatesForm(Printable console){
        if (Console.getFileMode()){
            this.console = new BlankConsole();
            this.scanner = new ExecuteFile
        }
    }
}
