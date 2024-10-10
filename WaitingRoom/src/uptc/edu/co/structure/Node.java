package uptc.edu.co.structure;

public class Node<T> {
	private T data;
	private int priority;
	private Node<T> next;
	private Node<T> previous;

	public Node(T data, int priority) {
		this.data = data;
		this.priority = priority;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", priority=" + priority + ", next=" + next + "]";
	}
}
