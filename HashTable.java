package Homework;


public class HashTable extends hashFunctions {

	int collision_count = 0;
	
	
	public HashTable() {
		super();
	}


	int SSF(String key) {
		int keyNumber = 0;
		// if hash function is SSF
		for (int i = 0; i < key.length(); i++) {
			char letter = key.charAt(i);
			keyNumber += letter;
		}

		return keyNumber % TABLE_SIZE;

	}
	
	int PAF(String key) {
		int functionpaf=0;
		for (int i = 0; i < key.length(); i++) {
			char letter = key.charAt(i);
			functionpaf+=letter*Math.pow(33, key.length()-i)%TABLE_SIZE;
		}
		return functionpaf%TABLE_SIZE;

	}
	
	public void remove(String key) {
		int hash = PAF(key);
		int newhash = doubleHashing(hash, key);
		if(table[hash].getKey().equals(key)) {
			table[hash] = null;
			count--;
		}
		else if(table[newhash]!=null) {
			table[newhash] =null;
		}
		else {
			System.out.println("Not Found");
		}
	}
	
	
	int linearProbing(int hash,String key) {
		int j = 0;
		int newhash = hash;
		while (table[newhash] != null) {
			if (table[newhash].getKey().equals(key)) {
				return newhash;
			}
			j++;
			newhash=(hash+j)%TABLE_SIZE;
		}
		return newhash;
	}
	int doubleHashing(int hash,String key) {
		int j = 0;
		int newhash=hash;
		while (table[newhash]!=null){
			if (table[newhash].getKey().equals(key)) {
				return newhash;
			}
			int function=7-(hash%7);
			 newhash=(hash%TABLE_SIZE+j*function)%TABLE_SIZE;
			j++;
			
		}
		
		return newhash;
		
	}
	
	
	

	@Override

	void put(String key, String value) {		
		// If the load factor is 50%
		// if(count==5*TABLE_SIZE/10)
		
		// If the load factor is 80%
		if (count == 8 * TABLE_SIZE / 10) {
			newSize(TABLE_SIZE * 2);
	   	}

		int hash = PAF(key);

		if (table[hash] == null) {
			table[hash] = new HashEntry(key, value);
			count++;
			return;
		}
		if (table[hash].getKey().equals(key)) {
			table[hash].getValueList().add(value);
		} 
		else {
			hash=doubleHashing(hash, key);
			if(table[hash]!= null) {
				table[hash].getValueList().add(value);
			}
			else {
				table[hash] = new HashEntry(key, value);
				count++;	
			}
			}
		collision_count++;
			
					
		}

	

	public void collision() {
		System.out.println(collision_count);
	}

	@Override
	void newSize(int doublesize) {
		while (PrimeNumber(doublesize) == false) {
			doublesize++;

		}
		int k=0;
		TABLE_SIZE = doublesize;
		HashEntry[] temp = table;
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] != null) {
				int hash = PAF(temp[i].getKey());
				if(table[hash]==null) {
					table[hash] = new HashEntry(temp[i].getKey(), "");
					table[hash].setValueList(temp[i].getValueList());
				}
				else {
					int newhash=doubleHashing(hash, temp[i].getKey());
							
						table[newhash] = new HashEntry(temp[i].getKey(), "");
						table[newhash].setValueList(temp[i].getValueList());
					}
				
				}
				
			}
		}


	

	}

