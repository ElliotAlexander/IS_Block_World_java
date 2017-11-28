import java.util.ArrayList;
import java.util.Random;

public class Utils {

    private Utils(){}

    public static Random rand = new Random();
    public static int previous_rand = 1;


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
            if(i % GoalStateChecker.N == 0 && i != 0){
                Logger.LogLine("");
            }
            Logger.Log("| " + currentBoard[i] + " ");
        }
        Logger.LogLine("");
    }
}
