import java.util.HashMap;

public class Constants {

    public static Integer N = 4;

    public static HashMap<Integer, Integer> goal_States = new HashMap<Integer, Integer>();
    static {
        goal_States.put(1, 5);
        goal_States.put(2, 9);
        goal_States.put(3, 13);
    }
}
