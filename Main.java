package Homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Main {
	static int ebb = 0;
	static HashTable stop_hash = new HashTable();
	static int k = 0;
	static int ÅŸ = 0;
	static String DELIMETERS = "[-+=" + " " + // space
			"\r\n " + // carriage return line fit
			"1234567890" + // numbers
			"’'\"" + // apostrophe
			"(){}<>\\[\\]" + // brackets
			":" + // colon
			"," + // comma
			"‒–—―" + // dashes
			"…" + // ellipsis
			"!" + // exclamation mark
			"." + // full stop/period
			"«»" + // guillemets
			"-‐" + // hyphen
			"?" + // question mark
			"‘’“”" + // quotation marks
			";" + // semicolon
			"/" + // slash/stroke
			"⁄" + // solidus
			"␠" + // space?
			"·" + // interpunct
			"&" + // ampersand
			"@" + // at sign
			"*" + // asterisk
			"\\" + // backslash
			"•" + // bullet
			"^" + // caret
			"¤¢$€£¥₩₪" + // currency
			"†‡" + // dagger
			"°" + // degree
			"¡" + // inverted exclamation point
			"¿" + // inverted question mark
			"¬" + // negation
			"#" + // number sign (hashtag)
			"№" + // numero sign ()
			"%‰‱" + // percent and related signs
			"¶" + // pilcrow
			"′" + // prime
			"§" + // section sign
			"~" + // tilde/swung dash
			"¨" + // umlaut/diaeresis
			"_" + // underscore/understrike
			"|¦" + // vertical/pipe/broken bar
			"⁂" + // asterism
			"☞" + // index/fist
			"∴" + // therefore sign
			"‽" + // interrobang
			"※" + // reference mark
			"]";

	public static void addHash(HashTable table, String[] addwords, HashTable stop_hash, String uzantı, String txt) {
		boolean flag = true;
		for (int i = 0; i < addwords.length; i++) {
			{
				if (stop_hash.Search(addwords[i].replaceAll(DELIMETERS, "").replaceAll("\\p{C}", "")) != null)
					flag = false;
			}
			if (flag) {
				table.put(addwords[i].replaceAll(DELIMETERS, "").replaceAll("\\p{C}", ""), uzantı);
			}
			flag = true;
		}
	}

	public static String[] readTxt(String uzantı) throws IOException {
		String words = "";
		try {
			File file = new File(uzantı);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				words += sc.nextLine().toLowerCase().replaceAll(DELIMETERS, " ").replaceAll("\\p{C}", "") + " ";
			}
			sc.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String[] wordsall = words.split(" ");
		return wordsall;
	}

	public static void read_stop_words(String uzantı) throws IOException {
		String words = "";
		try {
			File file = new File(uzantı);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				words += sc.nextLine().toLowerCase().replaceAll("\\p{C}", "") + " ";
			}
			sc.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String[] wordsall = words.split(" ");
		for (int i = 0; i < wordsall.length; i++) {
			stop_hash.put(wordsall[i].replaceAll(DELIMETERS, "").replaceAll("\\p{C}", ""), "");
		}
	}

	public static String[] findfiles() {
		File directoryPath = new File("bbc");
		String[] list = new String[5];
		int count = 0;
		for (File file : directoryPath.listFiles()) {
			list[count] = "bbc\\" + file.getName();
			count++;
		}
		return list;

	}

	public static String[] txt_names(String uzantı) {
		File directoryPath = new File(uzantı);
		ArrayList<String> txt_names = new ArrayList<String>();
		for (File file : directoryPath.listFiles()) {
			txt_names.add(uzantı + "\\" + file.getName());
		}
		String[] txt_name = new String[txt_names.size()];
		for (int i = 0; i < txt_name.length; i++) {
			txt_name[i] = txt_names.get(i);
		}
		return txt_name;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		long start = System.currentTimeMillis();
		HashTable txt_words_hash = new HashTable();

		String[] folders = findfiles();
		read_stop_words("stop_words_en.txt");

		for (int i = 0; i < folders.length; i++) {
			String[] txt_temps = txt_names(folders[i]);
			for (int j = 0; j < txt_temps.length; j++) {
				String[] words = readTxt(txt_temps[j]);
				addHash(txt_words_hash, words, stop_hash, folders[i] + "\\" + txt_temps[j], txt_temps[j]);

			}

		}
		long end = System.currentTimeMillis();
		System.out.println("Time : " + (end - start));
		System.out.println("Collusion : " + txt_words_hash.collision_count);
		
		double minValue=Integer.MAX_VALUE;
		double maxValue=Integer.MIN_VALUE;
		String[] searchwords = readTxt("1000.txt");
		double firstavarage = System.currentTimeMillis();
		for (int i = 0; i < searchwords.length; i++) {
			double firstsearch = System.currentTimeMillis();
			txt_words_hash.Search(searchwords[i].replaceAll(DELIMETERS, ""));
			double endsearch = System.currentTimeMillis();
			double time=(double)(endsearch-firstsearch);
			
			if(time<minValue) {
				minValue=time;
			}
			if(time>maxValue) {
				maxValue=time;
			}
		}
		double endavarage = System.currentTimeMillis();
		System.out.println("min Value : "+minValue);
		System.out.println("max value : "+maxValue);
		System.out.println("Avarage : "+((endavarage-firstavarage)/searchwords.length));
	}

}
