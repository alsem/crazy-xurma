package ru.alsem.springmvc.extjs;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExtData extends ExtResponse {

    @JsonProperty("data")
    private final List<Object> data = new ArrayList<Object>();

    @JsonProperty("total")
    private int total = 0;

    /**
     * Add a single Object to the data array
     *
     * @param item the Object to add to the array
     */
    public void add(Object item) {

        if (item == null) return;

        if (item instanceof Collection) {

            for (Object object : (Collection) item) {
                data.add(object);
                total++;
            }

        } else {
            data.add(item);
            total++;
        }
    }
}
