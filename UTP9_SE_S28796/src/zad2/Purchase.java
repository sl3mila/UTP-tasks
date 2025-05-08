/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad2;


import java.beans.*;

public class Purchase {

    private PropertyChangeSupport propertChange = new PropertyChangeSupport(this);
    private VetoableChangeSupport veto = new VetoableChangeSupport(this);

    private String prod;
    private String data;
    private double price;
    public double getPrice() {
        return price;
    }

    public Purchase(String prod, String data, double price) {
        this.prod = prod;
        this.data = data;
        this.price = price;
    }

    public void setPrice(double newPrice) throws PropertyVetoException {
            veto.fireVetoableChange("price", this.price, newPrice);
            propertChange.firePropertyChange("price", this.price, newPrice);
            this.price = newPrice;
    }

    public void setData(String newData) throws PropertyVetoException {
        veto.fireVetoableChange("data", this.data, newData);
        propertChange.firePropertyChange("data", this.data, newData);
        this.data = newData;
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyListner) {
        this.propertChange.addPropertyChangeListener(propertyListner);
    }

    public void addVetoableChangeListener(VetoableChangeListener vetoListener) {
        this.veto.addVetoableChangeListener(vetoListener);
    }

    @Override
    public String toString() {
        return "Purchase [prod=" + prod + ", data=" + data + ", price=" + price + "]";
    }
}  
