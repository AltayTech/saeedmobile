package ir.altaytech.saeedmobile.Model.subclass;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class ProductPropertyGroup {
    @SerializedName("GroupId")
    private int GroupId;
    @SerializedName("GroupTitle")
    private String GroupTitle;

    public ProductPropertyGroup(int groupId, String groupTitle) {
        GroupId = groupId;
        GroupTitle = groupTitle;
    }

    public int getGroupId() {
        return GroupId;
    }

    public String getGroupTitle() {
        return GroupTitle;
    }
}
