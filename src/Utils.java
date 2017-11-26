import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

    private Utils(){}

    public static Random rand = new Random();
    public static int previous_rand = 1;

    public static int[] index_to_coords(int i){
        int x = i % Constants.N;
        int y = (i - x) / Constants.N;
        return new int[]{x, y};
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
        BoardOperations.Move[] moves = {BoardOperations.Move.RIGHT, BoardOperations.Move.LEFT, BoardOperations.Move.DOWN, BoardOperations.Move.UP};
        ArrayList<Integer> returnarr = new ArrayList<>();
        for(Integer i : newindexes){
            if(!(i < 0 || i > ((n*n)-1))){
                if(!((agent+1) == n && (i == agent+1)) && !((agent % 4 == 0) && (i == agent-1))){
                    returnarr.add(i);
                }
            }
        }
        return returnarr.toArray(new Integer[returnarr.size()-1]);
    }

    // Avoid using this if at all possible.
    public static Integer[] getNeighbours(int[] board){
        int agent = getAgentIndex(board);
        return getNeighbours(Constants.N, agent);
    }

    public static int generate_rand(int limit){
        if (previous_rand == 0) return previous_rand = rand.nextInt(limit) + 1;
        final int rnd = rand.nextInt(limit-1) + 1;
        return previous_rand = (rnd < previous_rand? rnd : rnd + 1);
    }

    public static BoardOperations.Move getmove(int currentagent, int move, int n){
        if(move == currentagent - 1){
            return   BoardOperations.Move.LEFT;
        } else if(move == currentagent + 1){
            return BoardOperations.Move.RIGHT;
        } else if(move == currentagent - n){
            return BoardOperations.Move.UP;
        } else if(move == currentagent + n){
            return BoardOperations.Move.DOWN;
        } else {
            // LETS BREAK EVERYTHING
            return null;
        }
    }

    public static void printBoard(int[] board) {
        int[] currentBoard = board;
        for(int i = 0; i < currentBoard.length; i++){
            if(i % Constants.N == 0 && i != 0){
                Logger.LogLine("");
            }
            Logger.Log("| " + currentBoard[i] + " ");
        }
        Logger.LogLine("");
    }
}
