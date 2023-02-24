package sweeney_project.highly_composite_number_calculator;

import java.util.Arrays;

public class HighlyCompositeNumber implements Comparable<HighlyCompositeNumber> {

    private long num;
    private int factorCount;
    private int[] powers;

    HighlyCompositeNumber(int[] primes, int[] powers) {
        if (primes.length != powers.length) {
            this.powers = new int[1];
            this.powers[0] = -1;
            return;
        } else {

            this.num = 1;

            long temp;

            for (int i = 0; i < primes.length; i++) {
                temp = this.getNum() * (long) Math.pow(primes[i], powers[i]);

                if (Long.divideUnsigned(temp, (long) Math.pow(primes[i], powers[i])) == this.num) {
                    this.num = temp;
                } else {
                    this.powers = new int[1];
                    this.powers[0] = -1;
                    return;
                }
            }

            this.factorCount = 1;

            for (int p : powers) {
                this.factorCount *= p + 1;
            }
        }

        this.powers = powers;
    }


    public long getNum() {
        return num;
    }

    public int getFactorCount() {
        return factorCount;
    }

    public int[] getPowers() {
        return powers;
    }

    public String getPrimeFactorizationHTMLStr(int[] primes) {
        if (this.getNum() < 1) return null;

        String primeFactorization = primes[0] + "<sup>" + this.getPowers()[0] + "</sup>";

        for (int i = 1; i < primes.length; i++) {
            if (this.getPowers()[0] > 0) {
                primeFactorization = primeFactorization + " * " + primes[i] + "<sup>" + this.getPowers()[i] + "</sup>";
            }
        }

        return primeFactorization;
    }

    @Override
    public String toString() {
        return "Highly Composite Number: " + Long.toUnsignedString(this.getNum()) + "; Number of Factors: " + this.getFactorCount() + "; Prime Factor Powers: " + Arrays.toString(this.getPowers());
    }

    @Override
    public int compareTo(HighlyCompositeNumber highlyCompositeNumber) {
        if (highlyCompositeNumber == null) return -1;

        return Long.compareUnsigned(this.getNum(),highlyCompositeNumber.getNum());
    }
}
