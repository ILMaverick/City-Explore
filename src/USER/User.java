package USER;

public class User {
	public String id;
	public String name;
	public String surname;
	public String username;
	public String email;
	public String password;
	public Role role;
	
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
