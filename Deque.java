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
  }

  public Item removeLast() {
  }

  public Iterator<Item> iterator() {
  }

  public static void main(String[] args) {
  }
}
