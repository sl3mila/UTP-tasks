/**
 *
 *  @author Ślemp Emilia S28796
 *
 */

package zad1;


import com.sun.source.tree.PatternTree;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Finder {
    private String fname;

    public Finder(String fname){
        this.fname = fname;
    }

    private int scan(String what2Look4){
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;

            while ((line = br.readLine()) != null){
                Pattern pattern = Pattern.compile(what2Look4);
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    /*private boolean notCommented(String line) {
        return !line.trim().startsWith("//") && !line.trim().startsWith("/*");
    }*/

    public int getIfCount(){
        String badIf = "\\bif\\s*\\(";

        return scan(badIf);
    }

    public int getStringCount(String word){
        int count = 0;
        String pattern = "\\b\\" + word + "\\b";

        return scan(pattern);
    }

}
