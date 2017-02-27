package frogProblem;

/**
 * Class which represents a pond with n lilypads, some with frogs on them
 * and some empty. For the full problem see:
 * https://www.youtube.com/watch?v=X3HDnrehyDM&t=0s
 */
public class Pond {
    // The disposition of frogs and lilypads. Each cell of the array is a lilypad
    // and the number it contains is the number of frogs on it.
    private final int[] disposition;
    // The index of the lilypad from which to start the analysis.
    private int startPos;
    // If +1 frogs will jump to the right, if -1 to the left.
    private int direction;
    // Determines wehter this pond represents the solution of the problem (all the frogs
    // are on the same lilypad).
    private final boolean solution;
    // Used to prevent a position form being checked multiple times.
    private boolean positionTested;
    
    // Initalize the pond.
    public Pond(int[] disp, int start) {
        this.positionTested = false;
        this.disposition = disp;
        this.startPos = start;
        // Start going right.
        this.direction = +1;
        // Check if this position is a solution.
        int count = 0;
        for (int i = 0; i < this.disposition.length && count < 2; i++) {
            if (this.disposition[i] != 0) {
                count++;
            }
        }
        this.solution = count == 1;
    }
    
    public Pond move() {
        // Find the next valid move.
        while (this.positionTested || ! canMove()) {
            this.positionTested = false;
            
            if (this.startPos == disposition.length - 1 && this.direction == -1) {
                // If we have already analyzed the last index going left then this pond
                // is finished.
                return null;
            } else if (this.direction == +1) {
                // If we wer moving a certain index to the right now move it to the left.
                this.direction = -1;
            } else {
                // If we were moving a certain index to the left move the next one
                // to the right.
                this.direction = +1;
                this.startPos++;
            }
        }
        
        // Move the selected pile of frogs.
        int newPos = this.startPos + this.direction * this.disposition[this.startPos];
        // .clone() is important, otherwise any change on this pond will propagate
        // to the other ponds and we will lose out history of moves.
        int newData[] = this.disposition.clone();
        newData[newPos] += newData[this.startPos];
        newData[this.startPos] = 0;
        // If we moved index n there is no need to check moves on index lower than
        // the lowest index affected by the move.
        int newStart;
        if (direction == +1) {
            // If we moved right the lowest index affected by the move is the index
            // plus 1.
            newStart = startPos + 1;
        } else {
            // If we were moving to the left the lowest index affected is the newPos
            // index.
            newStart = newPos;
        }
        this.positionTested = true;
        return new Pond(newData, newStart);
    }
    
    // Check if the current move is a valid move (pos and newPos inside array boundaries,
    // pos and newPos cannot be 0).
    private boolean canMove() {
        int newPos = this.startPos + this.direction * this.disposition[this.startPos];
        return this.startPos >= 0 && this.startPos < disposition.length && this.disposition[this.startPos] != 0 &&
                newPos >= 0 && newPos < disposition.length && this.disposition[newPos] != 0;
    }
    
    public int[] getDisposition() {
        return this.disposition;
    }

    public boolean isSolution() {
        return solution;
    }
}