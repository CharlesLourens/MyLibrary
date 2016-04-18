/**
 * LibraryBook class represents a library book
 */

class LibraryBook{
	String bookName;
	boolean isAvailable = true;

	public LibraryBook(String name){
		bookName = name;
	}
	public String getName () {
		return bookName;
	}
	public boolean getIsAvailable () {
		return isAvailable;
	}

}