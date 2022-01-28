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

        PriorityQueue<WordNode> frontier = new PriorityQueue<>(A.GetNeighbors());

        WordNode Goal = new WordNode(word2, null, 999);

        boolean reached = false;

        int beg = 1;
        int end = 2;

        WordNode target = null;

        while(beg < end && !reached){

            A = frontier.poll();//head = queue[beg]
            explored.add(A);
            assert A != null;
            ArrayList<WordNode> temp = A.GetNeighbors();

            for (WordNode w: temp //for each neighbor u that isn't explored
                 ) {

                if(!exploredContains(explored, w)) {

                    frontier.add(w); //seen = true;
                    end++;
                    if(w.word.equals(Goal.word)){

                        explored.add(w);
                        target = w;
                        reached = true;
                        break;

                    }
                }

            }

            beg++;

        }

        if(reached){

            ArrayList<WordNode> path = new ArrayList<>();

            WordNode W = target;
            path.add(W);

            while(W.getParent()!= null){

                W = W.getParent();
                path.add(W);

            }

            for (int i = path.size()-1; i >= 0; i--) {

                System.out.println(path.get(i));

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

            if(e.word.equals(w.word)) return true;

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
