package gui;

import game.controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartWindow extends Application {

    private Controller controller;

    @Override
    public void start(Stage stage) throws Exception {
        controller = new Controller();
        stage.setTitle("Simulation");
        stage.setResizable(false);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);

        Button startButton = new Button("START SIMULATION");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.startSimulation();
                setStatistic(stage);
            }
        });
        vBox.getChildren().add(startButton);

        stage.setScene(new Scene(vBox, 200, 400));
        stage.show();
    }

    private void setStatistic(Stage stage) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(15);
        HBox[] milestones = new HBox[6];
        Text[][] matches = new Text[6][2];
        for (int i = 0; i < 6; i++) {
            matches[i][0] = new Text((int)Math.pow(10, i + 1) + ".: ");
            matches[i][1] = new Text();
            milestones[i] = new HBox();
            milestones[i].setAlignment(Pos.CENTER);
            milestones[i].setSpacing(15);
            milestones[i].getChildren().addAll(matches[i][0], matches[i][1]);
            vBox.getChildren().add(milestones[i]);
        }

        Text statisticsTitle = new Text("PlayerB's statistic");
        HBox statistic = new HBox();
        statistic.setAlignment(Pos.CENTER);
        statistic.setSpacing(15);
        Text[] stat = new Text[3];
        for (int i = 0; i < 3; i++) {
            stat[i] = new Text();
        }
        stat[0].setText("B win:" );
        stat[1].setText("Draw: ");
        stat[2].setText("B lose: ");
        vBox.getChildren().addAll(statisticsTitle, statistic);
        stage.setScene(new Scene(vBox, 200, 400));
    }
}
