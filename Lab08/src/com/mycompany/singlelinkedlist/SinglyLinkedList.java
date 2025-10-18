package com.mycompany.singlelinkedlist;

        class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

        class LinkedList {
        private Node head;
        private Node tail;

        public LinkedList() {
            head = null;
            tail = null;
        }

        public void append(Node newNode) {
            if (newNode == null) return;
            newNode.next = null;
            if (head == null) {
                head = newNode;
            } else {
                tail.next = newNode;
            }
            tail = newNode;
        }

        public void prepend(Node newNode) {
            if (newNode == null) return;
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head = newNode;
            }
        }

        public void insertAfter(Node currentNode, Node newNode) {
            if (newNode == null) return;
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else if (currentNode == tail) {
                tail.next = newNode;
                tail = newNode;
            } else {
                newNode.next = currentNode.next;
                currentNode.next = newNode;
            }
        }

        public void removeAfter(Node currentNode) {
            if (currentNode == null && head != null) {
                Node succeedingNode = head.next;
                head = succeedingNode;
                if (succeedingNode == null) tail = null;
            } else if (currentNode != null && currentNode.next != null) {
                Node succeedingNode = currentNode.next.next;
                currentNode.next = succeedingNode;
                if (succeedingNode == null) tail = currentNode; // removed tail
            }
        }


        public long sum() {
            long s = 0;
            for (Node c = head; c != null; c = c.next) s += c.data;
            return s;
        }

        public void print() {
            for (Node c = head; c != null; c = c.next) System.out.print(c.data + " ");
            System.out.println();
        }


        public long moveDigit3To(LinkedList dst) {
            long sum = 0;
            Node prev = null, cur = head;
            while (cur != null) {
                Node nxt = cur.next;                 // save next first
                if (sumOfPrimes.hasDigit3(cur.data)) {
                    // unlink from this list
                    if (prev == null) head = nxt;
                    else prev.next = nxt;
                    if (cur == tail) tail = prev;

                    // append to destination as a single node
                    cur.next = null;
                    dst.append(cur);

                    sum += cur.data;
                } else {
                    prev = cur;
                }
                cur = nxt;
            }
            return sum;
        }
    }


        class sumOfPrimes {
        static boolean isPrime(int n) {
            if (n < 2) return false;
            return isPrime(n, 2);
        }

        private static boolean isPrime(int n, int d) {
            if ((long) d * d > n) return true; // no divisor up to sqrt(n)
            if (n % d == 0) return false;
            return isPrime(n, d + 1);
        }

        static boolean hasDigit3(int x) {
            while (x > 0) {
                if (x % 10 == 3) return true;
                x /= 10;
            }
            return false;
        }

        static LinkedList primesUpTo(int n) {
            LinkedList list = new LinkedList();
            for (int x = 2; x <= n; x++) if (isPrime(x)) list.append(new Node(x));
            return list;
        }
    }






