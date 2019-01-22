package ch.nyp;

public class Beer {
	
	private String id;
	private String name;
	private String description;
	private int idStyle;
	
	public Beer(String id, String name, String description, int idStyle) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.idStyle = idStyle;
	}
	
	@Override
	public String toString() {
		return "Beer [id=" + id + "; name=" + name + "; description=" + description + "; idStyle=" + idStyle + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdStyle() {
		return idStyle;
	}

	public void setIdStyle(int idStyle) {
		this.idStyle = idStyle;
	}

}
