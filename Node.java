package Homework;

public class Node {

	String data = null;
	int count=1;
	Node next = null;
	
public Node(){
	
}
public Node(String data){
	this.data = data;
}
public Node(String data,Node node){
	this.data = data;
	this.next = node;
}
public Object getData() {
	return data;
}
public void setData(String data) {
	this.data = data;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public Node getNext() {
	return next;
}
public void setNext(Node next) {
	this.next = next;
}



}


