package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


public class ListCreator <Type> {

    ArrayList<Type> list;

    public ListCreator(ArrayList<Type> list) {
        this.list = list;
    }

    public static <Type> ListCreator<Type> collectFrom(List<Type> list) {
        return new ListCreator<>(new ArrayList<>(list));
    }

    public ListCreator<Type> when(Predicate<Type> when){
        list.removeIf( x -> !when.test(x));

        return this;
    }

    public <Type2> List<Type2> mapEvery(Function<Type, Type2> map){
        List<Type2> listMap = new ArrayList<>();

        for (Type x : list) {
            listMap.add(map.apply(x));
        }

        return listMap;
    }
}
