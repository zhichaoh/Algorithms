public class Deque<Item> implements Iterable<Item> {
  private class Node<Item> {
    private Item item;
    private Node next;
    private Node prev;
  };

  private int count = 0;
  private Node<Item> head = new Node<Item>();
  private Node<Item> tail = new Node<Item>();
  public Deque() {
    head = new Node<Item>();
    tail = new Node<Item>();
    head.next = tail;
    tail.prev = head;
  }

  public boolean isEmpty() {
    return count == 0;  
  }

  public int size() {
    return count;
  }


  public void addFirst(Item item) {
    if (item == null) throw new NullPointerException();
    Node<Item> node = new Node<Item>();
    node.item = item;

    Node oldHead = head.next;
    node.next = oldHead;
    head.next = oldHead;
    if (oldHead.prev != null) {
      oldHead.prev = node;
    }
    head.next = node;
    node.prev = head;
    count++;
  }

  public void addLast(Item item) {
    if (item == null) throw new NullPointerException();

    Node<Item> node = new Node<Item>();
    node.item = item;

    Node oldTail = tail.prev;
    oldTail.next = node;
    node.prev = oldTail;

    tail.prev = node;
    node.next = tail;
    count++;
  }

  public Item removeFirst() {
    if (count == 0) throw new java.util.NoSuchElementException();

    Node<Item> first = head.next;
    head.next = first.next;
    first.next.prev = head;
    count--;

    Item result = first.item;
    // make sure first's prev and next reference are free'd
    first.next = null;
    first.prev = null;
    first = null;
    return result;
  }

  public Item removeLast() {
    if (isEmpty()) throw new java.util.NoSuchElementException();

    Node<Item> last = tail.prev;
    tail.prev = last.prev;
    last.prev.next = tail;
    count--;
    Item result = last.item;
    // make sure last's prev and next reference are free'd
    last.next = null;
    last.prev = null;
    last = null;
    return result;
  }

  public java.util.Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements java.util.Iterator<Item> {
    private Node<Item> cursor = head.next;

    public boolean hasNext() {
      return (count != 0) && (cursor != tail);
    }

    public Item next() {
      if (count == 0 || cursor == tail) throw new java.util.NoSuchElementException();

      Item result = cursor.item;
      cursor = cursor.next;
      return result;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    Deque<Integer> items = new Deque<Integer>();
    for (int i = 0; i < 33; ++i) {
      items.addFirst(i);
    }

    for (Integer i : items) {
      StdOut.printf("%d ", i);
    }
    StdOut.println();

    for (int i = 0; i < 24; ++i) {
      items.removeLast();
    }

    StdOut.println("=====after removal====");
    for (Integer i : items) {
      StdOut.printf("%d ", i);
    }
    StdOut.println();

    for (int i = 0; i < 5; ++i) {
      items.removeFirst();
    }
    for (Integer i : items) {
      StdOut.printf("%d ", i);
    }
    StdOut.println();
  }
}
