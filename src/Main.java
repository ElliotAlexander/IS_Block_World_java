
public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        Logger.Log(Logger.Level.INFO, "initialising starting board...");


        // 5x5
        //int[] startState = {0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 1,2,3,-1,0};

        // 4x4
        int[] startState = {0,0,0,0, 0,0,0,0, 0,0,0,0, 1,2,3,-1};
        Utils.printBoard(startState);


        int[] newstate = {0,0,0,0, 0,1,0,0, 0,2,0,0, 0,3,-1,0};
        Utils.printBoard(newstate);


        //int[] completecase = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 3, -1};
        //Utils.printBoard(completecase);


        //AStar ash = new  AStar();
        //Logger.Log(Logger.Level.INFO, String.valueOf(ash.gn(completecase)));

        //BFS_exp bfse = new BFS_exp();
        //bfse.BFS(startState);
        //bfse.printComplete();

       //IterativeDeepening ids = new IterativeDeepening();
       //ids.IterativeDeepening(startState);

        AStar as = new AStar();
        as.runAStart(startState);
       // Logger.Log(Logger.Level.INFO, String.valueOf(as.fn(startState)));
        //Logger.Log(Logger.Level.INFO, String.valueOf(as.fn(newstate)));


        //DFS dfs = new DFS();
        //dfs.DFS_iterative(startState);

        //dfs.printComplete();
        //dfs.printMoves();
    }
}
