package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import structure.AVLTree;
import structure.NodeTreeAvl;

public class Manager {
    private AVLTree<Municipality> municipalityTree;

    public Manager() {
        municipalityTree = new AVLTree<>(Comparator.comparing(Municipality::getCode));
    }

    public boolean addMunicipality(String codeMunicipality, Property property) {
        NodeTreeAvl<Municipality> nodeMunicipality = municipalityTree.search(new Municipality(codeMunicipality));
        
        if (nodeMunicipality != null) {
            return nodeMunicipality.getData().addProperty(property);
        } else {
            Municipality newMunicipality = new Municipality(codeMunicipality);
            if (municipalityTree.add(newMunicipality)) {
                return newMunicipality.addProperty(property);
            }
        }
        return false;
    }

    public List<Property> municipalityProperties(String codeMunicipality) {
        NodeTreeAvl<Municipality> nodeMunicipality = municipalityTree.search(new Municipality(codeMunicipality));
        if (nodeMunicipality != null) {
            return nodeMunicipality.getData().getAllProperty();
        }
        return new ArrayList<>(); 
    }

    public Municipality majorMunicipalityProperties() {
        Municipality majorMunicipalityProperties = null;
        int maxProperties = 0;

        Iterator<Municipality> iterator = municipalityTree.preorderIterator();
        while (iterator.hasNext()) {
            Municipality municipality = iterator.next();
            int ownProperties = municipality.getPropertiesCount();
            if (ownProperties > maxProperties) {
                maxProperties = ownProperties;
                majorMunicipalityProperties = municipality;
            }
        }

        return majorMunicipalityProperties;
    }

    public AVLTree<Municipality> getMunicipalityTree() {
        return municipalityTree;
    }
}
