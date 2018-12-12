package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import selectionGUI.MainModesSelectionController;
import javafx.scene.shape.SVGPath;

public class Helpers {
	public static String readPath(){
		String temp="";
		String s="";
		File file = new File("path.txt");
		try {
			Scanner in = new Scanner(file);
			while(in.hasNextLine()){
				s=in.nextLine();
				temp+=s;
				
			}
		} catch (FileNotFoundException e) {
		}
		return temp;
	}
	public static String readLine(){
		String temp="";
		String s="";
		File file = new File("lines.txt");
		try {
			Scanner in = new Scanner(file);
				s=in.nextLine();
		} catch (FileNotFoundException e) {
		}
		return s;
	}
	public static String readOtherLabels(){
		String temp="";
		String s="";
		File file = new File("otherLabels.txt");
		try {
			Scanner in = new Scanner(file);
				s=in.nextLine();
		} catch (FileNotFoundException e) {
		}
		return s;
	}
	public static void readCountries(HashMap <String,Integer> countries,HashMap <Integer,SVGPath> countriesPath ){
		String temp="";
		String value = "";
		String path="";
		String s;
		File file;
		if(MainModesSelectionController.country==2)
			 file = new File("countries.txt");
		else
			file = new File("EgyptMap.txt");
		int i=0;
		try {
			Scanner in = new Scanner(file);
			while(in.hasNext()){
				s=in.nextLine();
				while(true){
					if(s.charAt(i)=='='){
						i=i+2;
						temp+=s.charAt(i);
						i++;
						temp+=s.charAt(i);
						i=i+3;
						while(s.charAt(i)!=' '){
							value+=s.charAt(i);
							i++;
						}
						countries.put(temp, Integer.parseInt(value));
						i=i+4;
						while(s.charAt(i)!='"'){
							path+=s.charAt(i);
							i++;
						}
						countriesPath.put(Integer.parseInt(value), new SVGPath());
						countriesPath.get(Integer.parseInt(value)).setContent(path);
						i=0;
						value="";
						path="";
						temp="";
						break;
					}
					else
						i++;
				}
			}
			
		} catch (FileNotFoundException e) {
		}	
	}

}
