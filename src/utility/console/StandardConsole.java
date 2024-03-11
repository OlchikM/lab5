package utility.console;

public abstract class StandardConsole implements Console{
    private final static String PS1 = "$ ";
    private final static String PS2 = "> ";
    public void print(Object obj){
        System.out.print(obj);
    }
    public void println(Object obj){
        System.out.println(obj);
    }


}
