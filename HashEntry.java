package Homework;


import java.util.LinkedList;

public class HashEntry  {
	private String key;
	private String value;
	private SingleLinkedList valueList;
	HashEntry(String key, String value) {
		this.key = key;
		valueList =new SingleLinkedList();
		valueList.add(value);
	}

	public String getKey() {
		return key;
	}

	public SingleLinkedList getValueList() {
		return valueList;
	}
	public String getValues() {
		return key + " " + valueList.display();
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValueList(SingleLinkedList valueList) {
		this.valueList = valueList;
	}
	
}