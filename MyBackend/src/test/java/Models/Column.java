package Models;


import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.testng.internal.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Column {

    @SerializedName("Name")
    private String Name;

    @SerializedName("Caption")
    private String Caption;

    @SerializedName("Visible")
    private boolean Visible;

    @SerializedName("Type")
    private String Type;

    @Nullable
    @SerializedName("SubColumns")
    private List<Column> SubColumns;

    public Column(String name, String caption, boolean visible, String type, List<Column> subColumns) {
        this.Name = name;
        this.Caption = caption;
        this.Visible = visible;
        this.Type = type;
        this.SubColumns = subColumns;
    }
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public boolean isVisible() {
        return Visible;
    }

    public void setVisible(boolean visible) {
        Visible = visible;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public List<Column> getSubColumns() {
        return SubColumns;
    }

    public void setSubColumns(ArrayList<Column> subColumns) {
        SubColumns = subColumns;
    }
}
