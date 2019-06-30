package game.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Contains the current sequence.
 */
public class Field {

    /**
     * The current sequence.
     */
    private List<Integer> numbers = new ArrayList<Integer>();

    /**
     * Creates a new sequence.
     */
    private void createSequence() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            numbers.add(rand.nextInt(9998) + 1);
        }
    }

    /**
     * Deletes the previous sequence and creates a new one.
     */
    public void newSequence() {
        numbers.clear();
        createSequence();
    }

    /**
     * Gives the terminals of the current sequence.
     *
     * @return the terminals
     */
    public int[] getTerminals() {
        int[] terminals = new int[2];
        terminals[0] = numbers.get(0);
        terminals[1] = numbers.get(numbers.size() - 1);
        return terminals;
    }

    /**
     * Deletes a terminal element from the current sequence.
     *
     * @param first if {@code true}, then deletes the first, else the last element
     */
    public void deleteElement(boolean first) {
        if (first) {
            numbers.remove(0);
        } else {
            numbers.remove(numbers.size() - 1);
        }
    }

    /**
     * Checks if the current sequence is empty.
     *
     * @return {@code true}, if the sequence is empty
     */
    public boolean isEmpty() {
        return numbers.isEmpty();
    }
}
