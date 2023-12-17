package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageLendings {

	@SuppressWarnings("unchecked")
	ManageLendings(Stage primaryStage, Admin admin) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		// Set LendingTable's data
		ObservableList<Lending> lendings = LendingUtils.getLendings();
		lendings.sort(Comparator.comparingInt(Lending -> Integer.parseInt(Lending.getLendingBook().getISBN())));
		
		// Create LendingTableView
		TableView<Lending> LendingTableView = new TableView<>(lendings);
		
		// Create LendingTableView's columns
		TableColumn<Lending, String> titleColumn = new TableColumn<>("User(ADT)");
		TableColumn<Lending, String> isbnColumn = new TableColumn<>("Book(ISBN)");
		TableColumn<Lending, String> lendingDateColumn = new TableColumn<>("Lending Date");
		TableColumn<Lending, String> finishDateColumn = new TableColumn<>("Finish Date");
		TableColumn<Lending, Button> buttonColumn = new TableColumn<>();
		
		// Set the value of each cell
		titleColumn.setCellValueFactory(new LendingTableValue("adt"));
		isbnColumn.setCellValueFactory(new LendingTableValue("isbn"));
		lendingDateColumn.setCellValueFactory(new LendingTableValue("lendingDate"));
		finishDateColumn.setCellValueFactory(new LendingTableValue("finishDate"));
		buttonColumn.setCellFactory(param -> new LendingTableCell(lendings));
		
		// Set custom cell factories for center alignment
        setCenterAlignment(titleColumn);
        setCenterAlignment(isbnColumn);
        setCenterAlignment(lendingDateColumn);
        setCenterAlignment(finishDateColumn);
		
		LendingTableView.getColumns().addAll(titleColumn, isbnColumn, lendingDateColumn, finishDateColumn, buttonColumn);
		
		// Return to AdminHomePage if "<<" button is clicked
		Button adminHomePage = new Button("<<");
		adminHomePage.setOnAction(event -> new AdminHomePage(primaryStage, admin));

		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(LendingTableView, adminHomePage);
		
		primaryStage.setTitle("ManageLendingsPage-Admin" + admin.getADT());
		Scene scene = new Scene(vbox);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Method to set center alignment for a column
    private <T> void setCenterAlignment(TableColumn<Lending, T> column) {
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
	
    // Cell's value
	private class LendingTableValue extends PropertyValueFactory<Lending, String> {
		
	    public LendingTableValue(String property) {
	        super(property);
	    }

	    @Override
	    public ObservableValue<String> call(CellDataFeatures<Lending, String> param) {
	        Lending Lending = param.getValue();
	        // Customize this logic based on your Lending class properties
	        if ("adt".equals(getProperty())) {
	            return new SimpleStringProperty(Lending.getLendingUser().getADT());
	        } 
	        else if ("isbn".equals(getProperty())) {
	            return new SimpleStringProperty(Lending.getLendingBook().getISBN());
	        }
	        else if ("lendingDate".equals(getProperty())) {
	            return new SimpleStringProperty(Lending.getLendingDate());
	        }
	        else if ("finishDate".equals(getProperty())) {
	            return new SimpleStringProperty(Lending.getLendingFinishDate());
	        }
	        else {
	            return super.call(param);
	        }
	    }
	}
	
	// Create a cell for "Terminate" button
	private class LendingTableCell extends TableCell<Lending, Button> {

		private ObservableList<Lending> lendings;
		
		LendingTableCell(ObservableList<Lending> lendings) {
			this.lendings = lendings;
		}

		@Override
        protected void updateItem(Button item, boolean empty) {

            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {

				Lending lending = getTableView().getItems().get(getIndex());

				Button terminateButton = new Button("Terminate");
				if(LocalDate.now().isEqual(lending.getLocalFinishDate()))
					Terminate(lending);
				
				terminateButton.setOnAction(event -> Terminate(lending));
				
				setGraphic(terminateButton);
            }
        }
		
		// Terminate function
		private void Terminate(Lending lending) {
			Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Terminating Lending" + lending.getLendingID());
            dialog.setHeaderText("Terminate Lending" + lending.getLendingID());
            dialog.getDialogPane().setContentText("Are you sure you want to terminate the Lending" + lending.getLendingID());
            
            // Set the button types
            ButtonType yesButtonType = new ButtonType("Yes", ButtonData.OK_DONE);
            ButtonType noButtonType = new ButtonType("No", ButtonData.NO);

            dialog.getDialogPane().getButtonTypes().addAll(yesButtonType, noButtonType);
            
            Button yesButton = (Button) dialog.getDialogPane().lookupButton(yesButtonType);

            // By default request focus on yes button
            Platform.runLater(() -> { 
            	yesButton.requestFocus();
            });
            
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == yesButtonType) {
                	try {
                		lendings.remove(lending);
	                	LendingUtils.deleteLending(lending);
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
