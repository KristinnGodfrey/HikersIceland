package is.hi.g.hikersicelands.hikersicelands.Entities;

import jdk.nashorn.internal.objects.annotations.Getter;

public enum ItemType {
    MOUNTAIN("mountain"),
    WILDLIFE("wildlife"),
    FLORA("flora");


    private final String displayValue;

    private ItemType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}

