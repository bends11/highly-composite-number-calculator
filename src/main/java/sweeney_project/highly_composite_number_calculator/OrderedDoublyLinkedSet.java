package sweeney_project.highly_composite_number_calculator;

public class OrderedDoublyLinkedSet<T extends Comparable<T>> {

    private Node cur;
    private Node start;

    private void next() {
        if (cur.next == null) throw new NullPointerException();
        cur = cur.next;
    }

    private void previous() {
        if (cur.previous == null) throw new NullPointerException();
        cur = cur.previous;
    }

    public T pop() {
        T ret = start.value;

        boolean isCurStart = false;

        try {
            if (cur == start) isCurStart = true;
            start = start.next;
            start.previous.next = null;
            start.previous = null;

            if (isCurStart) cur = start;
        } catch (NullPointerException e) {
            start = null;
            cur = null;
        }

        return ret;
    }

    public void add(T value) {
        if (this.isEmpty()) {
            Node node = new Node(value, null, null);
            start = node;
            cur = node;
        } else {
            this.seekClosest(value);

            if (cur.value.compareTo(value) > 0) {
                this.addBefore(value);
            } else if (cur.value.compareTo(value) < 0) {
                this.addAfter(value);
            }
        }
    }

    private void seekClosest(T value) {
        if (cur.value.compareTo(value) > 0) {
            while (cur.value.compareTo(value) > 0) {
                try {
                    this.previous();
                } catch (NullPointerException e) {
                    break;
                }
            }
        } else if (cur.value.compareTo(value) < 0) {
            while (cur.value.compareTo(value) < 0) {
                try {
                    this.next();
                } catch (NullPointerException e) {
                    break;
                }
            }
        }
    }

    private void addBefore(T value) {
        Node node = new Node(value, cur, cur.previous);

        try {
            cur.previous.next = node;
        } catch (NullPointerException e) {
            start = node;
        }

        cur.previous = node;
    }

    private void addAfter(T value) {
        Node node = new Node(value, cur.next, cur);

        try {
            cur.next.previous = node;
        } catch (NullPointerException e) {
            // Do nothing
        }

        cur.next = node;
    }

    public void print() {
        Node node = start;

        while (node != null) {
            System.out.println("node " + node.value);
            node = node.next;
        }
    }

    @Override
    public String toString() {
        String ret = "[";

        Node node = start;

        if (node != null) {
            ret = ret + node.value.toString();
            node = node.next;
        }

        while (node != null) {
            ret = ret + ", " + node.value.toString();
            node = node.next;
        }
        return ret + "]";
    }

    public boolean isEmpty() {
        return this.start == null;
    }

    private class Node {
        private T value;
        private Node next;
        private Node previous;

        private Node(T value, Node next, Node previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }
}
