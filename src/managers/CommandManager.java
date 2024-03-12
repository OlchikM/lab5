package managers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import commandSpace.commands.Command;

public class CommandManager {
    private final HashMap<String, Command> commandsHashMap = new HashMap<>();
    private final List<String> commandHistory = new ArrayList<>();

    public void addCommandToHashMap(Command command) {
        this.commandsHashMap.put(command.getName(), command);
    }

    public void addCommandToHashMap(Collection<Command> commands) {
        this.commandsHashMap.putAll(commands.stream().collect(Collectors.toMap(Command::getName, s -> s)));
    }
    public Collection<Command> getCommandsHashMap(){
        return this.commandsHashMap.values();
    }
    public void addToHistory(String line){
        if (line.isBlank()) return;
        this.commandHistory.add(line);
    }
    public List<String> getCommandHistory(){
        return this.commandHistory;
    }



}
