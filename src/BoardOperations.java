public class BoardOperations {

    public enum Move {LEFT, RIGHT, UP, DOWN}

    public static int[] move_board(Integer new_index, int[] inputboard){
        int[] board = inputboard.clone();
        board[Utils.getAgentIndex(board)] = board[new_index];
        board[new_index] = -1;
        return board;
    }
}
