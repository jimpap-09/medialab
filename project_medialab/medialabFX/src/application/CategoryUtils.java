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

public class CategoryUtils {
	
	// Get Categories path/file
	private static Path getCategoriesPath() {
		return Paths.get("medialab", "Categories");
	}
	
	private static File getCategoriesFile() {
		return getCategoriesPath().toFile();
	}
	
	// Check if a given category already exists
	public static boolean categoryAlreadyExists(Category category, ObservableList<Category> categories) {
        return categories.stream().anyMatch(cat -> cat.getTitle().equals(category.getTitle()));
    }
	
	// Get all categories
	public static ObservableList<Category> getCategories() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObservableList<Category> getcategories = FXCollections.observableArrayList();
        File categories = getCategoriesFile();
		if(categories.exists() && categories.isDirectory()) {
			File[] categoryFiles = categories.listFiles();
			if(categoryFiles != null) {
				for (File categoryFile : categoryFiles) {
					try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(categoryFile))) {
						getcategories.add((Category)in.readObject());
					}
				}
			}
		}
		return getcategories;
    }
	
	// Delete all categories
	public static void deleteCategories() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObservableList<Category> categories = getCategories();
		for(Category category : categories)
			category.delete();
    }
}