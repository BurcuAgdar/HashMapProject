package Homework;


public abstract class hashFunctions {
	public int TABLE_SIZE = 128;
	HashEntry[] table;
	int count = 0;

	public hashFunctions() {
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			table[i] = null;
		}

	}

	abstract int SSF(String key);

	abstract int PAF(String key);

	abstract int linearProbing(int keyNumber, String key);

	abstract int doubleHashing(int keyNumber, String key);

	abstract void newSize(int doublesize);
	
	abstract void put(String key, String value);
	
	public String Search(String key) {

		int hash = PAF(key);
		if (table[hash] == null) {
			return null;
		}
		if (table[hash].getKey().equals(key)) {
			return table[hash].getKey();
		}
		int newhash = doubleHashing(hash, key); // or doubleHash(hash,key);
		if (table[newhash] != null) {
			return table[hash].getKey();

		}
		return null;

	}

	public String display(String key) {
		int hash = PAF(key);
		if (table[hash] == null) {
			return null;
		}
		if (table[hash].getKey().equals(key)) {
			return table[hash].getValues();
		}
		int newhash=doubleHashing(hash,key);
		if(table[newhash]!=null) {
			return table[newhash].getValues();
		}
		
		return null;

	}

	
	public boolean PrimeNumber(int size) {
		for (int i = 2; i < size; i++) {
			if (size % i == 0) {
				return false;

			}

		}
		return true;
	}

	

}
