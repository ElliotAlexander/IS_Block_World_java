

/** EMBRACE THE GLORIFIED GRAPH SEARCH
 *
 *
 * This is seriously a super glorifeid graph search.
 *
 *
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {

    ArrayList<int[]> seenStates;
    int[] complete_board = null;

    public void BFS(int[] startState) {
        seenStates = new ArrayList<>();
        Queue<int[]> q = new ArrayBlockingQueue<>(100000);
        q.add(startState.clone());
        seenStates.add(startState.clone());

        while (!(q.isEmpty())) {
            int[] v = q.poll();
            outerloop:
            for (Integer i : Utils.getNeighbours(v)) {

                // generate the new board
                int[] newboard = BoardOperations.move_board(i, v);

                // Have we been in this state before
                for (int[] seenstate : seenStates) {
                    if(Arrays.equals(seenstate, newboard)){
                        continue outerloop;
                    }
                }

                // add the new board to the queue
                q.add(newboard.clone());
                seenStates.add(newboard.clone());
                if (GoalStateChecker.checkGoalState(newboard)) {
                    complete_board = newboard.clone();
                    return;
                }
            }
        }
    }


    public void printComplete(){
        if(complete_board == null){
            Logger.Log(Logger.Level.ERROR,"Run DFS first!");
        } else {
            Utils.printBoard(complete_board);
            Logger.Log(Logger.Level.INFO, "States explored : " + seenStates.size());
        }
    }
}
**/