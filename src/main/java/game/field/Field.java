package game.field;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field {

    private List<Integer> numbers = new ArrayList<Integer>();

    private void createSequence() {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            numbers.add(rand.nextInt(9998) + 1);
        }
    }

    public void newSequence() {
        numbers.clear();
        createSequence();
    }

    public int[] getTerminals() {
        int[] terminals = new int[2];
        terminals[0] = numbers.get(0);
        terminals[1] = numbers.get(numbers.size() - 1);
        return terminals;
    }

    public void deleteElement(boolean first) {
        if (first) {
            numbers.remove(0);
        } else {
            numbers.remove(numbers.size() - 1);
        }
    }

    public boolean isEmpty() {
        return numbers.isEmpty();
    }
}
