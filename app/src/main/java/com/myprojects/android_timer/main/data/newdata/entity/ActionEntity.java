package com.myprojects.android_timer.main.data.newdata.entity;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Objects;

/**
 * Table of entities that represent Actions you can record.
 * @see LogEntity
 * The relation {@link ActionEntity} - {@link LogEntity} is one to many.
 */
@Entity(tableName = "ACTION_TABLE")
public class ActionEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    private int id;

    @ColumnInfo(name = "NAME")
    private String name;

    @ColumnInfo(name = "DESCRIPTION")
    private String description;

//    public ActionEntity(String name) {
//        this.name = name;
//    }

    public ActionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected ActionEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<ActionEntity> CREATOR = new Creator<ActionEntity>() {
        @Override
        public ActionEntity createFromParcel(Parcel in) {
            return new ActionEntity(in);
        }

        @Override
        public ActionEntity[] newArray(int size) {
            return new ActionEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public String toString() {
        return "ActionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionEntity that = (ActionEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
