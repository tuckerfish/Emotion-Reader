public class MyQueue implements QueueInterface {
	/* 
	* TODO 2: Implement "MyQueue"
	*/

	int size = 0;

	// new linked list to be manipulated as a queue.
	ListInterface list = new MyLinkedList();

	// returns true if queue is empty.
	public boolean isEmpty(){
		return list.isEmpty();
	}

	// adds a new element at the end of the queue.
	public void enqueue(Object item){
		list.add(size,item);
		size++;
	}

	// returns and removes the first element of the queue.
	public Object dequeue(){
		if(size == 0){
			throw new QueueException("Tried to dequeue() an empty queue.");
		}
		Object item = list.get(0);
		list.remove(0);
		size--;
		return item;
	}

	// removes all the elements of the queue.
	public void dequeueAll(){
		list.removeAll();
	}

	// returns the first element of the queue.
	public Object peek(){
		if(size == 0){
			throw new QueueException("Tried to peek() an empty queue.");
		}
		return list.get(0);
	}

	// // returns a string of the contents of the queue, punctuated by paranthesis and commas.
	public String toString(){
		return list.toString();
	}
	
	/* 
	* TODO 2: Implement "MyQueue"
	*/

} 