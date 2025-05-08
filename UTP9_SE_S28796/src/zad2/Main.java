/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad2;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class Main {
  public static void main(String[] args) {

    Purchase purch = new Purchase("komputer", "nie ma promocji", 3000.00);
    System.out.println(purch);

    // --- tu należy dodać odpowiedni kod

    purch.addPropertyChangeListener((e) -> {
      System.out.printf("Change value of: %s from %s to: %s %n", e.getPropertyName(),
              e.getOldValue(), e.getNewValue());
    });

    purch.addVetoableChangeListener((evt) -> {
        if ("price".equals(evt.getPropertyName()) && (Double)evt.getNewValue() < 1000) {
          throw new PropertyVetoException("Price change to: " + evt.getNewValue() + " not allowed", evt);
        }});

    // ----------------------------------

    try {
      purch.setData("w promocji");
      purch.setPrice(2000.00);
      System.out.println(purch);

      purch.setPrice(500.00);

    } catch (PropertyVetoException exc) {
      System.out.println(exc.getMessage());
    }
    System.out.println(purch);

  }
}
