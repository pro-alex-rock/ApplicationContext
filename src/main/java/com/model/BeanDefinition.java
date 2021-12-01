package com.model;

import java.util.List;
import java.util.Map;

public class BeanDefinition {
    private String id;
    private String className;
    private List<Property> valueProperties;
    private List<Property> refProperties;

    /*private Map<String, String> valueDependencies;
    private Map<String, String> refDependencies;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Property> getValueProperties() {
        return valueProperties;
    }

    public void setValueProperties(List<Property> valueProperties) {
        this.valueProperties = valueProperties;
    }

    public List<Property> getRefProperties() {
        return refProperties;
    }

    public void setRefProperties(List<Property> refProperties) {
        this.refProperties = refProperties;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", valueProperties=" + valueProperties +
                ", refProperties=" + refProperties +
                '}';
    }
}
