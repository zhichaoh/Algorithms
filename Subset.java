public class Subset {
  public static void main(String[] args) {
    int K = Integer.parseInt(args[0]);

    RandomizedQueue<String> queue = new RandomizedQueue();
    while (! StdIn.isEmpty()) {
      queue.enqueue(StdIn.readString());
    }

    for (int i = 0; i < K; ++i) {
      StdOut.println(queue.dequeue());
    }
  }
}





      
