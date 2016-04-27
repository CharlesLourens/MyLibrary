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
		this.addBook("Lord of the Rings", "JRR Tolkin");
		this.addBook("Room", "Emma Donoghue");
		this.addBook("Being Ernest", "Oscar Wilde");

		this.users = new ArrayList<LibrarySystemUser>();
		this.addUser("admin", "admin", "Library", "Admin", true);

	}

	private boolean showInterface(){
		LibrarySystemUser activeUser = null;
		System.out.println("Select your Action");	
		System.out.println("1-Login");
		System.out.println("9-Exit");
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
			System.out.println("2-Loan a Book");
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
				if (s.toUpperCase().equals("LOAN A BOOK") || s.substring(0).equals("2")){
					System.out.println("Please enter the book number, from the list below, to loan the book");
					for (int i = 0; i < books.size(); i++) {
						LibraryBook book = books.get(i);
						if (book.getIsAvailable()){
							System.out.println(book.getBookNumber()+" - "+book.getBookName()+" ("+book.getBookAuthor()+")");							
						}
					}
					try {
						s = new BufferedReader(new InputStreamReader(System.in)).readLine();
						if (s.length() > 0){
							int bookNumber = Integer.parseInt(s);
							System.out.println(activeUser.loanBook(bookNumber));
						} 
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					} catch (NumberFormatException nfe){
						System.out.println("That wasn't a valid book number");
					}
				} else if (s.toUpperCase().equals("RETURN A BOOK") || s.substring(0).equals("3")){
					List<Integer> loanedBookNumbers = activeUser.getListBooksLoaned();
					if (loanedBookNumbers.size() > 0 ){
						System.out.println("Please enter the book number, from the list below, that you wish to return");
						for (int i = 0; i < loanedBookNumbers.size(); i++) {
							LibraryBook book = books.get(loanedBookNumbers.get(i));
							System.out.println(book.getBookNumber()+" - "+book.getBookName()+" ("+book.getBookAuthor()+")");							
						}
						try {
							s = new BufferedReader(new InputStreamReader(System.in)).readLine();
							if (s.length() > 0){
								int bookNumber = Integer.parseInt(s);
								System.out.println(activeUser.returnBook(bookNumber));
							} 
						} catch (IOException ioe){
							System.out.println("IOE thrown");
							return false;
						} catch (NumberFormatException nfe){
							System.out.println("That wasn't a valid book number");
						}					
					} else {
						System.out.println("You haven't got any books");
					}
				} else if (s.toUpperCase().equals("ADD A BOOK") || s.substring(0).equals("4")){
					String bookName, authorName;
					System.out.println("Please enter the book name");
					try {
						bookName = new BufferedReader(new InputStreamReader(System.in)).readLine();
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					}
					System.out.println("Please enter the Author's name");
					try {
						authorName = new BufferedReader(new InputStreamReader(System.in)).readLine();
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					}
					this.addBook(bookName, authorName);
				} else if (s.toUpperCase().equals("REMOVE A BOOK") || s.substring(0).equals("5")){
					System.out.println("Please enter the book number, from the list below, to remove");
					for (int i = 0; i < books.size(); i++) {
						LibraryBook book = books.get(i);
						if (book.getIsAvailable()){
							System.out.println(book.getBookNumber()+" - "+book.getBookName()+" ("+book.getBookAuthor()+")");							
						}
					}
					try {
						s = new BufferedReader(new InputStreamReader(System.in)).readLine();
						if (s.length() > 0){
							int bookNumber = Integer.parseInt(s);
							LibraryBook removedBook = books.get(bookNumber);
							books.remove(removedBook);
							System.out.println("Book removed!");
						} 
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					} catch (NumberFormatException nfe){
						System.out.println("That wasn't a valid book number");
					}
				} else if (s.toUpperCase().equals("ADD A LIBRARY USER") || s.substring(0).equals("6")){
					String libraryUserName, passW, firstName, lastName, admin;
					Boolean isAdmin = false;
					System.out.println("Is this a new employee? (Y/N) Default is 'N'");
					try {
						admin = new BufferedReader(new InputStreamReader(System.in)).readLine();
						if (admin.substring(0).toUpperCase().equals("Y")){
							isAdmin = true;
						}
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					}
					System.out.println("Please enter the client's first name");
					try {
						firstName = new BufferedReader(new InputStreamReader(System.in)).readLine();
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					}
					System.out.println("Please enter the client's last name");
					try {
						lastName = new BufferedReader(new InputStreamReader(System.in)).readLine();
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					}
					System.out.println("Please enter your userName");
					try {
						libraryUserName = new BufferedReader(new InputStreamReader(System.in)).readLine();
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					}
					passW = "Password";
					Boolean validPassword = false;
					while (!validPassword){
						System.out.println("Please enter your password. The password must be at least 4 characters");
						try {
							passW = new BufferedReader(new InputStreamReader(System.in)).readLine();
							if (passW.length() > 3){
								validPassword = true;
							}
						} catch (IOException ioe){
							System.out.println("IOE thrown");
							return false;
						}
					}
					this.addUser(libraryUserName, passW, firstName, lastName, isAdmin);
				} else if (s.toUpperCase().equals("REMOVE A LIBRARY USER") || s.substring(0).equals("7")){
					System.out.println("Please enter the user number, from the list below, to remove");
					for (int i = 0; i < users.size(); i++) {
						LibrarySystemUser user = users.get(i);
						System.out.println(user.getUserNumber()+" - "+user.getFirstName()+" "+user.getLastName()+" ("+user.getIsEmployee()+")");							
					}
					try {
						s = new BufferedReader(new InputStreamReader(System.in)).readLine();
						if (s.length() > 0){
							int userNumber = Integer.parseInt(s);
							LibrarySystemUser removedUser = users.get(userNumber);
							if (removedUser.equals(activeUser)){
								System.out.println("You cannot remove yourself!!");
							} else {
								users.remove(removedUser);
								System.out.println("User account removed!");
							}
						} 
					} catch (IOException ioe){
						System.out.println("IOE thrown");
						return false;
					} catch (NumberFormatException nfe){
						System.out.println("That wasn't a valid user number");
					}
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
	

	private void addBook(String name, String author){
		int i = books.size();
		LibraryBook book = new LibraryBook();
		book.setBookName(name);
		book.setBookAuthor(author);
		book.setBookNumber(i++);
		book.setIsAvailable(true);
		books.add(book);
	}

	private void addUser(String uName, String password, String fName, String lName, boolean admin){
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
		private List<Integer> booksLoaned = new ArrayList<Integer>(5);
		
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
		public List<Integer> getListBooksLoaned(){
			return booksLoaned;
		}
		public void setListBooksLoaned(List<Integer> bookList){
			booksLoaned = bookList;
		}
		public String loanBook(int bookNumber){
			if (booksLoaned.size() > 4){
				return "You cannot loan this book, please return one of your books first";
			} else {
				Iterator<LibraryBook> i = books.iterator();
				while (i.hasNext()){
					LibraryBook book = i.next();
					if (book.getBookNumber() == bookNumber) {
						booksLoaned.add(Integer.valueOf(bookNumber));
						book.setIsAvailable(false);
						return "Thank you, enjoy reading your book";
					}
				}
				return "That selection is not available";
			}
		}
		public String returnBook(int bookNumber){
			Iterator<LibraryBook> i = books.iterator();
			while (i.hasNext()){
				LibraryBook book = i.next();
				if (book.getBookNumber() == bookNumber) {
					booksLoaned.remove(Integer.valueOf(bookNumber));
					book.setIsAvailable(true);
					return "Thank you for returning the book";
				}
			}
			return "That selection is not available";
		}
	}	
}