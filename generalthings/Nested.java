package generalthings;

import java.util.Iterator;

class Stack {
    private Node head;

    // Static inner-class implementation: -> We need to be able to create Nodes from
    // outside so we prefer having it static.
    // If it can be STATIC, make it Static.
    public static class Node {
        private int value;
        private Node next;
    }

    void add(int val) {
        Node newNode = new Node();
        newNode.value = val;
        newNode.next = head;
        if (head == null) {
            head = newNode;
            return;
        }
        head = newNode;
    }

    // Non-Static inner-class Implementation -> We DONT need to be able to create
    // Iterator from the outside, so we don't make it static.

    // Allows the inner-class to have access to the 'private head' of the father
    // class Stack while keeping it private.
    public class Iterator implements java.util.Iterator {

        private Node current;

        Iterator() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
        }

        @Override
        public Object next() {
            int value = current.value;
            current = current.next;
            return value;
        }
    }
}

public class Nested {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Stack.Node node = new Stack.Node(); // can be created without outerclass instance (if Node is not private)
        System.out.println();
        for (int i = 0; i < 10; i++) {
            stack.add(i);
        }

        // Wont work:
        // Iterator iter = new Stack.Iterator();
        // Iterator iter = stack.new Iterator();

        // must be tied to an existing outer class instance! hence the special syntax:
        for (Iterator iter = stack.new Iterator(); iter.hasNext();) {
            System.out.println(iter.next());
        }
    }
}
