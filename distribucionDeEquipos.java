import java.util.Scanner; 
 import java.lang.Math; 
  
 class Node<T> { 
  
     private T data; 
     private Node nextNode; 
  
     public Node(T data){ 
         this.data = data; 
     } 
  
     public T getData() { 
         return data; 
     } 
  
     public void setData(T data) { 
         this.data = data; 
     } 
  
     public Node getNextNode() { 
         return nextNode; 
     } 
  
     public void setNextNode(Node nextNode) { 
         this.nextNode = nextNode; 
     } 
 } 
  
 class Queue<T> { 
  
     private int capacity; 
     private Node<T> front, rear; 
  
     public Queue(){ 
         capacity = 0; 
         front = rear = null; 
     } 
  
     public void enqueue(T data){ 
         Node node = new Node<T>(data); 
         if(isEmpty()){ 
             front = node; 
         }else { 
             rear.setNextNode(node); 
         } 
         rear = node; 
         capacity++; 
          
     }  
     public T dequeue() throws Exception { 
         if(isEmpty()){ 
             throw new Exception("Empty queue"); 
         } 
         T result = front.getData(); 
         front = front.getNextNode(); 
         capacity--; 
         if(isEmpty()){ 
             rear = null; 
         } 
         return result; 
     } 
          
  
     public T first() throws Exception { 
         if(isEmpty()){ 
             throw new Exception("Queue is empty"); 
         } 
         return front.getData(); 
     } 
  
     public  boolean isEmpty(){ 
         return capacity == 0; 
     } 
  
     public int size(){ 
         return capacity; 
     } 
 } 
  
 class Stack<T> { 
  
     private int size = 0; 
     private Node<T> top = null; 
  
     public Stack(){ 
     } 
      
     public void push(T data) { 
         Node tempNode = new Node<T>(data); 
         tempNode.setNextNode(top); 
         top = tempNode; 
         size++; 
     } 
      
     public void pop()throws Exception { 
         if(isEmpty()){ 
             throw new Exception("Empty Stack"); 
         } 
         Node node = top; 
         top = top.getNextNode(); 
         size--; 
     } 
      
     public T peek() throws Exception{ 
         if(isEmpty()){ 
             throw new Exception("Empty Stack"); 
         } 
         return top.getData(); 
     } 
  
     public int size(){ 
         return size; 
     } 
  
     public boolean isEmpty(){ 
         return size == 0; 
     } 
 } 
  
 class Entry<K, V> { 
  
     private K key; 
     private V value; 
     private Entry<K, V> next; 
  
     public Entry(K key, V value, Entry<K, V> next){ 
         this.key = key; 
         this.value = value; 
         this.next = next; 
     } 
  
     public K getKey() { 
         return key; 
     } 
  
     public void setKey(K key) { 
         this.key = key; 
     } 
  
     public V getValue() { 
         return value; 
     } 
  
     public void setValue(V value) { 
         this.value = value; 
     } 
  
     public Entry getNext() { 
         return next; 
     } 
  
     public void setNext(Entry<K, V> next) { 
         this.next = next; 
     } 
 } 
  
  
 class Hash<K, V> { 
  
     private int capacity = 20; //Initial default capacity 
  
     private Entry<K, V>[] table; //Array of Entry object 
  
     public Hash(int capacity){ 
         this.capacity = capacity; 
         table = new Entry[capacity]; 
     } 
     private int index(K key){ 
         if(key == null){ 
             return 0; 
         } 
         return Math.abs(key.hashCode() % capacity); 
     } 
     public void put(K key, V value){ 
          
         int index = index(key); 
         Entry newEntry = new Entry(key, value, null); 
         if(table[index] == null){ 
             table[index] = newEntry; 
         }else { 
             Entry<K, V> previousNode = null; 
             Entry<K, V> currentNode = table[index]; 
             while(currentNode != null){ 
                 if(currentNode.getKey().equals(key)){ 
                     currentNode.setValue(value); 
                     break; 
                 } 
                 previousNode = currentNode; 
                 currentNode = currentNode.getNext(); 
             } 
             if(previousNode != null) 
                 previousNode.setNext(newEntry); 
             } 
          
     } 
     public V get(K key){ 
        V value = null; 
         int index = index(key); 
         Entry<K, V> entry = table[index]; 
         while (entry != null){ 
             if(entry.getKey().equals(key)) { 
                 value = entry.getValue(); 
                 break; 
             } 
             entry = entry.getNext(); 
         } 
         return value; 
     } 
  
     public void remove(K key){ 
          
         int index = index(key); 
         Entry previous = null; 
         Entry entry = table[index]; 
         while (entry != null){ 
             if(entry.getKey().equals(key)){ 
                 if(previous == null){ 
                     entry = entry.getNext(); 
                     table[index] = entry; 
                     return; 
                 }else { 
                     previous.setNext(entry.getNext()); 
                     return; 
                 } 
             } 
             previous = entry; 
             entry = entry.getNext(); 
         } 
          
     } 
  
 } 
 class Main { 
     public static boolean isANumber(String str) { //function to determinate if a string is a number 
     if (str == null) { 
         return false; 
     } 
     try { 
         int  i = Integer.parseInt(str); 
     } catch (NumberFormatException nfe) { 
         return false; 
     } 
     return true; 
 } 
   public static  void reverseQueueInt(Queue<Integer> q) throws Exception 
 {   
     try{ 
     Stack<Integer> stack = new Stack(); 
     while (!q.isEmpty()) { 
         stack.push(q.first()); 
         q.dequeue(); 
     } 
     while (!stack.isEmpty()) { 
         q.enqueue(stack.peek()); 
         stack.pop(); 
     } 
     } 
       catch(Exception e){ 
           System.out.println(e); 
       } 
 } 
   public static  void reverseQueueString(Queue<String> q) throws Exception 
 {   
     try{ 
     Stack<String> stack = new Stack(); 
     while (!q.isEmpty()) { 
         stack.push(q.first()); 
         q.dequeue(); 
     } 
     while (!stack.isEmpty()) { 
         q.enqueue(stack.peek()); 
         stack.pop(); 
     } 
     } 
       catch(Exception e){ 
           System.out.println(e); 
       } 
 } 
  public static int minIndex(Queue<Integer> q,int sortedIndex) throws Exception 
 {   int min_index = -1; 
     int min_val = Integer.MAX_VALUE; 
     int n = q.size(); 
     try{ 
     for (int i=0; i<n; i++) 
     { 
         int curr = q.first(); 
         q.dequeue(); 
    
         // we add the condition i <= sortedIndex 
         // because we don't want to traverse 
         // on the sorted part of the queue, 
         // which is the right part. 
         if (curr <= min_val && i <= sortedIndex) 
         { 
             min_index = i; 
             min_val = curr; 
         } 
         q.enqueue(curr); 
     } 
     } 
      catch(Exception e){ 
          System.out.println(e); 
      } 
   return min_index; 
 } 
  public static void insertMinToRear(Queue<Integer> q, int min_index) throws Exception 
 { 
     try{ 
     int min_val=0; 
     int n = q.size(); 
     for (int i = 0; i < n; i++) 
     { 
         int curr = q.first(); 
         q.dequeue(); 
         if (i != min_index){ 
             q.enqueue(curr); 
         }else 
             min_val = curr; 
     } 
     q.enqueue(min_val); 
     } catch(Exception e){ 
         System.out.println(e);  
     }  
 } 
    public static void sort(Queue<Integer> numbers ) throws Exception 
 {  
  
 try 
 { 
     for (int i = 1; i <= numbers.size(); i++) 
     { 
         int min_index = minIndex(numbers, numbers.size() - i); 
         insertMinToRear(numbers, min_index); 
     } 
     reverseQueueInt(numbers); 
      
 } 
        catch(Exception e){ 
            System.out.println(e);  
        } 
    } 
  
     public static void Distribute (Queue<Integer> Order, Queue<Integer> Lots, Queue<Integer> Supplied )throws Exception{ // function to distribute the Order of Tech items with the Lot and also counts the number of items supplied 
        Queue<Integer> Lot = new Queue(); 
        try{ 
        for(int i=0; i<3; i++){ 
            Lot.enqueue(Lots.first()); 
            Lots.enqueue(Lots.dequeue()); 
        }   
        for(int i =0; i<4; i++){ 
         int demand = Order.first(); 
         int extra = 1; 
         Boolean computer = false; 
         Boolean laptop = false; 
         Boolean tablet = false; 
            for(int j =0; j<3; j++){ 
                int share = demand/3; 
                int LotNum = Lot.first(); 
                int supplied =0; 
                while(share>0 && LotNum>0){ 
                    LotNum-=1; 
                    demand-=1; 
                    share-=1; 
                    supplied+=1;  
                    } 
                if(demand%3 !=0 && LotNum<0 && share>0 && extra<=2){       if(!computer && j==1){ 
                    LotNum-=1; 
                    demand-=1; 
                    share-=1; 
                    supplied+=1; 
                    if(j==1){ 
                        computer = true; 
                    } 
                    if(j==2){ 
                        laptop = true; 
                    } 
                    if(j==3){ 
                        tablet = true; 
                    } 
                    extra++; 
                }else if(!laptop && j==2){ 
                    LotNum-=1; 
                    demand-=1; 
                    share-=1; 
                    supplied+=1; 
                    if(j==1){ 
                        computer = true; 
                    } 
                    if(j==2){ 
                        laptop = true; 
                    } 
                    if(j==3){ 
                        tablet = true; 
                    } 
                    extra++; 
                     
                }else if(!tablet && j==3){ 
                    LotNum-=1; 
                    demand-=1; 
                    share-=1; 
                    supplied+=1; 
                    if(j==1){ 
                        computer = true; 
                    } 
                    if(j==2){ 
                        laptop = true; 
                    } 
                    if(j==3){ 
                        tablet = true; 
                    } 
                    extra++; 
                }else if( tablet && computer && laptop){ 
                    tablet = false; 
                    computer = false; 
                    laptop = false; 
                }                                                                                                                                         } 
                Supplied.enqueue(supplied); 
                Lot.enqueue(LotNum);  
                Lot.dequeue(); 
            } 
            Order.enqueue(demand); 
            Order.dequeue(); 
             
        } 
             
        } 
            catch(Exception e){ 
            System.out.println(e); 
        }  
          
     } 
     public static void main (String args[]) throws Exception{ 
          
         Queue<String> Faculty = new Queue(); //initializate variables 
         Hash<Integer,String> FacultyHash = new Hash(40);    
         Queue<Integer> Order = new Queue(); 
         Queue<Integer> Lot = new Queue(); 
         Queue<Integer> Supplied = new Queue(); 
         Queue<Integer> Ordered = new Queue(); 
          
         Scanner scanner = new Scanner(System.in); //create scanner of input object         
         String line =""; // input string which will be iterate over Line by Line word by word because scanner object takes a whole line of input and not a word 
         int countWords = 0; // used to count words          
         int countDist = 0; 
          
        while(true){ //main iteration, we iterate  until we get out of lines to read 
            try{ 
              String Line = scanner.nextLine();//reading the Line of input 
          String[] arr = Line.split(" "); //split by word  
         for (String word: arr) { // loop thru Line string 
             if(word.equals("Ingenieria") || word.equals("Artes") || word.equals("Humanas") || word.equals("Medicina")){ 
                 Faculty.enqueue(word); 
             } 
             if(isANumber(word) && countWords<=7){ 
                 Order.enqueue(Integer.parseInt(word)); 
             } 
             else if(isANumber(word)){ 
                 Lot.enqueue(Integer.parseInt(word)); 
             } 
             if(word.equals("Distribuir")){//calling function 
                if(countDist<1){ 
                    for(int i =0; i<4; i++){ 
                        FacultyHash.put(Order.first(),Faculty.first()); 
                        Ordered.enqueue(Order.first()); 
                        Order.enqueue(Order.dequeue()); 
                        Faculty.enqueue(Faculty.dequeue()); 
                    } 
                    sort(Order); 
                }   
                Distribute(Order,Lot,Supplied);//calling function         
                 countDist++; 
             } 
             if(word.equals("Imprimir")){ 
                 for(int i=0; i<4; i++){ 
                         System.out.print(FacultyHash.get(Ordered.first())  + " " + Order.first() +" "+ "-" + " "); 
                     Order.enqueue(Order.dequeue()); 
                     Ordered.enqueue(Ordered.dequeue()); 
                     System.out.print("Computers"+" "+Supplied.first()+" "); 
                     Supplied.enqueue(Supplied.dequeue()); 
                     System.out.print("Laptops"+" "+Supplied.first()+" "); 
                     Supplied.enqueue(Supplied.dequeue()); 
                     System.out.println("Tablets"+" "+Supplied.first()); 
                     Supplied.enqueue(Supplied.dequeue()); 
                      
                         }   
                     } 
             countWords++; 
         } 
            } 
            catch(Exception e){ //when we reach the exception we break the while loop 
                break;  
                 
            }  
             
        }  
          
     } 
 }
