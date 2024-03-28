package commandSpace.commands;

import commandSpace.Console;
import commandSpace.ExecuteFileSpace;
import exceptions.CommandRuntimeError;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;
import exceptions.NoSuchCommand;
import managers.CommandManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Execute extends Command {
    private final Console console;
    private final CommandManager commandManager;

    public Execute(Console console, CommandManager commandManager) {
        super("execute_script", "file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.console = console;
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram {
        if (args == null || args.isEmpty()) {
            console.printError("Отсутствует путь к исполняемому файлу");
            return;
        } else {
            console.println("Путь к исполняемому файлу успешно получен");
        }
        Console.setFileMode(true);
        try {
            ExecuteFileSpace.pushFile(args.trim());
            for (String line = ExecuteFileSpace.readLine(); line != null; line = ExecuteFileSpace.readLine()) {
                try {
                    String[] command = (line.trim() + " ").split(" ", 2);
                    command[1] = command[1].trim();
                    commandManager.addToHistory(command[0]);
                    if (command[0].isBlank()) return;
                    if (command[0].equals("execute_script")) {
                        if (ExecuteFileSpace.isFileRepeat(command[1])) {
                            console.printError("Обнаружена рекурсия");
                            continue;
                        }
                    }
                    console.println("Выполняется команда " + command[0]);
                    commandManager.execute(command[0], command[1]);
                    if (command[0].equals("execute_script")) {
                        ExecuteFileSpace.popFile();
                    }
                } catch (NoSuchCommand e) {
                    console.printError("Такой команды нет в списке");
                } catch (NoSuchElementException e) {
                    console.printError("Пользовательский ввод не обнаружен");
                } catch (InvalidArguments e) {
                    console.printError("");
                } catch (CommandRuntimeError e) {
                    console.printError("Ошибка при исполнении команды");
                }
            }
            ExecuteFileSpace.popFile();
        } catch (NoSuchCommand e) {
            console.printError("Такой команды нет в списке");
        } catch (FileNotFoundException e) {
            console.printError("Исполняемый файл не найден");
        } catch (IOException e) {
            console.printError("Ошибка ввода/вывода");
        } catch (NoSuchElementException e) {
            console.println("Конец выполняемого скрипта");
        }
        Console.setFileMode(false);
    }
}

