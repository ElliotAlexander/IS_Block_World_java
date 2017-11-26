public class GoalStateChecker {

    public static boolean checkGoalState(int[] checkboard){
        if(checkboard[5]==1
                && checkboard[9]==2
                && checkboard[13]==3) {
            return true;
        } else {
            return false;
        }
    }
}
