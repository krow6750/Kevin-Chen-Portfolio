public class AbstractedList<T> implements LinkedList<T> {

    private Node<T> head;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public void add(T t) {
        Node<T> newNode = new Node<>(t);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public void remove(T t) {
        if (head == null) {
            return;
        }

        if (head.data.equals(t)) {
            head = head.next;
            size--;
        } else {
            removeRecursive(head, t);
        }
    }

    private void removeRecursive(Node<T> current, T t) {
        if (current.next == null) {
            return;
        }

        if (current.next.data.equals(t)) {
            current.next = current.next.next;
            size--;
        } else {
            removeRecursive(current.next, t);
        }
    }

    @Override
    public T get(int index) {
        if (head == null) {
            return null;
        }

        Node<T> current = head;
        int i = 0;
        while (current != null) {
            if (i == index) {
                return current.data;
            }
            i++;
            current = current.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
