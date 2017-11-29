import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


// This version of BFS is considerably slower, and uses a fair bit more memory, but will give steps
// Rather than just find the solution.

public class BFS_Tracked {

    private GoalStateChecker gsc;

    public BFS_Tracked(GoalStateChecker gsc){
        this.gsc = gsc;
    }


    public void BFS(int[] startState) {
        HashMap<Pair, Pair> parents = new HashMap<>();
        int nodes_expanded = 0;
        int depth = 0;
        Queue<Pair<int[], Integer>> q = new ArrayBlockingQueue<>(100000000);
        q.add(new Pair(startState, 0));
        while (!(q.isEmpty())) {
            Pair<int[], Integer> p = q.poll();
            nodes_expanded += 1;
            for (Integer i : BoardOperations.getNeighbours(p.val1)) {
                int[] newboard = BoardOperations.move_board(i, p.val1 );
                Pair newPair = new Pair(newboard, p.val2 + 1);
                q.add(newPair);
                parents.put(newPair, p);

                // GOAL STATE
                if (gsc.checkGoalState(newboard)) {
                    depth = p.val2 + 1;
                    Logger.Log(Logger.Level.INFO, "Nodes expanded: " + nodes_expanded);
                    Logger.Log(Logger.Level.INFO, "Depth : " + depth);
                    Utils.printBoard(newboard);

                    Pair<int[], Integer> next = newPair;
                    while(next != null){

                        Logger.Log("\n----- Start ---- \n");
                        Utils.printBoard(next.val1);
                        next = parents.get(next);
                        Logger.Log("\n----- End ----\n");
                    }

                    return;
                }
            }
        }
    }
}
