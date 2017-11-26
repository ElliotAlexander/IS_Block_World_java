public class AStarHeuristic {

    public static int gn(int[] board){
        int sum = 0;
        for(int i =0; i < board.length; i++){
            if(board[i] != 0 && board[i] != -1){
                int goal = Constants.goal_States.get(board[i]);
                int[] icoords = Utils.index_to_coords(i);
                int[] goalcoords = Utils.index_to_coords(goal);
                sum += Math.abs(icoords[0] - goalcoords[0]) + Math.abs(icoords[1] - goalcoords[1]);

                /**
                 *
                 * DEBUG
                 *
                int calculated = Math.abs(icoords.val1 - goalcoords.val1) + Math.abs(icoords.val2 - goalcoords.val2);
                sum += calculated;
                Logger.Log(Logger.Level.INFO, "Goal state for i = (" + goalcoords.val1 + "," + goalcoords.val2 + ").");
                Logger.Log(Logger.Level.INFO, "Calculated i = " + i + " at coords (" + icoords.val1 + "," + icoords.val2 + "). Sum = " + calculated);

                 **/
            }
        }
        return sum;
    }
}
