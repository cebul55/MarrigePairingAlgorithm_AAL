package DataGenerator;


public class Person {
    private Sex sex;
    private int height;
    private int headGirth;

    public Person (int sex, int height, int headGirth){
        switch (sex){
            case 0:
                this.sex = Sex.MALE;
                break;
            default:
                this.sex = Sex.FEMALE;
                break;
        }
        this.height = height;
        this.headGirth = headGirth;
    }

    public Sex getSex() {
        return sex;
    }

    public int getHeight() {
        return height;
    }

    public int getHeadGirth() {
        return headGirth;
    }
}
