package Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SDAProject {
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonProperty("group_type_id")
	private Integer groupTypeId;
	private List<Object> roles;
	private String name;

	public void setGroupTypeId(Integer groupTypeId){
		this.groupTypeId = groupTypeId;
	}

	public Integer getGroupTypeId(){
		return groupTypeId;
	}

	public void setRoles(List<Object> roles){
		this.roles = roles;
	}

	public List<Object> getRoles(){
		return roles;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"group_type_id = '" + groupTypeId + '\'' + 
			",roles = '" + roles + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}