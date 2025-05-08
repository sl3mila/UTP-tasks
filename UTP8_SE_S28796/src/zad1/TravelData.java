package src.zad1;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    public File dataDir;
    public TravelData(File dataDir) {
        this.dataDir = dataDir;
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        List<String> res = new ArrayList<>();
        File[] files = dataDir.listFiles();

        if (files != null) {
            for (File file : files) {
                try {
                    Scanner scan = new Scanner(file);

                    while (scan.hasNextLine()) {
                        String[] offers = scan.nextLine().split("\t");
                        String desc = format(offers, locale, dateFormat);
                        res.add(desc);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    private String  format(String[] offers, String loc, String dateFormat) throws ParseException {
        Locale locale = new Locale(loc.split("_")[0], loc.split("_")[1]);
        SimpleDateFormat inputDF = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat outputDF = new SimpleDateFormat(dateFormat, locale);

        String country = offers[1];
        String depart = outputDF.format(inputDF.parse(offers[2]));
        String ret = outputDF.format(inputDF.parse(offers[3]));
        String place = offers[4];
        String price = offers[5];
        String currency = offers[6];

        return String.format(locale, "%s %s %s %s %s %s %s", country, depart, ret, place, price, currency);
    }

        //TODO zwraca listę napisów, każdy z których jest opisem jednej oferty z
        // plików katalogu data, przedstawionym zgodnie z regułami i w języku
        // lokalizacji loc i przy podanym formacie daty
        // (możliwe formaty określa klasa SimpleDateFormat)

}