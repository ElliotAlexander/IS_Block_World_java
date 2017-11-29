import java.io.*;
import java.io.FileWriter;

public class Logger {

    public enum Level {ESSENTIALINFO, INFO, ERROR, WARNING};

    public static boolean MINIMAL_OUTPUT = false;

    public static void Log(Level level, String message){
        if(level == Level.INFO){
            return;
        }
        System.out.println("[" + level + "] " + message);
    }

    public static void Log(String message){
        System.out.print(message);
    }

    public static void LogLine(String message){
        System.out.println(message);
    }
}