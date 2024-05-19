package Pojo;

public class RolesItem{
	private Integer id;

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"RolesItem{" + 
			"id = '" + id + '\'' + 
			"}";
		}
}
