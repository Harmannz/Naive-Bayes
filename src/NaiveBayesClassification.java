import java.util.List;

import DAO.Instance;
import classifier.NaiveBayesClassifier;
import util.Util;

/**
 * This class uses the Naive Bayes method to classify the unlabelled instances. 
 * The class construct the classifier (Naive Bayes probability tables), and then applies 
 * the classifier to the data in trainingset.
 * @author Harman Singh
 * @version 1.0
 */
public class NaiveBayesClassification {
	private List<Instance> trainingSet, testSet;
	private NaiveBayesClassifier nbClassifier;
	private final static String trainingFile = "resources/spamLabelled.dat";
	private final static String testFile = "resources/spamUnlabelled.dat";

	
	public NaiveBayesClassification(final String traniningFile, final String testFile) {
		this.trainingSet = Util.loadDataSet(traniningFile, true);
		this.testSet = Util.loadDataSet(testFile, false);
		//compute the occurrence for trainingset
		nbClassifier = new NaiveBayesClassifier();
		nbClassifier.computeOccurrence(trainingSet);
		nbClassifier.computeProbability(trainingSet);
		performClassification();
	}
	/**
	 * Helper method for performing the classification on the test set
	 * @author Harman Singh
	 */
	private void performClassification(){
		int i = 1;
		Util.emphasise("Performing Classification");
		for(Instance instance : testSet){
			int prediction = nbClassifier.classify(instance.getAttributes());
			System.out.printf("Test Instance: %d. Prediction: %s\n", i, Util.getClassString(prediction));
			i++;
		}
	}
	
	public static void main(String[] args){
		if (args.length < 2){
			new NaiveBayesClassification(trainingFile, testFile);
		}else{
			new NaiveBayesClassification(args[0], args[1]);
		}
	}
}
