/**
 * Library class is the application for checking in books
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Library{
	List<LibraryBook> books;
	List<LibrarySystemUser> users;

	public static void main(String[] Args){
		Library library = new Library();
		library.initialize(library);
		while (library.showInterface()){
			// This is where magic happens...
		}
	}
	private void initialize(Library library){
		library.books = new ArrayList<LibraryBook>();
		library.addBook(books, "Lord of the Rings", "JRR Tolkin");
		library.addBook(books, "Room", "Emma Donoghue");
		library.addBook(books, "Being Ernest", "Oscar Wilde");

		library.users = new ArrayList<LibrarySystemUser>();
		library.addUser(users, "admin", "admin", "Library", "Admin", true);

	}

	private boolean showInterface(){
		boolean activeUser = false;
		while (activeUser){
			
		}
		System.out.println("Select your Action");	
		System.out.println("1-Login");
		System.out.println("9-Exit");
		int i = 0;
		try {
			String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
			if (s.toUpperCase().equals("LOGIN") || s.substring(0).equals("1")){
//				login();
			} else if (s.toUpperCase().equals("EXIT") || s.substring(0).equals("9")){
				return false;
			}
		} catch (IOException ioe){
			System.out.println("IOE thrown");
			return false;
		}
		return true;
	}
	

	private void addBook(List<LibraryBook> books, String name, String author){
		int i = books.size();
		LibraryBook book = new LibraryBook();
		book.setBookName(name);
		book.setBookAuthor(author);
		book.setBookNumber(i++);
		book.setIsAvailable(true);
		books.add(book);
	}

	private void addUser(List<LibrarySystemUser> users, String uName, String password, String fName, String lName, boolean admin){
		int i = users.size();
		LibrarySystemUser user = new LibrarySystemUser();
		user.setUserNumber(i++);
		user.setUserName(uName);
		user.setFirstName(fName);
		user.setLastName(lName);
		user.setIsEmployee(admin);
		user.setPassword(password);
		users.add(user);
	}

	private void borrowBook(){
		System.out.println("Available books");
		
	}
	
	class LibraryBook{
		private int bookNumber;
		private String bookName;
		private String bookAuthor;
		private boolean isAvailable;
		
		public int getBookNumber(){
			return bookNumber;
		}
		public void setBookNumber(int number){
			bookNumber = number;
		}
		public String getBookName(){
			return bookName;
		}
		public void setBookName(String name){
			bookName = name;
		}
		public String getBookAuthor(){
			return bookAuthor;
		}
		public void setBookAuthor(String author){
			bookAuthor = author;
		}
		public boolean getIsAvailable(){
			return isAvailable;
		}
		public void setIsAvailable(boolean available){
			isAvailable = available;
		}
	}
	
	class LibrarySystemUser{
		private int userNumber;
		private String userName;
		private String firstName;
		private String lastName;
		private String password;
		private boolean isEmployee;
		
		public int getUserNumber(){
			return userNumber;
		}
		public void setUserNumber(int number){
			userNumber = number;
		}
		public String getUserName(){
			return userName;
		}
		public void setUserName(String uname){
			userName = uname;
		}
		public String getFirstName(){
			return firstName;
		}
		public void setFirstName(String fname){
			firstName = fname;
		}
		public String getLastName(){
			return lastName;
		}
		public void setLastName(String lname){
			lastName = lname;
		}
		public String getPassword(){
			return password;
		}
		public void setPassword(String pw){
			password = pw;
		}
		public boolean getIsEmployee(){
			return isEmployee;
		}
		public void setIsEmployee(boolean employee){
			isEmployee = employee;
		}
	}	
}