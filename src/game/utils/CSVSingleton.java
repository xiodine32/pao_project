package game.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created on 09/05/16.
 * Class CSVSingleton
 */
public final class CSVSingleton {
    private static CSVSingleton instance = new CSVSingleton();

    private HashMap<String, HashMap<String, String>> csvHashMap = new HashMap<>();

    private CSVSingleton() {
        load("buttons.csv");
        load("tanks.csv");
        load("tiles.csv");
    }

    public static CSVSingleton getInstance() {
        return instance;
    }

    private void load(String name) {
        HashMap<String, String> result = new HashMap<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("res/" + name);
            Scanner scanner = new Scanner(fileInputStream);

            // skip help line
            scanner.nextLine();

            // continue scanning
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String key = line.substring(0, line.indexOf(','));
                String value = line.substring(line.indexOf(',') + 1 /*ignore ,*/);

                result.put(key, value);
            }
        } catch (FileNotFoundException e) {
            Debug.l(e.toString());
        }


        final String key = name.substring(0, name.lastIndexOf('.'));
        csvHashMap.put(key, result);
    }

    public String get(String type, String internalName) {
        return csvHashMap.get(type).get(internalName);
    }
}
