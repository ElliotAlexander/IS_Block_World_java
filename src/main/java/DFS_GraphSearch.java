import java.util.ArrayList;

public class DFS_GraphSearch {

    int previous = -1;
    ArrayList<BoardOperations.Move> moves = new ArrayList<>();
    private int nodes_expanded = 0;
    private int repeats_avoided;

    public void DFS_iterative(int[] start ){
            Logger.Log(Logger.Level.INFO, "Beginning DFS Search.");
            int[] current = start;
            while(!(GoalStateChecker.checkGoalState(current))){
                Integer current_agent = BoardOperations.getAgentIndex(current);

                Integer[] neighbours = BoardOperations.getNeighbours(current);
                int next = neighbours[Utils.generate_rand(neighbours.length)-1];
                if(next == previous){
                    repeats_avoided += 1;
                    continue;
                }

                nodes_expanded += 1;
                moves.add(Utils.getmove(current_agent, next, 4));
                current = BoardOperations.move_board(next, current);
                previous = current_agent;
            }

            Logger.Log(Logger.Level.INFO, " ---- Process ----");
            for(BoardOperations.Move m : moves){
                Logger.Log(m.toString() + " ");
            }
            Logger.Log(Logger.Level.INFO, " ---- End ----");
            Utils.printBoard(current);
            Logger.Log(Logger.Level.INFO, "Nodes expanded: " + nodes_expanded);
            Logger.Log(Logger.Level.INFO, "Repeats avoided: " + repeats_avoided);
    }
}
