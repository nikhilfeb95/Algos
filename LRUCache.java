class LRUCache {
    
    class Node {
        Node prev, next;
        int val;
        int key;
        
        public Node() {
            this.val = -1;
        }

        public Node(int val, int key) {
            this.val = val;
            this.key = key;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        nodes = new Node[10001];
        head = new Node();
        tail = new Node();

        //Setup the sentinal nodes
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node temp = nodes[key];
        if(temp == null)
            return -1;

        // Remove the entry from its current position and move to head
        remove(temp, key);
        add(temp, key);
        return temp.val;
    }
    
    public void put(int key, int value) {
        Node temp = nodes[key];

        // If node already exists move to the front of the linked list
        if(temp != null) {
            remove(temp, key);
            add(temp, key);
        }
        else {
            Node toAdd = new Node(value, key);
            // Time to replace the node
            if(size == capacity) {
                remove(tail.prev, tail.prev.key);
                add(toAdd, key);
            }
            else {
                add(toAdd, key);
                size++;
            }
        }
    }

    void add(Node first, int key) {
        Node next = head.next;
        head.next = first;
        first.prev = head;
        first.next = next;
        next.prev = first;
        nodes[key] = first;
    }

    void remove(Node toRemove, int key) {
        Node prev = toRemove.prev;
        Node next = toRemove.next;

        if(prev != null)
            prev.next = next;
        if(next != null)
            next.prev = prev;
        nodes[key] = null;
    }

    Node [] nodes;
    int size;
    int capacity;
    Node head;
    Node tail;

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(1);
        lru.put(2,1);
        System.out.println(lru.get(1));
    }
}
