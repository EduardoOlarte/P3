package uptc.edu.co.structure;

public class MyQueue<T> {

	private Node<T> front;
	private Node<T> rear;
	private int size;

	public MyQueue() {
		front = null;
		rear = null;
		size = 0;
	}

	public void push(T data, int priority) {
		Node<T> newNode = new Node<>(data, priority);

		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else if (front.getPriority() > priority) {
			newNode.setNext(front);
			front.setPrevious(newNode);
			front = newNode;
		} else {
			Node<T> current = front;
			while (current.getNext() != null && current.getNext().getPriority() <= priority) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			if (current.getNext() != null) {
				current.getNext().setPrevious(newNode);
			} else {
				rear = newNode;
			}
			current.setNext(newNode);
			newNode.setPrevious(current);
		}

		size++;
	}

	public T poll() {
		if (isEmpty()) {
			throw new IllegalStateException("Vacio");
		}
		T data = front.getData();
		front = front.getNext();
		if (front == null) {
			rear = null;
		} else {
			front.setPrevious(null);
		}
		size--;
		return data;
	}

	public T peek() {
		if (isEmpty()) {
			throw new IllegalStateException("vacio");
		}
		return front.getData();
	}

	public boolean isEmpty() {
		return front == null;
	}

	public boolean exist(T data) {
		Node<T> current = front;
		while (current != null) {
			if (current.getData().equals(data)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	public int size() {
		return size;
	}
}
