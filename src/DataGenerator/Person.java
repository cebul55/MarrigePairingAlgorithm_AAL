package DataGenerator;


import java.util.Comparator;

public class Person {
    //public final Comparator<Person> BY_HEIGHT = new PeopleComparator();
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

    public void writePerson(){
        //to test
        System.out.println(this.sex + " HEIGHT: " + this.getHeight() + " HEADGIRTH: " + this.getHeadGirth());
    }

    public int countDifference(Person p){
        if(this.getSex() == Sex.MALE && p.getSex() == Sex.FEMALE){
            return this.getHeight() + this.getHeadGirth() - p.getHeight() - p.getHeadGirth();
        }
        else if(this.getSex() == Sex.FEMALE && p.getSex() == Sex.MALE){
            return p.getHeight() + p.getHeadGirth() - this.getHeight() - this.getHeadGirth();
        }
        else return 0;
    }
}

