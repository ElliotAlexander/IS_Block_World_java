import Utils.Logger;

import java.util.ArrayList;
import java.util.Arrays;

public class InputParser {

    public int[] parse_string(String input){
        ArrayList<Integer> parsed_string = new ArrayList<>();
        input = input.replaceAll("\\{", "").replaceAll("}", "");
        String[] split_string = input.split(",");
        for(String new_int : split_string){
            try {
                parsed_string.add(Integer.parseInt(new_int));
            } catch (NumberFormatException e){
                Logger.Log(Logger.Level.ERROR, "Couldn't parse string " + new_int);
            }
        }

        int[] return_list = new int[parsed_string.size()];
        int index = 0;
        for(int i : parsed_string){
            return_list[index] = i;
            index++;
        }

        Logger.Log(Logger.Level.INFO, "Parsed array " + Arrays.toString(return_list));
        return return_list;
    }
}
