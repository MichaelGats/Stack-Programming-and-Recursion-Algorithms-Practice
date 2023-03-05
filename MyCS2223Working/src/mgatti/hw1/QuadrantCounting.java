 package mgatti.hw1;
//for question 2.1
import java.awt.Point;

import algs.hw1.fixed.CartesianTrials;
import algs.hw1.fixed.OrderedArray;
import algs.hw1.fixed.Sorting;

/**
 * For this question, you are asked to write efficient count that computes the number of Cartesian points in each quadrant.
 * 
 *              ^
 *       II     |     I
 *              | 
 * <-------------------------->
 *              |
 *       III    |     IV 
 *              V
 *
 *
 * You can take advantage of the fact that the points are already sorted by {@link Sorting#compareCartesianByQuadrant}. 
 */
public class QuadrantCounting extends CartesianTrials {

	/**
	 * Return the count of Cartesian points in the given quadrant, assuming that the points have already been sorted 
	 * in ascending order by {@link Sorting#compareCartesianByQuadrant}. Take advantage of this ordering to write a more
	 * efficient implementation than the naive, brute-force implementation
	 */
	@Override
	protected int countCartesiansInQuadrant(OrderedArray<Point> cartesians, int q) {

		if (q == 1) {
			//quadrant 1 has the left edge being the start of the array so we dont need to calculate that
			Point rightPoint = new Point (0, Integer.MAX_VALUE);

			//search to find the left edge 
			int low = 0; 
			int high = cartesians.length() - 1;
			int leftEdge = low;

			//search to find the right edge

			low = 0;
			high = cartesians.length()-1;
			while (low <= high ) {
				int mid = (low + high) /2; 
				int rc = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), rightPoint);

				if (rc <= 0) {
					low = mid + 1; 
				} else {
					high = mid -1;
				}
			}
			int rightEdge = high; 
			return rightEdge - leftEdge + 1;
		}
		if (q == 2) {
			//quadrant 2 has the left most point in the array being a positive y with a x closest to zero. 
			//It then would have the right most point being the farthest left with a y value close to zero before 
			//it gets to the 3rd quadrant.

			Point leftPoint = new Point (0,Integer.MAX_VALUE);
			Point rightPoint = new Point (Integer.MIN_VALUE, 0);

			//Search to find the left edge
			int low = 0; 
			int high = cartesians.length() - 1;
			while (low <= high) {
				int mid = (low+ high) / 2;
				int rc = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), leftPoint);
				if (rc >= 0) {
					high = mid -1;
				} else {
					low = mid +1;
				}
			}

			int leftEdge = low;

			//search to find the right edge

			low = 0;
			high = cartesians.length()-1;
			while (low <= high ) {
				int mid = (low + high) /2; 
				int rc = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), rightPoint);

				if (rc <= 0) {
					low = mid + 1; 
				} else {
					high = mid -1;
				}
			}
			int rightEdge = high; 
			return rightEdge - leftEdge + 1;
		}
		if (q == 3) {
			//Quadrant 3 has the left point being the min value of x with y being 0 and the right point being before it reaches quadrant 4 at zero, the min value of y.
			

			Point leftPoint = new Point (Integer.MIN_VALUE,0);
			Point rightPoint = new Point (0, Integer.MIN_VALUE);

			//Search to find the left edge
			int low = 0; 
			int high = cartesians.length() - 1;
			while (low <= high) {
				int mid = (low+ high) / 2;
				int rc = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), leftPoint);
				if (rc >= 0) {
					high = mid -1;
				} else {
					low = mid +1;
				}
			}

			int leftEdge = low;

			//search to find the right edge

			low = 0;
			high = cartesians.length()-1;
			while (low <= high ) {
				int mid = (low + high) /2; 
				int rc = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), rightPoint);

				if (rc <= 0) {
					low = mid + 1; 
				} else {
					high = mid -1;
				}
			}
			int rightEdge = high; 
			return rightEdge - leftEdge + 1;
		}
		if (q == 4) {
			//Just like quadrant 1, quadrant 4's right most point is the end of the array so we dont need to calculate that. The left point is (0, the min value of y).
			

			Point leftPoint = new Point (0,Integer.MIN_VALUE);

			//Search to find the left edge
			int low = 0; 
			int high = cartesians.length() - 1;
			while (low <= high) {
				int mid = (low+ high) / 2;
				int rc = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), leftPoint);
				if (rc >= 0) {
					high = mid -1;
				} else {
					low = mid +1;
				}
			}

			int leftEdge = low;

			//search to find the right edge
			high = cartesians.length()-1;
			int rightEdge = high;
			return rightEdge - leftEdge + 1;
		}
		return 0;

	}
		

	/** Leave this function unchanged. */
	public static void main(String[] args) {
		new QuadrantCounting().runTrial();
	}
}
