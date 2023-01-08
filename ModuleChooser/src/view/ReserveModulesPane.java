package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ReserveModulesPane extends HBox {
	
		//buttons
		private Button btnAddTerm1;
		private Button btnAddTerm2;
		private Button btnRemoveTerm1;
		private Button btnRemoveTerm2;
		private Button btnConfirmTerm1;
		private Button btnConfirmTerm2;
	
		//labels
		private Label lblUnselectedTerm1modules;
		private Label lblReservedTerm1modules;
		private Label lblInfoTerm1;
		private Label lblUnselectedTerm2modules;
		private Label lblReservedTerm2modules;
		private Label lblInfoTerm2;
		private Label lblError1;
		private Label lblError2;
		
		//list views
		private ListView<String> lvUnselectedTerm1;
		private ListView<String> lvReservedTerm1;
		private ListView<String> lvUnselectedTerm2;
		private ListView<String> lvReservedTerm2;
		private Accordion accordion;
		
	public ReserveModulesPane() {
		
		accordion = new Accordion();
		
		// create labels
		lblUnselectedTerm1modules = new Label("Unselected Term 1 modules");
		lblReservedTerm1modules = new Label("Reserved Term 1 modules");
		lblInfoTerm1 = new Label("Reserve 30 credits worth of term 1 modules");
		lblUnselectedTerm2modules = new Label("Unselected Term 2 modules");
		lblReservedTerm2modules = new Label("Reserved Term 2 modules");
		lblInfoTerm2 = new Label("Reserve 30 credits worth of term 2 modules");
		lblError1 = new Label("");
		lblError2 = new Label("");
		
		//create listViews 
		lvUnselectedTerm1 = new ListView<>();
		lvReservedTerm1 = new ListView<>();	
		lvUnselectedTerm2 = new ListView<>();
		lvReservedTerm2 = new ListView<>();
		
		//create buttons
		btnAddTerm1 = new Button("Add");
		btnAddTerm2 = new Button("Add");
		btnRemoveTerm1 = new Button("Remove");
		btnRemoveTerm2 = new Button("Remove");
		btnConfirmTerm1 = new Button("Confirm");
		btnConfirmTerm2 = new Button("Confirm");
		
		// create layout boxes
		VBox pageTerm1 = new VBox();
		HBox top1 = new HBox();
		VBox top1unselected = new VBox();
		VBox top1reserved = new VBox();
		HBox bottom1 = new HBox();
		VBox pageTerm2 = new VBox();
		HBox top2 = new HBox();
		VBox top2unselected = new VBox();
		VBox top2reserved = new VBox();
		HBox bottom2 = new HBox();
		
		HBox errorBox1 = new HBox();
		HBox errorBox2 = new HBox();
		
		errorBox1.getChildren().add(lblError1);
		errorBox2.getChildren().add(lblError2);
		
		//Arrange the layout boxes page 1
		top1unselected.getChildren().addAll(
				lblUnselectedTerm1modules,
				lvUnselectedTerm1
				);
		
		top1reserved.getChildren().addAll(
				lblReservedTerm1modules,
				lvReservedTerm1
				);
		
		bottom1.getChildren().addAll(
				lblInfoTerm1,
				btnAddTerm1,
				btnRemoveTerm1,
				btnConfirmTerm1
				);
		
		top1.getChildren().addAll(
				top1unselected,
				top1reserved
				);
		
		pageTerm1.getChildren().addAll(top1, bottom1, errorBox1);
		

		//Arrange the layout boxes page 2
		top2unselected.getChildren().addAll(
				lblUnselectedTerm2modules,
				lvUnselectedTerm2
				);
		
		top2reserved.getChildren().addAll(
				lblReservedTerm2modules,
				lvReservedTerm2
				);
		
		bottom2.getChildren().addAll(
				lblInfoTerm2,
				btnAddTerm2,
				btnRemoveTerm2,
				btnConfirmTerm2
				);
		
		top2.getChildren().addAll(
				top2unselected,
				top2reserved
				);
		
		pageTerm2.getChildren().addAll(top2, bottom2, errorBox2);
		
		TitledPane pane1 = new TitledPane("Term 1 modules", pageTerm1);
		
		TitledPane pane2 = new TitledPane("Term 2 modules", pageTerm2);

		accordion.getPanes().add(pane1);
		accordion.getPanes().add(pane2);
		
		//lock accordion 1 and expand accordion 0
		accordion.getPanes().get(1).setDisable(true);
		
		//styling ********* new Insets(top, right, bottom, left) same as css
		accordion.prefHeightProperty().bind(this.heightProperty());
		accordion.prefWidthProperty().bind(this.widthProperty());
		
		top1unselected.prefWidthProperty().bind(this.widthProperty().divide(2));
		top1reserved.prefWidthProperty().bind(this.widthProperty().divide(2));
		top1unselected.prefHeightProperty().bind(pane1.heightProperty());
		top1reserved.prefHeightProperty().bind(pane1.heightProperty());
		top1unselected.setPadding(new Insets(20, 10, 20, 20));
		top1reserved.setPadding(new Insets(20, 20, 20, 10));

		top2unselected.prefWidthProperty().bind(this.widthProperty().divide(2));
		top2reserved.prefWidthProperty().bind(this.widthProperty().divide(2));
		top2unselected.prefHeightProperty().bind(pane2.heightProperty());
		top2reserved.prefHeightProperty().bind(pane2.heightProperty());
		top2unselected.setPadding(new Insets(20, 10, 20, 20));
		top2reserved.setPadding(new Insets(20, 20, 20, 10));
		
		// 80% height of its parent
		lvUnselectedTerm1.prefHeightProperty().bind(top1unselected.heightProperty().multiply(0.8));
		lvReservedTerm1.prefHeightProperty().bind(top1reserved.heightProperty().multiply(0.8));

		lvUnselectedTerm2.prefHeightProperty().bind(top2unselected.heightProperty().multiply(0.8));
		lvReservedTerm2.prefHeightProperty().bind(top2reserved.heightProperty().multiply(0.8));

		bottom1.setAlignment(Pos.TOP_CENTER);
		bottom2.setAlignment(Pos.TOP_CENTER);
		
		bottom1.setMargin(lblInfoTerm1, new Insets(10, 10, 10, 0));
		bottom1.setMargin(btnAddTerm1, new Insets(10, 10, 10, 10));
		bottom1.setMargin(btnRemoveTerm1, new Insets(10, 10, 10, 10));
		bottom1.setMargin(btnConfirmTerm1, new Insets(10, 0, 10, 10));
		
		bottom2.setMargin(lblInfoTerm2, new Insets(10, 10, 10, 0));
		bottom2.setMargin(btnAddTerm2, new Insets(10, 10, 10, 10));
		bottom2.setMargin(btnRemoveTerm2, new Insets(10, 10, 10, 10));
		bottom2.setMargin(btnConfirmTerm2, new Insets(10, 0, 10, 10));
		
		lblError1.setTextFill(Color.RED);
		lblError2.setTextFill(Color.RED);
		
		this.getChildren().addAll(accordion);
		
		}

	//*********************** Errors ********************************
	
	public void setError1(String error) {
		lblError1.setText(error);
	}
	
	public void setError2(String error) {
		lblError2.setText(error);
	}
	
	//*********************** Unselected listViews *********************
	
	public String getUnselectedTerm1ListViewSelection() {
		return lvUnselectedTerm1.getSelectionModel().getSelectedItem();
	}
	
	public String getUnselectedTerm2ListViewSelection() {
		return lvUnselectedTerm2.getSelectionModel().getSelectedItem();
	}
	
	public void addToUnselectedTerm1ListView(String item) {
		lvUnselectedTerm1.getItems().add(item);
	}
	
	public void addToUnselectedTerm2ListView(String item) {
		lvUnselectedTerm2.getItems().add(item);
	}
	
	public void removeUnselectedTerm1ListViewElement(String item) {
		lvUnselectedTerm1.getItems().remove(item);
	}
	
	public void removeUnselectedTerm2ListViewElement(String item) {
		lvUnselectedTerm2.getItems().remove(item);
	}
	
	public ObservableList<String> getAllElementsFromUnselectedReservedTerm1ListView() {
		return lvUnselectedTerm1.getItems();
	}
	
	public ObservableList<String> getAllElementsFromUnselectedReservedTerm2ListView() {
		return lvUnselectedTerm2.getItems();
	}
	
	//************************* Reserved listViews **************************
	public String getReservedTerm1ListViewSelection() {
		return lvReservedTerm1.getSelectionModel().getSelectedItem();
	}
	
	public String getReservedTerm2ListViewSelection() {
		return lvReservedTerm2.getSelectionModel().getSelectedItem();
	}
	
	public void addToReservedTerm1ListView(String item) {
		lvReservedTerm1.getItems().add(item);
	}
	
	public void addToReservedTerm2ListView(String item) {
		lvReservedTerm2.getItems().add(item);
	}
	
	public void removeFromReservedTerm1ListView(String item) {
		lvReservedTerm1.getItems().remove(item);
	}
	
	public void removeFromReservedTerm2ListView(String item) {
		lvReservedTerm2.getItems().remove(item);
	}
	
	public ObservableList<String> getAllElementsFromReservedTerm1ListView() {
		return lvReservedTerm1.getItems();
	}
	
	public ObservableList<String> getAllElementsFromReservedTerm2ListView() {
		return lvReservedTerm2.getItems();
	}
	
	//*************************** Accordion stuff **************************
	public void unlockTerm2AccordionPage() {
		accordion.getPanes().get(1).setDisable(false);
		accordion.getPanes().get(0).setExpanded(false);
		accordion.getPanes().get(1).setExpanded(true);
	}
	
	public void expandAccordionPage0() {
		accordion.getPanes().get(0).setExpanded(true);
	}
	
	// ************************ Add handlers to buttons ********************
	
	public void addbtnAddTerm1Handler(EventHandler<ActionEvent> handler) {
		btnAddTerm1.setOnAction(handler);
	}
	
	public void addbtnAddTerm2Handler(EventHandler<ActionEvent> handler) {
		btnAddTerm2.setOnAction(handler);
	}
	
	public void addbtnRemoveTerm1Handler(EventHandler<ActionEvent> handler) {
		btnRemoveTerm1.setOnAction(handler);
	}
	
	public void addbtnRemoveTerm2Handler(EventHandler<ActionEvent> handler) {
		btnRemoveTerm2.setOnAction(handler);
	}
	
	public void addbtnConfirmTerm1Handler(EventHandler<ActionEvent> handler) {
		btnConfirmTerm1.setOnAction(handler);
	}
	
	public void addbtnConfirmTerm2Handler(EventHandler<ActionEvent> handler) {
		btnConfirmTerm2.setOnAction(handler);
	}
}
	
