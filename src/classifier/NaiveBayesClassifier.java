package classifier;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.Instance;
import util.Util;
/**
 * Wrapper class to create a classifier, which is essentially a Naive Bayes Probability Table
 * 
 * @author Harman Singh
 * @version 1.0
 */
public class NaiveBayesClassifier {
	//represents the number of binary attributes in an instance
	private final static int NUM_OF_ATTRIBUTES = 12;
	/** The keys represent all the different feature values. Because there are 12 attributes
	 *  There will be 24 keys since each attribute is binary.
	 *  The Value array represents occurrence/probability given [classA, classB]
	 */
	private Map<Integer, double[]> probabilityTable;
	private Map<Integer, int[]> occurrenceTable;
	private double probabilityClass0, probabilityClass1;
	
	public NaiveBayesClassifier(){
		this.occurrenceTable = new HashMap<Integer, int[]>();
		this.probabilityTable = new HashMap<Integer, double[]>();
		initialiseOccurrenceTable();
	}
	/**
	 * Helper method for initialise the occurence table.
	 * To avoid the zero probabilities. 
	 * Each occurence is initialised with a count of 1
	 * 
	 * @author Harman Singh
	 */
	private void initialiseOccurrenceTable(){
		for(int i = 0; i < NUM_OF_ATTRIBUTES * 2; i++){
			occurrenceTable.put(i, new int[]{1,1});
		}
	}
	/**
	 * Classifies attributes into class A or class B
	 * If return value is 0, then the attributes are classified as Class A
	 * If return value is 1, then the attributes are classified as Class B.
	 * 
	 * The classification is based on Naive Bayes Rules.
	 * @author Harman Singh
	 * @param attributes
	 * @return
	 */
	public int classify(List<Integer> attributes){
		double scoreClassA = 1.0, scoreClassB = 1.0;
		
		for(int i = 0; i < attributes.size(); i++){
			scoreClassA *= probabilityTable.get(2*i + attributes.get(i))[0];
			scoreClassB *= probabilityTable.get(2*i + attributes.get(i))[1];
		}
		scoreClassA *= probabilityClass0;
		scoreClassB *= probabilityClass1;
		System.out.println("P(Not Spam): " + scoreClassA);
		System.out.println("P(Spam): " + scoreClassB);
		if (scoreClassA >= scoreClassB){
			return Util.NOT_SPAM;
		}else{
			return Util.SPAM;
		}
	}
	/**
	 * Computes the occurrence of each attribute in the data parameter
	 * @author Harman Singh
	 * @param data
	 */
	public void computeOccurrence(List<Instance> data){
		for(Instance instance : data){
			//add occurrence of attribute to occurrence table
			List<Integer> attribute = instance.getAttributes();
			for(int i = 0; i < attribute.size(); i++){
				int[] value = occurrenceTable.get(2*i + attribute.get(i));
				//if class label == 0 then increase count for class A
				//if class label == 1 then increase count for class B
				value[instance.getClassLabel()] += 1;
			}
		}
		Util.emphasise("Occurrence Table");
		System.out.format("%s:%17s\n", "Feature", "[Not Spam, Spam]");
		for(Map.Entry<Integer, int[]> entry : occurrenceTable.entrySet()){
			System.out.println(printFeature(entry.getKey()) + ": " + Arrays.toString(entry.getValue()));
		}
	}
	
	private String printFeature(int key){
		int feature = key / 2 + 1;
		String value = (key % 2) == 1 ? "=true " : "=false";
		return "F" + feature + value;
	}
	
	/**
	 * Computes the probability of each attribute value in the data parameter.
	 * The probabilities have been adjusted to avoid zero probability issue with
	 * Naive Bayes classification.
	 * @author Harman Singh
	 * @param data
	 */
	public void computeProbability(List<Instance> data){
		//sum probabilities of classes
		int class0 = 0, class1 = 0;
		for(Instance instance: data){
			if(instance.getClassLabel() == 0){
				class0++;
			}else{
				class1++;
			}
		}
		// now calculate the probability of each attribute value
		for(int i = 0; i < NUM_OF_ATTRIBUTES * 2; i++){
			double reject = ((double)occurrenceTable.get(i)[0])/(class0+2);
			double approve = ((double)occurrenceTable.get(i)[1])/(class1+2);
			probabilityTable.put(i, new double[]{reject,approve});
		}
		probabilityClass0 = ((double)(class0+1))/(class0+class1+2);
		probabilityClass1 = ((double)(class1+1))/(class0+class1+2);
		//print statements for test
		Util.emphasise("Naive Bayes Probability Table");
		System.out.format("%s:%17s\n", "Feature", "[Not Spam, Spam]");
		for(Map.Entry<Integer, double[]> entry : probabilityTable.entrySet()){
			System.out.println(printFeature(entry.getKey()) + ": " + Arrays.toString(entry.getValue()));
		}
		System.out.println("\nP(Not Spam): " + probabilityClass0);
		System.out.println("P(Spam): " + probabilityClass1 + "\n");
	}
	
	public static void main(String[] args){
		//run this for testing
		NaiveBayesClassifier nt = new NaiveBayesClassifier();
		 
		for(Map.Entry<Integer, int[]> entry : nt.occurrenceTable.entrySet()){
			System.out.println(entry.getKey() + ": " + Arrays.toString(entry.getValue()));
		}
	}
}
