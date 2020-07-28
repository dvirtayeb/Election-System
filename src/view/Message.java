package view;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Message {
	Label lbl;
	ArrayList<Label> allLabels;
	VBox vb;

	public Message(Stage primaryStage) {

		lbl = new Label();
		allLabels = new ArrayList<>();

		vb = new VBox();
		vb.getChildren().addAll(allLabels);

		Scene scene = new Scene(vb, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setMessage(String str) {
		lbl.setText(str);
	}

	public void addMessage(String str) {
		allLabels.add(new Label(str));
		vb.getChildren().clear();
		vb.getChildren().addAll(allLabels);
	}
}
