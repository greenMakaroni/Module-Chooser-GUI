package view;


import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AboutPage extends VBox {
	
	private Label label;
	private Text text;
	public AboutPage() {
		
		label = new Label("About Page");
		text = new Text(
				"Create Profile tab\r\n" + 
				"This section creates user profile\n " +
				"Student profile will be created based on input from the form \n" +
				"Don't leave any fields empty!\n" +
				"Press 'Create Profile' button to create Student Profile\n" +
				"The program should navigate to the next section 'Select Modules'\n\n " + 
						
				"Select Modules tab\r\n" + 
				"This section enables user to select modules for term 1 and term 2 \n" +
				"The number of module credits per term needs to be 60 \n" +
				"Select module from the unselected list and press 'add' button to move it to selected list \n" +
				"Select module from the selected list and press 'remove' button to move it back to unselected list \n" +
				"Press 'Reset' to reset entire page \n" +
				"Press Submit to confirm your choices \n\n" +

				"Reserve Modules tab\r\n" + 
				"This section enables user to select reserved modules for term 1 and term 2 \n" +
				"Select 2 modules for each term \n" +
				"Select module from the list of unselected modules, and press 'add' button to add it to reserved modules list \n" + 
				"Select module from the selected list, press 'remove' to remove it \n" +
				"Press Confirm button to confirm your choices \n\n" +
				
				"Overview Selection tab\r\n" + 
				"This section displays Student Profile details and all selected options\n" + 
				
				"Press 'Save Overview' button to save Student Profile to a file");
		text.setStyle("-fx-font-size: 16px");
		HBox box = new HBox();
		box.getChildren().add(text);
		box.prefWidthProperty().bind(this.widthProperty());
		this.getChildren().add(box);
	}
	
}
	
