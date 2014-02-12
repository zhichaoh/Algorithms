public class Deque<Item> implements Iterable<Item> {
  private Item[] items;
  private int count;
  private int head;
  private int tail;
  public Deque() {
    items = (Item[]) new Object[16];
    count = 0;
    head = tail = 0;

  }

  public boolean isEmpty() {
    return head == tail;
  }

  public int size() {
    return (tail - head) & (items.length - 1);
  }

  private void doubleCapacity() {
    assert head == tail;
    int p = head;
    int n = items.length;
    int r = n - p;

    int newCapacity = n << 1;
    if (newCapacity < 0)
      throw new IllegalStateException("Deque too big!");

    Object[] temp = new Object[newCapacity];
    System.arraycopy(items, p, temp, 0, r);
    System.arraycopy(items, 0, temp, r, p);
    items = (Item[]) temp;

    head = 0;
    tail = n;
  }

  private void shrink() {
    if (size() >= items.length / 4) return;
    int p = head;
    int n = items.length;
    int r = n - p;
    StdOut.printf("p=%d, n=%d, r=%d\n", p, n, r);
    StdOut.printf("size=%d\n", size());

    int newCapacity = n >> 1;
    Object[] temp = new Object[newCapacity];

    if (head < tail) {
      System.arraycopy(items, head, temp, 0, size());
      for (Object i : temp) {
        StdOut.print(i + " ");
      }
      StdOut.println();
    }
    else if (head > tail) {
      int headPartSize = items.length - head;
      StdOut.printf("headPartSize=%d\n", headPartSize);
      StdOut.printf("tail=%d\n", tail);
      System.arraycopy(items, head, temp, 0, headPartSize);
      System.arraycopy(items, 0, temp, headPartSize, tail);
      for (Object i : temp) {
        StdOut.print(i + " ");
      }
      StdOut.println();
    }
    tail = size();
    StdOut.printf("after copy tail=%d\n", tail);
    head = 0;
  }

  public void addFirst(Item item) {
    if (item == null) throw new NullPointerException();
    items[head = (head-1) & (items.length - 1)] = item; 
    if (head == tail) doubleCapacity();
  }

  public void addLast(Item item) {
    if (item == null) throw new NullPointerException();

    items[tail] = item;
    tail = (tail + 1) & (items.length - 1);
    if (tail == head) doubleCapacity();
  }

  public Item removeFirst() {
    if (isEmpty()) throw new java.util.NoSuchElementException();

    shrink();
    int h = head;
    Item item = items[h];
    items[h] = null;
    head = (h+1) & (items.length + 1);
    return item;
  }

  public Item removeLast() {
    if (isEmpty()) throw new java.util.NoSuchElementException();

    shrink();
    int t = (tail-1) & (items.length - 1);
    Item item = items[t];
    items[t] = null;
    tail = t;
    StdOut.println("removed item=" + item);
    return item;
  }

  public java.util.Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements java.util.Iterator<Item> {
    private int cursor = head;
    private int fence = tail;

    public boolean hasNext() {
      return cursor != fence;
    }

    public Item next() {
      if (cursor == fence) throw new java.util.NoSuchElementException();

      Item result = items[cursor];

      cursor = (cursor + 1) & (items.length-1);
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
  }
}
