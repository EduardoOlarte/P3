package test;

import model.Manager;
import model.Municipality;
import model.Property;
import persistence.Data;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Manager manager = new Manager();
		Data data = new Data(manager);

		System.out.println("Iniciar carga");
		String filePath = "Data/15_BOYACA_R1.csv";
		int maxLines = 2000000; 

		data.loadData(filePath, maxLines);

		System.out.println("Carga terminada");

		String codeMunicipality = "15002";
		List<Property> properties = manager.municipalityProperties(codeMunicipality);

		List<String> resultProperties = new ArrayList<>();
		if (!properties.isEmpty()) {
			resultProperties.add("Predios en el municipio " + codeMunicipality + ":");
			for (Property property : properties) {
				resultProperties.add(property.toString());
			}
		} else {
			resultProperties.add("No se encontraron predios en el municipio: " + codeMunicipality);
		}

		Municipality majorMunicipalityProperties = manager.majorMunicipalityProperties();
		List<String> resultMunicipality = new ArrayList<>();
		if (majorMunicipalityProperties != null) {
			int numProperties = majorMunicipalityProperties.getPropertiesCount();
			resultMunicipality.add("Municipio con más predios: " + majorMunicipalityProperties.getCode() + " con "
					+ numProperties + " predios.");
		} else {
			resultMunicipality.add("No se encontraron municipios.");
		}

		printResult(resultProperties);
		printResult(resultMunicipality);
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println("Tiempo de ejecución: " + duration + " ms");
	}

	private static void printResult(List<String> result) {
		for (String results : result) {
			System.out.println(results);
		}
	}

}
