/**
 * Program Name: StudentLoanApp.java
 * Purpose: a gui prototype of an app that accepts some inputs and calculates student loans based on the entered values
 * Coder: Yevgeniya Anasheva
 * Date: Apr. 1, 2019
 */

import java.util.ArrayList;
import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
//get the listener imports
import javafx.event.*;

public class StudentLoanApp extends Application
{
	//data members
	ArrayList<Student> student = new ArrayList<Student>();
	private int idx; 
	private Button calcPaymentsBtn, clearFldsBtn;
	private Button submitBtn, nextStudentBtn, prevStudentBtn, createNewStudentBtn;
	private ListView<String> provinceList;
	private ObservableList<String> items = FXCollections.observableArrayList("ON", "AB", "QC", "NB", "NS", "PE", "NL", "MB", "SK", "BC", "YT", "NT", "NU");
	private TextField txtStudentId, txtSurname, txtMiddleName, txtFirstName, txtAptNumber, txtStreetNumber, txtStreetName, txtCity, txtPostalCode, txtCSLLoan, txtOSLLoan;
	private TextField txtInterest, txtAmortization, txtMonthlyCSL, txtMonthlyOSL, txtTotalMonthly, txtTotalRepaid, txtBorrowed, txtInterestLoans; 
	private double cslLoanAmount, oslLoanAmount, primeInterest, monthlyCSL, monthlyOSL, totalMonthly, totalRepaid, borrowed, interestLoans;
	private int studentID, amortization;
	private String studentInfo;
	private Label lbl1, errorLbl1, errorLbl2, inplbl14;
	private TabPane tabPane;
	private Tab gridTab;
	private final double CSL_INTEREST = 2.5, OSL_INTEREST = 1.0;
	
	public void start(Stage myStage)
	{
		myStage.setTitle("Yevgeniya Anasheva 0881163");
		
		//call a method to build an interface
		interfaceBuilder();
		
		Scene myScene = new Scene(tabPane, 450, 550);
		
		myStage.setScene(myScene);
		myStage.show();
	}
	/*
	 * Method name: interfaceBuilder()
	 * Purpose: builds the interface for the application
	 * Accepts: nothing
	 * Returns: nothing
	 */
	private void interfaceBuilder()
	{
			//create a tab pane and add tabs to it
			tabPane = new TabPane();
			//tab for input form
			Tab inputTab = new Tab();
			inputTab.setText("Input Form");
			
			//grid pane for input form
			GridPane gridInputPane = new GridPane();
			
			Label inplbl1 = new Label("Enter your data: ");
			Label inplbl2 = new Label("Student ID: ");
			Label inplbl3 = new Label("Surname: ");
			Label inplbl4 = new Label("Middle name: ");
			Label inplbl5 = new Label("First name: ");
			Label inplbl6 = new Label("Apt number: ");
			Label inplbl7 = new Label("Street number: ");
			Label inplbl8 = new Label("Street name: ");
			Label inplbl9 = new Label("City: ");
			Label inplbl10 = new Label("Province: ");
			Label inplbl11 = new Label("Postal code: ");
			Label inplbl12 = new Label("CSL Loan Amount: ");
			Label inplbl13 = new Label("OSL Loan Amount: ");
			inplbl14 = new Label("");
			inplbl14.setPrefWidth(150);
			inplbl14.setStyle("-fx-text-fill: #5c2d77");
			
			//this error label is for the input form
			errorLbl1 = new Label("");
			errorLbl1.setStyle("-fx-text-fill: red");
			
			txtStudentId = new TextField("0881163");
		  txtSurname = new TextField("Yevgeniya");
			txtMiddleName = new TextField("Jane");
			txtFirstName = new TextField("Anasheva");
			txtAptNumber = new TextField("42");
			txtStreetNumber = new TextField("125");
			txtStreetName = new TextField("Bay St");
			txtCity = new TextField("London");
			provinceList = new ListView<String>();
			provinceList.setItems(items);
			provinceList.setMaxHeight(50.0);
			txtPostalCode = new TextField("N6A 3Z0");
			txtCSLLoan = new TextField("2500");
			txtOSLLoan = new TextField("1500");
			
			submitBtn = new Button("Submit");
			prevStudentBtn = new Button("Prev");
			nextStudentBtn = new Button("Next");
			createNewStudentBtn = new Button("Create new student");
			
			//register button listener
			InputListener inpListener = new InputListener();
			submitBtn.setOnAction(inpListener);
			nextStudentBtn.setOnAction(inpListener);
			prevStudentBtn.setOnAction(inpListener);
			createNewStudentBtn.setOnAction(inpListener);
			
			//add elements to input form
			gridInputPane.setVgap(5);
			gridInputPane.setHgap(20);
			gridInputPane.add(inplbl1, 0, 0);
			gridInputPane.add(inplbl2, 0, 1);
			gridInputPane.add(inplbl3, 0, 2);
			gridInputPane.add(inplbl4, 0, 3);
			gridInputPane.add(inplbl5, 0, 4);
			gridInputPane.add(inplbl6, 0, 5);
			gridInputPane.add(inplbl7, 0, 6);
			gridInputPane.add(inplbl8, 0, 7);
			gridInputPane.add(inplbl9, 0, 8);
			gridInputPane.add(inplbl10, 0, 9);
			gridInputPane.add(inplbl11, 0, 10);
			gridInputPane.add(inplbl12, 0, 11);
			gridInputPane.add(inplbl13, 0, 12);
			gridInputPane.add(inplbl14, 0, 13);
			gridInputPane.add(prevStudentBtn, 0, 14);
			gridInputPane.add(errorLbl1, 0, 15);
			
			gridInputPane.add(txtStudentId, 1, 1);
			gridInputPane.add(txtSurname, 1, 2);
			gridInputPane.add(txtMiddleName, 1, 3);
			gridInputPane.add(txtFirstName, 1, 4);
			gridInputPane.add(txtAptNumber, 1, 5);
			gridInputPane.add(txtStreetNumber, 1, 6);
			gridInputPane.add(txtStreetName, 1, 7);
			gridInputPane.add(txtCity, 1, 8);
			gridInputPane.add(provinceList, 1, 9);
			gridInputPane.add(txtPostalCode, 1, 10);
			gridInputPane.add(txtCSLLoan, 1, 11);
			gridInputPane.add(txtOSLLoan, 1, 12);
			gridInputPane.add(submitBtn, 1, 13);
			gridInputPane.add(nextStudentBtn, 1, 14);
			gridInputPane.add(createNewStudentBtn, 1, 15, 1, 10);
			
			//tab for calculation form
			gridTab = new Tab();
			gridTab.setText("Calculation Form");
			
			//grid pane for calculation form
			GridPane gridPane = new GridPane();
			
			lbl1 = new Label("This is Yevgeniya’s Student Loan Calculator");
			lbl1.setStyle("-fx-font-size: 9pt");
			Label lbl4 = new Label("Current prime interest rate: ");
			Label lbl5 = new Label("Amortization period in months: ");
			Label lbl6 = new Label("Monthly CSL payment amount: ");
			Label lbl7 = new Label("Monthly OSL payment amount: ");
			Label lbl8 = new Label("Total monthly payment amount: ");
			Label lbl9 = new Label("Total amount of your repaid loan: ");
			Label lbl10 = new Label("Total amount you borrowed: ");
			Label lbl11 = new Label("Total interest on your loans: ");
			
			//this error label is for the calculation form
			errorLbl2 = new Label("");
			errorLbl2.setStyle("-fx-text-fill: red");
			
			txtInterest = new TextField();
			txtAmortization = new TextField();
			txtMonthlyCSL = new TextField();
			txtMonthlyOSL = new TextField();
			txtTotalMonthly = new TextField();
			txtTotalRepaid = new TextField();
			txtBorrowed = new TextField();
			txtInterestLoans = new TextField();
			
			//make the fields read-only
			txtMonthlyCSL.setEditable(false);
			txtMonthlyOSL.setEditable(false);
			txtTotalMonthly.setEditable(false);
			txtTotalRepaid.setEditable(false);
			txtBorrowed.setEditable(false);
			txtInterestLoans.setEditable(false);
			
			calcPaymentsBtn = new Button("Calculate Payments");
			clearFldsBtn = new Button("Clear fields");
			calcPaymentsBtn.setPrefWidth(200);
			clearFldsBtn.setPrefWidth(200);
			
			//register button listener for calculation form
			ButtonListener btnListener = new ButtonListener();
			calcPaymentsBtn.setOnAction(btnListener);
			clearFldsBtn.setOnAction(btnListener);
			
			//add elements to Calculation Form
			gridPane.setVgap(5);
			gridPane.add(lbl1, 0, 0);
			gridPane.add(lbl4, 0, 1);
			gridPane.add(lbl5, 0, 2);
			gridPane.add(lbl6, 0, 3);
			gridPane.add(lbl7, 0, 4);
			gridPane.add(lbl8, 0, 5);
			gridPane.add(lbl9, 0, 6);
			gridPane.add(lbl10, 0, 7);
			gridPane.add(lbl11, 0, 8);
			gridPane.add(errorLbl2, 0, 10);
			
			gridPane.add(txtInterest, 1, 1);
			gridPane.add(txtAmortization, 1, 2);
			gridPane.add(txtMonthlyCSL, 1, 3);
			gridPane.add(txtMonthlyOSL, 1, 4);
			gridPane.add(txtTotalMonthly, 1, 5);
			gridPane.add(txtTotalRepaid, 1, 6);
			gridPane.add(txtBorrowed, 1, 7);
			gridPane.add(txtInterestLoans, 1, 8);
			
			gridPane.setPadding(new Insets(20, 0, 0, 10));
			gridPane.add(calcPaymentsBtn, 0, 9);
			gridPane.add(clearFldsBtn, 1, 9);
			
			//aligns the input form to the centre of scene
			gridInputPane.setAlignment(Pos.CENTER);
			inputTab.setContent(gridInputPane);
			gridTab.setContent(gridPane);
			
			//register the listener for tab pane
			TabHandler tabHandler = new TabHandler();
			gridTab.setOnSelectionChanged(tabHandler);
			
			tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);	//makes it unavailable to close tabs
			tabPane.getTabs().add(inputTab);
			tabPane.getTabs().add(gridTab);
	}
	
	private class InputListener implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent ev)
		{
			if(ev.getSource() == submitBtn)
			{
				errorLbl1.setText("");
				
				//getting the input values
				String stdIdString = txtStudentId.getText();	
				String stdSurString = txtSurname.getText();
				String stdMidString = txtMiddleName.getText();
				String stdFirString = txtFirstName.getText();
				String stdAptNumString = txtAptNumber.getText();
				String stdStrNumString = txtStreetNumber.getText();
				String stdStrNameString = txtStreetName.getText();
				String stdCityString = txtCity.getText();
				String stdProvString = provinceList.getSelectionModel().getSelectedItem();
				String stdPostCode = txtPostalCode.getText();
				String stdCSLString = txtCSLLoan.getText();
				String stdOSLString = txtOSLLoan.getText();
				
				//checking for the number of digits
				if(stdIdString.length() != 7)
				{
					errorLbl1.setText("Incorrect number of digits\n for the student ID");
					return;
				}
				//checking if the student ID is not an integer value
				try
				{
					studentID = Integer.parseInt(stdIdString);
				}
				catch (NumberFormatException ex)
				{
					errorLbl1.setText("Your student ID \nis not a number");
					return;
				}
				//checking to see if CSL or OSL amounts are negative
				try
				{
					cslLoanAmount = Double.parseDouble(stdCSLString);
					oslLoanAmount = Double.parseDouble(stdOSLString);
					if(cslLoanAmount < 0 || oslLoanAmount < 0)
					{
						throw new Y_A_NegativeValueException();
					}
				}
				catch(Y_A_NegativeValueException ex)
				{
					//convert from negative to positive
					cslLoanAmount = Math.abs(cslLoanAmount);
					oslLoanAmount = Math.abs(oslLoanAmount);
					
					//display a message that it was converted
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText(ex.getMessage());
					alert.showAndWait();
				}
				
				//create an object
				Student student1 = new Student(stdIdString, stdSurString, stdMidString, stdFirString, stdAptNumString, stdStrNumString, stdStrNameString, stdCityString,
						stdProvString, stdPostCode, cslLoanAmount, oslLoanAmount);
				
				//pass the toString to studentInfo and then set it to a text field 
				studentInfo = student1.toString();
				lbl1.setText(studentInfo);
				inplbl14.setText("Go to calculation form");
				
				//add the object to the array list
				student.add(student1);
				//set the index of last created student
				idx = student.indexOf(student1);
			}
			
			if(ev.getSource() == createNewStudentBtn)
			{
				//just empties all the fields for the new data
				txtStudentId.setText("");	
				txtSurname.setText("");
				txtMiddleName.setText("");
				txtFirstName.setText("");
				txtAptNumber.setText("");
				txtStreetNumber.setText("");
				txtStreetName.setText("");
				txtCity.setText("");
				txtPostalCode.setText("");
				txtCSLLoan.setText("");
				txtOSLLoan.setText("");
				inplbl14.setText("");
			}
			
			//DISPLAYS NEXT STUDENT
			if(ev.getSource() == nextStudentBtn)
			{
				if(student.size() < 1) //if no students in the ArrayList, just display the message
				{
					errorLbl1.setText("You have " + student.size() + " students");
				}
				else
				{
					if(idx == student.size() - 1)	//if we are at the end, wrap around and display the first student
					{
						idx = 0;
					}
					else
					{
						idx = idx + 1;	//if we are not at the end, display student at current index + 1
					}		
					txtStudentId.setText(student.get(idx).getStudentID() );
					txtSurname.setText(student.get(idx).getSurname() );
					txtMiddleName.setText(student.get(idx).getMiddleName() );
					txtFirstName.setText(student.get(idx).getFirstName() );
					txtAptNumber.setText(student.get(idx).getAptNumber() );
					txtStreetNumber.setText(student.get(idx).getStreetNumber() );
					txtStreetName.setText(student.get(idx).getStreetName());
					txtCity.setText(student.get(idx).getCity() );
					txtPostalCode.setText(student.get(idx).getPostalCode() );
					txtCSLLoan.setText(Double.toString(student.get(idx).getCslLoanAmount() ) );
					txtOSLLoan.setText(Double.toString(student.get(idx).getOslLoanAmount() ) );	
				}
			}
			
			//DISPLAYS PREVIOUS STUDENT
			if(ev.getSource() == prevStudentBtn)
			{
				if(student.size() < 1)	//if no students in the ArrayList, just display the message
				{
					errorLbl1.setText("You have " + student.size() + " students");
				}
				else 
				{
					if(idx == 0)	//if we are at the beginning, wrap around and display last student
					{
						idx = student.size() - 1;
					}
					else
					{
						idx = idx - 1; //if we are not at the beginning, display element at current index - 1
					}
					txtStudentId.setText(student.get(idx).getStudentID() );
					txtSurname.setText(student.get(idx).getSurname() );
					txtMiddleName.setText(student.get(idx).getMiddleName() );
					txtFirstName.setText(student.get(idx).getFirstName() );
					txtAptNumber.setText(student.get(idx).getAptNumber() );
					txtStreetNumber.setText(student.get(idx).getStreetNumber() );
					txtStreetName.setText(student.get(idx).getStreetName());
					txtCity.setText(student.get(idx).getCity() );
					txtPostalCode.setText(student.get(idx).getPostalCode() );
					txtCSLLoan.setText(Double.toString(student.get(idx).getCslLoanAmount() ) );
					txtOSLLoan.setText(Double.toString(student.get(idx).getOslLoanAmount() ) );
				}				
			}	
		}//end method
	}//end inner class
	
	private class ButtonListener implements EventHandler<ActionEvent>, Y_A_LoanPayable
	{
		//implement and override the handle() method of the EventHandler<ActionEvent>
		@Override
		public void handle(ActionEvent ev)
		{
			//find out which button was pushed
			if(ev.getSource() == calcPaymentsBtn)
			{
				//getting the input values
				
				String primeInterestString = txtInterest.getText();	
				String amortString = txtAmortization.getText();
				try
				{
					primeInterest = Double.parseDouble(primeInterestString);
					amortization = Integer.parseInt(amortString);
					if(primeInterest < 0 || amortization < 0)
					{
						throw new Y_A_NegativeValueException();
					}
				}
				catch(Y_A_NegativeValueException ex)
				{
					//convert from negative to positive
					primeInterest = Math.abs(primeInterest);
					amortization = Math.abs(amortization);
					
					//display a message that it was converted
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText(ex.getMessage());
					alert.showAndWait();
				}
				
				if(primeInterest % .25 != 0)
				{
					errorLbl2.setText("Wrong Prime Interest");
					return;
				}
				else
				{
					//reset the error label if the interest is correct
					errorLbl2.setText("");
				}	
				
				//do the calculations and call the implemented function calculateLoanPayment()
				double interest = primeInterest + CSL_INTEREST;
				monthlyCSL = Math.round(calculateLoanPayment(cslLoanAmount, interest, amortization) * 100) / 100.0;
				interest = primeInterest + OSL_INTEREST;
				monthlyOSL = Math.round(calculateLoanPayment(oslLoanAmount, interest, amortization) * 100) / 100.0;
				totalMonthly = Math.round( (monthlyCSL + monthlyOSL) * 100) / 100.0;
				borrowed = cslLoanAmount + oslLoanAmount;
				interestLoans = Math.round( (totalMonthly * amortization - borrowed) * 100) / 100.0;
				totalRepaid = borrowed + interestLoans;
				
				//now display the calculations in the corresponding text fields
				txtMonthlyCSL.setText(Double.toString(monthlyCSL));
				txtMonthlyOSL.setText(Double.toString(monthlyOSL));
				txtTotalMonthly.setText(Double.toString(totalMonthly));
				txtTotalRepaid.setText(Double.toString(totalRepaid));
				txtBorrowed.setText(Double.toString(borrowed));
				txtInterestLoans.setText(Double.toString(interestLoans));	
				
			}
			else if (ev.getSource() == clearFldsBtn)
			{
				//clearing text fields
				txtInterest.setText("");	
				txtInterest.requestFocus();
				txtAmortization.setText("");
				txtMonthlyCSL.setText("");
				txtMonthlyOSL.setText("");
				txtTotalMonthly.setText("");
				txtTotalRepaid.setText("");
				txtBorrowed.setText("");
				txtInterestLoans.setText("");
			}
		}//end method handle()
		
		@Override
		public double calculateLoanPayment(double amount, double interest, int amortiz)
		{
			interest *= ANNUAL_RATE_TO_MONTHLY_RATE;
			double monthlyPaym = amount * interest * Math.pow( (1 + interest), amortiz) / ( Math.pow( (1 + interest), amortiz) - 1 );
			return monthlyPaym;
		}//end method
	}//end inner class
	
	private class TabHandler implements EventHandler<Event>
	{
		@Override
		public void handle(Event ev)
		{
			if(ev.getSource() == gridTab)
			{
				//this will change the information about a student based on the last used index as soon as the Calculation Form is selected
				if(student.size() != 0)
				{
					errorLbl2.setText("");
					studentInfo = student.get(idx).toString();
					cslLoanAmount = student.get(idx).getCslLoanAmount();
					oslLoanAmount = student.get(idx).getOslLoanAmount();
					lbl1.setText(studentInfo);
				}
				else
				{
					errorLbl2.setText("Go to input form first");
				}
			}
		}//end method
	}//end inner class
	
	public static void main(String[] args)
	{
		launch(args);
	}//end main
	
}//end class