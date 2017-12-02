import java.io.*;
import java.io.FileWriter;

public class Logger {

    public enum Level {ESSENTIALINFO, INFO, ERROR, WARNING};


    public static void Log(Level level, String message){
        System.out.println("[" + level + "] " + message);
    }

    public static void Log(String message){
        System.out.print(message);
    }

    public static void LogLine(String message){
        System.out.println(message);
    }
}