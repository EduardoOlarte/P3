package structure;


import java.util.ArrayList;
import java.util.List;

public class NodeNari<T> {
    private T data;
    private List<NodeNari<T>> children;

    public NodeNari(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<NodeNari<T>> getChildren() {
        return children;
    }

    public void addChild(NodeNari<T> child) {
        this.children.add(child);
    }

    public void removeChild(NodeNari<T> child) {
        this.children.remove(child);
    }

    @Override
    public String toString() {
        return "NodeNAry [data=" + data + ", children=" + children + "]";
    }
}

