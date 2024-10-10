package uptc.edu.co.structure;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;

public class MySimpleList<T> implements List<T> {
    private Node<T> head;
    private Node<T> last;

    @Override
    public int size() {
        Node<T> aux = head;
        int count = 0;
        while (aux != null) {
            count++;
            aux = aux.getNext();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> aux = head;
        while (aux != null) {
            if (aux.getData().equals(o)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> auxnode = head;

            @Override
            public boolean hasNext() {
                return auxnode != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = auxnode.getData();
                auxnode = auxnode.getNext();
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node<T> aux = head;
        for (int i = 0; i < size(); i++) {
            array[i] = aux.getData();
            aux = aux.getNext();
        }
        return array;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < this.size()) {
            return (E[]) toArray();
        } else {
            Node<T> aux = head;
            for (int i = 0; i < size(); i++) {
                a[i] = (E) aux.getData();
                aux = aux.getNext();
            }
            return a;
        }
    }

    @Override
    public boolean add(T e) {
        Node<T> newNode = new Node<>(e);

        if (head == null) {
            head = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> current = head;
        Node<T> previous = null;

        while (current != null) {
            if (current.getData().equals(o)) {
                if (current == head) {
                    head = current.getNext();
                    if (head == null) {
                        last = null;
                    }
                } else {
                    previous.setNext(current.getNext());
                    if (current == last) {
                        last = previous;
                    }
                }
                return true;
            }
            previous = current;
            current = current.getNext();
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean added = false;
        for (T t : c) {
            if (add(t)) {
                added = true;
            }
        }
        return added;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Node<T> aux = head;
        int i = 0;
        while (aux != null && i < index + c.size()) {
            if (i == index) {
                for (T element : c) {
                    this.add(i, element);
                    i++;
                }
            }
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node<T> current = head;
        Node<T> previous = null;
        boolean modified = false;

        while (current != null) {
            if (!c.contains(current.getData())) {
                if (current == head) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                modified = true;
            } else {
                previous = current;
            }
            current = current.getNext();
        }

        if (head == null) {
            last = null;
        }

        return modified;
    }

    @Override
    public void clear() {
        head = null;
        last = null;
    }

    @Override
    public T get(int index) {
        Node<T> aux = head;
        for (int i = 0; i < index; i++) {
            if (aux == null) {
                throw new IndexOutOfBoundsException();
            }
            aux = aux.getNext();
        }
        return aux.getData();
    }

    @Override
    public T set(int index, T element) {
        Node<T> aux = head;
        for (int i = 0; i < index; i++) {
            if (aux == null) {
                throw new IndexOutOfBoundsException();
            }
            aux = aux.getNext();
        }
        T oldData = aux.getData();
        aux.setData(element);
        return oldData;
    }

    @Override
    public void add(int index, T element) {
        if (index == 0) {
            Node<T> newNode = new Node<>(element);
            newNode.setNext(head);
            head = newNode;
            if (last == null) {
                last = head;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                if (current == null) {
                    throw new IndexOutOfBoundsException();
                }
                current = current.getNext();
            }
            Node<T> newNode = new Node<>(element);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            if (newNode.getNext() == null) {
                last = newNode;
            }
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        Node<T> previous = null;
        T removedData;

        if (index == 0) {
            removedData = head.getData();
            head = head.getNext();
            if (head == null) {
                last = null;
            }
            return removedData;
        }

        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.getNext();
        }

        removedData = current.getData();
        previous.setNext(current.getNext());

        if (current.getNext() == null) {
            last = previous;
        }

        return removedData;
    }

    @Override
    public int indexOf(Object o) {
        Node<T> aux = head;
        int index = 0;
        while (aux != null) {
            if (aux.getData().equals(o)) {
                return index;
            }
            aux = aux.getNext();
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        Node<T> aux = head;
        int index = 0;
        while (aux != null) {
            if (aux.getData().equals(o)) {
                lastIndex = index;
            }
            aux = aux.getNext();
            index++;
        }
        return lastIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not supported for singly linked list.");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported for singly linked list.");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        MySimpleList<T> subList = new MySimpleList<>();
        Node<T> current = head;

        for (int i = 0; i < fromIndex; i++) {
            current = current.getNext();
        }

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.getData());
            current = current.getNext();
        }

        return subList;
    }

    public void replaceAll2(UnaryOperator<T> operator) {
        Node<T> aux = head;
        while (aux != null) {
            T newValue = operator.apply(aux.getData());
            aux.setData(newValue);
            aux = aux.getNext();
        }
    }
}
