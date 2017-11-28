import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {

    int nodes_expanded = 0;
    int depth = 0;

    public void BFS(int[] startState) {
        Queue<Pair<int[], Integer>> q = new ArrayBlockingQueue<>(100000000);
        q.add(new Pair(startState, 0));
        while (!(q.isEmpty())) {
            Pair<int[], Integer> p = q.poll();
            nodes_expanded += 1;
            for (Integer i : BoardOperations.getNeighbours(p.val1)) {
                int[] newboard = BoardOperations.move_board(i, p.val1 );
                q.add(new Pair(newboard, p.val2 + 1));

                // GOAL STATE
                if (GoalStateChecker.checkGoalState(newboard)) {
                    depth = p.val2 + 1;
                    Logger.Log(Logger.Level.INFO, "Nodes expanded: " + nodes_expanded);
                    Logger.Log(Logger.Level.INFO, "Depth : " + depth);
                    Utils.printBoard(newboard);
                }
            }
        }
    }
}
