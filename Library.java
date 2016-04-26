/**
 * Library class is the application for checking in books
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

class Library{
	List<LibraryBook> books;
	List<LibrarySystemUser> users;

	public static void main(String[] Args){
		Library library = new Library();
		library.initialize();
		while (library.showInterface()){
			// This is where magic happens...
		}
	}
	private void initialize(){
		this.books = new ArrayList<LibraryBook>();
		this.addBook(books, "Lord of the Rings", "JRR Tolkin");
		this.addBook(books, "Room", "Emma Donoghue");
		this.addBook(books, "Being Ernest", "Oscar Wilde");

		this.users = new ArrayList<LibrarySystemUser>();
		this.addUser(users, "admin", "admin", "Library", "Admin", true);

	}

	private boolean showInterface(){
		LibrarySystemUser activeUser = null;
		System.out.println("Select your Action");	
		System.out.println("1-Login");
		System.out.println("9-Exit");
		int i = 0;
		try {
			String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
			if (s.toUpperCase().equals("LOGIN") || s.substring(0).equals("1")){
				activeUser = userLogin(users);
			} else if (s.toUpperCase().equals("EXIT") || s.substring(0).equals("9")){
				return false;
			}
		} catch (IOException ioe){
			System.out.println("IOE thrown");
			return false;
		}

		while (activeUser != null) {
			System.out.println("Welcome "+activeUser.getFirstName()+" "+activeUser.getLastName());
			System.out.println("What do you want to do today?");
			System.out.println("2-Take out a Book");
			System.out.println("3-Return a book");
			if (activeUser.getIsEmployee()){
				System.out.println("4-Add a Book");
				System.out.println("5-Remove a Book");
				System.out.println("6-Add a Library User");
				System.out.println("7-Remove a Library User");
			}
			System.out.println("8-Logout");
			try {
				String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
				if (s.toUpperCase().equals("TAKE OUT A BOOK") || s.substring(0).equals("2")){
//					takeOutBook(activeUser);
				} else if (s.toUpperCase().equals("RETURN A BOOK") || s.substring(0).equals("3")){
//					returnBook(activeUser);
				} else if (s.toUpperCase().equals("ADD A BOOK") || s.substring(0).equals("4")){
//					addBook(this.books);
				} else if (s.toUpperCase().equals("REMOVE A BOOK") || s.substring(0).equals("5")){
//					removeBook(this.books);
				} else if (s.toUpperCase().equals("ADD A LIBRARY USER") || s.substring(0).equals("6")){
//					addUser(this.users);
				} else if (s.toUpperCase().equals("REMOVE A LIBRARY USER") || s.substring(0).equals("7")){
//					removeUser(this.users);
				} else if (s.toUpperCase().equals("LOGOUT") || s.substring(0).equals("8")){
					activeUser = null;
				} else {
					System.out.println("Not a valid option try using only the option number");
				}				
			} catch (IOException ioe){
				System.out.println("IOE thrown");
				return false;
			}
		}
		return true;
	}
	
	private LibrarySystemUser userLogin(List<LibrarySystemUser> validUsers) {
		System.out.println("Enter UserName and hit enter to login, or leave blank and hit enter to return to main menu");
		try {
			String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
			if (s.length() > 0){
				Iterator<LibrarySystemUser> i = validUsers.iterator();
				while (i.hasNext()){
					LibrarySystemUser thisUser = i.next();
					if (thisUser.getUserName().toUpperCase().equals(s.toUpperCase())) {
						System.out.println("Enter Password and hit enter");
						s = new BufferedReader(new InputStreamReader(System.in)).readLine();
						if (thisUser.getPassword().equals(s)){
							return thisUser;
						} else {
							System.out.println("Invalid Credentials");
							return null;
						}
					}
				}
				System.out.println("UserName not found");
				return null;
			} else {
				return null;
			}
			
		} catch (IOException ioe){
			System.out.println("IOE thrown");
		}
		return null;
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