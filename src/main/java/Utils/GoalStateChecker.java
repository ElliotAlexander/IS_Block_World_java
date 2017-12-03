package Utils;

import java.util.HashMap;

public class GoalStateChecker {

    public static Integer N = 4;
    private HashMap<Integer, Integer> goal_States;

    public GoalStateChecker(int[] goal_states_input){
        goal_States = new HashMap<Integer, Integer>();
        int index = 1;
        for(int i : goal_states_input){
            goal_States.put(i, index);
            Logger.Log(Logger.Level.INFO, "Added goal state with index " + i + " for tile number " + index);
            index++;
        }
    }

    public boolean checkGoalState(int[] checkboard){
        for(int index : goal_States.keySet()){
            if(checkboard[index] != goal_States.get(index)){
                return false;
            }
        }
        return true;
    }


    public int getGoalState(int value){
        for(int s  : goal_States.keySet()){
            if(goal_States.get(s) == value){
                return s;
            }
        }
        return 0;
    }
}
