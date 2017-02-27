package frogProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class frogProblem {
    
    public static void main(String[] args) {
        // Analyze each movesHistory of the form 1011...1101 with n central frogs.
        for (int i = 1; i < 50; i++) {
            // This list will contain the history of the moves.
            List<Pond> movesHistory = new ArrayList<>();
            
            // Generate the movesHistory disposition.
            int[] startingDisp = new int[i + 4];
            Arrays.fill(startingDisp, 1);
            startingDisp[1] = 0;
            startingDisp[i + 2] = 0;

            movesHistory.add(new Pond(startingDisp, 1));

            // Start moving frogs until the history is empty (which means that
            // the initiali disposition is not solvable) or until we reach a disposition
            // which is a valid solution.
            while (! movesHistory.isEmpty() && ! movesHistory.get(movesHistory.size() - 1).isSolution()) {
                Pond newPond = movesHistory.get(movesHistory.size() - 1).move();
                if (newPond == null) {// If a move returns null it means that we have finished analyzing
                // the disposition it originated from.
                    movesHistory.remove(movesHistory.size() - 1);
                } else {
                    movesHistory.add(newPond);
                }
            }
            
            // Print out for each starting disposition wether it had a solution or not.
            System.out.println(i + 4 + " => " + ! movesHistory.isEmpty());
            
            // Print out the whole moveset for each starting disposition.
            /*
            System.out.println(i + 4);
            for (Pond p: movesHistory) {
                System.out.println(Arrays.toString(p.getDisposition()));
            }
            System.out.println();
            */
        }
        
    }
    
}
