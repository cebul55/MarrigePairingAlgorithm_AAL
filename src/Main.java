import DataGenerator.PeopleGenerator;

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

        PeopleGenerator peopleGenerator = new PeopleGenerator(2);
        for(int i = 0 ; i < 2; i++){
            System.out.println("Male " + i);
            System.out.println(peopleGenerator.getMalePeople().get(i).getSex());
            System.out.println(peopleGenerator.getMalePeople().get(i).getHeight());
            System.out.println(peopleGenerator.getMalePeople().get(i).getHeadGirth());
            System.out.println("Female" + i);
            System.out.println(peopleGenerator.getFemalePeople().get(i).getSex());
            System.out.println(peopleGenerator.getFemalePeople().get(i).getHeight());
            System.out.println(peopleGenerator.getFemalePeople().get(i).getHeadGirth());

        }
    }
}
