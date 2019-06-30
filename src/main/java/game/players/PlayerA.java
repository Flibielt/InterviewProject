package game.players;

import java.util.ArrayList;
import java.util.List;

/**
 * The algorithm of the {@link PlayerA}.
 */
public class PlayerA {

    /**
     * The elements that has been chosen.
     */
    private List<Integer> elements;
    /**
     * The sum of the chosen elements.
     */
    private int score;
    /**
     * The terminals of the current sequence.
     */
    private int[] terminals;
    /**
     * The reply of the current round, 0 if the first terminal, else 1.
     */
    private int reply;

    /**
     * The constructor of the {@link PlayerA}.
     */
    public PlayerA() {
        elements = new ArrayList<Integer>();
        score = 0;
    }

    /**
     * Sets the current terminals.
     *
     * @param terminals the current terminals
     */
    public void setTerminals(int[] terminals) {
        this.terminals = terminals;
    }

    /**
     * Chooses the biggest number from the terminals.
     */
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

    /**
     * Gives the reply for the current round.
     *
     * @return 0, if the first, 1, if the last element
     */
    public int getReply() {
        chooseNumber();
        return reply;
    }

    /**
     * Gives the current score of the player.
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player's data to default.
     */
    public void newMatch() {
        score = 0;
        elements.clear();
    }
}
