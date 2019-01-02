package DataGenerator;

import java.util.Random;
import static java.lang.Math.round;

public class RandomData {

    public RandomData(){};

    public int getRandomHeight(Sex sex){
        //males are heigher than females, ex average height in USA used to generate height - >
        //male: 176 cm
        //female: 162 cm
        Random rand = new Random();
        int gaussianHeight = 0;
        int averageMaleHeight = 176;
        int averageFemaleHeight = 162;
        int deviationHeight = 20;
        switch (sex){
            case MALE:{
                while (gaussianHeight < 155 || gaussianHeight > 206) {
                    gaussianHeight = (int) round((rand.nextGaussian() * deviationHeight + averageMaleHeight));
                }
                break;
            }
            case FEMALE:{
                while (gaussianHeight < 142 || gaussianHeight > 192) {
                    gaussianHeight = (int) round((rand.nextGaussian() * deviationHeight + averageFemaleHeight));
                }
                break;
            }
        }
        return gaussianHeight;
    }
//gaussian functions may return unnatural values like "-1" so added while() restrains
    public int getRandomHeadGirth(Sex sex){
        //average gitrth for men: 56-61 cm
        //for women: 53 -58.5 cm
        Random rand = new Random();
        int gaussianGirth = 0;
        int averageMaleHeadGirth = 59;
        int averageFemaleHeadGirth = 56;
        int deviationHeadGirth = 3;

        switch (sex){
            case MALE:{
                while (gaussianGirth < 53 || gaussianGirth > 65)
                gaussianGirth = (int) round((rand.nextGaussian() * averageMaleHeadGirth + deviationHeadGirth));
                break;
            }
            case FEMALE:{
                while(gaussianGirth < 50 || gaussianGirth > 61) {
                    gaussianGirth = (int) round((rand.nextGaussian() * averageFemaleHeadGirth + deviationHeadGirth));
                }
                break;
            }
        }
        return gaussianGirth;
    }

    public int gerRandomSex() {
        Random rand = new Random();
        return rand.nextInt(1);
    }
}
