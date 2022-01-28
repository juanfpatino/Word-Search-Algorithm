import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class hw1 {

    public static void main(String[] args)throws IOException {

        File dictFile = new File(args[0]);
        String word1 = args[1];
        String word2 = args[2];

        Scanner s = new Scanner(dictFile);
        ArrayList<String> dict = new ArrayList<>();
        String ss = s.nextLine();
        configDict(s, dict, ss);

        //BFS

        ArrayList<WordNode> explored = new ArrayList<>();

        WordNode A = new WordNode(word1, dict, 0);

        explored.add(A);

        ArrayList<WordNode> frontier = new ArrayList<>(A.GetNeighbors());

        WordNode Goal = new WordNode(word2, null, 999);

        boolean reached = false;

        int beg = 1;
        int end = 2;

        while(beg < end && !reached){

            A = frontier.get(beg);//head = queue[beg]

            ArrayList<WordNode> temp = A.GetNeighbors();

            for (WordNode w: temp //for each neighbor u that isn't explored
                 ) {

                if(!exploredContains(explored, w)) {

                    explored.add(w); //seen = true;
                    end++;
                    if(w.equals(Goal)){

                        reached = true;

                    }
                }

            }beg++;

        }

        if(reached){

            for (WordNode w:
                 explored) {

                System.out.println(w);

            }

        }
        else{

            System.out.println("No Solution");

        }

        //add neighbors to frontier




    }

    private static boolean exploredContains(ArrayList<WordNode> explored, WordNode w){



        for (WordNode e: explored
             ) {

            if(e.equals(w)) return true;

        }

        return false;

    }

    private static void configDict(Scanner s, List<String> dict, String ss) {
        while(true){

            String word = ss;

            dict.add(word);

            try{

                ss = s.nextLine();

            }catch( NoSuchElementException E){

                break;

            }

        }
    }
}
