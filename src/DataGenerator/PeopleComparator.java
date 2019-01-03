package DataGenerator;

import java.util.Comparator;

public class PeopleComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        if(o1.getHeight() < o2.getHeight()) return -1;
        else if(o1.getHeight() > o2.getHeight()) return 1;
        else return Integer.compare(o1.getHeadGirth(), o2.getHeadGirth());
    }
}
