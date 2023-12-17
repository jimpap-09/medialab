package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class WelcomePage {
	@SuppressWarnings("unchecked")
	WelcomePage(Stage primaryStage) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		Text sceneTitle = new Text("Welcome to Medialab");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

		ObservableList<Book> books = BookUtils.get5HighRatedBooks();
		
		TableView<Book> bookTableView = new TableView<>(books);		
		
		TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
		TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
		TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
		TableColumn<Book, String> avgRatingColumn = new TableColumn<>("Average Rating");
		TableColumn<Book, String> readersColumn = new TableColumn<>("Readers");
		
		titleColumn.setCellValueFactory(new BookTableValue("title"));
		authorColumn.setCellValueFactory(new BookTableValue("author"));
		isbnColumn.setCellValueFactory(new BookTableValue("isbn"));
		avgRatingColumn.setCellValueFactory(new BookTableValue("avgRating"));
		readersColumn.setCellValueFactory(new BookTableValue("readers"));
		
		// Set custom cell factories for center alignment
        setCenterAlignment(titleColumn);
        setCenterAlignment(authorColumn);
        setCenterAlignment(isbnColumn);
        setCenterAlignment(avgRatingColumn);
        setCenterAlignment(readersColumn);
		
		bookTableView.getColumns().addAll(titleColumn, authorColumn, isbnColumn, avgRatingColumn, readersColumn);
		
		Button signInButton = new Button("Sign in");
		signInButton.setOnAction(event -> new SignIn(primaryStage));

		Button signUpButton = new Button("Sign up");
		signUpButton.setOnAction(event -> new SignUp(primaryStage));
		
		HBox hb = new HBox(10);
		hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(signInButton, signUpButton);
		
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(sceneTitle, bookTableView, hb);
		
		Image logo = new Image("logo.png");
		Scene scene = new Scene(vbox, 800, 600);
		primaryStage.setTitle("WelcomePage");
		primaryStage.getIcons().add(logo);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	// Method to set center alignment for a column
    private <T> void setCenterAlignment(TableColumn<Book, T> column) {
        column.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setAlignment(Pos.CENTER);
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER);
                }
            }
        });
    }
    
    // Define the value of each cell
    private class BookTableValue extends PropertyValueFactory<Book, String> {
		
	    public BookTableValue(String property) {
	        super(property);
	    }

	    @Override
	    public ObservableValue<String> call(CellDataFeatures<Book, String> param) {
	        Book book = param.getValue();
	        // Customize this logic based on your Book class properties
	        if ("title".equals(getProperty())) {
	            return new SimpleStringProperty(book.getTitle());
	        } 
	        else if ("author".equals(getProperty())) {
	            return new SimpleStringProperty(book.getAuthor());
	        } 
	        else if ("isbn".equals(getProperty())) {
	            return new SimpleStringProperty(book.getISBN());
	        }
	        else if ("avgRating".equals(getProperty())) {
	            return new SimpleStringProperty(book.getAvgRating());
	        }
	        else if ("readers".equals(getProperty())) {
	            return new SimpleStringProperty(book.getReaders());
	        }
	        else {
	            return super.call(param);
	        }
	    }
	}
}