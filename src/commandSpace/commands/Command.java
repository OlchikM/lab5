package commandSpace.commands;

import commandSpace.Executable;
import exceptions.ExitProgram;
import exceptions.InvalidArguments;

import java.util.Objects;

public class Command implements Executable {
    private final String name;
    private final String args;
    private final String description;
    public Command(String name, String description) {
        this.name = name;
        this.args = null;
        this.description = description;
    }

    public Command(String name, String args, String description) {
        this.name = name;
        this.args = args;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Command p = (Command) o;
        return this.name.equals(p.name) && this.description.equals(p.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        if (args == null || args.isBlank()) {
            return name + ": " + description;
        }return name + " " + args + ": " + description;
    }

    @Override
    public void execute(String args) throws InvalidArguments, ExitProgram {
    }
}
