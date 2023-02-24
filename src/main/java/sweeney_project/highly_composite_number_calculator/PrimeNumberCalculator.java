package sweeney_project.highly_composite_number_calculator;

import java.util.ArrayList;

public class PrimeNumberCalculator {

    private int[] primes;

    PrimeNumberCalculator(int max) {
        boolean[] isCompositeList = new boolean[max];
        ArrayList<Integer> primesArray = new ArrayList<>((int) Math.log(max));

        int p = 2;
        isCompositeList[2] = false;
        primesArray.add(2);
        while (p < max) {
            for (int i = 2 * p; i < max; i += p) {
                isCompositeList[i] = true;
            }

            if (p < max - 1) {
                for (int i = p + 1; i < max; i++) {
                    if (!isCompositeList[i]) {
                        primesArray.add(i);
                        p = i;
                        break;
                    }

                    if (i == max - 1) {
                        p = max;
                        break;
                    }
                }
            } else {
                p = max;
            }
        }

        primes = new int[primesArray.size()];

        for (int i = 0; i < primesArray.size(); i++) {
            primes[i] = primesArray.get(i);
        }
    }

    public int[] getPrimes() {
        return primes;
    }
}

