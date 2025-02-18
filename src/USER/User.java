package USER;

public class User {
	public String id;
	public String name;
	public String surname;
	public String username;
	public String email;
	public String password;
	public Role role;

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	 public String getUsername() {
		 return username;
	 }
	 public void setUsername(String username) {
		 this.username = username;
	 }
	    
	 @Override
	 public String toString() {
		 return "User: " + username;
	 }


}
