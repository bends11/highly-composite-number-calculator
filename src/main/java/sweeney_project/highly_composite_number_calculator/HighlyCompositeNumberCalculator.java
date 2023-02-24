package sweeney_project.highly_composite_number_calculator;

import java.util.LinkedList;

public class HighlyCompositeNumberCalculator {

    private PrimeNumberCalculator primeNumberCalculator;
    private LinkedList<HighlyCompositeNumber> highlyCompositeNumbers;
    private OrderedDoublyLinkedSet<HighlyCompositeNumber> candidateHighlyCompositeNumbers;
    private HighlyCompositeNumber previous;


    public HighlyCompositeNumberCalculator(int max) {

        primeNumberCalculator = new PrimeNumberCalculator(max);

        highlyCompositeNumbers = new LinkedList<>();

        previous = new HighlyCompositeNumber(primeNumberCalculator.getPrimes(), new int[primeNumberCalculator.getPrimes().length]);

        highlyCompositeNumbers.add(previous);

        candidateHighlyCompositeNumbers = new OrderedDoublyLinkedSet<>();

    }

    private LinkedList<HighlyCompositeNumber> nextCandidateHighlyCompositeNumbers(HighlyCompositeNumber highlyCompositeNumber) {
        LinkedList<HighlyCompositeNumber> nextHighlyCompositeNumberList = new LinkedList<>();

        int[] nextPowers = highlyCompositeNumber.getPowers().clone();

        nextPowers[0] = nextPowers[0] + 1;
        nextHighlyCompositeNumberList.add(new HighlyCompositeNumber(primeNumberCalculator.getPrimes(), nextPowers));
        for (int i = 1; i < primeNumberCalculator.getPrimes().length; i++) {
            if (highlyCompositeNumber.getPowers()[i] < highlyCompositeNumber.getPowers()[i - 1]) {
                nextPowers = highlyCompositeNumber.getPowers().clone();
                nextPowers[i] = nextPowers[i] + 1;
                HighlyCompositeNumber nextHighlyCompositeNumber = new HighlyCompositeNumber(primeNumberCalculator.getPrimes(), nextPowers);
                if (nextHighlyCompositeNumber.getPowers()[0] >= 0) {
                    nextHighlyCompositeNumberList.add(nextHighlyCompositeNumber);
                } else {
                    break;
                }
            } else if (highlyCompositeNumber.getPowers()[i] == 0) {
                break;
            }
        }


        return nextHighlyCompositeNumberList;
    }

    public boolean nextHighlyCompositeNumber() {
        if (previous == null) return false;

        for (HighlyCompositeNumber candidateHighlyCompositeNumber : this.nextCandidateHighlyCompositeNumbers(previous)) {
            candidateHighlyCompositeNumbers.add(candidateHighlyCompositeNumber);
        }

        if (this.candidateHighlyCompositeNumbers.isEmpty()) return false;

        HighlyCompositeNumber highlyCompositeNumber = this.candidateHighlyCompositeNumbers.pop();

        while (!this.candidateHighlyCompositeNumbers.isEmpty() && highlyCompositeNumber.getFactorCount() <= previous.getFactorCount()) {
            highlyCompositeNumber = this.candidateHighlyCompositeNumbers.pop();
        }

        if (this.candidateHighlyCompositeNumbers.isEmpty() && highlyCompositeNumber.getFactorCount() <= previous.getFactorCount())
            return false;


        if (highlyCompositeNumber.getPowers()[highlyCompositeNumber.getPowers().length - 1] == 0) {
            previous = highlyCompositeNumber;
            highlyCompositeNumbers.add(previous);
            return true;
        }

        return false;

    }


    public LinkedList<HighlyCompositeNumber> getHighlyCompositeNumbers() {
        return highlyCompositeNumbers;
    }


    public void printHighlyCompositeNumbers() {
        for (HighlyCompositeNumber highlyCompositeNumber : this.getHighlyCompositeNumbers()) {
            System.out.println(highlyCompositeNumber);
        }

        System.out.println("Next Candidate: " + this.candidateHighlyCompositeNumbers);
    }
}
