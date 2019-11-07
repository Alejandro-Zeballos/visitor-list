package Logic;

public class Node {

	private Node next;
	private Node prev;
	private Person person;
	private String priority;
	
	public Node() {
		person = null;
		next = null;
		prev = null;
		priority = null;
	}
	
	public Node(Node prev, Node next, Person person) {
		this.prev = prev;
		this.next = next;
		this.person = person;
		this.priority = person.getPriority();
	}
	
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
