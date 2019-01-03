package DataGenerator;

import java.util.Collections;
import java.util.Vector;

public class PeopleVector implements PeopleVectorInterface {

    private Vector<Person> peopleVector;
    private int numberOfPeople;

    PeopleVector() {
        peopleVector = new Vector<>();
        numberOfPeople = 0;
    }

    public void addPerson(Person person){
        peopleVector.add(person);
        numberOfPeople++;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public Vector<Person> getPeopleVector() {
        return peopleVector;
    }

    @Override
    public void sortPeopleVectorByHeight() {
        peopleVector.sort(new PeopleComparator());
    }

}
