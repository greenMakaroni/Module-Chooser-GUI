package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import model.Course;
import model.Schedule;
import model.Module;
import model.StudentProfile;
import view.ModuleChooserRootPane;
import view.AboutPage;
import view.CreateStudentProfilePane;
import view.ModuleChooserMenuBar;
import view.SelectModulesPane;
import view.OverviewSelectionPane;
import view.ReserveModulesPane;

public class ModuleChooserController {

	//fields to be used throughout class
	private ModuleChooserRootPane view;
	
	private StudentProfile model;
	
	private CreateStudentProfilePane cspp;
	private ModuleChooserMenuBar mstmb;
	private SelectModulesPane smp;
	private ReserveModulesPane rmp;
	private OverviewSelectionPane osp;
	private AboutPage about;
	
	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		smp = view.getSelectModulesPane();
		rmp = view.getReserveModulesPane();
		osp = view.getOverviewSelectionPane();
		about = view.getAboutPage();
		
		//add courses to combobox in create student profile pane using the generateAndGetCourses helper method below
		
		cspp.addCoursesToComboBox(generateAndGetCourses());

		//attach event handlers to view using private helper method
		this.attachEventHandlers();	
	}

	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the CREATE STUDENT PROFILE PANE
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		
		//attach an event handler to the SELECT MODULES PANE
		smp.addbtnAddTerm1Handler(new SelectModulesAddBtn1());
		smp.addbtnAddTerm2Handler(new SelectModulesAddBtn2());
		smp.addbtnRemoveTerm1Handler(new SelectModulesRemoveBtn1());
		smp.addbtnRemoveTerm2Handler(new SelectModulesRemoveBtn2());
		smp.addbtnResetHandler(new SelectModulesResetBtn());
		smp.addbtnSubmit(new SelectModulesSubmitBtn());
		
		//attach an event handler to the RESERVE MODULES PANE
		rmp.addbtnAddTerm1Handler(new ReserveModulesTerm1Addbtn());
		rmp.addbtnAddTerm2Handler(new ReserveModulesTerm2Addbtn());
		rmp.addbtnRemoveTerm1Handler(new ReserveModulesTerm1RemoveBtn());
		rmp.addbtnRemoveTerm2Handler(new ReserveModulesTerm2RemoveBtn());
		rmp.addbtnConfirmTerm1Handler(new ReserveModulesConfirmBtn1());
		rmp.addbtnConfirmTerm2Handler(new ReserveModulesConfirmBtn2());
		
		//attach an event handler to the OVERVIEW SELECTION PANE
		osp.addbtnSaveHandler(new OverviewBtn());
		
		//attach an event handler to the MENU BAR
		mstmb.addAboutHandler(new OpenAboutPage());
		mstmb.addSaveHandler(new SaveMenuHandler());
		mstmb.addExitHandler(e -> System.exit(0));
		mstmb.addLoadHandler(new LoadMenuHandler());
	}
	
	
	
	// *********************** CreateStudentProfilePane handler ***********************************
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			String validationErrorString = validateInput(
							cspp.getStudentPnumber(),
							cspp.getStudentEmail(),
							cspp.getStudentName().getFirstName(),
							cspp.getStudentName().getFamilyName(),
							cspp.getStudentDate()
						);
				
			if(validationErrorString != "") {
				if(validationErrorString.contains(" Please enter p number! ") == true) {
					cspp.setErrorPnumber(" Please enter p number! ");
				}
				else {
					cspp.setErrorPnumber("");
				}
				
				if(validationErrorString.contains(" Please enter email! ") == true) {
					cspp.errorEmail(" Please enter email! ");
				}
				else {
					cspp.errorEmail("");
				}
				
				if(validationErrorString.contains(" Please enter first name! ") == true) {
					cspp.errorFirstName(" Please enter first name! ");
				}
				else {
					cspp.errorFirstName("");
				}
				
				if(validationErrorString.contains(" Please enter last name! ") == true) {
					cspp.errorSurname(" Please enter last name! ");
				}
				else {
					cspp.errorSurname("");
				}
				
				if(validationErrorString.contains(" Please select date! ") == true) {
					cspp.errorDate(" Please select date! ");
				}
				else {
					cspp.errorDate("");
				}
				
			}
			else {
				model.setStudentPnumber(cspp.getStudentPnumber());
				model.setStudentEmail(cspp.getStudentEmail());
				model.setStudentName(cspp.getStudentName());
				model.setStudentCourse(cspp.getSelectedCourse());
				model.setSubmissionDate(cspp.getStudentDate());
				
				cspp.resetAllErrorLabels();
				
				// set initial state for select modules pane 
				smp.resetAll();
				setInitialStateOfSelectModulesPane();
				view.changeTab(1);
			}
		}
	}
	
	public String validateInput(String pNumber, String email, String firstName, String lastName, LocalDate date) {
		
		String error = "";
		
		if(pNumber.length() < 1) {
			error = error + " Please enter p number! ";
		}
		
		if(email.length() < 1) {
			error = error + " Please enter email! ";
		}
		
		if(firstName.length() < 1) {
			error = error + " Please enter first name! ";
		}
		
		if(lastName.length() < 1) {
			error = error + " Please enter last name! ";
		}
		
		if(date == null) {
			error = error + " Please select date! ";
		}

		return error;
	}
	
	
	// *********************** SelectModulesPane handlers ***********************************
	private class SelectModulesAddBtn1 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			addUnselectedModuleToSelectedListView(smp.getUnselectedTerm1ListViewSelection());
		}
	}
	
	private class SelectModulesAddBtn2 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			addUnselectedModuleToSelectedListView(smp.getUnselectedTerm2ListViewSelection());
		}
	}
	
	private class SelectModulesRemoveBtn1 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			removeSelected(smp.getSelectedTerm1ListViewSelection());
		}
	}
	
	private class SelectModulesRemoveBtn2 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			removeSelected(smp.getSelectedTerm2ListViewSelection());
		}
	}
	
	private class SelectModulesResetBtn implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			smp.resetAll();
			setInitialStateOfSelectModulesPane();
			smp.changeColorOfCreditsText1(false);
			smp.changeColorOfCreditsText2(false);
			smp.setErrorLabel("");

		}
	}
	
	private class SelectModulesSubmitBtn implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Integer totalCredits = Integer.parseInt(smp.getCurrentValueOfCurrentTerm1CreditsListView()) + Integer.parseInt(smp.getCurrentValueOfCurrentTerm2CreditsListView());
			
			if(totalCredits == 120) {
				saveSelectedModulesToStudentProfile();
				setInitialStateOfReserveModulesPane();
				smp.setErrorLabel("");
			}
			else {
				if(Integer.parseInt(smp.getCurrentValueOfCurrentTerm1CreditsListView()) < 60 && Integer.parseInt(smp.getCurrentValueOfCurrentTerm2CreditsListView()) < 60) {
					smp.setErrorLabel("Select 60 credit worth of modules for term 1 and term 2");
				} 
				else if (Integer.parseInt(smp.getCurrentValueOfCurrentTerm1CreditsListView()) < 60) {
					smp.setErrorLabel("Select 60 credit worth of modules for term 1");
				}
				else {
					smp.setErrorLabel("Select 60 credit worth of modules for term 2");
				}
			}
		}
	}
	
	// SelectModulesPane helpful handler methods
	public void setInitialStateOfSelectModulesPane() {
		
		smp.changeColorOfCreditsText1(false);
		smp.changeColorOfCreditsText2(false);
		
		model
		.getStudentCourse()
		.getAllModulesOnCourse()
		.forEach(module -> {
			if(smp.getAllElementsFromSelectedTerm1ListView().contains(module.getModuleCode() + ": " + module.getModuleName()) == false) {
				if(smp.getAllElementsFromSelectedTerm2ListView().contains(module.getModuleCode() + ": " + module.getModuleName()) == false) {
					if(smp.getAllElementsFromSelectedYearLongListView().contains(module.getModuleCode() + ": " + module.getModuleName()) == false) {
						if ( module.getDelivery() == Schedule.TERM_1 ) {	
							if( module.isMandatory() == true ) {
								smp.addToSelectedTerm1ListView( module.getModuleCode() + ": " + module.getModuleName() );
								smp.addToCurrentTerm1CreditsListView( Integer.toString(module.getModuleCredits() ));
							}
							else {
								smp.addToUnselectedTerm1ListView( module.getModuleCode() + ": " + module.getModuleName() );
							}
						
						}	
						else if( module.getDelivery() == Schedule.TERM_2 ) {
								if( module.isMandatory() == true ) {
									smp.addToSelectedTerm2ListView( module.getModuleCode() + ": " + module.getModuleName() );
									smp.addToCurrentTerm2CreditsListView( Integer.toString( module.getModuleCredits() ));
								}
								else {
									smp.addToUnselectedTerm2ListView( module.getModuleCode() + ": " + module.getModuleName() );
								}	
						}	
						else if (module.getDelivery() == Schedule.YEAR_LONG){
								smp.addToYearLongListView( module.getModuleCode() + ": " + module.getModuleName() );
								smp.addToCurrentTerm1CreditsListView( Integer.toString( module.getModuleCredits() / 2 ));
								smp.addToCurrentTerm2CreditsListView( Integer.toString( module.getModuleCredits() / 2 ));
							}	
					}
				}
			}
			

		});
	}
	
	public void saveSelectedModulesToStudentProfile() {
		smp.getAllElementsFromSelectedTerm1ListView().forEach(element -> {
			Module moduleInQuestion = model.getStudentCourse().getModuleByCode(element.substring(0, 8));
			model.addSelectedModule(moduleInQuestion);
		});
		
		smp.getAllElementsFromSelectedTerm2ListView().forEach(element -> {
			Module moduleInQuestion = model.getStudentCourse().getModuleByCode(element.substring(0, 8));
			model.addSelectedModule(moduleInQuestion);
		});
		
		smp.getAllElementsFromSelectedYearLongListView().forEach(element -> {
			Module moduleInQuestion = model.getStudentCourse().getModuleByCode(element.substring(0, 8));
			model.addSelectedModule(moduleInQuestion);
		});
	}
	
	public void addUnselectedModuleToSelectedListView(String unselected) {	
		Module moduleInQuestion = model.getStudentCourse().getModuleByCode(unselected.substring(0, 8));
		
		if(moduleInQuestion.getDelivery() == Schedule.TERM_1) {
			if( smp.getAllElementsFromSelectedTerm1ListView().contains(unselected) != true ) {
				if( Integer.parseInt(smp.getCurrentValueOfCurrentTerm1CreditsListView()) + moduleInQuestion.getModuleCredits() <= 60) {
					String term1ModuleCredits = Integer.toString(moduleInQuestion.getModuleCredits());
					smp.addToSelectedTerm1ListView(unselected);
					smp.removeFromUnselectedTerm1ListView(unselected);
					smp.addToCurrentTerm1CreditsListView(term1ModuleCredits);
					
					if( Integer.parseInt(smp.getCurrentValueOfCurrentTerm1CreditsListView()) == 60) {
						smp.changeColorOfCreditsText1(true);
					}
					
				}
			}
		}
		else if(moduleInQuestion.getDelivery() == Schedule.TERM_2) {
			if(smp.getAllElementsFromSelectedTerm2ListView().contains(unselected) != true) {
				if( Integer.parseInt(smp.getCurrentValueOfCurrentTerm2CreditsListView()) + moduleInQuestion.getModuleCredits() <= 60) {
					String term2ModuleCredits = Integer.toString( moduleInQuestion.getModuleCredits() );
					smp.addToSelectedTerm2ListView(unselected);
					smp.removeFromUnselectedTerm2ListView(unselected);
					smp.addToCurrentTerm2CreditsListView(term2ModuleCredits);
					
					if( Integer.parseInt(smp.getCurrentValueOfCurrentTerm2CreditsListView()) == 60) {
						smp.changeColorOfCreditsText2(true);
					}
				}

			}
		}
	}
	
	public void removeSelected(String selected) {
		Module moduleInQuestion = model.getStudentCourse().getModuleByCode(selected.substring(0, 8));
		if(moduleInQuestion.getDelivery() == Schedule.TERM_1 && moduleInQuestion.isMandatory() != true) {
			smp.removeFromSelectedTerm1ListView(selected);
			smp.subFromCurrentTerm1CreditsListView( Integer.toString(moduleInQuestion.getModuleCredits()) );
			smp.addToUnselectedTerm1ListView(selected);
			smp.changeColorOfCreditsText1(false);

			
		}
		else if (moduleInQuestion.getDelivery() == Schedule.TERM_2 && moduleInQuestion.isMandatory() != true) {
			smp.removeFromSelectedTerm2ListView(selected);
			smp.subFromCurrentTerm2CreditsListView( Integer.toString(moduleInQuestion.getModuleCredits()));
			smp.addToUnselectedTerm2ListView(selected);
			smp.changeColorOfCreditsText2(false);
		}
	}
	
	// *********************** ReserveModulesPane handlers ***********************************
	
	// Reserve Module Pane handlers
	
	private class ReserveModulesTerm1Addbtn implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			addUnselectedReservedModuleToSelected(rmp.getUnselectedTerm1ListViewSelection());
		}
	}
	
	private class ReserveModulesTerm2Addbtn implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			addUnselectedReservedModuleToSelected(rmp.getUnselectedTerm2ListViewSelection());
		}
	}
	
	public class ReserveModulesTerm1RemoveBtn implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			removeReservedFromSelectedListView(rmp.getReservedTerm1ListViewSelection());
		}
	}
	
	public class ReserveModulesTerm2RemoveBtn implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			removeReservedFromSelectedListView(rmp.getReservedTerm2ListViewSelection());
		}
	}
	
	public class ReserveModulesConfirmBtn1 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			if(rmp.getAllElementsFromReservedTerm1ListView().size() == 2) {
				addReservedModulesToDataModel(rmp.getAllElementsFromReservedTerm1ListView());
				rmp.setError1("");
				rmp.setError2("");
				rmp.unlockTerm2AccordionPage();
			}
			else {
				rmp.setError1("Please select 2 reserve modules for term 1");
			}
		}
	}
	
	public class ReserveModulesConfirmBtn2 implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Integer number = rmp.getAllElementsFromReservedTerm1ListView().size() + rmp.getAllElementsFromReservedTerm2ListView().size();
			if( number == 4) {
				addReservedModulesToDataModel(rmp.getAllElementsFromReservedTerm2ListView());
				rmp.setError1("");
				rmp.setError2("");
				setInitialStateOfOverviewSelectionPane();
				view.changeTab(3);
			}
			else {
				if(rmp.getAllElementsFromReservedTerm1ListView().size() != 2 && rmp.getAllElementsFromReservedTerm2ListView().size() != 2) {
					rmp.setError1("Please select 2 reserve modules for each term... you need to select 4 in total!");
					rmp.setError2("Please select 2 reserve modules for each term... you need to select 4 in total!");
				}
				else if(rmp.getAllElementsFromReservedTerm1ListView().size() != 2) {
					rmp.setError1("Please select 2 reserve modules for term 1");
					rmp.setError2("Please select 2 reserve modules for term 1");
				}
				else {
					rmp.setError1("Please select 2 reserve modules for term 2");
					rmp.setError2("Please select 2 reserve modules for term 2");
				}
			}
		}
	}

	
	// Reserve Modules Pane useful methods
	
	public void addUnselectedReservedModuleToSelected(String unselected) {	
		Module moduleInQuestion = model.getStudentCourse().getModuleByCode(unselected.substring(0, 8));
		
		if(moduleInQuestion.getDelivery() == Schedule.TERM_1) {
			if( rmp.getAllElementsFromReservedTerm1ListView().contains(unselected) != true ) {
				if( rmp.getAllElementsFromReservedTerm1ListView().size() < 2) {
					rmp.addToReservedTerm1ListView(unselected);
					rmp.removeUnselectedTerm1ListViewElement(unselected);
				}
			}
		}
		else if(moduleInQuestion.getDelivery() == Schedule.TERM_2) {
			if( rmp.getAllElementsFromReservedTerm2ListView().contains(unselected) != true ) {
				if( rmp.getAllElementsFromReservedTerm2ListView().size() < 2) {
					rmp.addToReservedTerm2ListView(unselected);
					rmp.removeUnselectedTerm2ListViewElement(unselected);
				}
			}
		}
	}
	
	public void removeReservedFromSelectedListView(String selected) {
		Module moduleInQuestion = model.getStudentCourse().getModuleByCode(selected.substring(0, 8));
		if(moduleInQuestion.getDelivery() == Schedule.TERM_1) {
			rmp.removeFromReservedTerm1ListView(selected);
			rmp.addToUnselectedTerm1ListView(selected);
			
		}
		else if (moduleInQuestion.getDelivery() == Schedule.TERM_2) {
			rmp.removeFromReservedTerm2ListView(selected);
			rmp.addToUnselectedTerm2ListView(selected);
		}
	}
	
	public void setInitialStateOfReserveModulesPane() {
		model
		.getStudentCourse()
		.getAllModulesOnCourse()
		.forEach(module -> {
			if(rmp.getAllElementsFromUnselectedReservedTerm1ListView().contains(module.getModuleCode() + ": " + module.getModuleName()) == false) {
				if(rmp.getAllElementsFromUnselectedReservedTerm2ListView().contains(module.getModuleCode() + ": " + module.getModuleName()) == false) {
					if( model.getAllSelectedModules().contains(module) != true) {
						if( module.getDelivery() == Schedule.TERM_1) {
							rmp.addToUnselectedTerm1ListView(module.getModuleCode() + ": " + module.getModuleName());
						}
						else if ( module.getDelivery() == Schedule.TERM_2) {
							rmp.addToUnselectedTerm2ListView(module.getModuleCode() + ": " + module.getModuleName());
						}
					}
				}
			}

		});
		
		view.changeTab(2);
		rmp.expandAccordionPage0();
	}
	
	public void addReservedModulesToDataModel(ObservableList<String> modules) {
		modules.forEach(module -> {
			Module moduleInQuestion = model.getStudentCourse().getModuleByCode(module.substring(0, 8));
			model.getAllReservedModules().add(moduleInQuestion);
		});
		
	}
	
	
	// *********************** OverviewSelectionPane handlers ***********************************
	
	private class OverviewBtn implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
				try {
					saveToFilePrintWriter();
					osp.setConfirmLabelText(model.getStudentPnumber() + " Student profile saved!");
					
					
				} catch (FileNotFoundException e1) {
					System.out.println(e1);
				}
		}
	}
	
	// ********************* OverviewSelection useful handler methods
	
	public void saveToFilePrintWriter() throws FileNotFoundException {
		PrintWriter p = new PrintWriter(model.getStudentPnumber() + "StudentProfileOverview.dat");
		p.println("Pno" + " " + model.getStudentPnumber());
		p.println("firstName" + " " + model.getStudentName().getFirstName());
		p.println("lastName" + " " + model.getStudentName().getFamilyName());
		p.println("email" + " " + model.getStudentEmail());
		p.println("date" + " " + model.getSubmissionDate());
		p.println("course" + " " + model.getStudentCourse().getCourseName());	
		p.println("selectedModules" + " " + model.getAllSelectedModules());
		p.println("reservedModules" + " " + model.getAllReservedModules());
		p.println("end");
		p.flush();
		p.close();
	}
	
	
	public void setInitialStateOfOverviewSelectionPane() {
		
		osp.addSingleElementToProfileListView("Student Profile: ");
		osp.addSingleElementToProfileListView(" ");
		
		osp
		.addElementsToProfileListView(List
				.of("Name: " + model.getStudentName().getFirstName() + " " + model.getStudentName().getFamilyName(),
				"PNo: " + model.getStudentPnumber(),
				"Email: " + model.getStudentEmail(),
				"Date: " + model.getSubmissionDate(),
				"Course: " + model.getStudentCourse().getCourseName()
				));
			
			osp.addElementToSelectedListView("Selected Modules: ");
			osp.addElementToSelectedListView(" ");
		
			model.getAllSelectedModules().forEach(module -> {
				osp.addElementToSelectedListView("Module code: " + module.getModuleCode() + ", Module name: " + module.getModuleName());
				String isMandatory = (module.isMandatory()) ? " Yes" : " No";
				String deliveryTerm = (module.getDelivery() == Schedule.TERM_1) ? " Term 1" : (module.getDelivery() == Schedule.TERM_2) ? " Term 2" : " Year long";
				osp.addElementToSelectedListView("Credits: " + module.getModuleCredits() + ", Mandatory on your course? " + isMandatory + ", Delivery:" + deliveryTerm);
				osp.addElementToSelectedListView(" ");
			});
			
			osp.addElementToReservedListView("Reserved Modules: ");
			osp.addElementToReservedListView(" ");
			
			model.getAllReservedModules().forEach(module -> {
				osp.addElementToReservedListView("Module code: " + module.getModuleCode() + ", Module name: " + module.getModuleName());
				String deliveryTerm = (module.getDelivery() == Schedule.TERM_1) ? " Term 1" : (module.getDelivery() == Schedule.TERM_2) ? " Term 2" : " Year long";
				osp.addElementToReservedListView("Credits: " + module.getModuleCredits() + ", Delivery:" + deliveryTerm);
				osp.addElementToReservedListView(" ");
			});
					
	}
	
	// Save & Load handlers
	private class SaveMenuHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studentProfileSaveFile.dat"))) {
				
				oos.writeObject(model);
				oos.flush();
			}
			catch (IOException ioExcep) {
				System.out.println("Error while saving: " + ioExcep);
			}
		}
	}
	
	// About Page handler
	private class OpenAboutPage implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
					Tab aboutTab = new Tab("About Tab", about);
					aboutTab.setClosable(true);
					
					if( view.getTabPane().getTabs().size() < 5 ) {
						view.getTabPane().getTabs().add(aboutTab);
						view.changeTab(4);
					}
			}
	}
	
	private class LoadMenuHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studentProfileSaveFile.dat"))) {
				
				model = (StudentProfile) ois.readObject();
				
				System.out.println(model);
				
				if(model.getAllSelectedModules().size() == 0) {
					smp.resetAll();
					setInitialStateOfSelectModulesPane();
					view.changeTab(1);
				}
				else if(model.getAllSelectedModules().size() != 0 && model.getAllReservedModules().size() == 0) {
					smp.resetAll();
					setInitialStateOfSelectModulesPane();
					setInitialStateOfReserveModulesPane();
					model.getAllSelectedModules().forEach(module -> {
						addUnselectedModuleToSelectedListView(module.getModuleCode() + ": " + module.getModuleName() );
					});
					view.changeTab(2);
				}
				else if(model.getAllSelectedModules().size() != 0 && model.getAllReservedModules().size() != 0) {
					smp.resetAll();
					setInitialStateOfSelectModulesPane();
					setInitialStateOfReserveModulesPane();
					rmp.unlockTerm2AccordionPage();
					model.getAllSelectedModules().forEach(module -> {
						addUnselectedModuleToSelectedListView(module.getModuleCode() + ": " + module.getModuleName() );
					});
					
					model.getAllReservedModules().forEach(module -> {
						addUnselectedReservedModuleToSelected(module.getModuleCode() + ": " + module.getModuleName() );
					});
					
					setInitialStateOfOverviewSelectionPane();
					view.changeTab(3);
				}
			} 
			catch(IOException ioExcep) {
				System.out.println("Error loading: " + ioExcep);
			}
			catch(ClassNotFoundException c) {
				System.out.println("Class not found: " + c);
			}
			
		}
	}
	
	
	//helper method - generates course and module data and returns courses within an array
	private Course[] generateAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;

		return courses;
	}

}
