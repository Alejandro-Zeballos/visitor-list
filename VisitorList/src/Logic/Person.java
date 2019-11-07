package Logic;

public class Person {
	
	private String name;
	private String passport;
	private String date;
	private String priority;
	private String id;
	
	public Person(String name, String passport, String date, String priority, String id) {
		this.name = name;
		this.passport = passport;
		this.date = date;
		this.priority = priority;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.name + " - Priority: " + this.priority;
	}

}
