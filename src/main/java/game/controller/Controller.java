package game.controller;

import game.field.Field;
import game.players.PlayerA;
import game.players.PlayerB;

import java.util.Random;

public class Controller {

    private Field field;
    private PlayerA playerA;
    private PlayerB playerB;
    private int[] results;
    private boolean gameCanContinue;

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
        field.newSequence();
        gameCanContinue = true;
    }

    private boolean isFieldEmpty() {
        return field.isEmpty();
    }

    private void deleteFieldElement(int position) {
        field.deleteElement(position == 0);
        if (isFieldEmpty()) {
            gameCanContinue = false;
        }
    }

    public boolean canGameContinue() {
        return gameCanContinue;
    }

    public void nextRound(boolean bStart) {
        int[] terminals = field.getTerminals();
        if (bStart) {
            playerBStep();
            playerAStep();
        } else {
            playerAStep();
            playerBStep();
        }

        if (!isFieldEmpty()) {
            if (terminals[0] == field.getTerminals()[0]) {
                if (terminals[1] == field.getTerminals()[1]) {
                    deleteFieldElement(0);
                    if (!isFieldEmpty()) {
                        deleteFieldElement(1);
                    }
                }
            }
        }
    }

    private void playerAStep() {
        if (gameCanContinue) {
            sendTerminals();
            int reply = playerA.getReply();
            if (reply != -1) {
                deleteFieldElement(reply);
            }
        }
    }

    private void playerBStep() {
        if (gameCanContinue) {
            sendTerminals();
            int reply = playerB.getReply();
            if (reply != -1) {
                deleteFieldElement(reply);
            }
        }
    }

    public void analyzeMatch() {
        if (playerA.getScore() > playerB.getScore()) {
            results[0]++;
        } else if (playerA.getScore() < playerB.getScore()) {
            results[2]++;
        } else {
            results[1]++;
        }
        playerA.newMatch();
        playerB.newMatch();
    }

    public void writeResults() {
        System.out.println("PlayerA wins: " + results[0]);
        System.out.println("Draw: " + results[1]);
        System.out.println("PlayerB wins: " + results[2]);
        double percentage = ((double)results[2] / ((double)results[0] + (double)results[1] + (double)results[2])) * 100;
        System.out.println("PlayerB wins: " + percentage + "%");
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        boolean bStart;
        Random rand = new Random();

        for (int i = 0; i < 1000; i++) {
            controller.newGame();
            bStart = rand.nextBoolean();

            while (!controller.isFieldEmpty() && controller.canGameContinue()) {
                controller.nextRound(bStart);
            }
            controller.analyzeMatch();
        }

        controller.writeResults();
    }
}
