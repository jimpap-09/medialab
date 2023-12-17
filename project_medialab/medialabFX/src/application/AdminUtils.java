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

public class AdminUtils {
	
	// Get Admins path/file
	private static Path getAdminsPath() {
		return Paths.get("medialab", "Admins");
	}
	
	private static File getAdminsFile() {
		return getAdminsPath().toFile();
	}
    
	// Get all admins
 	public static ObservableList<Admin> getAdmins() throws FileNotFoundException, IOException, ClassNotFoundException {
 		ObservableList<Admin> getAdmins = FXCollections.observableArrayList();
 		File admins = getAdminsFile();
		File[] adminFiles = admins.listFiles();
		for (File adminFile : adminFiles)
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(adminFile))) {
				getAdmins.add((Admin)in.readObject());
			}
 		return getAdmins;
     }
 	
 	// Delete all admins
 	public static void deleteAdmins() throws FileNotFoundException, IOException, ClassNotFoundException {
   		ObservableList<Admin> admins = getAdmins();
   			for(Admin admin : admins)
   				admin.delete();
     }
 	
 	// Get an admin with given username and password
  	public static Admin getAdminByUsernameAndPassword(String username, String password) throws FileNotFoundException, ClassNotFoundException, IOException {
  		ObservableList<Admin> admins = getAdmins();
  		for(Admin admin : admins)
  			if(admin.getUserName().equals(username) && admin.getPassword().equals(password))
  				return admin;
  		return null;
  	}
	
  	// Check if a given username already exists
  	public static boolean UsernameAlreadyExists(String username) throws FileNotFoundException, ClassNotFoundException, IOException {
  		ObservableList<Admin> admins = getAdmins();
  		for(Admin admin : admins)
  			if(admin.getUserName().equals(username))
  				return true;
  		return false;
  	}
  	
  	// Check if a given email already exists
   	public static boolean EmailAlreadyExists(String email) throws FileNotFoundException, ClassNotFoundException, IOException {
   		ObservableList<Admin> admins = getAdmins();
   		for(Admin admin : admins)
   			if(admin.getEmail().equals(email))
   				return true;
   		return false;
   	}
   	
   	// Check if a given adt already exists
   	public static boolean ADTAlreadyExists(String adt) throws FileNotFoundException, ClassNotFoundException, IOException {
   		ObservableList<Admin> admins = getAdmins();
   		for(Admin admin : admins)
   			if(admin.getADT().equals(adt))
   				return true;
   		return false;
   	}
}