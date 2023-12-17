package application;

import static javafx.geometry.HPos.CENTER;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminHomePage {
	
	    AdminHomePage(Stage primaryStage, Admin admin) {
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));
	
	        Text scenetitle = new Text("Welcome Admin" + admin.getADT() + " !");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        grid.add(scenetitle, 0, 0, 2, 1);
	        
	        Button manageBooksButton = new Button("ManageBooks");
	        manageBooksButton.setOnAction(event-> {
				try {
					new ManageBooks(primaryStage, admin);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			});
	        
	        Button manageCategoriesButton = new Button("ManageCategories");
	        manageCategoriesButton.setOnAction(event-> {
				try {
					new ManageCategories(primaryStage, admin);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			});
	        
	        Button manageUsersButton = new Button("ManageUsers");
	        manageUsersButton.setOnAction(event-> {
				try {
					new ManageUsers(primaryStage, admin);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			});
	        
	        Button manageLendingsButton = new Button("ManageLendings");
	        manageLendingsButton.setOnAction(event -> {
	        	try {
					new ManageLendings(primaryStage, admin);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
	        });

	        Button signOutButton = new Button("Sign Out");
	        signOutButton.setOnAction(event-> new SignIn(primaryStage));
	        
	        Button restartButton = new Button("Reset");
	        restartButton.setOnAction(event -> {
	        	try {
					AppUtils.ResetMediaLab();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
	        });
	        
	        VBox vbBtn = new VBox(10);   
	        vbBtn.setAlignment(Pos.BOTTOM_CENTER);
	        vbBtn.getChildren().addAll(manageBooksButton, manageCategoriesButton, manageUsersButton, manageLendingsButton, signOutButton, restartButton);
	        grid.add(vbBtn, 1, 7);
	        
	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 9);
	        GridPane.setColumnSpan(actiontarget, 2);
	        GridPane.setHalignment(actiontarget, CENTER);	        
	
	        Scene scene = new Scene(grid, 420, 420);
	        primaryStage.setTitle("HomePage-Admin" + admin.getADT());
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
}
