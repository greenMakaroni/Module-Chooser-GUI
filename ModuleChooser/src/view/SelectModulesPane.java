package view;

import javafx.collections.ObservableList;
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
import javafx.scene.text.Text;

public class SelectModulesPane extends HBox {
	
	// buttons
	private Button btnAddTerm1;
	private Button btnRemoveTerm1;
	private Button btnAddTerm2;
	private Button btnRemoveTerm2;
	private Button btnReset;
	private Button btnSubmit;
	
	//labels
	private Label lblSelectedYearLongModules;
	private Label lblUnselectedTerm1Modules;
	private Label lblUnselectedTerm2Modules;
	private Label lblTerm1;
	private Label lblTerm2;
	private Label lblSelectedTerm1Modules;
	private Label lblSelectedTerm2Modules;
	private Label lblCurrentTerm1Credits;
	private Label lblCurrentTerm2Credits;
	private Label lblError;
	
	//list views
	private ListView<String> lvUnselectedTerm1ModuleslistView;
	private ListView<String> lvUnselectedTerm2ModuleslistView;
	private ListView<String> lvSelectedYearLongModules;
	private ListView<String> lvSelectedTerm1Modules;
	private ListView<String> lvSelectedTerm2Modules;
	private Text lvCurrentTerm1Credits;
	private Text lvCurrentTerm2Credits;
	
	
	public SelectModulesPane() {
				
		//creating labels
		lblSelectedYearLongModules = new Label("Selected Year Long modules");
		lblUnselectedTerm1Modules = new Label("Unselected Term 1 modules");	
		lblUnselectedTerm2Modules = new Label("Unselected Term 2 modules");
		lblTerm1 = new Label("Term 1");
		lblTerm2 = new Label("Term 2");
		lblSelectedTerm1Modules = new Label("Selected Term 1 modules");
		lblSelectedTerm2Modules = new Label("Selected Term 2 modules");
		lblCurrentTerm1Credits = new Label("Current term 1 credits: ");
		lblCurrentTerm2Credits = new Label("Current term 2 credits: ");
		lblError = new Label("");
		
		//creating buttons
		 btnAddTerm1 = new Button("Add");
		 btnAddTerm2 = new Button("Add");
		 btnRemoveTerm1 = new Button("Remove");
		 btnRemoveTerm2 = new Button("Remove");
		 btnReset = new Button("Reset");
		 btnSubmit = new Button("Submit");
		
		//creating list views
		lvSelectedYearLongModules = new ListView<>();
		lvUnselectedTerm1ModuleslistView = new ListView<>();
		lvUnselectedTerm2ModuleslistView = new ListView<>();
		lvSelectedTerm1Modules = new ListView<>();
		lvSelectedTerm2Modules = new ListView<>();
		lvCurrentTerm1Credits = new Text("0");
		lvCurrentTerm2Credits = new Text("0");
		
		//error label bottombox
		HBox errorBox = new HBox();
		
		//creating layout boxes
		VBox leftVBox = new VBox();
		VBox rightVBox = new VBox();

		HBox leftButtonsBox1 = new HBox();
		HBox leftButtonsBox2 = new HBox();
		
		HBox leftCurrentTerm1Credits = new HBox();
		HBox rightCurrentTerm2Credits = new HBox();
		
		HBox resetButtonBox = new HBox();
		HBox submitButtonBox = new HBox();
				
		resetButtonBox.getChildren().addAll(lblError, btnReset);
		submitButtonBox.getChildren().add(btnSubmit);
		
		leftButtonsBox1.getChildren().addAll(
				lblTerm1,
				btnAddTerm1,
				btnRemoveTerm1
				);
		
		leftButtonsBox2.getChildren().addAll(
				lblTerm2,
				btnAddTerm2,
				btnRemoveTerm2
				);
		
		leftCurrentTerm1Credits.getChildren().addAll(
				lblCurrentTerm1Credits,
				lvCurrentTerm1Credits
				);
		
		rightCurrentTerm2Credits.getChildren().addAll(
				lblCurrentTerm2Credits,
				lvCurrentTerm2Credits
				);
		
		leftVBox.getChildren().addAll(
				lblUnselectedTerm1Modules,
				lvUnselectedTerm1ModuleslistView,		
				leftButtonsBox1,
				lblUnselectedTerm2Modules,
				lvUnselectedTerm2ModuleslistView,
				leftButtonsBox2,
				leftCurrentTerm1Credits,
				resetButtonBox
				);
		
		rightVBox.getChildren().addAll(
				lblSelectedYearLongModules,
				lvSelectedYearLongModules,	
				lblSelectedTerm1Modules,
				lvSelectedTerm1Modules,		
				lblSelectedTerm2Modules,
				lvSelectedTerm2Modules,
				rightCurrentTerm2Credits,
				submitButtonBox
				);
		
		// **************** styling **********************
		lblError.setTextFill(Color.RED);
		
		leftVBox.prefWidthProperty().bind(this.widthProperty().divide(2));
		leftVBox.prefHeightProperty().bind(this.heightProperty());
		rightVBox.prefWidthProperty().bind(this.widthProperty().divide(2));
		rightVBox.prefHeightProperty().bind(this.heightProperty());

		
		this.setPadding(new Insets(20, 20, 20, 20));
		
		
		leftVBox.setMargin(lvUnselectedTerm1ModuleslistView, new Insets(0, 10, 20, 0));
		leftVBox.setMargin(lvUnselectedTerm2ModuleslistView, new Insets(0, 10, 20, 0));
		
		lvUnselectedTerm1ModuleslistView.prefHeightProperty().bind(this.heightProperty().divide(3));
		lvUnselectedTerm2ModuleslistView.prefHeightProperty().bind(this.heightProperty().divide(3));
		
		lvSelectedYearLongModules.prefHeightProperty().bind(this.heightProperty().divide(5));
		lvSelectedTerm1Modules.prefHeightProperty().bind(this.heightProperty().divide(3));
		lvSelectedTerm2Modules.prefHeightProperty().bind(this.heightProperty().divide(3));
		
		rightVBox.setMargin(lvSelectedYearLongModules, new Insets(0, 0, 20, 0));		
		rightVBox.setMargin(lvSelectedTerm1Modules, new Insets(0, 0, 20, 0));
		rightVBox.setMargin(lvSelectedTerm2Modules, new Insets(0, 0, 20, 0));
		
		
		leftButtonsBox1.setAlignment(Pos.CENTER);
		leftButtonsBox1.setMargin(btnAddTerm1, new Insets(0, 10, 20, 10));
		leftButtonsBox1.setMargin(btnRemoveTerm1, new Insets(0, 10, 20, 10));
		leftButtonsBox1.setMargin(lblTerm1, new Insets(0, 10, 20, 10));
		
		leftButtonsBox2.setAlignment(Pos.CENTER);
		leftButtonsBox2.setMargin(btnAddTerm2, new Insets(0, 20, 20, 20));
		leftButtonsBox2.setMargin(btnRemoveTerm2, new Insets(0, 10, 20, 10));
		leftButtonsBox2.setMargin(lblTerm2, new Insets(0, 10, 20, 10));
		
		
		rightCurrentTerm2Credits.setAlignment(Pos.CENTER);
		leftCurrentTerm1Credits.setAlignment(Pos.CENTER);

		rightCurrentTerm2Credits.setMargin(lvCurrentTerm2Credits, new Insets(20, 0, 0, 10));
		rightCurrentTerm2Credits.setMargin(lblCurrentTerm2Credits, new Insets(20, 10, 0, 0));
		
		leftCurrentTerm1Credits.setMargin(lvCurrentTerm1Credits, new Insets(20, 0, 0, 10));
		leftCurrentTerm1Credits.setMargin(lblCurrentTerm1Credits, new Insets(20, 10, 0, 0));
		
		lvCurrentTerm1Credits.setStyle("-fx-font-size: 22px");
		lvCurrentTerm2Credits.setStyle("-fx-font-size: 22px");
		lvCurrentTerm1Credits.setFill(Color.RED);
		lvCurrentTerm2Credits.setFill(Color.RED);
		
		submitButtonBox.setAlignment(Pos.CENTER_LEFT);
		resetButtonBox.setAlignment(Pos.CENTER_RIGHT);
		submitButtonBox.setMargin(btnSubmit, new Insets(20, 0, 0, 10));
		resetButtonBox.setMargin(btnReset, new Insets(20, 10, 0, 20));
		leftCurrentTerm1Credits.autosize();
		leftCurrentTerm1Credits.prefWidthProperty().bind(this.widthProperty().divide(10));
		
		
		//Add all to SelectModulesPane
		this.getChildren().addAll(
				leftVBox,
				rightVBox
				);
		
		}
	
	// Error
	public void setErrorLabel(String error) {
		lblError.setText(error);
	}
	
	//***************** Unselected ListViews *********************
	public String getUnselectedTerm1ListViewSelection() {
		return lvUnselectedTerm1ModuleslistView.getSelectionModel().getSelectedItem();
	}
	
	public String getUnselectedTerm2ListViewSelection() {
		return lvUnselectedTerm2ModuleslistView.getSelectionModel().getSelectedItem();
	}
	
	public void addToUnselectedTerm1ListView(String item) {
		lvUnselectedTerm1ModuleslistView.getItems().add(item);
	}
	
	public void addToUnselectedTerm2ListView(String item) {
		lvUnselectedTerm2ModuleslistView.getItems().add(item);
	}
	
	public void removeFromUnselectedTerm1ListView(Object o) {
		lvUnselectedTerm1ModuleslistView.getItems().remove(o);
	}
	
	public void removeFromUnselectedTerm2ListView(Object o) {
		lvUnselectedTerm2ModuleslistView.getItems().remove(o);
	}
	
	//***************** Selected ListViews *************************************************
	
	public String getSelectedTerm1ListViewSelection() {
		return lvSelectedTerm1Modules.getSelectionModel().getSelectedItem();
	}
	
	public String getSelectedTerm2ListViewSelection() {
		return lvSelectedTerm2Modules.getSelectionModel().getSelectedItem();
	}
	
	public void addToSelectedTerm1ListView(String item) {
		lvSelectedTerm1Modules.getItems().add(item);
	}
	
	public void addToSelectedTerm2ListView(String item) {
		lvSelectedTerm2Modules.getItems().add(item);
	}
	
	public ObservableList<String> getAllElementsFromSelectedTerm1ListView() {
		return lvSelectedTerm1Modules.getItems();
	}
	
	public ObservableList<String> getAllElementsFromSelectedTerm2ListView() {
		return lvSelectedTerm2Modules.getItems();
	}
	
	
	public void removeFromSelectedTerm1ListView(Object o) {
		lvSelectedTerm1Modules.getItems().remove(o);
	}
	
	public void removeFromSelectedTerm2ListView(Object o) {
		lvSelectedTerm2Modules.getItems().remove(o);
	}
	
	// ******************* CurrentTermCredits ListViews *************************

	public void changeColorOfCreditsText1(boolean is60) {
		if(is60 == true) {
			lvCurrentTerm1Credits.setFill(Color.GREEN);
		}
		else {
			lvCurrentTerm1Credits.setFill(Color.RED);
		}
		
	}
	
	public void changeColorOfCreditsText2(boolean is60) {
		if(is60 == true) {
			lvCurrentTerm2Credits.setFill(Color.GREEN);
		}
		else {
			lvCurrentTerm2Credits.setFill(Color.RED);
		}
		
	}
	
	public String getCurrentValueOfCurrentTerm1CreditsListView() {
		return lvCurrentTerm1Credits.getText();
	}
	
	public String getCurrentValueOfCurrentTerm2CreditsListView() {
		return lvCurrentTerm2Credits.getText();
	}
	
	public void addToCurrentTerm1CreditsListView(String item) {
			Integer tempCurrent = Integer.parseInt(lvCurrentTerm1Credits.getText()) + Integer.parseInt(item);
			lvCurrentTerm1Credits.setText(Integer.toString(tempCurrent));
	}
	
	public void addToCurrentTerm2CreditsListView(String item) {
		Integer tempCurrent = Integer.parseInt(lvCurrentTerm2Credits.getText()) + Integer.parseInt(item);
		lvCurrentTerm2Credits.setText(Integer.toString(tempCurrent));
	}
	
	public void subFromCurrentTerm1CreditsListView(String item) {
		
		Integer newValue = Integer.parseInt(lvCurrentTerm1Credits.getText()) - Integer.parseInt(item);
		
		lvCurrentTerm1Credits
		.setText(Integer.toString(newValue));
	}
	
	public void subFromCurrentTerm2CreditsListView(String item) {
		Integer newValue = Integer.parseInt(lvCurrentTerm2Credits.getText()) - Integer.parseInt(item);
		lvCurrentTerm2Credits
		.setText(Integer.toString(newValue));
	}
	
	public void resetAll() {
		lvUnselectedTerm1ModuleslistView.getItems().clear();
		lvUnselectedTerm2ModuleslistView.getItems().clear();
		lvSelectedYearLongModules.getItems().clear();
		lvSelectedTerm1Modules.getItems().clear();
		lvSelectedTerm2Modules.getItems().clear();
		lvCurrentTerm1Credits.setText("0");
		lvCurrentTerm2Credits.setText("0");
	}
	
	//********************** YEAR LONG MODULES listView **********************
	
	public void addToYearLongListView(String item) {
		lvSelectedYearLongModules.getItems().add(item);
	}
	
	public ObservableList<String> getAllElementsFromSelectedYearLongListView() {
		return lvSelectedYearLongModules.getItems();
	}
	
	//*************************** Attach button handlers **************************
			
	public void addbtnAddTerm1Handler(EventHandler<ActionEvent> handler) {
		btnAddTerm1.setOnAction(handler);
	}
	
	public void addbtnRemoveTerm1Handler(EventHandler<ActionEvent> handler) {
		btnRemoveTerm1.setOnAction(handler);
	}
	
	public void addbtnAddTerm2Handler(EventHandler<ActionEvent> handler) {
		btnAddTerm2.setOnAction(handler);
	}
	
	public void addbtnRemoveTerm2Handler(EventHandler<ActionEvent> handler) {
		btnRemoveTerm2.setOnAction(handler);
	}
	
	public void addbtnResetHandler(EventHandler<ActionEvent> handler) {
		btnReset.setOnAction(handler);
	}
	
	public void addbtnSubmit(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);
	}
	
}
	
