package com.rialzista.edu.periodictable.Model.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class PeriodicElement implements Parcelable {

    private String image;
    private String marker;
    private String group;
    private Integer position;
    private String name;
    private Integer number;
    private String small;
    private Double molar;
    private Integer[] electrons;

    public PeriodicElement() {
        this.marker = "";
        this.name = "";
        this.number = -1;
        this.small = "";
    }

    protected PeriodicElement(Parcel in) {
        image = in.readString();
        marker = in.readString();
        group = in.readString();
        name = in.readString();
        number = in.readInt();
        small = in.readString();
    }

    public static final Creator<PeriodicElement> CREATOR = new Creator<PeriodicElement>() {
        @Override
        public PeriodicElement createFromParcel(Parcel in) {
            return new PeriodicElement(in);
        }

        @Override
        public PeriodicElement[] newArray(int size) {
            return new PeriodicElement[size];
        }
    };

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

    public Integer getNumber() {
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

    public String getMarker() {
        return marker;
    }

    public String getImagePath() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(marker);
        dest.writeString(group);
        dest.writeString(name);
        dest.writeInt(number);
        dest.writeString(small);
    }
}