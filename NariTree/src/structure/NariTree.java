package structure;

import java.util.*;

public class NariTree<T> implements Iterable<T> {
	private NodeNari<T> root;
	private Comparator<T> comparator;

	public NariTree(Comparator<T> comparator, T rootValue) {
		this.comparator = comparator;
		this.root = new NodeNari<>(rootValue); 
	}

	public boolean add(T value, T parentValue) {
		if (root == null) {
			root = new NodeNari<>(value);
			return true;
		}

		NodeNari<T> parentNode = search(root, parentValue);
		if (parentNode != null) {
			insertOrdered(parentNode.getChildren(), new NodeNari<>(value));
			return true;
		}

		return false; 
	}

	private void insertOrdered(List<NodeNari<T>> children, NodeNari<T> newNode) {
		int i = 0;
		while (i < children.size() && comparator.compare(newNode.getData(), children.get(i).getData()) > 0) {
			i++;
		}
		children.add(i, newNode);
	}

	public boolean contains(T value) {
		return search(root, value) != null;
	}

	private NodeNari<T> search(NodeNari<T> current, T value) {
		if (current == null) {
			return null;
		}
		if (current.getData().equals(value)) {
			return current;
		}
		for (NodeNari<T> child : current.getChildren()) {
			NodeNari<T> result = search(child, value);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	public boolean remove(T parentValue, T childValue) {
		NodeNari<T> parentNode = search(root, parentValue);
		if (parentNode == null) {
			return false;
		}

		return parentNode.getChildren().removeIf(child -> child.getData().equals(childValue));
	}

	@Override
	public Iterator<T> iterator() {
		List<T> nodesList = new ArrayList<>();
		fillList(root, nodesList);
		return nodesList.iterator();
	}

	private void fillList(NodeNari<T> node, List<T> nodesList) {
		if (node != null) {
			nodesList.add(node.getData());
			for (NodeNari<T> child : node.getChildren()) {
				fillList(child, nodesList);
			}
		}
	}
}
