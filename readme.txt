AAL Projekt  AAL-6-LS vururuk
Semestr 18Z

Bartosz Cybulski
nr indeksu : 283721
mail: 283721@pw.edu.pl ; B.Cybulski@stud.elka.pw.edu.pl

1.Opis problemu
	Problemem jest wybór par małżeństw kobieta-mężczyzna z pośród populacji osobników opisanych wzrostem i obwodem głowy w sposób taki, że mężczyzna musi mieć większy wzrost i mieć większy obwód głowy niż kobieta. Warunkiem spełnienia problemu jest to, że każdy osobnik powinien mieć małżonka.

2. Sposoby aktywacji programu

java -jar MarrigePairingAlgorithm_AAL.jar [number of people to generate]
java -jar MarrigePairingAlgorithm_AAL.jar g [number of people to generate]
java -jar MarrigePairingAlgorithm_AAL.jar f [filename]
java -jar MarrigePairingAlgorithm_AAL.jar h [first number] [step] [number iterations] [iterations for instance]

Ostatnie wejście przeprowadza proces testowania z pomiarem czasu dla rosnącego n(first number) o podaną wartość (step) dla x iteracji instancji problemu, powtórzone jest to dla każdej spośród liczby instancji

3. Dane podawane na wejsciu:
number of poeople to generate -> Parametr określający wygenerowanie podanej liczby osob JEDNEJ płci. Zatem wielkość generowanego problemu jest dwukrotnie wyższa.

plik podawany na wejście to plik .txt. Każda linia w pliku ma postać:
	[PLEC];[WZROST];[OBWODGLOWY]
4.Krótki opis rozwiązania:

- Algorytm kojarzenia małżeństw -> Przechodzimy przez kolejne wierzchołki grafu, Jeżeli trafiamy na nieskojarzoną(nie połączoną z mężczyzną) pannę to próbujemy utworzyć ścieżkę rozszrzającą prowadzącą do pierwszego napotkanego kawalera. Ścieżka roszerzająca jest naprzemienna, zawiera kra∑dzie wolne i skojarzone. 
Jeżeli znajdziemy ścieżkę rozszerzającą, to wszystkie krawędzie wolne zamieniamy na skojarzone, a skojarzone na wolne. Do znalezienia ścieżki wykorzystuję metodę BFS i uproszczoną strukturę drzewa rozpinającego wszerz ( potrzeba jedynie ścieżkę od liści do korzenia, zatem jest realizowane w tablicy, w której elementy o indeksie i zawierają numery wierzchołków grafu będące wierzchołkami nadrzędnymi w drzewie rozpinającym w stosunku do i-tego wierzchołka. Korzeń reprezentowany przez wart -1. W ten sposób możemy dojść od liścia do korzenia.
Drzewo tworzymy: jeśli węzeł jest panną to dodajemy do drzewa wszystkie krawędzie łączące pannę z kawalerami, jeżeli nie byli skojarzene -> powstają krawędzie nieskojarzone;
Jeśli węzeł jest kawalerem to do drzewa dodajemy krawędź skojarzoną.

Generowanie wierzchołków -> przy pomocy funkcji Random.nextGaussian()

5. Ze względu na mocne powiązanie problemu z generatorem danych, generator danych został załączony jako moduł rozwiązania.

6. Dane testowe są generowane z wykorzystaniem funkcji Random.getNextGaussian(), która zwraca losowe liczby z ustalonego zakresu, które w przybliżeniu tworzą rozkład naturalny Gaussa. W taki sposób, z dobrym przybliżeniem można zobrazować populację ludzi.

Problem został rozwiązany na strukturze grafu, 


