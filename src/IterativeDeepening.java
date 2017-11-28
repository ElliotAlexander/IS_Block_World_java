import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class IterativeDeepening {

    private int nodes_expanded;
    private int[] complete_board;
    private int depth;

    public void IterativeDeepening(int[] starting_board){
        int n = 1;
        while(IterativeDeepeningAux(n, starting_board) == false){
            Logger.Log(Logger.Level.INFO, "Increasing depth to " + n + " with " + nodes_expanded + " nodes expanded.");
            n++;
        }
        Logger.Log(Logger.Level.INFO, "Success!");
        Logger.Log(Logger.Level.INFO, "Depth : " + depth);
        Logger.Log(Logger.Level.INFO, "Nodes expanded: " + nodes_expanded);
        Utils.printBoard(complete_board);
    }


    private boolean IterativeDeepeningAux(int max_depth, int[] startState){
        Queue<Pair<int[], Integer>> q = new ArrayBlockingQueue<>(100000000);
        q.add(new Pair(startState, 0));
        while (!(q.isEmpty())) {
            Pair<int[], Integer> p = q.poll();
            nodes_expanded += 1;


            if(p.val2 == max_depth){
                return false;
            }
            for (Integer i : BoardOperations.getNeighbours(p.val1)) {
                int[] newboard = BoardOperations.move_board(i, p.val1 );
                q.add(new Pair(newboard, p.val2 + 1));
                if (GoalStateChecker.checkGoalState(newboard)) {
                    depth = p.val2 + 1;
                    complete_board = newboard;
                    return true;
                }
            }
        }
        return false;
    }
}
