//A0112675H : Sitti Maryam binte Rashid Ridza

import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class TextBuddy {
	
	private static String MESSAGE_WELCOME = "Welcome to TextBuddy. %s is ready for use";
	private static String MESSAGE_INVALID = "Invalid command!";
	private static String MESSAGE_ADDED = "added to %s: \"%s\"\n";
	private static String MESSAGE_CLEAR = "all content deleted from %s \n";
	private static String MESSAGE_COMMAND = "command: ";
	private static String MESSAGE_DELETE = "deleted from %s: \"%s\"\n";
	private static String MESSAGE_EMPTY = "%s is empty\n";
	private static String MESSAGE_SEARCH_FAIL = "%s is not found. \n";
	private static String MESSAGE_SEARCH_SUCCESS = "%s is found. \n";

	private static File newFile, tempFile;
	private static BufferedWriter buffWriter;

	private static Scanner scanner = new Scanner(System.in);
	
	// use ArrayList to store the input by user
	private final static ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		String fileName = args[0];
		String commandInput, commandType, textInput;

		newFile = checkFile(fileName);

		System.out.println(String.format(MESSAGE_WELCOME, newFile));

		while (true) {

			System.out.print(MESSAGE_COMMAND);
			commandInput = scanner.nextLine();

			textInput = removeCommandType(commandInput);
			commandType = getFirstWord(commandInput.toUpperCase());

			executeCommand(commandType, textInput, newFile);
		}

	}

	private static File checkFile(String fileName) {

		File checkNewFile = new File(fileName);
		try {
			if (!checkNewFile.exists())
				checkNewFile.createNewFile();
			else {
				String line;
				BufferedReader buffReader = new BufferedReader(new FileReader(
						fileName));
				while ((line = buffReader.readLine()) != null) {
					list.add(line);
				}
				buffReader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return checkNewFile;
	}

	private static String getFirstWord(String commandInput) {
		StringTokenizer commandToken = new StringTokenizer(commandInput);
		return commandToken.nextToken();
	}

	private static String removeCommandType(String commandInput) {
		return commandInput.replace(getFirstWord(commandInput), "").trim();
	}

	private static void executeCommand(String commandType, String textInput, File newFile) {

		switch (commandType) {

		case "EXIT":	System.exit(0);
						
		case "ADD":		add(textInput, newFile);
						break;
		
		case "DELETE":	delete(textInput, newFile);		
						break;

		case "DISPLAY":	display(newFile);
						break;

		case "CLEAR":	clear(newFile);
						break;
		
		case "SORT":	sort(newFile);
						break;
						
		case "SEARCH":	search(textInput, newFile);
						break;

		default:		System.out.println(MESSAGE_INVALID);
		}
	}

	private static void writeToFile(String textInput, File fileName) {

		try {

			FileWriter file = new FileWriter(fileName, true);

			buffWriter = new BufferedWriter(file);
			buffWriter.write(textInput);
			buffWriter.newLine();
			buffWriter.flush();
			buffWriter.close();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void add(String textInput, File fileName){
		list.add(textInput);
		writeToFile(textInput, newFile);
		System.out.println(String.format(MESSAGE_ADDED, newFile, textInput));
	}
	
	private static void delete(String textInput, File fileName){
		
		int index = Integer.parseInt(textInput);
		if(index > 0 && list.size() >= index){
			index = index - 1; //index start at 0
			String deletedText = list.get(index);
			list.remove(index);
			System.out.println(String.format(MESSAGE_DELETE, newFile, deletedText));
			tempFile = new File("store.txt");
			for(int i = 0; i<list.size(); i++){
			writeToFile(list.get(i), tempFile);
			}
			newFile.delete();
			tempFile.renameTo(newFile);
		}
		else
			System.out.println(String.format(MESSAGE_INVALID, fileName));
	}
	
	private static void clear(File fileName){
		BufferedWriter outputFile;
		try{
			outputFile = new BufferedWriter(new FileWriter(fileName.getName(), false));
			outputFile.write("");
			outputFile.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		list.clear();
		System.out.println(String.format(MESSAGE_CLEAR, fileName));
	}
	
	private static void display(File fileName){
		if (list.isEmpty())
			System.out.println(String.format(MESSAGE_EMPTY, newFile));

			for (int i = 0; i < list.size(); i++) {
				System.out.print(i + 1 + ". ");
				System.out.println(list.get(i));
			}
	}
	
	private static void sort(File fileName){
		
	}
	
	private static void search(String textInput, File fileName){
		
		for(int i = 0; i <list.size(); i++){
			String temp = list.get(i);
			if(temp.contains(textInput)){
				System.out.println(String.format(MESSAGE_SEARCH_SUCCESS, textInput));
			}
			else{
				System.out.println(String.format(MESSAGE_SEARCH_FAIL, textInput));
			}
		}
	}
}

