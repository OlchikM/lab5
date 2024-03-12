package commandSpace.commands;

import commandSpace.Console;
import managers.CommandManager;

public class Help extends Command {
    private final CommandManager commandManager;
    private final Console console;
    public Help(Console console, CommandManager commandManager){
        super("help", "вывести справку по доступным коммандам");
        this.console = console;
        this.commandManager = commandManager;
    }
    public void execute(String args){
        commandManager.getCommandsHashMap().forEach(command -> console.println(command.toString()));
    }
}
