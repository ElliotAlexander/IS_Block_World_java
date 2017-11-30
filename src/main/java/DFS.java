import java.util.ArrayList;

public class DFS {

    private GoalStateChecker gsc;

    public DFS(GoalStateChecker gsc){
        this.gsc = gsc;
    }



    public void DFS_iterative(int[] start ){
        int nodes_expanded = 0;
        ArrayList<BoardOperations.Move> moves = new ArrayList<>();

        Logger.Log(Logger.Level.INFO, "Beginning DFS Search.");
        int[] current = start;
        while(!(gsc.checkGoalState(current))){
            Integer current_agent = BoardOperations.getAgentIndex(current);
            nodes_expanded += 1;

            Integer[] neighbours = BoardOperations.getNeighbours(current);
            int next = neighbours[Utils.generate_rand(neighbours.length)-1];
            moves.add(Utils.getmove(current_agent, next, GoalStateChecker.N ));
            current = BoardOperations.move_board(next, current);
        }

        Logger.Log(Logger.Level.INFO, " ---- Process ----");
        for(BoardOperations.Move m : moves){
            Logger.Log(m.toString() + " ");
        }

        Logger.Log(Logger.Level.INFO,  "\n ---- End ----\n");
        Utils.printBoard(current, GoalStateChecker.N);
        Logger.Log(Logger.Level.ESSENTIALINFO, "Nodes expanded: " + nodes_expanded);
    }
}
