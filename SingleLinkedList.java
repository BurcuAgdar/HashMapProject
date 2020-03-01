package Homework;


public class SingleLinkedList {

	Node head = null;
	String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public SingleLinkedList() {
	}

	public SingleLinkedList(Node head) {
		this.head = head;
	}
	public void add(String input){
		Node inputNode = new Node( input);
		if(head == null){
			head = inputNode;
		}
		else{
			Node temp = head;
			if(temp.getData().equals(input)) {
				temp.setCount(temp.getCount()+1);
			}
			else {
			while(temp.getNext() != null){
				if(temp.getData().equals(input)) {
					temp.setCount(temp.getCount()+1);
				}
				temp = temp.getNext();
			}
			temp.setNext(inputNode);
			}
		}
		
	}
	public String display(){
		Node temp = head;
		String output ="";
		int count = 0;
		while(temp!= null){
			output += temp.getData() + "--> "+temp.count+" ";
			count +=  temp.count;
			temp = temp.getNext();
		}
		return  output;
	}
	
	public int size(){
		Node temp = head;
		int size_number = 0;
		while(temp!= null){
			size_number ++;
			temp = temp.getNext();
		}
		return size_number;
	}

	public boolean search (Object input){
		
		Node temp = head;
		while(temp!= null){
	        if(temp.getData().equals(input) == true){
	        	return true;
	        }
			temp = temp.getNext();
		}
		return false;
	}
	public Object findMax(){
		Node temp = head;
		if(head == null){
			System.out.println("SLL is empty ");
			return null;
		}
		else{
		    temp = head;
			Object max = temp.getData();
			temp = temp.getNext();
			while(temp!= null){
		        if((int) temp.getData() > (int) max){
		        	max = temp.getData();
		        }
		        temp = temp.getNext();
			}
			return max;
		}
	}
	
	public boolean remove (Object input){
		if(head == null){
			System.out.println("SLL is empty !");
			return false;
		}
		else if ( head.getData().equals(input) == true){
			head = head.getNext();
			return true;
		}
		else{

			Node prev = head;
			Node temp = prev.getNext();
			while(temp!= null){
		        if(temp.getData().equals(input)){
		        	prev.setNext(temp.getNext());
		        	return true;
		        }
		        prev = temp;
		        temp = temp.getNext();
			}
			System.out.println("Element could not be found ");
			return false;
		}
	}

	
}

