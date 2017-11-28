
import java.util.*;

public class AStar {

    private int nodes_Expanded;

    public int fn(int[] board, int[] starting_agent){
        int sum = 0;
        int current_agent = BoardOperations.getAgentIndex(board);

        int[] current_agent_coords = {current_agent % GoalStateChecker.N, (current_agent - (current_agent % GoalStateChecker.N)) / GoalStateChecker.N};

        for(int i =0; i < board.length; i++){
            if(board[i] != 0 && board[i] != -1) {

                int goal = GoalStateChecker.getGoalState(board[i]);
                int[] icoords = {i % GoalStateChecker.N, (i - i % GoalStateChecker.N) / GoalStateChecker.N};
                //Logger.Log(Logger.Level.INFO, "Current coordinates : (" + icoords[0] + "," + icoords[1]);
                int[] goalcoords = {goal % GoalStateChecker.N, (goal - (goal % GoalStateChecker.N)) / GoalStateChecker.N};
                //Logger.Log(Logger.Level.INFO, "Goal coordinates : (" + goalcoords[0] + "," + goalcoords[1] + ")");
                int distance = Math.abs(icoords[0] - goalcoords[0]) + Math.abs(icoords[1] - goalcoords[1]);
                //Logger.Log(Logger.Level.INFO, "Distance: " + distance);
                sum += distance;
            }
        }
        sum += 1;
        return sum;
    }

    class BoardPosition implements Comparable<BoardPosition> {

        public int[] board;
        public Integer cost;
        public BoardPosition parent;

        public BoardPosition(int[] board, Integer cost, BoardPosition parent){
            this.board = board;
            this.cost = cost;
            this.parent = parent;
        }

        @Override
        public int compareTo(BoardPosition input){
            return this.cost.compareTo(input.cost);
        }

        @Override
        public boolean equals(Object input){
            BoardPosition inputbp = (BoardPosition)input;
            return this.board.equals(inputbp.board);
        }

    }


    public void runAStart(int[] board){
        int escape = 0;

        int agent = BoardOperations.getAgentIndex(board);
        int[] starting_agent = {agent % GoalStateChecker.N, (agent - (agent % GoalStateChecker.N)) / GoalStateChecker.N};

        TreeSet<BoardPosition> openList = new TreeSet<>();
        TreeSet<BoardPosition> closedList = new TreeSet<>();
        openList.add(new BoardPosition(board, 10000, null));
        while(!openList.isEmpty()){

            if(escape == 1000){
                break;
            } else {
                escape += 1;
            }

            BoardPosition current = openList.pollFirst();
            Logger.Log("Loading new board position.");
            Utils.printBoard(current.board);

            if(GoalStateChecker.checkGoalState(current.board)){
                System.out.println("YOU DID IT");
                Utils.printBoard(current.board);
                return;
            }

            closedList.add(current);
            for(int i : BoardOperations.getNeighbours(current.board)){
                int[] neighbour = BoardOperations.move_board(i, current.board);
                int f = fn(neighbour, starting_agent);
                BoardPosition newpos = new BoardPosition(neighbour, f, current);

                boolean openlistcontains = false;
                BoardPosition openlistneighbour = null;
                for(BoardPosition p : openList){
                    if(p.equals(newpos)){
                        openlistneighbour = p;
                        openlistcontains = true;
                        break;
                    }
                }

                boolean closedlistcontains = false;
                for(BoardPosition p: closedList){
                    if(p.equals(newpos)){
                        closedlistcontains = true;
                        break;
                    }
                }


                if(!closedlistcontains){
                 if(!openlistcontains){
                     openList.add(newpos);
                 } else {
                     if(openlistneighbour.cost > f){
                         openList.remove(openlistneighbour);
                         openList.add(newpos);
                     }
                 }
                }
            }
            Logger.Log(Logger.Level.INFO, "Closed list size : " + closedList.size());
        }
        int a = 1;
        for(BoardPosition x : closedList){
            Logger.Log(Logger.Level.INFO, "Board " + a);
            Utils.printBoard(x.board);
            a++;
        }
        System.out.println("No solution found");
    }

}
