import java.util.ArrayList;

public class BoardOperations {

    public enum Move {LEFT, RIGHT, UP, DOWN}

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
        BoardOperations.Move[] moves = {BoardOperations.Move.RIGHT, BoardOperations.Move.LEFT, BoardOperations.Move.DOWN, BoardOperations.Move.UP};
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
}
