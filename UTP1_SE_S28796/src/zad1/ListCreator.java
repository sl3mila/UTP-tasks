/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListCreator <Type> {

    List<Type> list;

    public ListCreator(List<Type> list) {
        this.list = list;
    }

    public static <Type>ListCreator<Type> collectFrom (List<Type> list) {
        //tworzy liste
        ListCreator listCreator = new ListCreator(list);
        return listCreator;
    }

    public ListCreator<Type> when(Selector select){  //przyjmuje selektor

        List<Type> when = new ArrayList<Type>();

        for (int i = 0; i < list.size(); i++) {
            if (select.sel(list.get(i))){
                when.add(list.get(i));
            }
        }

        this.list = when;
        return this;
    }

    public List<Integer> mapEvery(Mapper mapper){  //przyjmuje mapper

        List<Type> map = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            map.add((Type) mapper.map(list.get(i)));
        }

        return (List<Integer>) map;
    }

    // Uwaga: klasa musi być sparametrtyzowana
}  
