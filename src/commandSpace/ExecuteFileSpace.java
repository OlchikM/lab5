package commandSpace;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayDeque;

public class ExecuteFileSpace implements Inputable{
    private static final ArrayDeque<String> paths = new ArrayDeque<>();
    private static final ArrayDeque<BufferedInputStream> fileReader = new ArrayDeque<>();
    private static void pushFile(String path) throws FileNotFoundException {
        paths.push(String.valueOf(Paths.get(path)));
        fileReader.push(new BufferedInputStream(new FileInputStream(path)));
    }
    public static void popFile() throws IOException{
        fileReader.getFirst().close();
        fileReader.pop();
        paths.pop();
    }
    public File getPath(){
        return new File(paths.getLast());
    }
    public static String readLine() throws IOException {
        BufferedInputStream bf = fileReader.getFirst();
        StringBuilder res = new StringBuilder();
        int nextByte;
        while ((nextByte = bf.read()) != -1) {
            char ch = (char) nextByte;
            if (ch == '\n' || ch == '\r') {
                break; // Прекращаем чтение при обнаружении символа новой строки
            }
            res.append(ch);
        }
        return res.toString();
    }
    @Override
    public String nextLine(){
        try{
            return readLine();
        } catch (IOException e){
            return "";
        }
    }
}
