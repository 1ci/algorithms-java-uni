/************************************************/
/*** Purpose:                                 ***/
/***                                          ***/
/*** Author: Hristo Hristov                   ***/
/*** Date: 29/10/2016                         ***/
/***                                          ***/
/*** Note: Based on skeleton code provided by ***/
/*** Jason Steggles 23/09/2016                ***/
/************************************************/

import java.io.*;
import java.text.*;
import java.util.*;

public class Search {

/** Global variables for counting comparisons **/
public int compSeq = 0;
public int compBin = 0;
public int compHash = 0;

/** Array of values to be searched and size **/
private int[] A;
private int size;

/** Hash array and size **/
private int[] H;
private int hSize;

/** Constructor **/
Search(int n, int hn)
{
	/** set size of array **/
	size = n;
	hSize = hn;

	/** Create arrays **/
	A = new int[size];
	H = new int[hSize];

	/** Initialize hash array **/
	/** Assume -1 indicates a location is empty **/
	for (int i = 0; i < hSize; i++)
	{
		H[i] = -1;
	}
}

/********************************************/
/*** Read a file of numbers into an array ***/
/********************************************/
public void readFileIn(String file)
{
	try
	{
		/** Set up file for reading **/
		FileReader reader = new FileReader(file);
		Scanner in = new Scanner(reader);

		/** Loop round reading in data **/
		for (int i = 0; i < size && in.hasNextInt(); i++)
		{
			/** Get net value **/
			A[i] = in.nextInt();
		}
		in.close();
	}
	catch (IOException e)
	{
		System.out.println("Error processing file " + file);
	}
}

/*********************/
/*** Hash Function ***/
/*********************/
public int hash(int key)
{
	return key % hSize;
}

/*****************************/
/*** Display array of data ***/
/*****************************/
public void displayData(int line, String header)
{
	/** Integer Formatter **/
	NumberFormat FI = NumberFormat.getInstance();
	FI.setMinimumIntegerDigits(3);

	/** Print header string **/
	System.out.print(header);

	/** Display array data **/
	for (int i = 0; i < size; i++)
	{
		/** New line? **/
		if (i % line == 0)
		{
			System.out.println();
		}

		/** Display value **/
		System.out.print(FI.format(A[i]) + " ");
	}
}

/**************************/
/*** Display hash array ***/
/**************************/
public void displayHash(int line)
{
	/** Integer Formatter **/
	NumberFormat FI = NumberFormat.getInstance();
	FI.setMinimumIntegerDigits(3);

	/** Print header string **/
	System.out.print("Hash Array of size " + hSize);

	/** Display array data **/
	for (int i = 0; i < hSize; i++)
	{
		/** New line? **/
		if (i % line == 0)
		{
			System.out.println();
		}

		/** Display value **/
		System.out.print(FI.format(H[i]) + " ");
	}
}

/*************************/
/*** Sequential search ***/
/*************************/
public int seqSearch(int key) {
	int i;
	for (i = 0; i < size; ++i) {
		if (A[i] == key) {
			compSeq += i + 1;
			return i;
		}
		//++compSeq;
		if (A[i] > key) {
			++i; // Account for "if (A[i] == key)"
			break;
		}
	}
	compSeq += i;
	return -1;
}

/*********************/
/*** Binary search ***/
/*********************/
public int binSearch(int key, int L, int R) {
	/** Have the array pointers crossed? **/
	if (L > R) 
		return -1; /** We couldn't find the key **/

	/** Calculate a mid point **/
	int m = (L + R) / 2;
	//System.out.println(m + " num: " + A[m]);

	/** Check if the key is found **/
	++compBin;
	if (key == A[m])
		return m; // Found!

	/** Decide which side to search in next if not found **/
	//++compBin;
	if (key > A[m])
		return binSearch(key, m + 1, R); // RHS
	else
		return binSearch(key, L, m - 1); // LHS
}

/**********************************************/
/*** Inserts an element into the hash array ***/
/**********************************************/
private void addToHash(int value)
{
	/** Calculate index at which to store the value **/
	int index = hash(value);
	
	/** Try to find a blank location **/
	int i;
	
	/** Start by going towards the right of the array **/
	for (i = index; i < hSize; ++i) {
		if (H[i] == -1) {
			H[i] = value;
			return;
		}
	}
	/** If you reach the end, start over up until the original index **/
	for (i = 0; i < index; ++i) {
		if (H[i] == -1) {
			H[i] = value;
			return;
		}
	}
	/** Insertion failed. No available unused location found **/
}

/**************************************/
/*** Reads data into the hash array ***/
/**************************************/
public void readIntoHash(String file)
{
	try
	{
		/** Set up file for reading **/
		FileReader reader = new FileReader(file);
		Scanner in = new Scanner(reader);

		/** Loop round reading in data **/
		for (int i = 0; i < hSize && in.hasNextInt(); i++)
		{
			/** Get net value **/
			addToHash(in.nextInt());
		}
		in.close();
	}
	catch (IOException e)
	{
		System.out.println("Error processing file " + file);
	}
}

/********************************************/
/*** Allows the hash table to be searched ***/
/********************************************/
public int hashSearch(int key)
{
	/** Calculate index at which the key might be **/
	int index = hash(key);
	
	/** Try to find where it actually is **/
	int i;
	
	/** We're using linear probing. So go towards the right first. **/
	for (i = index; i < hSize; ++i) {
		
		++compHash; // Also, what should we do first? Check if the value at the location is empty (the thing above) OR check if it equals the key first (value we're searching for)?
		if (H[i] == key) {
			return i;
		}
		//++compHash; // Does checking if the cell (value at the location) is empty count as a comparison?
		if (H[i] == -1) { // Is it empty (has this location ever been used/populated)?
			return -1;
		}
	}
	/** Start over up until where we thought it might be **/
	for (i = 0; i < index; ++i) {
		
		++compHash;
		if (H[i] == key) {
			return i;
		}
		//++compHash;
		if (H[i] == -1) {
			return -1;
		}
	}
	
	/** We couldn't find the key **/
	return -1;
}

} /*** End of class Search ***/