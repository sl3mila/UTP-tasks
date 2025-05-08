package zad2;

import java.util.function.Function;

public class InputConverter <T>{

    private T input;

    public InputConverter(T input){
        this.input = input;
    }

    public <Love> Love convertBy(Function... functions) {
        Object theInput = input;
        Object res = null;

        for (Function x : functions){
            res = x.apply(theInput);
            theInput = res;
        }

        return (Love) res;
    }
}
