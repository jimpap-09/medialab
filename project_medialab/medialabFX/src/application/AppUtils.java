package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppUtils {
	
	// Private fields/methods
	private static List<String> categories;
	
	private static void createAdmin() {
 		(new Admin("medialab", "medialab_2024", "media", "lab", "adminMedialab@medialab.gr", "Medialab")).store();
 	}
	
	private static void createUsers() {
		for(int i = 1; i <= 3; i++) {
			String index = Integer.toString(i);
			(new User("username" + index, "password" + index, "firstname" + index, "lastname" + index, "user" + index + "@medialab.gr", index)).store();
		}
	}
	
	private static void createCategories() {
		categories = new ArrayList<>();
		categories.add("Fiction");
		categories.add("Non-Fiction");
		categories.add("Mystery");
		categories.add("Romance");
		categories.add("Fantasy");
		categories.add("History");
		categories.add("Biography");
		categories.add("Finance");
		categories.add("Nature");
		categories.add("Adventure");
		
		for(String categoryTitle : categories)
			(new Category(categoryTitle)).store();
	}
	
	private static void createBooks() {
		for(int i = 1; i <= 10; i++) {
			int randomIndex = new Random().nextInt(categories.size());
			String category = categories.get(randomIndex);
			String index = Integer.toString(i);
			(new Book("title" + index, "author" + index, "publisher" + index, category, index, "200" + index, "2")).store();
		}
	}
	
	// ResetMedialab
	public static void ResetMediaLab() throws FileNotFoundException, ClassNotFoundException, IOException {
		AdminUtils.deleteAdmins();
		System.out.println("Admins deleted!");
		UserUtils.deleteUsers();
		System.out.println("Users deleted!");
		CategoryUtils.deleteCategories();
		System.out.println("Categories deleted!");
		BookUtils.deleteBooks();
		System.out.println("Books deleted!");
		LendingUtils.deleteLendings();
		System.out.println("Lendings deleted!");
		createAdmin();
		System.out.println("Admin 'medialab' created!");
		createUsers();
		System.out.println("3 Users created!");
		createCategories();
		System.out.println("10 Categories created!");
		createBooks();
		System.out.println("10 Books created!");
	}
}
