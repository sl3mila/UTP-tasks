/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad1;


import java.util.*;

public class Main{
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {

    Selector<Integer> sel = new Selector<Integer>() {
      @Override
      public boolean sel(Integer arg) {
        if ( arg < 10 ){
          return true;
        } else {
          return false;
        }
      }
    };

    Mapper<Integer, Integer> map = new Mapper<Integer, Integer>() {
      @Override
      public Integer map(Integer arg) {
        return arg + 10;
      }
    };

    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {

    Selector<String> sel = new Selector<String>() {
      @Override
      public boolean sel(String arg) {
        if (arg.length() > 3) {
          return true;
        } else {
          return false;
        }
      }
    };

    Mapper<String, Integer> map = new Mapper<String, Integer>() {
      @Override
      public Integer map(String arg) {
        return arg.length() + 10;
      }
    };

    return ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
