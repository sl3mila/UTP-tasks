package zad4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T> {

    private XList() {
        super();
    }

    public XList (Object... o){
        this.addAll(XList.of(o));
    }

    public static <T> XList<T> of(Object... o){
        XList xL = new XList<>();

        boolean combine = false;
        if (o.length > 1){
            combine = true;

            for (Object x : o){
                if (!(x instanceof Collection || x.getClass().isArray())){
                    combine = false;
                    break;
                }
            }
        }

        for (Object x : o){
            if (x instanceof Collection && !combine) {
                ((Collection) x).forEach(s -> xL.addAll(XList.of(s)));
            } else if (x.getClass().isArray() && !combine) {
                Arrays.stream(((Object[]) x)).forEach(s -> xL.addAll(XList.of(s)));
            } else {
                xL.add(x);
            }
        }
        return xL;
    }

    public static XList<String> charsOf(String s){
        List<String> chars = new ArrayList<>();
        for (char x : s.toCharArray()) {
            chars.add(String.valueOf(x));
        }

        return XList.of(chars);
    }

    public static XList<String> tokensOf(String s){
        return XList.tokensOf(s, "\\s+");
    }

    public static XList<String> tokensOf(String s, String sep) {
        return XList.of(s.split(sep));
    }

    public XList<Integer> union(Object... o) {
        XList xL = new XList(this);
        xL.addAll(XList.of(o));
        return xL;
    }

    public XList diff(Object... o) {
        XList xL = new XList(this);
        xL.removeAll(XList.of(o));
        return xL;
    }

    public XList unique() {
        XList xL = new XList();

        this.forEach(s -> {
            if (!xL.contains(s)) {
                xL.add(s);
            }
        });

        return xL;
    }

    public XList<T> combine() {
        List<T> res = new ArrayList<>();
        T comb;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                comb = (T) ((this.get(i).toString()) + (this.get(j).toString()));
                res.add(comb);
            }
        }
        return new XList<T>(res);
    }

    public String join(String sep) {
        StringBuilder sb = new StringBuilder();

        for (T t : this) {
            sb.append(t.toString() + sep);
        }
        return sb.toString();
    }

    public String join() {
        StringBuilder sb = new StringBuilder();

        for (T t : this) {
            sb.append(t.toString() + " ");
        }
        return sb.toString();
    }

    public <Live> XList<Live> collect(Function<T, Live> function) {
        List<Live> res = new ArrayList<Live>();
        for (Live r : res) {
            res.add(function.apply((T) r));
        }
        return new XList<Live>(res);
    }

    public void forEachWithIndex(BiConsumer<T, Integer> con) {
        for (int i = 0; i < this.size(); i++) {
            con.accept(this.get(i), i);
        }
    }
}
