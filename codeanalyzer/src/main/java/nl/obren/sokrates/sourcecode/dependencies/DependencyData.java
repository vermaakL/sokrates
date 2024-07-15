package nl.obren.sokrates.sourcecode.dependencies;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class DependencyData {
    @JsonIgnore
    private List<String> data = new ArrayList<>();

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public void addData(String datum) {
        this.data.add(datum);
    }

    public void removeData(String datum) {
        this.data.remove(datum);
    }

    public boolean contains(String datum) {
        return this.data.contains(datum);
    }
}
