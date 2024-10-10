package structure;

public class MyStack<T> {

	private Node<T> top;
	private int size;

	public MyStack() {
		top = null;
		size = 0;
	}

	public void push(T data) {
		Node<T> newNode = new Node<>(data);
		newNode.setNext(top);
		top = newNode;
		size++;
	}

	public T pop() {
		if (isEmpty()) {
		}
		T data = top.getData();
		top = top.getNext();
		size--;
		return data;
	}

	public T peek() {
		if (isEmpty()) {
		}
		return top.getData();
	}

	public boolean isEmpty() {
		return top == null;
	}

	public boolean exist(T data) {
		Node<T> current = top;
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
