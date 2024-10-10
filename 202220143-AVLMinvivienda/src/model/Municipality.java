package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import structure.BinaryTree;

public class Municipality {
    private String code;
    private BinaryTree<Property> properties;

    public Municipality(String code) {
        this.code = code;
        this.properties = new BinaryTree<>(Comparator.comparing(Property::getDirection));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BinaryTree<Property> getProperties() {
        return properties;
    }

    public boolean addProperty(Property property) {
        if (properties.contains(property)) {
            return false;
        }
        return properties.add(property);
    }

    public List<Property> getAllProperty() {
        List<Property> propertiesList = new ArrayList<>();
        Iterator<Property> iterator = properties.iterator();
        
        while (iterator.hasNext()) {
            propertiesList.add(iterator.next());
        }
        
        return propertiesList;
    }

    public int getPropertiesCount() {
        return properties.size();
    }

    @Override
    public String toString() {
        return "Municipio{" + "codigo='" + code + '\'' + ", cantidad de predios=" + getPropertiesCount() + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
