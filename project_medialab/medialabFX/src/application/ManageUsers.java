package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageUsers {

	ManageUsers(Stage primaryStage, Admin admin) throws FileNotFoundException, ClassNotFoundException, IOException {
		
	        primaryStage.setTitle("ManageUsersPage");
	        
	        // Set userList's data
	        ObservableList<User> users = UserUtils.getUsers();
	        
	        // Create userListView
	        ListView<User> userListView = new ListView<>(users);
	        userListView.setCellFactory(param -> new UserListCell(users, primaryStage, admin));
	        
	        // Return to AdminHomePage when "<<" is clicked
	        Button adminhomepage = new Button("<<");
	        adminhomepage.setOnAction(event-> new AdminHomePage(primaryStage, admin));
	        
	        HBox hbox = new HBox(10);
	        hbox.getChildren().add(adminhomepage);
	        VBox vbox = new VBox(10);
	        vbox.getChildren().addAll(userListView, hbox);
	        Scene scene = new Scene(vbox, 800, 600);
	        vbox.setAlignment(Pos.CENTER);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}

    // Create a cell for the text and "Edit", "Delete" buttons
    private class UserListCell extends ListCell<User> {
    	
    	private ObservableList<User> users;
    	private Stage primaryStage;
    	private Admin admin;
    	
    	UserListCell(ObservableList<User> users, Stage primaryStage, Admin admin) {
    		this.users = users;
    		this.primaryStage = primaryStage;
    		this.admin = admin;
	    }
    	
    	@Override
    	protected void updateItem(User item, boolean empty) {
    		
    		super.updateItem(item, empty);
    		
    		if(empty || item == null) {
    			setText(null);
    			setGraphic(null);
    		} else {
    			HBox hbox = new HBox(10);
    			setText("User" + item.getADT());
    			
    			// Go to EditUsersPage when "Edit" button is clicked
    			Button editButton = new Button("Edit");
    			editButton.setOnAction(event -> {
					try {
						new EditUsers(item, primaryStage, admin);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				});
    			
    			// "Delete button"
    			Button deleteButton = new Button("Delete");
    			deleteButton.setOnAction(event -> handleDelete(item));
    			
    			hbox.getChildren().addAll(editButton, deleteButton);
    			setGraphic(hbox);
    		}
    	}

    	// Delete function
	    private void handleDelete(User user) {

	    	Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Deleting User" + user.getADT());
            dialog.setHeaderText("Delete User" + user.getADT());
            
            // Set the button types
            ButtonType yesButtonType = new ButtonType("Yes", ButtonData.OK_DONE);
            ButtonType noButtonType = new ButtonType("No", ButtonData.NO);
            
            dialog.getDialogPane().getButtonTypes().addAll(yesButtonType, noButtonType);

            
            Button yesButton = (Button) dialog.getDialogPane().lookupButton(yesButtonType);
            dialog.getDialogPane().setContentText("Are you sure you want to delete the User" + user.getADT() + " ?");

            // Request focus on the yesButton by default
            Platform.runLater(() -> { 
            	yesButton.requestFocus();
            });
            
            dialog.setResultConverter(dialogButton -> {
            	// When the save button is clicked...
                if (dialogButton == yesButtonType) {
                	try {
                    	users.remove(user);
						LendingUtils.deleteLendingsOfSameUser(user);
                    	user.delete();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
                    return yesButtonType;
                }
                return noButtonType;
            });
            
            dialog.showAndWait();
            		
	    }
    }
}