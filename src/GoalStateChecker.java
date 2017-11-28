import java.util.HashMap;

public class GoalStateChecker {

    public static Integer N = 4;

    public static HashMap<Integer, Integer> goal_States = new HashMap<Integer, Integer>();

    static {
        GoalStateChecker.goal_States.put(5, 1);
        GoalStateChecker.goal_States.put(9, 2);
        GoalStateChecker.goal_States.put(13, 3);
    }

    public static boolean checkGoalState(int[] checkboard){
        for(int index : goal_States.keySet()){
            if(checkboard[index] != goal_States.get(index)){
                return false;
            }
        }
        return true;
    }


    public static int getGoalState(int value){
        for(int s  : goal_States.keySet()){
            if(goal_States.get(s) == value){
                return s;
            }
        }
        return 0;
    }
}
