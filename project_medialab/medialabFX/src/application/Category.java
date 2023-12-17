package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

class Category implements Serializable{

	//Private fields/methods
	private static final long serialVersionUID = 1L;
	private String title;
	
	// Constructor
	Category(String title){
		setTitle(title);
	}
	
	// Setters
	public void setTitle(String title) {
		this.title = title;
	}

	// Getters
	public String getTitle() {
		return this.title;
	}
	
	public String getFileName() {
		return this.getTitle() + ".ser";
	}
	
	public Path getPath() {
		return Paths.get("medialab", "Categories", getFileName());
	}
	
	public File getCategoryFile() {
		return getPath().toFile();
	}
	
	// Store
	public void store() {
		File categoryFile = getCategoryFile();
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(categoryFile))) {
				out.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Delete
	public void delete() {
		File categoryFile = getCategoryFile();
        categoryFile.delete();
	}
	
	// toString
	@Override
	public String toString() {
		return this.title;
	}
}