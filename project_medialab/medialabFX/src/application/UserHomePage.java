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

public class UserHomePage {
	
	    UserHomePage(Stage primaryStage, User user) {
	    	
	        GridPane grid = new GridPane();
	        Scene scene = new Scene(grid, 800, 600);
	        grid.setAlignment(Pos.CENTER);
	        grid.setHgap(10);
	        grid.setVgap(10);
	        grid.setPadding(new Insets(25, 25, 25, 25));

	        Text scenetitle = new Text("Welcome User" + user.getADT() + " !");
	        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        grid.add(scenetitle, 0, 0, 2, 1);
	        
	        Button searchButton = new Button("SearchBooks");
	        searchButton.setOnAction(event -> {
				try {
					new SearchBooks(primaryStage, user);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			});
	        
	        Button lendingButton = new Button("Lendings");
	        lendingButton.setOnAction(event -> {
	        	try {
					new ViewLendingBooks(primaryStage, user);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			});
	        
	        Button signOutButton = new Button("Sign Out");
	        signOutButton.setOnAction(event -> new SignIn(primaryStage));

	        VBox vBox = new VBox(10);
	        vBox.setAlignment(Pos.BOTTOM_CENTER);
	        vBox.getChildren().addAll(searchButton, lendingButton, signOutButton);
	        grid.add(vBox, 1, 7);

	        final Text actiontarget = new Text();
	        grid.add(actiontarget, 1, 9);
	        GridPane.setColumnSpan(actiontarget, 2);
	        GridPane.setHalignment(actiontarget, CENTER);

	        primaryStage.setTitle("HomePage-User" + user.getADT());
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
}
