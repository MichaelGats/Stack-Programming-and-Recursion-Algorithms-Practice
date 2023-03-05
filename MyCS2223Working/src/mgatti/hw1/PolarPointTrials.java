package mgatti.hw1;
//for question 2.2
import algs.hw1.fixed.OrderedArray;
import algs.hw1.fixed.PolarPoint;
import algs.hw1.fixed.PolarTrials;
import algs.hw1.fixed.Sorting;

/**
 * Modify this class to complete the following operations:
 * 
 *  1. Determine if a PolarPoint belongs to an array
 */
public class PolarPointTrials extends PolarTrials {

	/** 
	 * Return true if p belongs to the OrderedArray. You can find the length of the ordered array
	 * and you can get any individual element by its index location (i.e., the 5th one, or the 19th one).
	 * 
	 * You know that the OrderedArray is already sorted by Sorting.comparePolarByTheta, which means you can
	 * take advantage of this ordering.
	 */
	@Override
	protected boolean existsThetaOrdered(OrderedArray<PolarPoint> polars, PolarPoint p) {
		
		//Example of a binary array search
		int lo = 0;
		  int hi = polars.length()-1;

		  while (lo <= hi) {
		    int mid = (lo+hi)/2;

		    int rc = Sorting.comparePolarByTheta.compare(polars.get(mid), p);
		    if (rc < 0) {
		      lo = mid+1;
		    } else if (rc > 0) {
		      hi = mid-1;
		    } else {
		      return true;
		    }
		  }
		  return false;
		}
	
	/**
	 * Return the number of PolarPoints between angles [min, max], inclusive on both sides.
	 * 
	 * You know that the OrderedArray is already sorted by Sorting.comparePolarByTheta, which means you can
	 * take advantage of this ordering.
	 */
	@Override
	protected int countBetweenThetaOrdered(OrderedArray<PolarPoint> polars, int min, int max) {
		PolarPoint leftPoint = new PolarPoint(0,min);
		PolarPoint rightPoint = new PolarPoint (Integer.MAX_VALUE, max);
		
		
		int low = 0; 
		int high = polars.length() - 1;
		while (low <= high) {
			int mid = (low+ high) / 2;
			int rc = Sorting.comparePolarByTheta.compare(polars.get(mid), leftPoint);
			if (rc >= 0) {
				high = mid -1;
			} else {
				low = mid +1;
			}
		}

		int leftEdge = low;

		//search to find the right edge

		low = 0;
		high = polars.length()-1;
		while (low <= high ) {
			int mid = (low + high) /2; 
			int rc = Sorting.comparePolarByTheta.compare(polars.get(mid), rightPoint);

			if (rc <= 0) {
				low = mid + 1; 
			} else {
				high = mid -1;
			}
		}
		int rightEdge = high; 
		return rightEdge - leftEdge + 1;
	}
	
	/** Do not change this function. Just execute it. */
	public static void main(String[] args) {
		new PolarPointTrials().runTrial();    // FIXED DEFECT IN INITIAL RELEASE
	}
}
