/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class CustomersPurchaseSortFind {

    private ArrayList<Purchase> all;

    public void readFile(String file){
        this.all = new ArrayList<Purchase>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                this.all.add(new Purchase(line));
            }
        } catch (IOException exep) {
            exep.printStackTrace();
        }
    }

    private Comparator<Purchase> filter(String byWhat){
        if (byWhat.equals("Koszty")) {
            return (s1, s2) -> {
                int difference = (int) Math.ceil(s1.getFullPrice() - s2.getFullPrice());

                if (difference == 0){
                    return s1.purchaseId.compareTo(s2.purchaseId);
                }

                return difference;
            };
        } else if (byWhat.equals("Nazwiska")) {
            return (s1, s2) -> {
              int diference = s1.surname.compareTo(s2.surname);
              if (diference == 0) {
                  return s1.purchaseId.compareTo(s2.purchaseId);
              }
              return diference;
            };
        }
        return (s1, s2) -> 0;
    }


    public void showSortedBy(String filter) {
        System.out.println(filter);

        this.all
                .stream()
                .sorted(this.filter(filter))
                .forEach(s -> System.out.println(
                        s.turnIntoString(
                                filter.equals("Koszty")
                        )
                ));

        System.out.println("");
    }

    public void showPurchaseFor(String purchaseId){
        System.out.println("Klient " + purchaseId);

        this.all
                .stream()
                .filter((s) -> s.purchaseId.equals(purchaseId))
                .forEach(s -> System.out.println(s));

        System.out.println("");
    }
}
