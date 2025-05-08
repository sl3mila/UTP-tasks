/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad1;


public interface Mapper <Type, FunType> {
    //modyfikuje
    public FunType map(Type arg);

    // Uwaga: interfejs musi być sparametrtyzowany
}  
