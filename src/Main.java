import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("HELLO AAL");

        // create random object
        Random randomno = new Random();
        double rand = randomno.nextGaussian();
        // check next Gaussian value
        System.out.println("Next Gaussian value: " + rand);
        System.out.println("Next Gaussian HEIGHT value: " + (rand * 20 + 180));
    }
}
