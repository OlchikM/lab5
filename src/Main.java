import commandSpace.Console;
import commandSpace.commands.Help;
import commandSpace.commands.*;
import exceptions.ExitProgram;
import managers.CollectionManager;
import managers.CommandManager;
import managers.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();
        //Scanner yy = new Scanner(System.in);
        //console.println("Введите путь к файлу + файл (желательно без ошибок)");
        if (args.length == 0){
            console.println("Программа завершена экстренно");
            return;
        }
        FileManager fileManager = new FileManager(console, collectionManager, args[0].trim());
        try {
            if (fileManager.findFile()) {
                fileManager.createObjects();
            }
        } catch (ExitProgram e) {
            console.println("До свидания!");
            return;
        }

        console.println("Добро пожаловать!" + "\n" + "Введите help для получения списка доступных команд");

        commandManager.addCommandToHashMap(List.of(
                new Help(console, commandManager),
                new Info(collectionManager, console),
                new Show(collectionManager, console),
                new AddElement(collectionManager, console),
                new Update(collectionManager, console),
                new RemoveById(collectionManager, console),
                new Clear(collectionManager, console),
                new Save(console, fileManager),
                new Execute(console, commandManager),
                new Exit(),
                new RemoveLower(console, collectionManager),
                new AddIfMax(collectionManager, console),
                new History(commandManager, console),
                new FilterStartsWithName(console, collectionManager),
                new FilterGreaterThanFuelConsumption(console, collectionManager),
                new PrintUniqueFuelConsumption(console, collectionManager)
        ));
        new RuntimeManager(console, commandManager).runInteractive();
    }
}