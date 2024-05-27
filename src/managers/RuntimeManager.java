package managers;

import commandSpace.Printable;
import exceptions.CommandRuntimeError;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import exceptions.NoSuchCommand;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class RuntimeManager {
    private final Printable console;
    private final CommandManager commandManager;

    public RuntimeManager(Printable console, CommandManager commandManager) {
        this.commandManager = commandManager;
        this.console = console;
    }

    public void launch(String[] receivedCommand) throws ExitProgram, InvalidArguments, NoSuchCommand, CommandRuntimeError {
        if (receivedCommand[0].isEmpty()) return;
        commandManager.execute(receivedCommand[0], receivedCommand[1]);
    }

    public void runInteractive() {
        Scanner userScanner = ScannerManager.getUserScanner();
        while (true) {
            try {
                if (!userScanner.hasNext()) throw new ExitProgram();
                String userCommand = userScanner.nextLine().trim() + " ";
                this.launch(userCommand.split(" ", 2));
                commandManager.addToHistory(userCommand);
            } catch (NoSuchElementException e) {
                console.printError("So sad");
            } catch (InvalidArguments e) {
                console.printError("Введены неверные аргументы для команды");
            } catch (NoSuchCommand e) {
                console.printError("Такой команды нет в списке (введите help для получения списка доступных команд)");
            } catch (CommandRuntimeError e) {
                console.printError("Ошибка при исполнении команды");
            } catch (ExitProgram e) {
                console.println("До свидания!");
                return;
            }
        }
    }
    
}