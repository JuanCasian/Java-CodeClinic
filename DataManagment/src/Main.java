import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        DataObj dataObj = new DataObj();
        double result;
        try {
            dataObj.getData();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = dataObj.calculateSlope();
        printResults(result);
    }

    public static void printResults(double res) {
        System.out.println("The slope of pressure change is: " + res);
        System.out.println("Which indicates that the weather is going to be:");
        if (res == 0) {
            System.out.println("Same as before");
        } else if (res > 0) {
            System.out.println("Good weather: SUNNY!");
        } else {
            System.out.println("Bad weather conditions: STORMY!");
        }
    }
}
