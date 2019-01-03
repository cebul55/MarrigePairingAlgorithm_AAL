package DataGenerator;

import java.util.Vector;

public class PeopleGenerator {

    private PeopleVector malePeople = new PeopleVector();
    private PeopleVector femalePeople = new PeopleVector();

    public PeopleGenerator(int numberOfEachSex){

        Person tmpMale;
        Person tmpFemale;
        for(int i = 0; i < numberOfEachSex; i++){
            tmpMale = new Person(Sex.MALE);
            tmpFemale = new Person(Sex.FEMALE);

            malePeople.addPerson(tmpMale);
            femalePeople.addPerson(tmpFemale);
        }
    }

    public PeopleVector getFemalePeople() {
        return femalePeople;
    }

    public PeopleVector getMalePeople() {
        return malePeople;
    }

    public void sortByHeight(){
        malePeople.sortPeopleVectorByHeight();
        femalePeople.sortPeopleVectorByHeight();
    }
}
