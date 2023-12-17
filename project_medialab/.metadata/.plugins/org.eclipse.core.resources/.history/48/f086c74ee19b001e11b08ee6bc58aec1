package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Admin extends User {

	// Private fields/methods
	private static final long serialVersionUID = 1L;

	// Constructor
	Admin(String username, String password, String firstname, String lastname, String email, String adt) {
		super(username, password, firstname, lastname, email, adt);
	}
	
	// Getters
	public String getFileName() {
		return "Admin" + this.getADT() + ".ser";
	}
	
	public Path getPath() {
		return Paths.get("medialab", "Admins", getFileName());
	}
	
	public File getAdminFile() {
		return getPath().toFile();
	}
	
	//Store
	public void store() {
		
        File adminFile = getAdminFile();
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(adminFile))) {
				out.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Delete
	public void delete() {

        File adminFile = getAdminFile();
        adminFile.delete();
	}
}
