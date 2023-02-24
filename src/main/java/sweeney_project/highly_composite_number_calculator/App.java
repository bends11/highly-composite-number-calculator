
package sweeney_project.highly_composite_number_calculator;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args) {
        HighlyCompositeNumberCalculator highlyCompositeNumberCalculator = new HighlyCompositeNumberCalculator(44);

        while (highlyCompositeNumberCalculator.nextHighlyCompositeNumber()) {
            // Do Nothing
        }

        System.out.println("-----------------------------------------");
        highlyCompositeNumberCalculator.printHighlyCompositeNumbers();
    }
}
