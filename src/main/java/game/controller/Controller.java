package game.controller;

import game.field.Field;
import game.players.PlayerA;
import game.players.PlayerB;

public class Controller {

    private Field field;
    private PlayerA playerA;
    private PlayerB playerB;
    private int[] results;

    private Controller() {
        field = new Field();
        playerA = new PlayerA();
        playerB = new PlayerB();
        results = new int[3];
    }

    private void sendTerminals() {
        int[] terminals = field.getTerminals();

        playerA.getTerminals(terminals);
        playerB.getTerminals(terminals);
    }

    private void newGame() {
        field.createSequence();
    }

    private boolean isFieldEmpty() {
        return field.isEmpty();
    }

    private void deleteFieldElement(int position) {
        field.deleteElement(position == 0);
    }

    public void nextRound() {
        sendTerminals();
        deleteFieldElement(playerA.getReply());
        deleteFieldElement(playerB.getReply());
    }

    public void analyzeMatch() {
        if (playerA.getScore() > playerB.getScore()) {
            results[0]++;
        } else if (playerA.getScore() < playerB.getScore()) {
            results[2]++;
        } else {
            results[1]++;
        }
    }

    public void writeResults() {
        System.out.println("PlayerA wins: " + results[0]);
        System.out.println("Draw: " + results[1]);
        System.out.println("PlayerB wins: " + results[2]);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        PlayerA playerA = new PlayerA();
        PlayerB playerB = new PlayerB();

        for (int i = 0; i < 10; i++) {
            controller.newGame();

            while (!controller.isFieldEmpty()) {
                controller.nextRound();
            }

            controller.analyzeMatch();
        }

        controller.writeResults();
    }
}
