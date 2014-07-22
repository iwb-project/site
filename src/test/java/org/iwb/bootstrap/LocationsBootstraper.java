package org.iwb.bootstrap;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrMatcher;
import org.apache.commons.lang.text.StrTokenizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.io.IOUtils.readLines;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public class LocationsBootstraper {


    private static Map<String, DBObject> loadCities() throws IOException {
        Map<String, DBObject> cities = new HashMap<>();
        List<String> lines = readLines(LocationsBootstraper.class.getResourceAsStream("/cities.csv"), "UTF-8");
        for (String line : lines) {
            StrTokenizer tokenizer = StrTokenizer.getCSVInstance(line);
            String[] values = tokenizer.getTokenArray();
            String id = values[9];
            String name = values[4];
            if (name.length() < values[3].length()) {
                name = StringUtils.capitalize((values[3].substring(0, values[3].length() - name.length())).toLowerCase()) + name;
            }
            String postal = values[7];
            cities.put(id, new BasicDBObject("_id", id).append("name", name).append("postal", postal.split("-")));
        }
        return cities;
    }

    public static void main(String... arguments) throws IOException {

        Map<String, DBObject> cities = loadCities();
        Map<String, DBObject> atTheEnd = new HashMap<>();

        List<String> lines = readLines(LocationsBootstraper.class.getResourceAsStream("/metropolises.csv"));

        for (String line : lines) {
            StrTokenizer tokenizer = StrTokenizer.getCSVInstance(line);
            String[] values = tokenizer.getTokenArray();

            DBObject instance;

            if (!atTheEnd.containsKey(values[2])) {
                instance = new BasicDBObject("_id", values[2])
                        .append("name", values[3])
                        .append("cities", new ArrayList<DBObject>());
                atTheEnd.put(values[2], instance);
            } else {
                instance = atTheEnd.get(values[2]);
            }

            List<DBObject> holder = (List<DBObject>) instance.get("cities");
            holder.add(cities.get(values[0]));

        }
        List<String> rows = new ArrayList<>();
        for (Map.Entry<String, DBObject> entry : atTheEnd.entrySet()) {
            String name = ((String)(entry.getValue().get("name")))
                    .replaceFirst("^CC ", "Communauté de commune ")
                    .replaceFirst("^CA ", "Communauté d'agglomération ")
                    .replaceFirst("^CU ", "Communauté urbaine ")
                    .replaceFirst("^SAN ", "Syndicat d'agglomération nouvelle ");
            entry.getValue().put("name", name);
            rows.add(entry.getValue().toString());
        }
        FileUtils.writeLines(FileUtils.getFile("dump.json"), "UTF-8", rows);

    }

}
