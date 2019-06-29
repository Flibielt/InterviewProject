package game.field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Field {

    private List<Integer> numbers = new ArrayList<Integer>();

    public void createSequence() {
        for (int i = 0; i < 1000; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt(1, 10000));
        }
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
