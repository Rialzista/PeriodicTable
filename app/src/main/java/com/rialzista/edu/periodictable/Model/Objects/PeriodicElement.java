package com.rialzista.edu.periodictable.Model.Objects;

import java.util.Locale;

public class PeriodicElement {

    private String group;
    private Integer position;
    private String name;
    private String number;
    private String small;
    private Double molar;
    private Integer[] electrons;

    public String getGroup() {
        return group;
    }

    public Integer getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getLocalizationName(Locale locale) {
        // Find locale in periodical_table_localization.json -> find name
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getSmall() {
        return small;
    }

    public Double getMolar() {
        return molar;
    }

    public Integer[] getElectrons() {
        return electrons;
    }
}