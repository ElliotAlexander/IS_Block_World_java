package Utils;

import java.util.Random;

public class RandomGenerator {

    public static Random rand = new Random();
    public static int previous_rand = 1;


    public static int generate_rand(int limit){
        if (previous_rand == 0) return previous_rand = rand.nextInt(limit) + 1;
        if(limit == 1){ return 1;}
        final int rnd = rand.nextInt(limit - 1) + 1;
        return previous_rand = (rnd < previous_rand? rnd : rnd + 1);
    }

}
