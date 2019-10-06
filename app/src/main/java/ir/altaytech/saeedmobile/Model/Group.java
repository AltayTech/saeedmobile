package ir.altaytech.saeedmobile.Model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;
@Keep
public class Group {
    @SerializedName("Id")
    private int Id;

    @SerializedName("Title")
    private String Title;

    @SerializedName("GroupPicPath")
    private String GroupPicPath;

    @SerializedName("SubGroups")
    private List<Group> SubGroups;


    public Group(int id, String title, String groupPicPath, List<Group> subGroups) {
        Id = id;
        Title = title;
        GroupPicPath = groupPicPath;
        SubGroups = subGroups;
    }

    public Group(int id, String title, String groupPicPath) {
        Id = id;
        Title = title;
        GroupPicPath = groupPicPath;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getGroupPicPath() {
        return GroupPicPath;
    }

    public List<Group> getSubGroups() {
        return SubGroups;
    }
}

