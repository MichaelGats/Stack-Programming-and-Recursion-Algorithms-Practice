package mgatti.hw1;

import algs.days.day03.FixedCapacityStack;

/**
 * Use a Stack to compute the Zeckendorf representation for a number. 
 * 
 * The Zeckendorf representation for 1,000,000 is "10001010000000000010100000000"
 *                                                 |   | |           | |
 *                                                 |   | |           | 55
 *                                                 |   | |           |
 *                                                 |   | |           144
 *                                                 |   | 46368
 *                                                 |   |
 *                                                 |   121393
 *                                                 |
 *                                                 832040
 *
 * 1,000,000 = 832,040 + 121393 + 46368 + 144 + 55                                                            
 *
 * left-most digit is the most significant and the right-most is the least significant.
 */
public class Zeckendorf {

	/**
	 * Return a Stack of integers containing (in reverse order) the Fibonacci numbers from 1 up to and including n.
	 * 
	 * Calling makeFibonacciStack(12) would return a stack with the following integers: 1, 2, 3, 5, 8
	 * 
	 * Note: while the first two Fibonacci numbers F0=0 and F1=1, the numbers that appear on the stack refer to F2 and higher, to avoid
	 * confusion.
	 */
	public static FixedCapacityStack<Long> makeFibonacciStack(long n) {
		FixedCapacityStack<Long> stack = new FixedCapacityStack<Long>(200);
		long n_minusTwo = 1;
		long n_minusOne = 2;
		stack.push(n_minusTwo);
		stack.push(n_minusOne);
		
		long next = n_minusOne + n_minusTwo;
		while (next <= n) {
			stack.push(next);
			n_minusTwo = n_minusOne;
			n_minusOne = next;
			
			next = n_minusOne + n_minusTwo;
		}
		return stack;
	}
	
	/**
	 * Compute the Zeckendorf representation for n, that is, the unique sequence of sums of Fibonacci numbers, where none are duplicated.
	 * 
	 * The first 7 Fibonacci numbers that make up the representation are: 1, 2, 3, 5, 8, 13, 21
	 * 
	 * The Zeckendorf representation for the numbers from 1 to 10 are:
	 * 
	 * 1  = 1     = 1
	 * 2  = 10    = 2
	 * 3  = 100   = 3
	 * 4  = 101   = 3 + 1
	 * 5  = 1000  = 5
	 * 6  = 1001  = 5 + 1
	 * 7  = 1010  = 5 + 2
	 * 8  = 10000 = 8
	 * 9  = 10001 = 8 + 1
	 * 10 = 10010 = 8 + 2
	 * ...
	 * 
	 * This function should use makeFibonacciStack() to construct a stack containing (in reverse order) the Fibonacci numbers from F2 to Fn
	 * where Fn is the largest Fibonacci number smaller than or equal to n.
	 */
	public static String zeckendorfRepresentation(long n) {
		//I asked the professor about why I was missing the lingering zeros, and 
		//Its leaving the while loop too early. I looked into it using the debugger and it makes sense because there are 
		//still elements that won't be referenced before it leaves the loop because N would be zero. 
		//But I am not sure how to change the condition so it would not leave until its finished.
		FixedCapacityStack<Long> stack = makeFibonacciStack(n);
		String fib = new String();
			if (n == 0 || n == 1) {
			fib = fib.valueOf(n);
			return fib;
		}
		else {
			while (n > 0) {
				long largest = stack.pop();
				if (largest <= n) {
					//System.out.println(largest);
					n = n - largest;
					fib = fib + "1";

				}
				else {
					fib = fib + "0";
				}
			}
		}

		return fib;
	}

	


	/** No need to modify below this line. */
	public static void main(String[] args) {
		System.out.println("N\tZeck. Repr.");
		for (int i = 1; i <= 20; i++) {
			System.out.println(i + "\t" + zeckendorfRepresentation(i));
		}
	}
}
