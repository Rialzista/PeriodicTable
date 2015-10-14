package com.rialzista.edu.periodictable.Model.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<PeriodicSection> getSections(PeriodicTable periodicTable, boolean isNeedCorrectOrder) {
        List<PeriodicSection> sections = new ArrayList<>();

        if (!isNeedCorrectOrder) {
            sections.addAll(Arrays.asList(periodicTable.getTable()));
            sections.add(new PeriodicSection(periodicTable.getLanthanoids()));
            sections.add(new PeriodicSection(periodicTable.getActinoids()));
        } else {
            sections = correctOrder(periodicTable);
        }

        return sections;
    }

    private static List<PeriodicSection> correctOrder(PeriodicTable periodicTable) {
        List<PeriodicSection> orderedSections = new ArrayList<>();

        orderedSections.addAll(Arrays.asList(periodicTable.getTable()));

        //find pos "lanthanoids"
        changeElements(orderedSections, periodicTable.getLanthanoids(), 2, 5);
        //find pos "actinoids"
        changeElements(orderedSections, periodicTable.getActinoids(), 2, 6);

        return orderedSections;
    }

    private static void changeElements(List<PeriodicSection> orderedSections, PeriodicElement[] elementsToAdd, int positionElem, int positionSection) {
        PeriodicSection section = orderedSections.get(positionSection);

        ArrayList<PeriodicElement> elements = new ArrayList<>();

        elements.addAll(Arrays.asList(section.getElements()));
        elements.remove(positionElem);
        elements.addAll(positionElem, Arrays.asList(elementsToAdd));

        PeriodicElement[] resArr = new PeriodicElement[elements.size()];
        resArr = elements.toArray(resArr);

        section = new PeriodicSection(section.getWiki(), resArr);

        orderedSections.remove(positionSection);
        orderedSections.add(positionSection, section);
    }

}
