package Utils;

import java.util.ArrayList;

public class BoardOperations {

    public enum Move {L, R, U, D}

    public static int[] move_board(Integer new_index, int[] inputboard){
        int[] board = inputboard.clone();
        board[getAgentIndex(board)] = board[new_index];
        board[new_index] = -1;
        return board;
    }

    public static Integer getAgentIndex(int[] board) {
        int y = 0;
        while(true) {
            if (board[y] == -1) {
                return y;
            } else {
                y++;
            }
        }
    }

    private static Integer[] getNeighbours(Integer n, int agent){
        Integer[] newindexes = {agent+1, agent-1, agent - n, agent+n };
        ArrayList<Integer> returnarr = new ArrayList<>();
        for(Integer i : newindexes){
            if(!(i < 0 || i > ((n*n)-1))){
                if(!((agent+1) % n == 0 && (i == agent+1)) && !((agent % 4 == 0) && (i == agent-1))){
                    returnarr.add(i);
                }
            }
        }
        return returnarr.toArray(new Integer[returnarr.size()-1]);
    }

    // Avoid using this if at all possible.
    public static Integer[] getNeighbours(int[] board){
        int agent = getAgentIndex(board);
        return getNeighbours(GoalStateChecker.N, agent);
    }

    public static Move getmove(int currentagent, int move, int n){
        if(move == currentagent - 1){
            return Move.L;
        } else if(move == currentagent + 1){
            return Move.R;
        } else if(move == currentagent - n){
            return Move.U;
        } else if(move == currentagent + n){
            return Move.D;
        } else {
            // LETS BREAK EVERYTHING
            return null;
        }
    }

    public static void printBoard(int[] board, int N) {
        int[] currentBoard = board;
        for(int i = 0; i < currentBoard.length; i++){
            if(i % N == 0 && i != 0){
                Logger.LogLine("");
            }
            Logger.Log("| " + currentBoard[i] + " ");
        }
        Logger.LogLine("");
    }
}
