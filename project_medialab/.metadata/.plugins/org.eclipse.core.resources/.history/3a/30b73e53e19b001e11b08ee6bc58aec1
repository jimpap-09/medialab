package application;

import static javafx.geometry.HPos.CENTER;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignUp {
	
	    SignUp(Stage primaryStage) {
	    	   	
	        GridPane grid = new GridPane();
	        Scene scene = new Scene(grid, 800, 600);

	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("Let's create an account!");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        grid.add(scenetitle, 0, 0, 2, 1);

	        TextField userField = new TextField();
	        PasswordField passwordField = new PasswordField();
	        TextField firstNameField = new TextField();
	        TextField lastNameField = new TextField();
	        TextField emailField = new TextField();
	        TextField adtField = new TextField();
	        
	        grid.add(new Label("Username:"), 0, 1);
	        grid.add(userField, 1, 1);
	        grid.add(new Label("Password:"), 0, 2);
	        grid.add(passwordField, 1, 2);
	        grid.add(new Label("Firstname:"), 0, 3);
	        grid.add(firstNameField, 1, 3);
	        grid.add(new Label("Lastname:"), 0, 4);
	        grid.add(lastNameField, 1, 4);
	        grid.add(new Label("Email:"), 0, 5);
	        grid.add(emailField, 1, 5);
	        grid.add(new Label("ADT:"), 0, 6);
	        grid.add(adtField, 1, 6);
	        
	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 9);
	        GridPane.setColumnSpan(actiontarget, 2);
	        GridPane.setHalignment(actiontarget, CENTER);
	        
	        Button submitUserButton = new Button("Submit User");

	        // Submit as a User
	        submitUserButton.setOnAction(event -> {
	        	
	        	String userNameToCheck = userField.getText();
	        	String emailToCheck = emailField.getText();
	        	String adtToCheck = adtField.getText();
	        	
	        	try {
        			if(userField.getText().isEmpty() ||
						passwordField.getText().isEmpty() ||
						firstNameField.getText().isEmpty() ||
						lastNameField.getText().isEmpty() ||
						emailField.getText().isEmpty() ||
						adtField.getText().isEmpty()
					) {
        				actiontarget.setText("there is empty field!");
						actiontarget.setFill(Color.RED);
        			}
	        		else if(UserUtils.UsernameAlreadyExists(userNameToCheck)) {
	        			actiontarget.setText("user username already exists!");
						actiontarget.setFill(Color.RED);
	        		}else if(UserUtils.EmailAlreadyExists(emailToCheck)) {
						actiontarget.setText("user email already exists!");
						actiontarget.setFill(Color.RED);
					}else if(UserUtils.ADTAlreadyExists(adtToCheck)) {
						actiontarget.setText("user adt already exists!");
						actiontarget.setFill(Color.RED);
					}else {
						User user = new User(userField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText(), emailField.getText(), adtField.getText());
						user.store();
						new SignUp(primaryStage);
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
	        });
	        
	        // Submit as an Admin
	        Button submitAdminButton = new Button("Submit Admin");
	        submitAdminButton.setOnAction(event -> {
	        	String userNameToCheck = userField.getText();
	        	String emailToCheck = emailField.getText();
            	String adtToCheck = adtField.getText();
            	try {
            		if(userField.getText().isEmpty() ||
						passwordField.getText().isEmpty() ||
						firstNameField.getText().isEmpty() ||
						lastNameField.getText().isEmpty() ||
						emailField.getText().isEmpty() ||
						adtField.getText().isEmpty()
					) {
        				actiontarget.setText("there is empty field!");
						actiontarget.setFill(Color.RED);
        			}
            		else if(AdminUtils.UsernameAlreadyExists(userNameToCheck)) {
	        			actiontarget.setText("admin username already exists!");
						actiontarget.setFill(Color.RED);
	        		}else if(AdminUtils.EmailAlreadyExists(emailToCheck)) {
						actiontarget.setText("admin email already exists!");
						actiontarget.setFill(Color.RED);
					}else if(AdminUtils.ADTAlreadyExists(adtToCheck)) {
						actiontarget.setText("admin adt already exists!");
						actiontarget.setFill(Color.RED);
					}else {
						Admin admin = new Admin(userField.getText(), passwordField.getText(), firstNameField.getText(), lastNameField.getText(), emailField.getText(), adtField.getText());
						admin.store();
						new SignUp(primaryStage);
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
	        });
	        
	        // Return to WelcomePage
	        Button welcomePageButton = new Button("WelcomePage");
	        welcomePageButton.setOnAction(event -> {
				try {
					new WelcomePage(primaryStage);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			});

	        HBox hBox = new HBox(10);
	        VBox vBox = new VBox(10);
	        
	        hBox.setAlignment(Pos.BOTTOM_CENTER);
	        hBox.getChildren().addAll(submitUserButton, submitAdminButton);
	        vBox.setAlignment(Pos.BOTTOM_CENTER);
	        vBox.getChildren().addAll(hBox, welcomePageButton);
	        grid.add(vBox, 1, 7);

	        primaryStage.setTitle("SignUpPage");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
	    
}
