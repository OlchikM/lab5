package commandSpace.commands;

import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import managers.CommandManager;

import java.util.List;

public class History extends Command{
    private final CommandManager commandManager;
    private final Console console;
    public History(CommandManager commandManager, Console console){
        super("history", "вывести последние 9 команд (без их аргументов)");
        this.commandManager = commandManager;
        this.console = console;
    }
    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram {
        if (!args.isBlank()) throw new InvalidArguments();
        List<String> history = commandManager.getCommandHistory();
        if (history.isEmpty()){
            console.println("История команд пуста");
            return; // завершение метода
        }
        for (String command: history.subList(Math.max(history.size() - 9, 0), history.size())){
            console.println(command);
        }
    }
}
