import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Iterative_Deepening_Tracked {

    private int nodes_expanded;
    private int[] complete_board;
    private int depth;
    private GoalStateChecker gsc;


    public Iterative_Deepening_Tracked(GoalStateChecker gsc){
        this.gsc = gsc;
    }

    public void IterativeDeepening(int[] starting_board){
        int n = 1;
        while(IterativeDeepeningAux(n, starting_board) == false){
            Logger.Log(Logger.Level.INFO, "Increasing depth to " + n + " with " + nodes_expanded + " nodes expanded.");
            n++;
        }
        Logger.Log(Logger.Level.INFO, "Success!");
        Logger.Log(Logger.Level.INFO, "Depth : " + depth);
        Logger.Log(Logger.Level.ESSENTIALINFO, "Nodes expanded: " + nodes_expanded);
        Utils.printBoard(complete_board, GoalStateChecker.N);
    }


    private boolean IterativeDeepeningAux(int max_depth, int[] startState){
        Queue<Pair<int[], Integer>> q = new ArrayBlockingQueue<>(100000000);
        HashMap<Pair, Pair> parents = new HashMap<>();
        Pair current = new Pair(startState, 0);
        q.add(current);
        while (!(q.isEmpty())) {
            Pair<int[], Integer> p = q.poll();
            nodes_expanded += 1;


            if(p.val2 == max_depth){
                return false;
            }
            for (Integer i : BoardOperations.getNeighbours(p.val1)) {
                int[] newboard = BoardOperations.move_board(i, p.val1 );
                Pair next = new Pair(newboard, p.val2 + 1);
                q.add(next);
                parents.put(next, p);
                if (gsc.checkGoalState(newboard)) {
                    depth = p.val2 + 1;
                    complete_board = newboard;

                    Pair<int[], Integer> x = next;
                    while(x != null){
                        Logger.Log("\n----- Start ---- \n");
                        Utils.printBoard(x.val1, GoalStateChecker.N);
                        x = parents.get(x);
                        Logger.Log("\n----- End ----\n");
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
