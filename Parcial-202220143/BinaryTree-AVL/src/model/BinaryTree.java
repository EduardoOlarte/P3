package model;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import structure.MyStack;

public class BinaryTree<T> implements ITree<T> {
    private NodeTree<T> root;
    private Comparator<T> comparator;

    public BinaryTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    @Override
    public boolean add(T value) {
        if (root == null) {
            root = new NodeTree<>(value);
            return true;
        }
        root = addRecursive(root, value);
        return true;
    }

    private NodeTree<T> addRecursive(NodeTree<T> node, T value) {
        if (node == null) {
            return new NodeTree<>(value);
        }
        int cmp = comparator.compare(value, node.getData());
        if (cmp < 0) {
            node.setLeft(addRecursive(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(addRecursive(node.getRight(), value));
        } else {
            return node; // No se permiten duplicados
        }

        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

        return balance(node);
    }

    private NodeTree<T> balance(NodeTree<T> node) {
        int balanceFactor = getBalance(node);

        if (balanceFactor > 1 && getBalance(node.getLeft()) >= 0) {
            return rotateRight(node);
        }

        if (balanceFactor < -1 && getBalance(node.getRight()) <= 0) {
            return rotateLeft(node);
        }

        if (balanceFactor > 1 && getBalance(node.getLeft()) < 0) {
            node.setLeft(rotateLeft(node.getLeft()));
            return rotateRight(node);
        }

        if (balanceFactor < -1 && getBalance(node.getRight()) > 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }

        return node;
    }

    private int height(NodeTree<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    private int getBalance(NodeTree<T> node) {
        return node == null ? 0 : height(node.getLeft()) - height(node.getRight());
    }

    private NodeTree<T> rotateRight(NodeTree<T> y) {
        NodeTree<T> x = y.getLeft();
        NodeTree<T> T2 = x.getRight();

        x.setRight(y);
        y.setLeft(T2);

        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

        return x;
    }

    private NodeTree<T> rotateLeft(NodeTree<T> x) {
        NodeTree<T> y = x.getRight();
        NodeTree<T> T2 = y.getLeft();

        y.setLeft(x);
        x.setRight(T2);

        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);

        return y;
    }

    @Override
    public boolean remove(T value) {
        if (contains(value)) {
            root = removeRecursive(root, value);
            return true;
        }
        return false;
    }

    private NodeTree<T> removeRecursive(NodeTree<T> node, T value) {
        if (node == null) {
            return null;
        }

        int cmp = comparator.compare(value, node.getData());
        if (cmp < 0) {
            node.setLeft(removeRecursive(node.getLeft(), value));
        } else if (cmp > 0) {
            node.setRight(removeRecursive(node.getRight(), value));
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                NodeTree<T> temp = (node.getLeft() != null) ? node.getLeft() : node.getRight();
                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                NodeTree<T> temp = findMin(node.getRight());
                node.setData(temp.getData());
                node.setRight(removeRecursive(node.getRight(), temp.getData()));
            }
        }

        if (node == null) {
            return node;
        }

        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);

        return balance(node);
    }

    private NodeTree<T> findMin(NodeTree<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(NodeTree<T> current, T value) {
        if (current == null) {
            return false;
        }
        int cmp = comparator.compare(value, current.getData());
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return containsRecursive(current.getLeft(), value);
        } else {
            return containsRecursive(current.getRight(), value);
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Comparator<T> comparator() {
        return comparator;
    }

    public void printTree() {
        if (root == null) {
            System.out.println("Árbol vacío");
        } else {
            printTree(root, 0);
        }
    }

    private void printTree(NodeTree<T> node, int level) {
        if (node != null) {
            // Imprimir el nodo actual con la indentación correspondiente a su nivel
            System.out.println(repeatSpaces(level * 2) + node.getData());

            // Llamar recursivamente al hijo izquierdo y derecho con un nivel mayor
            printTree(node.getLeft(), level + 1);
            printTree(node.getRight(), level + 1);
        }
    }

    // Método auxiliar para reemplazar .repeat()
    private String repeatSpaces(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }




    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator(root);
    }

    private class InOrderIterator implements Iterator<T> {
        private MyStack<NodeTree<T>> stack;
        private NodeTree<T> current;

        public InOrderIterator(NodeTree<T> root) {
            stack = new MyStack<>();
            current = root;
            pushLeft(current);
        }

        private void pushLeft(NodeTree<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            NodeTree<T> node = stack.pop();
            T result = node.getData();
            if (node.getRight() != null) {
                pushLeft(node.getRight());
            }
            return result;
        }
    }
}
