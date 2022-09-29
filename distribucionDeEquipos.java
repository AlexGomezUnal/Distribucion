import java.util.Scanner;
import java.lang.Math;

class Node<T> {

    private T data;
    private Node nextNode;

    public Node(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
}

class Queue<T> {

    private int capacity;
    private Node<T> front, rear;

    public Queue(){
        capacity = 0;
        front = rear = null;
    }

    public void enqueue(T data){
        Node node = new Node<T>(data);
        if(isEmpty()){
            front = node;
        }else {
            rear.setNextNode(node);
        }
        rear = node;
        capacity++;
        
    } 
    public T dequeue() throws Exception {
        if(isEmpty()){
            throw new Exception("Empty queue");
        }
        T result = front.getData();
        front = front.getNextNode();
        capacity--;
        if(isEmpty()){
            rear = null;
        }
        return result;
    }
        

    public T first() throws Exception {
        if(isEmpty()){
            throw new Exception("Queue is empty");
        }
        return front.getData();
    }

    public  boolean isEmpty(){
        return capacity == 0;
    }

    public int size(){
        return capacity;
    }
}

class Stack<T> {

    private int size = 0;
    private Node<T> top = null;

    public Stack(){
    }
    
    public void push(T data) {
        Node tempNode = new Node<T>(data);
        tempNode.setNextNode(top);
        top = tempNode;
        size++;
    }
    
    public void pop()throws Exception {
        if(isEmpty()){
            throw new Exception("Empty Stack");
        }
        Node node = top;
        top = top.getNextNode();
        size--;
    }
    
    public T peek() throws Exception{
        if(isEmpty()){
            throw new Exception("Empty Stack");
        }
        return top.getData();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}

class Main {
    public static boolean isANumber(String str) { //function to determinate if a string is a number
    if (str == null) {
        return false;
    }
    try {
        int  i = Integer.parseInt(str);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
  public static  void reverseQueueInt(Queue<Integer> q) throws Exception
{  
    try{
    Stack<Integer> stack = new Stack();
    while (!q.isEmpty()) {
        stack.push(q.first());
        q.dequeue();
    }
    while (!stack.isEmpty()) {
        q.enqueue(stack.peek());
        stack.pop();
    }
    }
      catch(Exception e){
          System.out.println(e);
      }
}
  public static  void reverseQueueString(Queue<String> q) throws Exception
{  
    try{
    Stack<String> stack = new Stack();
    while (!q.isEmpty()) {
        stack.push(q.first());
        q.dequeue();
    }
    while (!stack.isEmpty()) {
        q.enqueue(stack.peek());
        stack.pop();
    }
    }
      catch(Exception e){
          System.out.println(e);
      }
}
 public static int minIndex(Queue<Integer> q,int sortedIndex) throws Exception
{   int min_index = -1;
    int min_val = Integer.MAX_VALUE;
    int n = q.size();
    try{
    for (int i=0; i<n; i++)
    {
        int curr = q.first();
        q.dequeue();
  
        // we add the condition i <= sortedIndex
        // because we don't want to traverse
        // on the sorted part of the queue,
        // which is the right part.
        if (curr <= min_val && i <= sortedIndex)
        {
            min_index = i;
            min_val = curr;
        }
        q.enqueue(curr);
    }
    }
     catch(Exception e){
         System.out.println(e);
     }
  return min_index;
}
 public static void insertMinToRear(Queue<Integer> q,Queue<String> names, int min_index) throws Exception
{
    try{
    int min_val=0;
    String minName="";
    int n = q.size();
    for (int i = 0; i < n; i++)
    {
        int curr = q.first();
        q.dequeue();
        String strCurr = names.first(); 
        names.dequeue();
        if (i != min_index){
            q.enqueue(curr);
            names.enqueue(strCurr);
        }else
            min_val = curr;
        	minName = strCurr;
    }
    q.enqueue(min_val);
    names.enqueue(minName);
    } catch(Exception e){
        System.out.println(e); 
    } 
}
   public static void sort(Queue<String> names,Queue<Integer> numbers ) throws Exception
{ 

try
{
    for (int i = 1; i <= numbers.size(); i++)
    {
        int min_index = minIndex(numbers, numbers.size() - i);
        insertMinToRear(numbers,names, min_index);
    }
    reverseQueueInt(numbers);
    reverseQueueString(names);
    
}
       catch(Exception e){
           System.out.println(e); 
       }
}

    public static void Distribute (Queue<Integer> Order, Queue<Integer> Lots, Queue<Integer> Supplied )throws Exception{ // function to distribute the Order of Tech items with the Lot and also counts the number of items supplied
       Queue<Integer> Lot = new Queue();
       try{
       while(!Supplied.isEmpty()){
           Supplied.dequeue();
       } 
       for(int i=0; i<3; i++){
           Lot.enqueue(Lots.first());
           Lots.enqueue(Lots.dequeue());
           Supplied.enqueue(0);
           
       }  
       for(int i =0; i<4; i++){
        
           int share = Order.first()/3;
          
           int needed = Order.first()%3;
           
           int extra =0; //if we need more to supply because some of the lots are zeros or are inferior than their share
           int totalSupplied =0; //count how many tech items we have supplied 
           int countNeeded =0; //as share can't always be a whole number we will need 2 or 1 extra item to add to the share
           for(int j = 0; j<3; j++){
               if(Lot.first() == 0){//if supplied Lot is just 0
                extra += share;
                Supplied.enqueue(0);
                totalSupplied+=0;
                Lot.enqueue(Lot.dequeue()); //rearrenge since dequeue returns a data 
               }
               else if((Lot.first() - share)<0){//if the Lot can handle all of the order            
                
                   extra += Math.abs(Lot.first()-share);
                   Supplied.enqueue(Lot.first());
                   totalSupplied+=Lot.first();
                   Lot.enqueue(0);
                   Lot.dequeue();
               }
               else{//case where neither of the items provided by lot are zero or inferior to share    
                   if(needed != 0 && countNeeded<2){    
                   Lot.enqueue(Lot.first() - (share+extra+1));
                   Supplied.enqueue(share + extra+1);
                   Lot.dequeue();
                   totalSupplied+=(share+extra+1);
                   countNeeded++;
                    
                   }  
                   else{
                   Lot.enqueue(Lot.first() - (share+extra));
                   Supplied.enqueue(share + extra);
                   totalSupplied+=(share+extra); 
                   Lot.dequeue();
                   } 
               }
               Order.enqueue(Order.first()-totalSupplied);
               Order.dequeue();
               totalSupplied=0;
               
       } 
           extra =0;
       }
       }
           catch(Exception e){
           System.out.println(e);
       } 
        
    }
    public static void main (String args[]) throws Exception{
        
        Queue<String> Faculty = new Queue(); //initializate variables
        Queue<Integer> Order = new Queue();
        Queue<Integer> Lot = new Queue();
        Queue<Integer> Supplied = new Queue();
        
        Scanner scanner = new Scanner(System.in); //create scanner of input object        
        String line =""; // input string which will be iterate over Line by Line word by word because scanner object takes a whole line of input and not a word
        int countWords = 0; // used to count words         
        
        
       while(true){ //main iteration, we iterate  until we get out of lines to read
           try{
             String Line = scanner.nextLine();//reading the Line of input
         String[] arr = Line.split(" "); //split by word 
        for (String word: arr) { // loop thru Line string
            if(word.equals("Ingenieria") || word.equals("Artes") || word.equals("Humanas") || word.equals("Medicina")){
                Faculty.enqueue(word);
            }
            if(isANumber(word) && countWords<=7){
                Order.enqueue(Integer.parseInt(word));
            }
            else if(isANumber(word)){
                Lot.enqueue(Integer.parseInt(word));
            }
            if(word.equals("Distribuir")){
               sort(Faculty,Order); //calling function 
               Distribute(Order,Lot,Supplied);//calling function
            }
            if(word.equals("Imprimir")){
                Supplied.enqueue(Supplied.dequeue());
                for(int i=0; i<4; i++){
                        System.out.print(Faculty.first() + " " + Order.first() +" "+ "-" + " ");
                    Faculty.enqueue(Faculty.dequeue());
                    Order.enqueue(Order.dequeue());
                    System.out.print("Computers"+" "+Supplied.first()+" ");
                    Supplied.enqueue(Supplied.dequeue());
                    System.out.print("Laptops"+" "+Supplied.first()+" ");
                    Supplied.enqueue(Supplied.dequeue());
                    System.out.println("Tablets"+" "+Supplied.first());
                    Supplied.enqueue(Supplied.dequeue());
                    
                        }  
                    }
            countWords++;
        }
           }
           catch(Exception e){ //when we reach the exception we break the while loop
               break; 
               
           } 
           
       } 
        
    }
}
