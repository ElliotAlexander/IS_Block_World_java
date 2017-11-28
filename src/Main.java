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


        //int[] newstate = {0,0,0,0, 0,1,0,0, 0,2,0,0, 0,3,-1,0};
        //Utils.printBoard(newstate);


        //int[] completecase = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 3, -1};
        //Utils.printBoard(completecase);

        //AStar asa = new AStar();
        //asa.AStarStart(startState);

        //BFS bfse = new BFS();
        //bfse.BFS(startState);


      //Iterative_Deepening ids = new Iterative_Deepening();
       //ids.IterativeDeepening(startState);

        Iterative_Deepening_Tracked idst = new Iterative_Deepening_Tracked();
        idst.IterativeDeepening(startState);

        //AStar as = new AStar();
        //as.runAStart(startState);
       // Logger.Log(Logger.Level.INFO, String.valueOf(as.fn(startState)));
        //Logger.Log(Logger.Level.INFO, String.valueOf(as.fn(newstate)));


        //DFS_GraphSearch dfs = new DFS_GraphSearch();
        //dfs.DFS_iterative(startState);


        //DFS dfs = new DFS();
        //dfs.DFS_iterative(startState);



    }
}
