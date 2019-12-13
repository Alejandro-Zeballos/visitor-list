package Logic;


import java.util.List;
;

public class DList {

	
	private Node head;
	private Node tail;
	private int size;
	private Node lastHigh;
	private Node lastMedium;
	private List<Person> list;
	private Database db;
	
	public DList() {
		//refill with the database people in the quee, create nodes for each person
		db = new Database();
		size = 0;
		lastHigh = null;
		lastMedium = null;
		
		
		list = db.getVisitorList(); //this will return and ArrayList of visitor in from the database
		if(list != null) {
			//this for each iteration will populate my doubly linked list in order
			for(Person person: list) {
				addVisitor(person);
			}
		}
	}
	
	public void lookAfter() {
		
		db.removeVisitor(head.getPerson().getId());
		head = head.getNext();
		head.setPrev(null);
			
			
	
	}
	
	public void removeVisitor(String id) {
		System.out.println(id);
		db.removeVisitor(id);
		
	}
	
	public int checkPosition(String id) {
		int iterations = 0;
    	Node tmp = head;
    	System.out.println("geting values if uterations inside getPerson method");
        while(tmp != null){
        	iterations++;
        	System.out.println(iterations);
            if(tmp.getPerson().getId().equals(id)) {
            	
            	return iterations;
            }
            tmp = tmp.getNext();
        }
        return 0;
	}
	public void removeTail(String id) {
		System.out.println(id);
		db.removeVisitor(id);
		tail.getPrev().setNext(null);
		tail = tail.getPrev();
		
	}
	
	public void moveForward() {
		
	}
	
	public void addVisitor(Person person) {
		
		size++;
		
		if(head == null) {
			Node newNode = new Node(null, null, person);
			head= newNode;
			tail = newNode;
			return;
		}
		
		if(person.getPriority().equals("LOW")) {
			Node newNode = new Node(tail, null, person);
			tail.setNext(newNode);
			tail = newNode;

		}
		
		if(person.getPriority().equals("MEDIUM")) {
	
			if(head.getPerson().getPriority().equals("LOW")) {
				Node newNode = new Node(null, head, person);
				head.setPrev(newNode);
				head = newNode;
				return;
			}
			
			Node loopingNode = head;
			
			while(loopingNode!=null) {
				if(loopingNode.getPerson().getPriority().equals("LOW")) {
					Node newNode = new Node(loopingNode.getPrev(), loopingNode, person);
					loopingNode.getPrev().setNext(newNode);
					loopingNode.setPrev(newNode);
					return; 
				}
				loopingNode = loopingNode.getNext();
			}
			
			
			Node newNode = new Node(tail, null, person);
			tail.setNext(newNode);
			tail = newNode;
			System.out.println("After adding a person");
			iterateForward();
		}
	////////////////////////////////////////////////////////////	
		if(person.getPriority().equals("HIGH")) {
			String headPriority = head.getPerson().getPriority();
			if(headPriority.equals("LOW")||headPriority.equals("MEDIUM")) {
				Node newNode = new Node(null, head, person);
				head.setPrev(newNode);
				head = newNode;
				return;
			}
			
			Node loopingNode = head;
			
			while(loopingNode!=null) {
				String nodePriority = loopingNode.getPerson().getPriority();
				if(nodePriority.equals("LOW")|| nodePriority.equals("MEDIUM")) {
					Node newNode = new Node(loopingNode.getPrev(), loopingNode, person);
					loopingNode.getPrev().setNext(newNode);
					loopingNode.setPrev(newNode);
					return;
				}
				loopingNode = loopingNode.getNext();
			}
			
			
			Node newNode = new Node(tail, null, person);
			tail.setNext(newNode);
			tail = newNode;
		}
		/////////////////////////////////////////////////////////////////////
	}

	//////////////////////////////////////////////////////////////////
    public void iterateForward(){
    	System.out.println("");
        System.out.println("-------------------------------------------------------");
        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null){
            System.out.println(tmp.getPerson().getPriority() +" - " + tmp.getPerson().getName() + " - ID: " + tmp.getPerson().getId());
            tmp = tmp.getNext();
        }
    }
    //////////////////////////////////////////////////////////////////////////
    
    public Person getPerson(String id) {
    	Node tmp = head;
        while(tmp != null){
            if(tmp.getPerson().getId().equals(id)) {
            	return tmp.getPerson();
            }
            tmp = tmp.getNext();
        }
        return null;
    }
    
	
    /////////////////////////////////////////////////////////////////////////
	public Node getHead() {
		return head;
	}
	public void setHead(Node head) {
		this.head = head;
	}
	public Node getTail() {
		return tail;
	}
	public void setTail(Node tail) {
		this.tail = tail;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Node getLastHigh() {
		return lastHigh;
	}
	public void setLastHigh(Node lastHigh) {
		this.lastHigh = lastHigh;
	}
	public Node getLastMedium() {
		return lastMedium;
	}
	public void setLastMedium(Node lastMedium) {
		this.lastMedium = lastMedium;
	}

	
	
	
}
