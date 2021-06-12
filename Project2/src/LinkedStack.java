
public class LinkedStack {
	private Node topNode;

	public void push(Share data) {
		Node  newNode = new Node (data);
		newNode.setNext(topNode);
		topNode = newNode;
	}

	public Node pop() {
		Node  toDel = topNode;
		if (topNode != null)
			topNode = topNode.getNext();
		return toDel;
	}

	public boolean isEmpty() {
		return topNode == null;
	}

	public void clear() {
		topNode = null;
	}

	public void print() { 
		Node curr = topNode;
		while (curr != null) {
			System.out.print(curr.getData());
			curr = curr.getNext();

		}
	}
	
	public String print2() { 
		Node curr = topNode;
		String S="";
		
		while (curr != null) {
			System.out.print(curr.getData());
			S+=(curr.getData().toString());
			curr = curr.getNext();
			
		}
		return S;
	}

	
	
	
}
