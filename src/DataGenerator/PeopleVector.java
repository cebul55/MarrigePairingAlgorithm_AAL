package DataGenerator;

import java.util.Collections;
import java.util.Vector;

public class PeopleVector implements PeopleVectorInterface {

    private Vector<Person> peopleVector;
    private int numberOfPeople = 0;
    private int sumHeights = 0;
    private int sumHeadGirths = 0;

    public PeopleVector() {
        peopleVector = new Vector<>();
    }

    public void addPerson(Person person){
        this.peopleVector.add(person);
        this.sumHeights += person.getHeight();
        this.sumHeadGirths += person.getHeadGirth();
        this.numberOfPeople++;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public Vector<Person> getPeopleVector() {
        return peopleVector;
    }

    public int getSumHeights(){
        return sumHeights;
    }

    public int getSumHeadGirths(){
        return sumHeadGirths;
    }

    @Override
    public void sortPeopleVectorByHeight() {
        peopleVector.sort(new PeopleComparator());
    }

}
