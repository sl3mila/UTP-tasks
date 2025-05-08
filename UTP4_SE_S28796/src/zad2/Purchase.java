/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad2;


public class Purchase {

    public String purchaseId;
    public String surname;
    public String name;
    public String prod;
    public float price;
    public float amount;

    public Purchase(String purchase) {
        String[] frag = purchase.split(";");

        this.purchaseId = frag[0];

        String[] clientName = frag[1].split(" ");
        this.surname = clientName[0];
        this.name = clientName[1];

        this.prod = frag[2];

        this.price = Float.parseFloat(frag[3]);

        this.amount = Float.parseFloat(frag[4]);
    }

    public float getFullPrice() {
        return this.price * this.amount;
    }

    public String turnIntoString(boolean withFullPrice) {
        String done = String.join(
                ";",
                this.purchaseId,
                this.surname + " " + this.name,
                this.prod,
                Float.toString(this.price),
                Float.toString(this.amount)
        );

        if(!withFullPrice) {
            return done;
        } else {
            String asString = Float.toString(this.getFullPrice());
            return done + " (koszt: " + asString + ")";
        }
    }

    @Override
    public String toString(){
        return this.turnIntoString(false);
    }
}
