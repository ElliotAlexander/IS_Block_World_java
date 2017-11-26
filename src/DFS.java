import java.util.ArrayList;

public class DFS {

    int previous = -1;
    ArrayList<BoardOperations.Move> moves = new ArrayList<>();
    private int[] complete_board = null;
    private int nodes_expanded = 0;

    public void DFS_iterative(int[] start ){

        int[] current = start;

        while(!(GoalStateChecker.checkGoalState(current))){
            Integer current_agent = Utils.getAgentIndex(current);
            nodes_expanded += 1;

            Integer[] neighbours = Utils.getNeighbours(current);
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
        complete_board = current;
    }

    public void printMoves(){
        for(BoardOperations.Move m : moves){
            Logger.Log(m.toString() + " ");
        }
    }

    public void printComplete(){
        if(complete_board == null){
            System.out.println("Run DFS first!");
        } else {
            Utils.printBoard(complete_board);
            Logger.Log(Logger.Level.INFO, "Nodes expanded: " + nodes_expanded);
        }
    }
}
