import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Math;

public class MaxHeap {
    private static int[] Heap;
    private int size;
    private int maxsize;
    private static int swapcallcount = 0;


    // Constructor to initialize an
    // empty max heap with given maximum
    // capacity.
    public MaxHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize + 1];
        Heap[0] = Integer.MAX_VALUE;
    }

    // Returns position of parent
    private int parent(int pos)
    {
        return pos / 2;
    }

    // Below two functions return left and
    // right children.
    private int leftChild(int pos)
    {
        return (2 * pos);
    }
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    // Returns true of given node is leaf
    private boolean isLeaf(int pos)
    {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
        swapcallcount = swapcallcount + 1;
    }

    // A recursive function to max heapify the given
    // subtree. This function assumes that the left and
    // right subtrees are already heapified, we only need
    // to fix the root.
    private void maxHeapify(int pos)
    {
        if (isLeaf(pos))
            return;

        if (Heap[pos] < Heap[leftChild(pos)] ||
            Heap[pos] < Heap[rightChild(pos)]) {

            if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            }
            else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    // Inserts a new element to max heap
    public void insert(int element)
    {
        Heap[++size] = element;

        // Traverse up and fix violated property
        int current = size;
        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void print()
    {
        for (int i = 1; i <= size; i++) {
            System.out.print(Heap[i]);
            System.out.println();
        }
    }

    // Remove an element from max heap
    public int extractMax()
    {
        int popped = Heap[1];
        Heap[1] = Heap[size--];
        maxHeapify(1);
        return popped;
    }

    public static void array_swap(int[] arr, int index1, int index2){
      int temp = arr[index1];
      arr[index1] = arr[index2];
      arr[index2] = temp;
    }

    //heap verification for winner
    static boolean isHeap(int arr[], int i, int n) {
      // If a leaf node
        System.out.println("Debug Line: checking for heap status");
        if (i > (n - 2) / 2) {
            return true;
        }

    // If an internal node and is greater than its children, and
    // same is recursively true for the children
        if (arr[i] >= arr[2 * i + 1] && arr[i] >= arr[2 * i + 2]
                && isHeap(arr, 2 * i + 1, n) && isHeap(arr, 2 * i + 2, n)) {
            return true;
        }

        return false;
    }
    public static void ghettoprint(int[] randint){
      System.out.println("Debug - Ghetto Print ");

      for (int i = 0; i < randint.length; i++) {
        System.out.print(randint[i]);
        System.out.println();
      }
    }

    //not sure if this works or how it would connect



    public static void main(String[] arg)
    {
      int userswapmax;
      userswapmax = 0;

      int useractions;
      useractions = 0;

      int[] randint = new int[15];
      int arrlen = randint.length;
      //Generates 15 Random Numbers in the range 1 -100
      for(int i = 0; i < arrlen; i++) {
        randint[i] = (int)(Math.random()*100 + 1);
      }
      MaxHeap maxHeap = new MaxHeap(15);
      for(int i = 0; i < arrlen; i++) {
        maxHeap.insert(randint[i]);
      }
        System.out.println("Debug - The Max Heap is ");

        maxHeap.print();
        System.out.println();
        System.out.println("Debug - Ideal Swap Count is: " + swapcallcount);
        System.out.println("Randomized array, shouldn't make sense");

        double wpc;
        wpc = 15.0 - Math.ceil(Math.log(15.0));
        System.out.println("Debug - Worst Possible Case: " + wpc);
        int delta;
        delta = (int)wpc;

        //Needs to be replaced by game input menu selection
        Scanner diffinput = new Scanner(System.in);
        System.out.println("Difficulty setting inputs, 0 for 10, 1 for 5, 2 for 1");
        int difficulty = diffinput.nextInt();
        if (difficulty == 0){
          userswapmax = 10 + delta;
        }
        else if(difficulty == 1){
          userswapmax = 5 + delta;
        }
        else if(difficulty == 2){
          userswapmax = 1 + delta;
        }
        System.out.println("Debug - Max Actions Permitted (not currently active): " + userswapmax);
        int[] outputclone;
        while(true){
           //align with game loop
           //scan for selection input, pass to alpha1
           ghettoprint(randint);
           Scanner alpha = new Scanner(System.in);
           System.out.println("Enter starting swap index (DEBUG: or enter 40 to debug exit)");
           int alpha1 = alpha.nextInt();
           //debug exit so i don't have to keep restarting terminal
           if(alpha1 == 40){
             return;
           }

           //in game replace by swap target sheep, pass to beta1
           int omega;
           omega = 0;
           System.out.println("Choose a neighboring sheep: ");
           while(omega == 0){
             Scanner beta = new Scanner(System.in);
             int beta1 = beta.nextInt();
             if(alpha1 == 0 && (beta1 == 1|| beta1 == 2)){
               omega = 1;
             }
             else if(alpha1 == 1 && (beta1 == 0 || beta1 == 3 || beta1 == 4)){
               omega = 1;
             }
             else if(alpha1 == 2 && (beta1 == 0 || beta1 == 5 || beta1 == 6)){
               omega = 1;
             }
             else if(alpha1 == 3 && (beta1 == 1 || beta1 == 7 || beta1 == 8)){
               omega = 1;
             }
             else if(alpha1 == 4 && (beta1 == 1 || beta1 == 9 || beta1 == 10)){
               omega = 1;
             }
             else if(alpha1 == 5 && (beta1 == 2 || beta1 == 11 || beta1 == 12)){
               omega = 1;
             }
             else if(alpha1 == 6 && (beta1 == 2 || beta1 == 13 || beta1 == 14)){
               omega = 1;
             }
             else if(alpha1 == 7 && (beta1 == 3)){
               omega = 1;
             }
             else if(alpha1 == 8 && (beta1 == 3)){
               omega = 1;
             }
             else if(alpha1 == 9 && (beta1 == 4)){
               omega = 1;
             }
             else if(alpha1 == 10 && (beta1 == 4)){
               omega = 1;
             }
             else if(alpha1 == 11 && (beta1 == 5)){
               omega = 1;
             }
             else if(alpha1 == 12 && (beta1 == 5)){
               omega = 1;
             }
             else if(alpha1 == 13 && (beta1 == 6)){
               omega = 1;
             }
             else if(alpha1 == 14 && (beta1 == 6)){
               omega = 1;
             }
             else {
               System.out.println("Baaaaaa!  That doesn't seem right!  Try something else!");
             }
           }


           array_swap(randint, alpha1, beta1);

           //pass this one to the thing?

           outputclone = randint.clone();
        //  ghettoprint(randint);
           useractions = useractions + 1;
        //pass useractions to thing
           if(useractions > userswapmax){
             //lose condition
             System.out.println("You lose");
             return;
           }
           if (isHeap(randint, 0, randint.length-1)){
             System.out.println("A winrar is you!");
             return;
           }
        //   //if useractions exceceds userswapmax, you lose
        }
    }
}
