package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserUtils {
	
	// Get Users path/file
	private static Path getUsersPath() {
		return Paths.get("medialab", "Users");
	}
	
	private static File getUsersFile() {
		return getUsersPath().toFile();
	}
    
	// Get all users
 	public static ObservableList<User> getUsers() throws FileNotFoundException, IOException, ClassNotFoundException {
 		ObservableList<User> getUsers = FXCollections.observableArrayList();
 		File users = getUsersFile();
 		File[] userFiles = users.listFiles();
 		for (File userFile : userFiles)
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(userFile))) {
				getUsers.add((User)in.readObject());
			}
 		return getUsers;
     }
 	
 	// Delete all users
  	public static void deleteUsers() throws FileNotFoundException, IOException, ClassNotFoundException {
   		ObservableList<User> users = getUsers();
      	for(User user : users)
      		user.delete();
      }
	
 	// Get a user with given username and password
 	public static User getUserByUsernameAndPassword(String username, String password) throws FileNotFoundException, ClassNotFoundException, IOException {
 		ObservableList<User> users = getUsers();
 		for(User user : users)
 			if(user.getUserName().equals(username) && user.getPassword().equals(password))
 				return user;
 		return null;
 	}
 	
 	// Check if a given username already exists
 	public static boolean UsernameAlreadyExists(String username) throws FileNotFoundException, ClassNotFoundException, IOException {
 		ObservableList<User> users = getUsers();
 		for(User user : users)
 			if(user.getUserName().equals(username))
 				return true;
 		return false;
 	}
 	
 	// Check if a given email already exists
  	public static boolean EmailAlreadyExists(String email) throws FileNotFoundException, ClassNotFoundException, IOException {
  		ObservableList<User> users = getUsers();
  		for(User user : users)
  			if(user.getEmail().equals(email))
  				return true;
  		return false;
  	}
  	
  	// Check if a given adt already exists
  	public static boolean ADTAlreadyExists(String adt) throws FileNotFoundException, ClassNotFoundException, IOException {
  		ObservableList<User> users = getUsers();
  		for(User user : users)
  			if(user.getADT().equals(adt))
  				return true;
  		return false;
  	}
}