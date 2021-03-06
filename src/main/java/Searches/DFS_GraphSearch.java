package Searches;

import Utils.BoardOperations;
import Utils.GoalStateChecker;
import Utils.Logger;
import java.util.ArrayList;

import static Utils.BoardOperations.getmove;
import static Utils.RandomGenerator.generate_rand;

public class DFS_GraphSearch {

    GoalStateChecker gsc;

    public DFS_GraphSearch(GoalStateChecker gsc){
        this.gsc = gsc;
    }




    public void DFS_iterative(int[] start ){
         ArrayList<BoardOperations.Move> moves = new ArrayList<>();
        Logger.Log(Logger.Level.INFO, "Initialising nodes expanded and repeats avoided to zero.");
         int nodes_expanded = 0, repeats_avoided = 0, previous = -1;

            Logger.Log(Logger.Level.INFO, "Beginning Searches.DFS Search.");
            int[] current = start;
            while(!(gsc.checkGoalState(current))){
                Integer current_agent = BoardOperations.getAgentIndex(current);
                Integer[] neighbours = BoardOperations.getNeighbours(current);
                int next = neighbours[generate_rand(neighbours.length)-1];
                if(next == previous){
                    repeats_avoided += 1;
                    continue;
                }

                nodes_expanded += 1;
                moves.add(getmove(current_agent, next, 4));
                current = BoardOperations.move_board(next, current);
                previous = current_agent;
            }

            Logger.Log(Logger.Level.INFO, " ---- Process ----");
            for(BoardOperations.Move m : moves){
                Logger.Log(m.toString() + " ");
            }
            Logger.Log(Logger.Level.INFO, " ---- End ----");
            BoardOperations.printBoard(current, GoalStateChecker.N);
            Logger.Log(Logger.Level.ESSENTIALINFO, "Nodes expanded: " + nodes_expanded);
            Logger.Log(Logger.Level.INFO, "Repeats avoided: " + repeats_avoided);
    }
}
