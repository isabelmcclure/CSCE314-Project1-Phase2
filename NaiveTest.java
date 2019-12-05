import java.math.BigInteger;

public class NaiveTest
{
	public static boolean isPrime(BigInteger candidate)
	{
		if (!candidate.isProbablePrime((100))) return false; // Weed out the likely not primes.
		
		//the following is my code from phase 1, modified to take in a single BigInteger candidate
		int primeIndicator = 0;
		for (BigInteger j = candidate; j.compareTo(BigInteger.valueOf(0)) == 1; j = j.subtract(BigInteger.valueOf(1))) {
			if (candidate.mod(j) == BigInteger.valueOf(0)) {
				primeIndicator = primeIndicator + 1;
			}
		}
		if (primeIndicator == 2) {//only divisible by itself and 1
			return true;
		}
		return false;
		
		
	}
}