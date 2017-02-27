package com.bhs.util;

public class QueueImpl {
	int size;
	int qa[];
	int  end;
	int front;
	int currentSize;
	
	QueueImpl(int size){
		
		this.size=size;
		qa=new int[size];
		end=-1;
		front=-1;
		currentSize=0;
	}
	
	void queue(int qValue) throws Exception{
		
		/*if(overFlow()){
			
		//	try {
				throw new Exception("Queue OverFlow!!!");
		//	} catch (Exception e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		}
		else{
			//end++;
			
			
			qa[++end]=qValue;
			System.out.println("Queue Value: "+qValue);
			currentSize++;
		}*/
		
		
		if (end == -1) 
        {
            front = 0;
            end = 0;
            qa[end] = qValue;
        }
        else if (end + 1 >= size)
            throw new IndexOutOfBoundsException("Overflow Exception");
        else if ( end + 1 < size)
            qa[++end] = qValue;    
		
		System.out.println("Queue Value: "+qValue);
		//currentSize++ ;    
    }    

		
		

	void dequeue() throws Exception{
		
		/*if(overFlow()){
			
		//	try {
				throw new Exception("Queue OverFlow!!!");
		//	} catch (Exception e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		}
		else{
		currentSize--;
		front++;
			int dValue=qa[--front];
			System.out.println("DeQueue Value: "+dValue);
			//currentSize++;
			//end--;
		//}
*/	
		
		
		//currentSize-- ;
		if(front==-1)
			throw new Exception("Queue is Empty!!!!!!!!");
        int dValue = qa[front];
        if ( front == end) 
        {
            front = -1;
            end = -1;
        }
        else
            front++;                
        System.out.println("DeQueue Value: "+dValue);
	}
	

	/*private boolean overFlow() {
		return currentSize>size-1;
	}*/
	
	final static public void main(String[]args) throws Exception{
		
		QueueImpl queueImpl=new QueueImpl(4);
		queueImpl.dequeue();
		queueImpl.queue(1);
		queueImpl.queue(2);
		queueImpl.queue(3);
		queueImpl.queue(4);
		queueImpl.queue(5);
		queueImpl.queue(6);
		queueImpl.dequeue();
		queueImpl.queue(3);
		queueImpl.dequeue();
		queueImpl.queue(4);
		queueImpl.dequeue();
		queueImpl.dequeue();
		queueImpl.queue(5);
		queueImpl.dequeue();
	}

}
