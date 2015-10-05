package com.rialzista.edu.periodictable.Model.Objects;

public class PeriodicSection {

    private String wiki;
    private PeriodicElement[] elements;

    public PeriodicSection(PeriodicElement[] elements) {
        this.wiki = "";
        this.elements = elements;
    }

    public PeriodicSection(String wiki, PeriodicElement[] elements) {
        this.wiki = wiki;
        this.elements = elements;
    }

    public String getWiki() {
        return wiki;
    }

    public PeriodicElement[] getElements() {
        return elements;
    }
}
