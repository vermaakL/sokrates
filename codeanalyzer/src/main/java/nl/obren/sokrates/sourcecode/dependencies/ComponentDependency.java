package nl.obren.sokrates.sourcecode.dependencies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ComponentDependency {
    private String fromComponent;
    private int locFrom = 0;
    private double valueFrom = 0;
    private double valueTo = 0;
    private List<DependencyEvidence> evidence = new ArrayList<>();
    private String toComponent;
    private int count = 1;
    private String text = null;
    private DependencyData data = new DependencyData();
    private String color = "";

    public ComponentDependency() {
    }

    public ComponentDependency(String fromComponent, String toComponent) {
        this.fromComponent = fromComponent;
        this.toComponent = toComponent;
    }

    public String getFromComponent() {
        return fromComponent;
    }

    public void setFromComponent(String fromComponent) {
        this.fromComponent = fromComponent;
    }

    public String getToComponent() {
        return toComponent;
    }

    public int getLocFrom() {
        return locFrom;
    }

    public double getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(double valueFrom) {
        this.valueFrom = valueFrom;
    }

    public double getValueTo() {
        return valueTo;
    }

    public void setValueTo(double valueTo) {
        this.valueTo = valueTo;
    }

    public void setLocFrom(int locFrom) {
        this.locFrom = locFrom;
    }

    public List<DependencyEvidence> getEvidence() {
        return evidence;
    }

    public void setEvidence(List<DependencyEvidence> evidence) {
        this.evidence = evidence;
    }

    public void setToComponent(String toComponent) {
        this.toComponent = toComponent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ComponentDependency) {
            ComponentDependency that = (ComponentDependency) other;
            return this.getDependencyString().equals(that.getDependencyString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getDependencyString().hashCode();
    }

    public String getDependencyString() {
        return (StringUtils.isNotBlank(color) ? color + " / " : "") + fromComponent + " -> " + toComponent;
    }

    @JsonIgnore
    public boolean hasPathFrom(String path) {
        return this.evidence.stream().anyMatch(e -> e.getPathFrom().equalsIgnoreCase(path));
    }

    @JsonIgnore
    public void increment(int delta) {
        count += delta;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @JsonIgnore
    public List<String> getData() {
        return data.getData();
    }

    @JsonIgnore
    public void setData(List<String> data) {
        this.data.setData(data);
    }

    @JsonIgnore
    public boolean containsData(String path) {
        return data.contains(path);
    }

    @JsonIgnore
    public void addData(String path) {
        data.addData(path);
    }
}
