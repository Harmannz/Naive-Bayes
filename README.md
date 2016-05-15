# Naive-Bayes
A Naive Bayes Classifier that builds a classifier from training set and applies it to the test set.
## Description  
The labelled data set is in the file spamLabelled.dat, which describes 200 emails, labelled as spam
or non-spam. Each email is specified by 12 binary attributes, indicating the presence of features
such as “Viagra”, “MILLION DOLLARS”, significant amounts of text in CAPS, an invalid reply-to
address, and so on. Note that there are 212=4096 possible input patterns, compared to a data set
of just 200 examples.   
`The layout of the data is that each row is an instance of features from one email, and columns
correspond to the features, which are binary: the feature is either there or not. The last (rightmost)
column is the class: 1 = spam, 0 = non-spam.`   
>The file spamUnlabelled.dat contains 10 new input patterns to be classified.
>There’s a good entry in [wikipedia] (https://en.wikipedia.org/wiki/Naivety)
>that discusses exactly the domain we’re applying the algorithm to.   

Zero probabilities are a problem for the Naive Bayes method.   
For example, if the training data did not include a C = 1 instance with attribute F8 = 1, the
simplest version of the algorithm will assume that P(C = 1|F8 = 1) = 0, and never predict C = 1
if F8 = 1. This is generally a bad idea because P(C = 1|F8 = 1) is unlikely to be exactly zero,
even if it is very low. **The simplest solution is to initialise all the counts to 1, rather than 0, which
means every P(C|F) has at least a low probability.** 
## Requirements
Your job is to use the Naive Bayes method to classify the unlabelled instances in the spamUnlabelled.dat
file. The method should use the training data in spamLabelled.dat to construct the classifier (Naive
Bayes probability tables), and then apply the classifier to the data in spamUnlabelled.dat
Your program should take two file names as command line arguments, construct a classifier from
the data in the first file, and then apply the classifier to the data in the second file.
