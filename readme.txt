AAL Projekt  AAL-6-LS vururuk
Semestr 18Z

Bartosz Cybulski
nr indeksu : 283721
mail: 283721@pw.edu.pl ; B.Cybulski@stud.elka.pw.edu.pl

1.Opis problemu
	Problemem jest wybór par małżeństw kobieta-mężczyzna z pośród populacji osobników opisanych wzrostem i obwodem głowy w sposób taki, że mężczyzna musi mieć większy wzrost i mieć większy obwód głowy niż kobieta. Warunkiem spełnienia problemu jest to, że każdy osobnik powinien mieć małżonka.

2. Sposoby aktywacji programu

1) java -jar MarrigePairingAlgorithm_AAL.jar [number of people to generate]

2) java -jar MarrigePairingAlgorithm_AAL.jar g [number of people to generate]

3) java -jar MarrigePairingAlgorithm_AAL.jar f [filename]

4) java -jar MarrigePairingAlgorithm_AAL.jar h [first number] [step] [number iterations] [iterations for instance]

Ostatnie wejście (4) przeprowadza proces testowania z pomiarem czasu dla rosnącego n(first number) o podaną wartość (step) dla x iteracji instancji problemu, powtórzone jest to dla każdej spośród liczby instancji. Jako wyjście zostaje generowany plik .csv TIMERRESULTS.csv w którym są zwracane wielkości instancji problemu oraz czas działania algorytmu

Dla wywołań 1-3 w konsoli zostaje wypisany wynik działania programu.

3. Dane podawane na wejsciu:
number of poeople to generate -> Parametr określający wygenerowanie podanej liczby osob JEDNEJ płci. Zatem wielkość generowanego problemu jest dwukrotnie wyższa.

plik podawany na wejście to plik .txt. Każda linia w pliku ma postać:
	[PLEC];[WZROST];[OBWODGLOWY]

4.W folderze "MarrigePairingAlgorithm_AAL/out/artifacts/MarrigePairingAlgorithm_AAL_jar"
Znajduje się skompilowany plik wykonywalny .jar oraz folder "resourceTests" (zawierający przykładowe grafy, na których można sprawdzić poprawność działania algorytmu. Poprawne wyniki dla testów znajdują się w pliku "Expected_Results.txt".
Plik TIMERESULTS.csv -> jest generowany na wyjściu wywołania nr 4
plik PAIRS.csv -> może być generowany opcjonalnie po wywołaniach 1-3, należy "peopleGraph.writePairsToFile(workingDirectory + "/PAIRS.csv");" w pliku Main.java

5.Krótki opis rozwiązania:

- Algorytm kojarzenia małżeństw -> Przechodzimy przez kolejne wierzchołki grafu, Jeżeli trafiamy na nieskojarzoną(nie połączoną z mężczyzną) pannę to próbujemy utworzyć ścieżkę rozszrzającą prowadzącą do pierwszego napotkanego kawalera. Ścieżka roszerzająca jest naprzemienna, zawiera kra∑dzie wolne i skojarzone. 
Jeżeli znajdziemy ścieżkę rozszerzającą, to wszystkie krawędzie wolne zamieniamy na skojarzone, a skojarzone na wolne. Do znalezienia ścieżki wykorzystuję metodę BFS i uproszczoną strukturę drzewa rozpinającego wszerz ( potrzeba jedynie ścieżkę od liści do korzenia, zatem jest realizowane w tablicy, w której elementy o indeksie i zawierają numery wierzchołków grafu będące wierzchołkami nadrzędnymi w drzewie rozpinającym w stosunku do i-tego wierzchołka. Korzeń reprezentowany przez wart -1. W ten sposób możemy dojść od liścia do korzenia.
Drzewo tworzymy: jeśli węzeł jest panną to dodajemy do drzewa wszystkie krawędzie łączące pannę z kawalerami, jeżeli nie byli skojarzene -> powstają krawędzie nieskojarzone;
Jeśli węzeł jest kawalerem to do drzewa dodajemy krawędź skojarzoną.

Generowanie wierzchołków -> przy pomocy funkcji Random.nextGaussian()

6. Ze względu na mocne powiązanie problemu z generatorem danych, generator danych został załączony jako moduł rozwiązania.

7. Dane testowe są generowane z wykorzystaniem funkcji Random.getNextGaussian(), która zwraca losowe liczby z ustalonego zakresu, które w przybliżeniu tworzą rozkład naturalny Gaussa. W taki sposób, z dobrym przybliżeniem można zobrazować populację ludzi.

Problem został rozwiązany na strukturze grafu, 


