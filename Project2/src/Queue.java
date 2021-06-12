class Queue {
	Node front, rear;

	public Queue() {
		front = rear = null;
	}
	
	public void clear() {
		front = rear = null;
	}
	
	public void enqueue(Share data) {
		Node newNode = new Node(data);
		if (rear == null) {
			front = rear = newNode;
			return;
		}
		this.rear.setNext(newNode);
		this.rear = newNode;
	}

	public boolean isEmpty() {
		if (front == null) {
			return true;
		}
		else
			return false;
	}
	public Node dequeue() {
		if (isEmpty()) {
			System.out.println("Sorry the Queue is Empty");
			return null;
		}

		Node  toDel = front;
		if (front != null)
			front = front.getNext();
		
		
		if (this.front == null)
			this.rear = null;
		return toDel;
	}
	
	public void print() { // and in this function we print data
		Node curr = front;
		while (curr != null) {
			System.out.print(curr.getData());
			curr = curr.getNext();

		}
	}
}