package Searches;

import Resources.Pair;
import Utils.BoardOperations;
import Utils.GoalStateChecker;
import Utils.Logger;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {

    private GoalStateChecker gsc;

    public BFS(GoalStateChecker gsc){
        this.gsc = gsc;
    }


    public void BFS(int[] startState) {
        Queue<Pair<int[], Integer>> q = new ArrayBlockingQueue<>(100000000);
        q.add(new Pair(startState, 0));
        int nodes_expanded = 0;
        int depth = 0;
        while (!(q.isEmpty())) {
            Pair<int[], Integer> p = q.poll();
            nodes_expanded += 1;
            for (Integer i : BoardOperations.getNeighbours(p.val1)) {
                int[] newboard = BoardOperations.move_board(i, p.val1 );
                Pair newPair = new Pair(newboard, p.val2 + 1);
                q.add(newPair);

                // GOAL STATE
                if (gsc.checkGoalState(newboard)) {
                    depth = p.val2 + 1;
                    Logger.Log(Logger.Level.ESSENTIALINFO, "Nodes expanded: " + nodes_expanded);
                    Logger.Log(Logger.Level.INFO, "Depth : " + depth);
                    BoardOperations.printBoard(newboard, GoalStateChecker.N);
                    return;
                }
            }
        }
    }
}
