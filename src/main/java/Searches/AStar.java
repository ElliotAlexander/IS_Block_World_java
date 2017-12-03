package Searches;

import Resources.Board;
import Utils.GoalStateChecker;
import Utils.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AStar {
    
    private GoalStateChecker gsc;
    private int nodes_expanded = 0;
    private PriorityQueue<BoardPosition> openlist;
    private ArrayList<BoardPosition> closedlist;

    public AStar(GoalStateChecker gsc){
        this.gsc = gsc;
    }

    public void AStarStart(int[] startPosition){
        nodes_expanded = 0;

        // Setup open and closed lists.
        openlist = new PriorityQueue<>();
        closedlist = new ArrayList<>();


        // Setup start node.
        BoardPosition start = new BoardPosition(startPosition);
        start.finalcost = 0;
        openlist.add(start);

        BoardPosition current;
        while(!openlist.isEmpty()){
            current = openlist.poll();
            nodes_expanded += 1;

            // COMPLETE STATE
            if(gsc.checkGoalState(current.getBoard_position())){
                complete(current, nodes_expanded);
                return;
            }

            for(int neighbourindex : current.getNeighbours()){
                BoardPosition newbp = current.move_board(neighbourindex);
                newbp.finalcost = hn(newbp.getBoard_position()) + current.g;
                newbp.g = current.g + 1;
                newbp.parent = current;


                boolean openlistcontains = false;
                BoardPosition openlistnew = null;
                for(BoardPosition bp : openlist){
                    if(Arrays.equals(newbp.getBoard_position(), bp.getBoard_position())){
                        openlistcontains = true;
                        openlistnew = bp;
                        break;
                    }
                }

                boolean closedlistcontains = false;
                BoardPosition closedlistnew = null;
                for(BoardPosition bp : closedlist){
                    if(Arrays.equals(newbp.getBoard_position(), bp.getBoard_position())){
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

    private int hn(int[] board){
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

    private void complete(BoardPosition current, int nodes_Ex){
        BoardPosition next = current;
        int count = 0;
        Logger.LogLine("");
        Logger.Log(Logger.Level.INFO, " ---- Process ----");
        while(next != null){
            next.printBoard();
            next = next.parent;
            System.out.println("");
            count += 1;
        }
        Logger.Log(Logger.Level.INFO, " ---- End ----\n\n");

        Logger.Log(Logger.Level.INFO, "Complete in " + (count-1) + " iterations.");
        Logger.Log(Logger.Level.INFO, "Nodes expanded: " + nodes_expanded);
        Logger.Log(Logger.Level.INFO, "Final board: \n");
        current.printBoard();
        Logger.LogLine("");
        Logger.Log(Logger.Level.INFO, "Done!");
        return;
    }

    class BoardPosition extends Board implements Comparable<BoardPosition>{

        int g = 0;
        Integer finalcost = 0;
        BoardPosition parent;

        public BoardPosition(int[] board){
            super(board);
        }

        @Override
        public int compareTo(BoardPosition o) {
            return this.finalcost.compareTo(o.finalcost);
        }

        @Override
        public BoardPosition move_board(Integer new_index){
            Board b = super.move_board(new_index);
            BoardPosition bp = new BoardPosition(b.getBoard_position());
            bp.setAgentIndex(b.getAgentIndex());
            return bp;
        }
    }
}
