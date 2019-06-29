package game.players;

import java.util.ArrayList;
import java.util.List;

public class PlayerA {

    private List<Integer> elements;
    private int score;
    private int[] terminals;
    private int reply;

    public PlayerA() {
        elements = new ArrayList<Integer>();
        score = 0;
    }

    public void getTerminals(int[] terminals) {
        this.terminals = terminals;
    }

    private void chooseNumber() {
        if (terminals[0] > terminals[1]) {
            reply = 0;
        } else {
            reply = 1;
        }

        if (elements.contains(terminals[reply])) {
            reply = -1;
        } else {
            elements.add(terminals[reply]);
            score += terminals[reply];
        }
    }

    public int getReply() {
        chooseNumber();
        return reply;
    }

    public int getScore() {
        return score;
    }

    public void newMatch() {
        score = 0;
        elements.clear();
    }
}
