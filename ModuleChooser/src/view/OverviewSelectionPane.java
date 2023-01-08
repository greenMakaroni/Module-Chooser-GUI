package view;

import java.util.Collection;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OverviewSelectionPane extends VBox {
	
	private ListView<String> lvProfile;
	private ListView<String> lvSelected;
	private ListView<String> lvReserved;
	private Button btnSave;
	private Label lblConfirmSave;
	
	public OverviewSelectionPane() {
			
			lvProfile = new ListView();
			lvSelected = new ListView();
			lvReserved = new ListView();
			btnSave = new Button("Save Overview");
			lblConfirmSave = new Label("");
			
			HBox bottomBox = new HBox();	
			bottomBox.getChildren().addAll(lvSelected, lvReserved);
			
			HBox buttonBox = new HBox();
			buttonBox.getChildren().add(btnSave);
			
			HBox labelBox = new HBox();
			labelBox.getChildren().add(lblConfirmSave);
			
			//Styling
			lblConfirmSave.setTextFill(Color.GREEN);
			lvSelected.prefWidthProperty().bind(bottomBox.widthProperty().divide(2));
			lvReserved.prefWidthProperty().bind(bottomBox.widthProperty().divide(2));
			
			this.setMargin(lvProfile, new Insets(0, 0, 20, 0));
			bottomBox.setMargin(lvSelected, new Insets(0, 10, 20, 0));
			bottomBox.setMargin(lvReserved, new Insets(0, 0, 20, 10));

			buttonBox.prefWidthProperty().bind(this.widthProperty());
			buttonBox.setAlignment(Pos.CENTER);
			
			this.setPadding(new Insets(20, 20, 20, 20));
			
			bottomBox.prefWidthProperty().bind(this.widthProperty());
			
			this.getChildren().addAll(
					lvProfile,
					bottomBox,
					buttonBox,
					labelBox
					);
		}
	
	public void setConfirmLabelText(String message) {
		lblConfirmSave.setText(message);
	}
	
	public void addElementsToProfileListView(List<String> items) {
		lvProfile.getItems().addAll(items);
	}
	
	public void addSingleElementToProfileListView(String item) {
		lvProfile.getItems().add(item);
	}
	
	public void addElementToSelectedListView(String item) {
		lvSelected.getItems().add(item);
	}
	
	public void addElementToReservedListView(String item) {
		lvReserved.getItems().add(item);
	}
	
	//****************************** Button handler *************************************************
	public void addbtnSaveHandler(EventHandler<ActionEvent> handler) {
		btnSave.setOnAction(handler);
	}
	
}
	
