import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AStar {

    // Spaghetti code incoming

    private GoalStateChecker gsc;

    public AStar(GoalStateChecker gsc){
        this.gsc = gsc;
    }

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
        int nodes_expanded = 0;

        BoardPosition current;
        while(true){
            current = openlist.poll();


            if(current == null){
                break;
            }
            nodes_expanded += 1;

            // COMPLETE STATE
            if(gsc.checkGoalState(current.board)){
                BoardPosition next = current;
                int count = 0;
                Logger.LogLine("");
                Logger.Log(Logger.Level.INFO, " ---- Process ----");
                while(next != null){
                    Utils.printBoard(next.board, GoalStateChecker.N);
                    next = next.parent;
                    System.out.println("");
                    count += 1;
                }
                Logger.Log(Logger.Level.INFO, " ---- End ----\n\n");

                Logger.Log(Logger.Level.INFO, "Complete in " + (count-1) + " iterations.");
                Logger.Log(Logger.Level.INFO, "Nodes expanded: " + nodes_expanded);
                Logger.Log(Logger.Level.INFO, "Final board: \n");
                Utils.printBoard(current.board, GoalStateChecker.N);
                Logger.LogLine("");
                Logger.Log(Logger.Level.INFO, "Done!");
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
                    sum += manhatten_distance(i, gsc.getGoalState(board[i]));
                }
            }
            return sum;
        }

    private int manhatten_distance(int start, int index){
        int[] icoords = {index % GoalStateChecker.N, (index - index % GoalStateChecker.N) / GoalStateChecker.N};
        int[] goalcoords = {start % GoalStateChecker.N, (start - (start % GoalStateChecker.N)) / GoalStateChecker.N};
        return  Math.abs(icoords[0] - goalcoords[0]) + Math.abs(icoords[1] - goalcoords[1]);
    }
}
