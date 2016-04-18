/**
 * LibrarySystemUser class represents a library user, 
 * This user could be working in the library or a patron
 */

 public class LibrarySystemUser{
	private String userName;
	private boolean isAdmin;
	
	public LibrarySystemUser(String name, boolean admin){
		userName = name;
		isAdmin = admin;
	}
	
	public String getName () {
		return userName;
	}
	public boolean getIsAdmin () {
		return isAdmin;
	}
}