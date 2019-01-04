package PairingAlgorithm;

import DataGenerator.PeopleGenerator;
import DataGenerator.PeopleVector;
import DataGenerator.Person;

import java.util.Vector;

public class PeopleGraph {

    private Vector<Vector<Person>> peopleGraph = new Vector<Vector<Person>>();
    private static int differenceOfSums;

    public PeopleGraph(PeopleGenerator peopleGenerator){
        this(peopleGenerator.getMalePeople(), peopleGenerator.getFemalePeople());
    }

    public PeopleGraph(PeopleVector malePeople, PeopleVector femalePeople){

        differenceOfSums = (malePeople.getSumHeights() + malePeople.getSumHeadGirths()) - (femalePeople.getSumHeights() + femalePeople.getSumHeadGirths());

        malePeople.sortPeopleVectorByHeight();
        femalePeople.sortPeopleVectorByHeight();

        assert(malePeople.getNumberOfPeople() == femalePeople.getNumberOfPeople());

        Vector<Person> tmpMale = malePeople.getPeopleVector();
        Vector<Person> tmpFemale = femalePeople.getPeopleVector();

        for(int i = 0; i < malePeople.getNumberOfPeople(); i++){
            int tmpMaleHeight = tmpMale.get(i).getHeight();
            int tmpMaleHeadGirth = tmpMale.get(i).getHeadGirth();

            int j = 0;
            int tmpFemaleHeight = tmpFemale.get(j).getHeight();
            int tmpFemaleHeadGirth = tmpFemale.get(j).getHeadGirth();

            Vector<Person> tmpVector = new Vector<>();
            tmpVector.add(tmpMale.get(i));


            while (tmpMaleHeight > tmpFemaleHeight && j < femalePeople.getNumberOfPeople() ){
                tmpFemaleHeight = tmpFemale.get(j).getHeight();
                tmpFemaleHeadGirth = tmpFemale.get(j).getHeadGirth();

                if( tmpMaleHeight > tmpFemaleHeight && tmpMaleHeadGirth > tmpFemaleHeadGirth){
                    tmpVector.add(tmpFemale.get(j));
                    j++;
                }
                else if (tmpMaleHeight > tmpFemaleHeight && tmpMaleHeadGirth <= tmpFemaleHeadGirth ){
                    //Wzrost jest wiekszy wiec jest sens sprawdzac kolejna kobiete, bez tworzenia krawedzi
                    //todo chech if that else statement is necessary
                    j++;
                }
            }
            peopleGraph.add(tmpVector);
        }
    }

    public boolean checkIfMenHasVector(){
        for (Vector<Person> aPeopleGraph : peopleGraph) {
            if (aPeopleGraph.size() == 1) {
                //that means man cannot be paired with any women -> end of algorithm
                return false;
            }
        }
        return true;
    }

    public int getDifferenceOfSums(){
        return differenceOfSums;
    }

    public void writePeopleGraph(){
        //to test
        int i = 0;
        int j = 0;
        for(Vector<Person> aPeopleGraph : peopleGraph ) {
            System.out.println("---" + i + "---");
            j = 0;
            for(Person aVectorPerson : aPeopleGraph){
                System.out.print(j + " ");
                aVectorPerson.writePerson();
                j++;
            }
            i++;
        }
    }
}
