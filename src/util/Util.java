package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DAO.Instance;
/**
 * Utility class for loading the data set from file as well as miscellaneous methods
 * @author Harman Singh
 * @version 1.0 
 */
public class Util {
	// Two possible classifications
    public static final int NOT_SPAM = 0;
    public static final int SPAM = 1;
    
    /**
     * Loads the data set from filename. 
     * If the data set is labelled, then the last binary digit will be used as the class label
     * 
     * @author Harman Singh
     * @param filename Name of the file to load data from
     * @param isLabelled Whether or not the data set is labelled or not
     * @return
     */
    public static List<Instance> loadDataSet(final String filename, final boolean isLabelled){
		List<Instance> data = new ArrayList<>();
		System.out.println("Reading data from file: " + filename);
		try (Scanner din = new Scanner(new File(filename))){
			//read each instance represented as a row
			while (din.hasNextLine()){
				Scanner line = new Scanner (din.nextLine());
				int classLabel = -1;
				List<Integer> attributes = new ArrayList<>();
				while(line.hasNext()){
					attributes.add(line.nextInt());
				}
				//If data set is labelled, then the last digit is the class label
				if (isLabelled){
					//remove last binary digit since it is the class label
					classLabel = attributes.remove(attributes.size()-1);
				}
				line.close(); //must close the scanner
				if (classLabel == -1 && isLabelled) {
					throw new RuntimeException("Class label not found for a labelled instance");
				}
				data.add(new Instance(classLabel, attributes));
			}
			System.out.printf("Read %d instances\n", data.size());
			
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File not found: " + filename);
		}
		
		return data;
	}
    
    /**
     * Emphasises a message in console.
     * @param message
     */
    public static void emphasise(String message) {
    	printHash(message);
        System.out.println("# " + message + " #");
        printHash(message);
    }
    
    public static String getClassString(int classLabel) {
        return (classLabel == SPAM ? "SPAM" : "NOT SPAM");
    }

    private static void printHash(String message) {
        for (int i = 0; i < message.length() + 4; i++) {
            System.out.print("#");
        }
        System.out.print("\n");
    }
}
