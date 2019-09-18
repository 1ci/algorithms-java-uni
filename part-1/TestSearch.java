/******************************************************/
/*** Purpose: Test class to illustrate Search class ***/
/***                                                ***/
/*** Author: Hristo Hristov                         ***/
/*** Date: 29/10/2016                               ***/
/******************************************************/

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TestSearch {

/** Filepath constants **/
private static final String FILEPATH_DATA_1 = "C:\\Users\\Ici\\Desktop\\data1.txt";
private static final String FILEPATH_DATA_2 = "C:\\Users\\Ici\\Desktop\\data2.txt";
private static final String FILEPATH_SEARCH_1 = "C:\\Users\\Ici\\Desktop\\search1.txt";
private static final String FILEPATH_SEARCH_2 = "C:\\Users\\Ici\\Desktop\\search2.txt";

/** Array to store search values in **/
private int[] searchValues;

/** Constructor **/
private TestSearch(int arraySize, String file)
{
	searchValues = new int[arraySize];
	readFileIn(file);
}

public static void main(String[] args)
{
	/** Performing Test 1 **/
	System.out.print("Performing Test 1\n-----------------\n");
	
	Search S = new Search(100, 151);
	
	/** Read in test data **/
	S.readFileIn(FILEPATH_DATA_1);
	S.readIntoHash(FILEPATH_DATA_1);
	
	/** Display data and hash arrays **/
	S.displayData(20, "Data Array");
	System.out.print("\n\n");
	S.displayHash(20);
	System.out.print("\n");
	
	/** Read in search values **/
	TestSearch searchFile = new TestSearch(10, FILEPATH_SEARCH_1);
	
	/** Declare vars calculating avg comparisons of search algorithms **/
	float avgSeqSearchComp = 0.0f;
	float avgBinSearchComp = 0.0f;
	float avgHashSearchComp = 0.0f;
	
	/** Let's record the best and worst cases too **/
	int minSeqSearchComp = Integer.MAX_VALUE;
	int minBinSearchComp = Integer.MAX_VALUE;
	int minHashSearchComp = Integer.MAX_VALUE;
	
	int maxSeqSearchComp = 0;
	int maxBinSearchComp = 0;
	int maxHashSearchComp = 0;
	
	/** Loop through search values and perform search tests **/
	for (int searchValue : searchFile.searchValues)
	{
		/** Reset comparison counters **/
		S.compSeq = 0;
		S.compBin = 0;
		S.compHash = 0;
		
		/** Test sequential search **/
		int index = S.seqSearch(searchValue);
		System.out.printf("\nseqSearch( %d ) = %d; compSeq = %d\n", searchValue, index, S.compSeq);
		avgSeqSearchComp += (float) S.compSeq;
		if (S.compSeq < minSeqSearchComp) minSeqSearchComp = S.compSeq;
		if (S.compSeq > maxSeqSearchComp) maxSeqSearchComp = S.compSeq;
		
		/** Test binary search **/
		index = S.binSearch(searchValue, 0, 99);
		System.out.printf("binSearch( %d ) = %d; compBin = %d\n", searchValue, index, S.compBin);
		avgBinSearchComp += (float) S.compBin;
		if (S.compBin < minBinSearchComp) minBinSearchComp = S.compBin;
		if (S.compBin > maxBinSearchComp) maxBinSearchComp = S.compBin;
		
		/** Test hash search **/
		index = S.hashSearch(searchValue);
		System.out.printf("hashSearch( %d ) = %d; compHash = %d\n", searchValue, index, S.compHash);
		avgHashSearchComp += (float) S.compHash;
		if (S.compHash < minHashSearchComp) minHashSearchComp = S.compHash;
		if (S.compHash > maxHashSearchComp) maxHashSearchComp = S.compHash;
	}
	
	/** Calculate average comparisons and print them out **/
	avgSeqSearchComp /= (float) searchFile.searchValues.length;
	avgBinSearchComp /= (float) searchFile.searchValues.length;
	avgHashSearchComp /= (float) searchFile.searchValues.length;
	
	System.out.printf("\nMin, Avg, Max Sequential Search Comparisons: %d; %.1f; %d\n", minSeqSearchComp, avgSeqSearchComp, maxSeqSearchComp);
	System.out.printf("Min, Avg, Max Binary Search Comparisons: %d; %.1f; %d\n", minBinSearchComp, avgBinSearchComp, maxBinSearchComp);
	System.out.printf("Min, Avg, Max Hash Search Comparisons: %d; %.1f; %d\n", minHashSearchComp, avgHashSearchComp, maxHashSearchComp);
	
	/** Performing Test 2 **/
	System.out.print("\nPerforming Test 2\n-----------------\n");
	
	S = new Search(1000, 1499);
	
	/** Read in test data **/
	S.readFileIn(FILEPATH_DATA_2);
	S.readIntoHash(FILEPATH_DATA_2);
	
	/** Display data and hash arrays **/
	S.displayData(20, "Data Array");
	System.out.print("\n\n");
	S.displayHash(20);
	System.out.print("\n");
	
	/** Read in search values **/
	searchFile = new TestSearch(10, FILEPATH_SEARCH_2);
	
	/** Re-use vars calculating min,avg,max comparisons of search algorithms **/
	avgSeqSearchComp = 0.0f;
	avgBinSearchComp = 0.0f;
	avgHashSearchComp = 0.0f;
	
	minSeqSearchComp = Integer.MAX_VALUE;
	minBinSearchComp = Integer.MAX_VALUE;
	minHashSearchComp = Integer.MAX_VALUE;
	
	maxSeqSearchComp = 0;
	maxBinSearchComp = 0;
	maxHashSearchComp = 0;
	
	/** Loop through search values and perform search tests **/
	for (int searchValue : searchFile.searchValues)
	{
		/** Reset comparison counters **/
		S.compSeq = 0;
		S.compBin = 0;
		S.compHash = 0;
		
		/** Test sequential search **/
		int index = S.seqSearch(searchValue);
		System.out.printf("\nseqSearch( %d ) = %d; compSeq = %d\n", searchValue, index, S.compSeq);
		avgSeqSearchComp += (float) S.compSeq;
		if (S.compSeq < minSeqSearchComp) minSeqSearchComp = S.compSeq;
		if (S.compSeq > maxSeqSearchComp) maxSeqSearchComp = S.compSeq;
		
		/** Test binary search **/
		index = S.binSearch(searchValue, 0, 999);
		System.out.printf("binSearch( %d ) = %d; compBin = %d\n", searchValue, index, S.compBin);
		avgBinSearchComp += (float) S.compBin;
		if (S.compBin < minBinSearchComp) minBinSearchComp = S.compBin;
		if (S.compBin > maxBinSearchComp) maxBinSearchComp = S.compBin;
		
		/** Test hash search **/
		index = S.hashSearch(searchValue);
		System.out.printf("hashSearch( %d ) = %d; compHash = %d\n", searchValue, index, S.compHash);
		avgHashSearchComp += (float) S.compHash;
		if (S.compHash < minHashSearchComp) minHashSearchComp = S.compHash;
		if (S.compHash > maxHashSearchComp) maxHashSearchComp = S.compHash;
	}
	
	/** Calculate average comparisons and print them out **/
	avgSeqSearchComp /= (float) searchFile.searchValues.length;
	avgBinSearchComp /= (float) searchFile.searchValues.length;
	avgHashSearchComp /= (float) searchFile.searchValues.length;
	
	System.out.printf("\nMin, Avg, Max Sequential Search Comparisons: %d; %.1f; %d\n", minSeqSearchComp, avgSeqSearchComp, maxSeqSearchComp);
	System.out.printf("Min, Avg, Max Binary Search Comparisons: %d; %.1f; %d\n", minBinSearchComp, avgBinSearchComp, maxBinSearchComp);
	System.out.printf("Min, Avg, Max Hash Search Comparisons: %d; %.1f; %d", minHashSearchComp, avgHashSearchComp, maxHashSearchComp);
}

/********************************************/
/*** Read a file of numbers into an array ***/
/********************************************/
private void readFileIn(String file)
{
	try
	{
		/** Set up file for reading **/
		FileReader reader = new FileReader(file);
		Scanner in = new Scanner(reader);

		/** Loop round reading in data **/
		for (int i = 0; i < searchValues.length && in.hasNextInt(); i++)
		{
			/** Get net value **/
			searchValues[i] = in.nextInt();
		}
		in.close();
	}
	catch (IOException e)
	{
		System.out.println("Error processing file " + file);
	}
}

} /*** End of class TestSearch ***/