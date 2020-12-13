import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import Phase_End_Interfaces.*;
import customException.Duplicate_File;
import customException.FileNotInDirectoryException;
import customException.InvalidInputException;

public class FileClass implements FIle_Interface {
	public static List<String> fileList = new ArrayList<>();

	public void syncLists() {
		List<String> pathnames = new ArrayList<>();
		File f = new File("./");
		pathnames = Arrays.asList(f.list());
		pathnames = pathnames.stream().filter(p -> p.endsWith(".txt")).collect(Collectors.toList());
		fileList.clear();
		fileList.addAll(pathnames);
	}

	public void showFileList() {
		syncLists();
		List<String> pathnames = new ArrayList<>();
		File f = new File("./");
		pathnames = Arrays.asList(f.list());
		pathnames = pathnames.stream().filter(p -> p.endsWith(".txt")).collect(Collectors.toList());
		fileList.clear();
		fileList.addAll(pathnames);
		if (fileList.isEmpty()) {
			System.out.println("The current directory doesnt have any files");
		}
		System.out.println("These are the files in the system. \n\n");
		Collections.sort(fileList, String.CASE_INSENSITIVE_ORDER);
		fileList.forEach(file -> System.out.println("\t"+file));

	}

	public static List<String> getFileList() {
		return fileList;
	}

	public static void setFileList(List<String> fileList) {
		FileClass.fileList = fileList;

	}

	public void remove() throws FileNotInDirectoryException {
		syncLists();
		System.out.println("****************************************************");
		System.out.println("\tPlease enter the name of the file you  want to delete.");
		Scanner scanner = new Scanner(System.in);
		String file = scanner.nextLine();
		String regex = ".*\\.txt$";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(file);
		boolean found = matcher.matches();
		try {
			if(!found) {
				throw new InvalidInputException("\n\tFile names must end in .txt");
			}
		}catch(InvalidInputException e) {
			System.err.println(e.getMessage());
		}
		while (!found) {
			
			System.out.println("\n\tPlease enter another file ending with .txt");
			file = scanner.nextLine();
			matcher = p.matcher(file);
			found = matcher.matches();
			//System.out.println(found);
		}
		File myFile = new File(file);
		if (myFile.delete()) {
			fileList.remove(file);
			System.out.println("\n\tThe file " + file + " was successfully deleted.");
		} else {
			throw new FileNotInDirectoryException("\n\tThis file is not in the directory");
		}

	}

	public void add() throws Duplicate_File, IOException {
		System.out.println("****************************************************");
		System.out.println("\tPlease enter the name of the file you  want to add.");
		Scanner scanner = new Scanner(System.in);
		String file = scanner.nextLine();
		String regex = ".*\\.txt$";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(file);
		boolean found = matcher.matches();
		try {
			if(!found) {
				throw new InvalidInputException("\n\tFile names must end in .txt");
			}
		}catch(InvalidInputException e) {
			System.err.println(e.getMessage());
		}
		while (!found) {
			
			System.out.println("\n\tPlease enter another file ending with .txt");
			file = scanner.nextLine();
			matcher = p.matcher(file);
			found = matcher.matches();
			//System.out.println(found);
		}
		File myFile = new File(file);
		if (myFile.createNewFile()) {
			fileList.add(file);

			System.out.println("\n\tThe file " + file + " has been added successfully");
		} else {
			throw new Duplicate_File("This file already exists");

		}

	}
}
