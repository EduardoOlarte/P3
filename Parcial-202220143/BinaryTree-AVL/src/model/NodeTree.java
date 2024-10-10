package model;


public class NodeTree<T> {
    T data;
    NodeTree<T> left;
    NodeTree<T> right;
    int height; // Para llevar la altura del nodo en el árbol AVL

    public NodeTree(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1; // Al inicio, la altura de un nodo recién creado es 1.
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public NodeTree<T> getLeft() {
		return left;
	}

	public void setLeft(NodeTree<T> left) {
		this.left = left;
	}

	public NodeTree<T> getRight() {
		return right;
	}

	public void setRight(NodeTree<T> right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "NodeTree [data=" + data + ", left=" + left + ", right=" + right + ", height=" + height + "]";
	}


    
}
