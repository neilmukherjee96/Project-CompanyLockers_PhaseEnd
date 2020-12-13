package Phase_End_Interfaces;

import java.io.IOException;

import customException.Duplicate_File;
import customException.FileNotInDirectoryException;

public interface FIle_Interface {
	public void showFileList();
	public void remove() throws FileNotInDirectoryException;
	public void add() throws Duplicate_File, IOException;

}
