package application;

import java.io.IOException;
import java.util.List;

import application.Book.Comment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewBookInfo {

	ViewBookInfo(Stage primaryStage, User user, Book book) {
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("View Book" + book.getISBN() + " Info!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        // Return to SearchBooksPage
        Button searchBooksButton = new Button("<<");
        searchBooksButton.setOnAction(event-> {
			try {
				new SearchBooks(primaryStage, user);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		});

        // Add Labels and TextFields for each book's property
        grid.add(new Label("Title:"), 0, 1);
        grid.add(new Label(book.getTitle()), 1, 1);
        grid.add(new Label("Author:"), 0, 2);
        grid.add(new Label(book.getAuthor()), 1, 2);
        grid.add(new Label("Publisher:"), 0, 3);
        grid.add(new Label(book.getPublisher()), 1, 3);
        grid.add(new Label("Category:"), 0, 4);
        grid.add(new Label(book.getCategory()), 1, 4);
        grid.add(new Label("ISBN:"), 0, 5);
        grid.add(new Label(book.getISBN()), 1, 5);
        grid.add(new Label("YearOfPublishing:"), 0, 6);
        grid.add(new Label(book.getYop()), 1, 6);
        grid.add(new Label("Copies:"), 0, 7);
        grid.add(new Label(book.getCopies()), 1, 7);
        grid.add(new Label("AverageRating:"), 0, 8);
        grid.add(new Label(book.getAvgRating()), 1, 8);
        grid.add(new Label("Readers:"), 0, 9);
        grid.add(new Label(book.getReaders()), 1, 9);
        grid.add(new Label("Comments:"), 0, 10);
        grid.add(searchBooksButton, 0, 12);

    	VBox vbox = new VBox(10);

    	// Add comments
        List<Comment> comments = book.getComments();        
        if(!comments.isEmpty()) {
        	VBox commentsVBox = new VBox(10);

		    for (Comment comment : comments) {
		        HBox commentsHBox = new HBox(10);
		        Label userLabel = new Label("User" + comment.getUser().getADT() + ":");
		        Label commentLabel = new Label(comment.getComment());
		        commentsHBox.getChildren().addAll(userLabel, commentLabel);
		        commentsVBox.getChildren().add(commentsHBox);
		    }
	        	grid.add(commentsVBox, 1, 10);
		    }
        else {
        	grid.add(new Label("None"), 1, 10);
        }
        
        vbox.getChildren().add(grid);

        primaryStage.setTitle("ViewBookInfoPage-User" + user.getADT());
        Scene scene = new Scene(vbox, 420, 420);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
