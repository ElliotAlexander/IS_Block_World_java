import java.util.ArrayList;

public class DFS_GraphSearch {

    int previous = -1;
    ArrayList<BoardOperations.Move> moves = new ArrayList<>();
    private int[] complete_board = null;
    private int nodes_expanded = 0;

    public void DFS_iterative(int[] start ){
        Logger.Log(Logger.Level.INFO, "Beginning DFS Graph Saerch");

        int[] current = start;
        while(!(GoalStateChecker.checkGoalState(current))){
            Integer current_agent = BoardOperations.getAgentIndex(current);
            nodes_expanded += 1;

            Integer[] neighbours = BoardOperations.getNeighbours(current);
            int next = -1;
            for(int x : neighbours){
                if(!(x > 15 || x < 0 || x == previous)){
                    next = neighbours[Utils.generate_rand(neighbours.length)-1];
                }
            }

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
    }
}
