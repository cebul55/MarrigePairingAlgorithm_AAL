package DataGenerator;


public class Person {
    private Sex sex;
    private int height;
    private int headGirth;

    public Person (int sex, int height, int headGirth){
        this.setSex(sex);
        this.height = height;
        this.headGirth = headGirth;
    }

    public Person(Sex sex){
        RandomData rand = new RandomData();
        this.sex = sex;
        this.height = rand.getRandomHeight(this.sex);
        this.headGirth = rand.getRandomHeadGirth(this.sex);
    }

    public Person(){
        RandomData rand = new RandomData();
        this.setSex(rand.gerRandomSex());
        this.height = rand.getRandomHeight(this.sex);
        this.headGirth = rand.getRandomHeadGirth(this.sex);
    }

    private void setSex(int sex){
        switch (sex){
            case 0:
                this.sex = Sex.MALE;
                break;
            default:
                this.sex = Sex.FEMALE;
                break;
        }
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
