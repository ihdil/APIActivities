package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class HomeWork15Pojo{
	private String _id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_id() {
		return _id;
	}

	@Override
 	public String toString(){
		return 
			"HomeWork15Pojo{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",password = '" + password + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}
