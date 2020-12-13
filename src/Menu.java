import java.io.IOException;
import Phase_End_Interfaces.*;
import customException.Duplicate_File;
import customException.FileNotInDirectoryException;
import customException.InvalidInputException;

import java.util.Scanner;

public class Menu implements Menu_Interface {
	public void showMainMenu() throws InvalidInputException {
		Scanner scan = new Scanner(System.in);
		System.out.println("****************************************************");
		System.out.println("\t Welcome to Company Locker Pvt Ltd...\n\n");
		System.out.println(" \tThis program will allow you to safely store your documents"
				+ "\n\twhich you can later retrieve from anywhere. Any documents that have"
				+ "\n\toutlived their purpose can be deleted.");
		System.out.println();
		System.out.println("Would you like to enter the application y/n?");
		System.out.println("****************************************************");
		System.out.println("\t\t\t developed by Neil Mukherjee");
		System.out.println("\nYour Selection");
		String enter = scan.nextLine().toLowerCase();
		try {
			if (enter.equals("y")) {
				showSubMenu();
			} else if (enter.equals("n")) {
				System.out.println("\n\n****************Thank you for visiting Company Locker Pvt Ltd********************************");
			}

			else {

				throw new InvalidInputException("Invalid input");
			}
		} catch (InvalidInputException e) {
			System.err.println(e.getMessage());
			showMainMenu();
		}
	}

	public void showSubMenu() {
		Scanner scan = new Scanner(System.in);
		int counter = 0;
		while (counter == 0) {
			System.out.println("****************************************************");
			System.out.println("Please press 1 to show files for the system");
			System.out.println("Please press 2 to add a file");
			System.out.println("Please press 3 to delete a file");
			System.out.println("Please press 4 to return to main menu\n");
			System.out.println("****************************************************");
			System.out.println("\nYour Entry:");
			String choice = scan.nextLine();
			try {
				if (choice.equals("1")) {
					FileClass myFile = new FileClass();
					myFile.showFileList();
				} else if (choice.equals("2")) {
					FileClass myFile = new FileClass();
					try {
						myFile.add();
					} catch (Duplicate_File e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					} catch (IOException e) {

						System.err.println("You need to input a filename");
					}
				} else if (choice.equals("3")) {
					FileClass myFile = new FileClass();
					try {
						myFile.remove();
					} catch (FileNotInDirectoryException e) {
						// TODO Auto-generated catch block
						System.err.println(e.getMessage());
					}
				} else if (choice.equals("4")) {
					counter++;
				} else {
					throw new InvalidInputException("Please select a choice from 1-4");
				}
			} catch (InvalidInputException e) {
				System.err.println(e.getMessage());
			}

		}
		try {
			showMainMenu();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu();

		try {
			menu.showMainMenu();
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

		// TODO Auto-generated catch block

	}
}