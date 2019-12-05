// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class FileAccess {
  
  public static boolean loadPrimes(Primes primes, String filename) {
	  	try {
			File file = new File(Config.DATAPATH + filename);
			Scanner sc = new Scanner(file);
			while (sc.hasNextBigInteger()) {
				primes.addPrime(sc.nextBigInteger());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
  }
  
  public static boolean loadCrosses(Primes primes, String filename) {
	  try {
			File file = new File(Config.DATAPATH + filename);
			Scanner sc = new Scanner(file).useDelimiter(",");
			while (sc.hasNextBigInteger()) {
				Pair<BigInteger> newPair = new Pair<BigInteger>(null, null);
				newPair.setLeft(sc.nextBigInteger());
				if (sc.hasNextBigInteger()) {
					newPair.setRight(sc.nextBigInteger());
				}
				primes.addCross(newPair);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;
	}
  
  public static boolean savePrimes(Primes primes, String filename)
  {
	  if (primes.primeCount() == 0) {//no primes in list
		  return false;
	  }
	  try {
		FileWriter fstream = new FileWriter(Config.DATAPATH + filename);
		BufferedWriter bw = new BufferedWriter(fstream);
		Primes.IterablePrimes itr = primes.iteratePrimes();
		for(BigInteger p : itr) {
			try {
				bw.write(p.toString());
				bw.newLine();
			} catch (IOException e) {
				return false;
			}
		}
		bw.close();
	} catch (FileNotFoundException e) {
		return false;
	} catch (IOException e1) {
		return false;
	}

  	return true;
  }
  
  public static boolean saveCrosses(Primes primes, String filename)
  {
	  if (primes.crossesCount() == 0) {//no crosses in list
		  return false;
	  }
	  try {
		FileWriter fstream = new FileWriter(Config.DATAPATH + filename);
		BufferedWriter bw = new BufferedWriter(fstream);
		Primes.IterableCrosses itr = primes.iterateCrosses();
		for(Pair<BigInteger> p : itr) {
			try {
				bw.write(p.left().toString());
				bw.write(",");
				bw.write(p.right().toString());
				bw.newLine();
			} catch (IOException e) {
				return false;
			}
		}
		bw.close();
	} catch (FileNotFoundException e) {
		return false;
	} catch (IOException e1) {
		return false;
	}

  	return true;
  }
}