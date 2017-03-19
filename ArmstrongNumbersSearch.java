

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Search of Armstrong numbers (Narcissistic numbers)
*/
public class Solution {
    public static void main(String[] args) {
        long time0 = System.currentTimeMillis();
        long[] longs = getNumbers(100000);
        for (int i = 0; i < longs.length; i++) {
            System.out.print(longs[i] + ", ");
        }
        System.out.println();
        long time1 = System.currentTimeMillis();
        System.out.println("Time of execution of the program in seconds:");
        System.out.println((time1 - time0) / 1000d);
        System.out.println("memory: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024) + " mb");
    }
  /*  public static long[] getNumbers(long N){
        ArrayList<Long> resultList = new ArrayList<>();
        long[] armstrongNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084, 548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051, 88593477,146511208, 472335975, 534494836, 912985153,4679307774L};
        for (long number: armstrongNumbers){
            if (number < N) resultList.add(number);
        }
        long [] result = new long[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }*/

    /*public static long[] getNumbers(long N) {
        //Threshold = number of digits in the number + 1
        int threshold = 10;
        LinkedList<Long> resultList = new LinkedList<>();
        // initial powers for the number 0-9:
        long[] powers = { 0l, 1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l };
        //Start of the search of Armstrong numbers:
        for (long i = 1; i < N; i++) {
            //Check if number of digits in the number changed (always changes at 10, 100, 1000, 10000, and so on):
            if (i == threshold) {
                //Set new threshold (number of digits in the number + 1)
                threshold *= 10;
                //Setting new powers (the number of digits in the number incremented by one (in line 44)
                // and therefore the power incremented also by 1)
                for (int pi = 1; pi < 10; pi++) {
                    powers[pi] *= pi;
                }
            }
            //s -- single number from the range [1,N); k -- result of the formula of the Armstrong number.
            long s = i;
            long k = 0;
            //Calculation of the Armstrong number, starting from the end (Example 123456 -- start from 6, then 5, then 4..)
            while (s > 0) {
                //receiving last digit in the number:
                int r = (int)(s % 10);
                //adding to the result the digit r already in the required power:
                k += powers[r];
                // if result is already bigger then original number there is no sens to continue working on it (exit from the while (s > 0)body):
                if (k > i)
                    break;
                //deleting the last digit from the number (if the number from the range 1-9 it becomes 0):
                s /= 10;
            }
            // if the Armstrong number -- put to the list:
            if (k == i)
                resultList.add(i);
        }
        long [] result = new long[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
        //return list.toArray(new Long[]{});
    } */

    /*   static boolean isArmstrongNumber(long x){
           if(x < 0) return false;

           String xStr = Long.toString(x);
           int m = xStr.length();
           long sum = 0;

           for(char c : xStr.toCharArray()){
               sum += Math.pow(Character.digit(c, 10), m);
           }
           return sum == x;
       }

       static long power(long x, long n) {
           long result = 1;
           while (n != 0) {
               //   if (n % 2 != 0)
               //  {
               result *= x;
               n--;
               //  }
               //  x *= x;
               //  n /= 2;
           }
           return result;
       }
       */
    public static long[] getNumbers(long N) {
        List<Long> resultList = generate(Long.valueOf(N).toString().length());
        List<Long> resultListCleaned = new ArrayList<>();
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i) < N)
                resultListCleaned.add(resultList.get(i));
        }

        long[] result = new long[resultListCleaned.size()];
        for (int i = 0; i < resultListCleaned.size(); i++) {
                result[i] = resultListCleaned.get(i);
        }
        return result;
    }

    /* public static int numberOfZeros(){

     }*/
    private static int N;
    private static int[] digitsMultiSet;
    private static int[] testMultiSet;

    private static List<Long> results;
    private static long maxPow;
    private static long minPow;

    private static long[][] pows;

    private static void genPows(int N) {
        if (N > 20) throw new IllegalArgumentException();
        pows = new long[10][N + 1];
        for (int i = 0; i < pows.length; i++) {
            long p = 1;
            for (int j = 0; j < pows[i].length; j++) {
                pows[i][j] = p;
                p *= i;
            }
        }
    }

    private static boolean check(long pow) {
        if (pow >= maxPow) return false;
        if (pow < minPow) return false;

        for (int i = 0; i < 10; i++) {
            testMultiSet[i] = 0;
        }

        while (pow > 0) {
            int i = (int) (pow % 10);
            testMultiSet[i]++;
            if (testMultiSet[i] > digitsMultiSet[i]) return false;
            pow = pow / 10;
        }

        for (int i = 0; i < 10; i++) {
            if (testMultiSet[i] != digitsMultiSet[i]) return false;
        }

        return true;
    }

    private static void search(int digit, int unused, long pow) {
        if (pow >= maxPow) return;

        if (digit == -1) {
            if (check(pow)) results.add(pow);
            return;
        }

        if (digit == 0) {
            digitsMultiSet[digit] = unused;
            search(digit - 1, 0, pow + unused * pows[digit][N]);
        } else {
            // Check if we can generate more than minimum
            if (pow + unused * pows[digit][N] < minPow) return;

            long p = pow;
            for (int i = 0; i <= unused; i++) {
                digitsMultiSet[digit] = i;
                search(digit - 1, unused - i, p);
                if (i != unused) {
                    p += pows[digit][N];
                    // Check maximum and break the loop - doesn't help
                    // if (p >= maxPow) break;
                }
            }
        }
    }

    public static List<Long> generate(int maxN) {
        if (maxN >= 20) throw new IllegalArgumentException();

        genPows(maxN);
        results = new ArrayList<>();
        digitsMultiSet = new int[10];
        testMultiSet = new int[10];

        for (N = 1; N <= maxN; N++) {
            minPow = (long) Math.pow(10, N - 1);
            maxPow = (long) Math.pow(10, N);

            search(9, N, 0);
        }

        Collections.sort(results);

        return results;
    }
}
