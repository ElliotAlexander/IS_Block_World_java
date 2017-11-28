import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AStar {

    class BoardPosition implements Comparable<BoardPosition>{

        int g = 0;
        Integer finalcost = 0;
        int[] board;
        BoardPosition parent;

        public BoardPosition(int[] board){
            this.board = board;
        }

        @Override
        public int compareTo(BoardPosition o) {
            return this.finalcost.compareTo(o.finalcost);
        }
    }

    PriorityQueue<BoardPosition> openlist;
    ArrayList<BoardPosition> closedlist;


    public void AStarStart(int[] startPosition){
        openlist = new PriorityQueue<>();
        closedlist = new ArrayList<>();

        BoardPosition start = new BoardPosition(startPosition);
        start.finalcost = 0;
        openlist.add(start);


        BoardPosition current;
        while(true){
            current = openlist.poll();

            if(current == null){
                break;
            }

            if(GoalStateChecker.checkGoalState(current.board)){
                BoardPosition next = current;
                Logger.Log(Logger.Level.INFO, " ---- Process ----");
                while(next.parent != null){
                    Utils.printBoard(next.parent.board);
                    next = next.parent;
                    System.out.println("");
                }

                Logger.Log(Logger.Level.INFO, "Final board: ");
                Utils.printBoard(current.board);


                System.out.println("Done!");
                return;
            }

            for(int neighbourindex : BoardOperations.getNeighbours(current.board)){
                int[] pos = BoardOperations.move_board(neighbourindex, current.board);
                BoardPosition newbp = new BoardPosition(pos);
                newbp.finalcost = hn(pos) + current.g;
                newbp.g = current.g + 1;
                newbp.parent = current;


                boolean openlistcontains = false;
                BoardPosition openlistnew = null;
                for(BoardPosition bp : openlist){
                    if(Arrays.equals(pos, bp.board)){
                        openlistcontains = true;
                        openlistnew = bp;
                        break;
                    }
                }

                boolean closedlistcontains = false;
                BoardPosition closedlistnew = null;
                for(BoardPosition bp : closedlist){
                    if(Arrays.equals(pos, bp.board)){
                        closedlistcontains = true;
                        closedlistnew = bp;
                        break;
                    }
                }

                if(openlistcontains) {

                    // IF THERES A MORE EFFICIENT PATH TO WHERE WE'VE BEEN - STOPS LOOPS
                    if (newbp.finalcost < openlistnew.finalcost) {
                        openlist.remove(openlistnew);
                        openlist.add(newbp);
                        break;
                    }
                } else if(closedlistcontains){
                    if(newbp.finalcost < closedlistnew.finalcost){
                        closedlist.remove(closedlistnew);
                        openlist.add(closedlistnew);
                        break;
                    }
                } else {
                    openlist.add(newbp);
                }
            }
        }
    }

    public int hn(int[] board){
        int sum = 0;
        for(int i =0; i < board.length; i++){
            if(board[i] != 0 && board[i] != -1) {
                sum += Utils.manhatten_distance(i, GoalStateChecker.getGoalState(board[i]));
            }
        }
        return sum;
    }
}
