
public class Node {
	private Node next;
	private Share Data;

	public Node(Share data) {

		this.Data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Share getData() {
		return Data;
	}

	public void setData(Share data) {
		Data = data;
	}
	
	
	
}


