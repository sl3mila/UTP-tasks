package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T> {

    private T thing;

    private Maybe(T thing){
        this.thing = thing;
    }

    public static <T2> Maybe<T2> of(T2 thing){
        return new Maybe<>(thing);
    }

    public void ifPresent(Consumer cons){
        if(this.thing != null){
            cons.accept(thing);
        }
    }

    public <T2> Maybe<T2> map(Function<T, T2> function) {
        return this.thing != null ? new Maybe<>(function.apply(this.thing)) : new Maybe<>(null);
    }

    public boolean isPresent () {
        return thing != null;
    }

    public T orElse(T thing) {
        return (this.thing != null) ? this.thing : thing;
    }

    public T get() {
        if (thing == null){
            throw new NoSuchElementException("error");
        } else {
            return this.thing;
        }
    }

    public Maybe<T> filter(Predicate<T> predi) {
        if (this.thing == null){
            return this;
        } else if (predi.test(this.thing)) {
            return this;
        } else {
            return Maybe.of(null);
        }
    }

    @Override
    public String toString() {
        if(this.thing == null) {
            return "Maybe is empty";
        } else {
            return "Maybe has value " + this.thing;
        }
    }
}
