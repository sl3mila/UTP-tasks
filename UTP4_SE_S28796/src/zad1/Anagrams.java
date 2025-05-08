/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    private List<String> str;

    public Anagrams(String str) {

        this.str = new ArrayList<String>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            String line;

            while((line = bufferedReader.readLine()) != null){
                this.str.addAll(Arrays.asList(line.split(" ")));
            }

            bufferedReader.close();
        } catch (IOException exep){
            exep.printStackTrace();
        }
    }

    //znalezienie anagramu
    public boolean isAnagram(String fr, String sc) {
        TreeSet<String> treeSetOne = new TreeSet<String>();
        TreeSet<String> treeSetTwo = new TreeSet<String>();

        treeSetOne.addAll(Arrays.asList(fr.split("")));
        treeSetTwo.addAll(Arrays.asList(sc.split("")));

        return treeSetOne.equals(treeSetTwo);
    }

    //zwraca listę list słów będacych anagramami
    public List<ArrayList<String>> getSortedByAnQty(){
        ArrayList<ArrayList<String>> anagramki = new ArrayList<ArrayList<String>>();

        for(String x : this.str){
            boolean isAna = false;

            //znalezienie anagramów z list z użyciem klasy do znajdowania angramów (crazy)
            for (ArrayList<String> y : anagramki){
                String word = y.get(0);

                if(this.isAnagram(word, x)) {
                    y.add(x);
                    isAna = true;
                    break;
                }
            }

            //dodanie znalezionych anagramów do anagramków
            if (!isAna) {
                ArrayList<String> omgStop = new ArrayList<String>();
                omgStop.add(x);
                anagramki.add(omgStop);
            }
        }

        return anagramki
                .stream()
                .sorted((x, y) -> {
                    int difference = y.size() - x.size();

                    if(difference == 0){
                        return x.get(0).compareTo(y.get(0));
                    }
                    return difference;
                })
                .collect(Collectors.toList());
    }

    public String getAnagramsFor(String word) {
        //znalezienie anagramów
        ArrayList<String> list = this
                .getSortedByAnQty()
                .stream()
                .filter((s) -> this.isAnagram(word, s.get(0)))
                .findAny()
                .orElse(null);


        //usunięcie kopii word z list
        List<String> eraseWordFromArl = list
                .stream()
                .filter(s -> !s.equals(word))
                .collect(Collectors.toList());

        //gdy list jest puste
        if (list == null) {
            return word + ": null";
        }

        return word + ": " + eraseWordFromArl;
    }
}  
