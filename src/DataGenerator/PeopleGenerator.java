package DataGenerator;

import java.util.Vector;

public class PeopleGenerator {
    private Vector<Person> malePeople = new Vector<>();
    private Vector<Person> femalePeople = new Vector<>();

    public PeopleGenerator(int numberOfEachSex){

        Person tmpMale;
        Person tmpFemale;
        for(int i = 0; i < numberOfEachSex; i++){
            tmpMale = new Person(Sex.MALE);
            tmpFemale = new Person(Sex.FEMALE);

            malePeople.add(tmpMale);
            femalePeople.add(tmpFemale);
        }
    }

    public Vector<Person> getFemalePeople() {
        return femalePeople;
    }

    public Vector<Person> getMalePeople() {
        return malePeople;
    }
}
