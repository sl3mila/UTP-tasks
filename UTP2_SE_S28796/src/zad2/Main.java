/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad2;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
            .filter( whereTo -> whereTo.startsWith("WAW") && whereTo.split(" ").length == 3 )
            .map( map -> {
              String[] data = map.split(" ");
              String whereTo = data[1];
              int price = (int) (Double.parseDouble(data[2]) * ratePLNvsEUR);
              return "to " + whereTo + " - price in PLN:  " + price;
            })
            .collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
