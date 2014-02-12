public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] items;
  private int count;
  private int head;
  private int tail;
  public RandomizedQueue() {
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

    int newCapacity = n >> 1;
    Object[] temp = new Object[newCapacity];

    if (head < tail) {
      System.arraycopy(items, head, temp, 0, size());
    }
    else if (head > tail) {
      int headPartSize = items.length - head;
      System.arraycopy(items, head, temp, 0, headPartSize);
      System.arraycopy(items, 0, temp, headPartSize, tail);
    }
    items = (Item[]) temp;
    tail = size();
    head = 0;
  }

  /*
  public void addFirst(Item item) {
    if (item == null) throw new NullPointerException();
    items[head = (head-1) & (items.length - 1)] = item; 
    if (head == tail) doubleCapacity();
  }
  */

  public void enqueue(Item item) {
    if (item == null) throw new NullPointerException();

    items[tail] = item;
    tail = (tail + 1) & (items.length - 1);
    if (tail == head) doubleCapacity();
  }

  public Item dequeue() {
    if (isEmpty()) throw new java.util.NoSuchElementException();

    shrink();
    int h = head;
    
    int randomIdx = (h + StdRandom.uniform(size())) & (items.length - 1);
    // swap the head item with the random item
    Item temp = items[h];

    items[h] = items[randomIdx];
    items[randomIdx] = temp;


    Item item = items[h];
    items[h] = null;
    head = (h+1) & (items.length - 1);
    return item;
  }

  public Item sample() {
    if (isEmpty()) throw new java.util.NoSuchElementException();
    int randomIdx = (head + StdRandom.uniform(size())) & (items.length - 1);
    return items[randomIdx];
  }

  /*
  public Item removeLast() {
    if (isEmpty()) throw new java.util.NoSuchElementException();

    shrink();
    int t = (tail-1) & (items.length - 1);
    Item item = items[t];
    items[t] = null;
    tail = t;
    return item;
  }
  */

  public java.util.Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements java.util.Iterator<Item> {
    private int cursor = head;
    private int fence = tail;
    private int count = size();

    public boolean hasNext() {
      return cursor != fence;
    }

    public Item next() {
      if (cursor == fence) throw new java.util.NoSuchElementException();

      int randomIdx = (cursor + StdRandom.uniform(count)) & (items.length - 1);

      Item temp = items[randomIdx];
      items[randomIdx] = items[cursor];
      items[cursor] = temp;

      Item result = temp;

      cursor = (cursor + 1) & (items.length - 1);
      count--;
      return result;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {
    RandomizedQueue<Integer> items = new RandomizedQueue<Integer>();
    for (int i = 0; i < 33; ++i) {
      items.enqueue(i);
    }

    for (Integer i : items) {
      StdOut.printf("%d ", i);
    }
    StdOut.println();

    for (Integer i : items) {
      StdOut.printf("%d ", i);
    }
    StdOut.println();
    
    for (int i = 0; i < 24; ++i) {
      StdOut.printf("%d ", items.sample());
    }
    StdOut.println();

  }
}
