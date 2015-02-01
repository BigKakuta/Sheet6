package edu.kit.informatik;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Daniel Meyer
 * @version 1.0
 *          <p/>
 *          This class provides method and data structure to safe the words of a book related to the sites in the book
 *          where they appear
 */
public class BookMap {

    /**
     * Safes the current pagenumber while reading the book out
     */
    private String currentPage;

    /**
     *
     */
    private TreeMap<String, String> map = new TreeMap<>();

    /**
     * if the given line present a new pagenumber the method will safe this value in "currentPage"
     * if the given line is a new set of words it will write the words as key in the  Treemap "map"
     * for the currentPage as value, if there already value to the given key it will add the new value to it
     *
     * @param line a line of book which has to be analysed
     */
    public void put(String line) {
        String s;
        if (line.matches("Seite" + "\\d+")) {
            currentPage = line.substring(5);
        } else {
            String[] splittedLine = line.split(" ");
            for (String aSplittedLine : splittedLine) {
                if (!aSplittedLine.isEmpty()) {
                    if (map.containsKey(aSplittedLine)) {
                        if (!map.get(aSplittedLine).contains(currentPage)) {
                            s = map.get(aSplittedLine) + "," + currentPage;
                            map.put(aSplittedLine, s);
                        }
                    } else {
                        map.put(aSplittedLine, currentPage);
                    }
                }
            }
        }
    }

    /**
     * This method return the value safed in "map" for an key given as parm
     *
     * @param key the given key
     * @return value safed in "map" for an key given as parm
     */
    public String getValueAt(String key) {
        try {
            if (map.containsKey(key)) {
                return key + ":" + map.get(key);
            } else {
                return key + ":" + null;
            }
        } catch (NullPointerException e) {
            return null + ":" + null;
        }
    }

    /**
     * this method will return the value-key pairs safed map in the following formart:
     * key1:value1.0,...,value1.n,key2:value2.0,..,value2.n
     *
     * @return this method will return the value-key pairs safed map in given format
     */
    public String info() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(",");
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

}
