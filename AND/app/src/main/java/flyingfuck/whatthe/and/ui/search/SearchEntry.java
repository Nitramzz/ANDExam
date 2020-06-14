package flyingfuck.whatthe.and.ui.search;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.util.Base64;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "entry_table")
public class SearchEntry implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int PKid;

    //@SerializedName("id")
    @Expose
    private int id;
   // @SerializedName("members")
   @Expose
    private boolean members;
   // @SerializedName("tradeable")
   @Expose
    private boolean tradeable;
    //@SerializedName("icon")
    @Expose
    private String icon;
    //@SerializedName("name")
    @Expose
    private String name;
    //@SerializedName("release_date")
    @Expose
    private String release_date;
   // @SerializedName("lowalch")
   @Expose
    private int lowalch;
    //@SerializedName("highalch")
    @Expose
    private int highalch;

    public SearchEntry(int id, boolean members, boolean tradeable, String icon, String name, String release_date, int lowalch, int highalch) {
        this.id = id;
        this.members = members;
        this.tradeable = tradeable;
        this.icon = icon;
        this.name = name;
        this.release_date = release_date;
        this.lowalch = lowalch;
        this.highalch = highalch;
    }

    public String getWebId()
    {
        return String.valueOf(getId());
    }

    public Bitmap getBitmapIcon()
    {
        byte[] decodedString = Base64.decode(this.icon, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public int getPKid() {
        return PKid;
    }

    public void setPKid(int PKid) {
        this.PKid = PKid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMembers() {
        return members;
    }

    public void setMembers(boolean members) {
        this.members = members;
    }

    public boolean isTradeable() {
        return tradeable;
    }

    public void setTradeable(boolean tradeable) {
        this.tradeable = tradeable;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getLowalch() {
        return lowalch;
    }

    public void setLowalch(int lowalch) {
        this.lowalch = lowalch;
    }

    public int getHighalch() {
        return highalch;
    }

    public void setHighalch(int highalch) {
        this.highalch = highalch;
    }
}