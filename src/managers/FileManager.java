package managers;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import commandSpace.Printable;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;
import commandSpace.Console;
import exceptions.ExitProgram;
import exceptions.FileModeException;
import exceptions.InvalidForm;
import models.Vehicle;

public class FileManager {
    private String text;
    private final String pathToFile;
    private final Printable console;
    private final CollectionManager collectionManager;
    private final Gson gson = new GsonBuilder()
            .setDateFormat("dd/MM/yyyy HH:mm")
            .setPrettyPrinting().create();

    public FileManager(Console console, CollectionManager collectionManager, String pathToFile) {
        this.console = console;
        this.collectionManager = collectionManager;
        this.pathToFile = pathToFile;
    }

    public boolean findFile() throws ExitProgram {
        File file = new File(pathToFile);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] date = new byte[(int) file.length()];
            int bytesRead = bufferedInputStream.read(date);
            if (bytesRead != file.length()) {
                console.printError("Ошибка чтения файла");
                throw new ExitProgram();
            }
            text = new String(date);
            if (text.isEmpty()) {
                console.printError("Передан пустой файл");
                return false;
            }
        } catch (FileNotFoundException e) {
            console.printError("Файл не найден");
            throw new ExitProgram();
        } catch (IOException e) {
            console.printError("Ошибка ввода/вывода " + e);
            throw new ExitProgram();
        }
        return true;
    }
    public void createObjects() throws ExitProgram{
        try {
            Type collectionType = new TypeToken<HashSet<Vehicle>>(){}.getType();
            HashSet<Vehicle> hashSet = gson.fromJson(text, collectionType);
            CollectionManager collectionManagerCreating = new CollectionManager();
            collectionManagerCreating.setCollection(hashSet);
            this.collectionManager.setLastSaveTime(collectionManagerCreating.getLastSaveTimeInDate());
            this.collectionManager.setLastInitTime(collectionManagerCreating.getInitTimeInDate());
            if (collectionManagerCreating.getCollection() == null){
                console.printError("Коллекция в файле пуста");
                return;
            }
            for (Vehicle vehicle: collectionManagerCreating.getCollection()){
                if (collectionManager.checkExistById(vehicle.getId())){
                    console.printError("Найдены повторяющиеся id в файле");
                    throw new ExitProgram();
                }
                this.collectionManager.addElement(vehicle);
            }
            this.collectionManager.setLastSaveTime(collectionManagerCreating.getLastSaveTimeInDate());
        } catch (JsonSyntaxException e){ // InvalidForm |
            console.printError("Объекты из файла невалидны");
            e.printStackTrace();
        } catch(JsonParseException e){
            console.printError("Нарушена структура json файла с данными");
        }
        Vehicle.updateIdPoint(collectionManager.getCollection());
    }
    public void saveObjects(){
        String filePath = pathToFile;
        if (filePath == null || filePath.isEmpty()){
            console.printError("Пустой путь недопустим");
            return;
        } else {
            console.println("Путь к файлу успешно получен");
        }
        try{
            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath));
            printWriter.write(gson.toJson(collectionManager));
            printWriter.close();
        } catch (FileNotFoundException e){
            console.printError("Файл не найден");
        } catch (IOException e){
            console.printError("Ошибка ввода/вывода");
        }
    }

}
