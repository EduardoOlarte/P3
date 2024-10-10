package persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import model.Manager;
import model.Property;

public class Data {
    private static final int MUNICIPALITY_INDEX = 1;
    private static final int NUM_PREDIAL_INDEX = 2;
    private static final int DIRECTION_INDEX = 3;
    private static final int ECONOMIC_DESTINY_INDEX = 4;
    private static final int LAND_AREA_INDEX = 5;
    private static final int BUILT_AREA_INDEX = 6;
    private static final int PREVIOUS_NUM_PREDIAL_INDEX = 7;

    private Manager manager;

    public Data(Manager manager) {
        this.manager = manager;
    }

    public void loadData(String filePath, int maxLines) {
        int lineCount = 0;
        int propertyCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                lineCount++;

                if (isFirstLine) {
                    isFirstLine = false;
                    continue; 
                }

                if (lineCount > maxLines || line.trim().isEmpty()) {
                    break; 
                }

                String[] values = line.split(",");
                String codeMunicipality = getValue(values, MUNICIPALITY_INDEX);
                String numPredial = getValue(values, NUM_PREDIAL_INDEX);
                String direccion = getValue(values, DIRECTION_INDEX);
                String economicDestiny = getValue(values, ECONOMIC_DESTINY_INDEX);
                String landArea = getValue(values, LAND_AREA_INDEX);
                String builtArea = getValue(values, BUILT_AREA_INDEX);
                String previousNumPredial = getValue(values, PREVIOUS_NUM_PREDIAL_INDEX);

                Property property = new Property(numPredial, direccion, economicDestiny, landArea, builtArea,
                        previousNumPredial);
                if (manager.addMunicipality(codeMunicipality, property)) {
                    propertyCount++;
                }
// lineas comentariadas usadas para visualizar el proceso
//                if (lineCount % 1000 == 0) {
//                    System.out.println(String.format("Procesadas %d líneas, %d propiedades añadidas.", lineCount, propertyCount));
//                }
            }
        } catch (IOException e) {
            handleIOException(e, filePath);
        }

 //       System.out.println(String.format("Carga completada. Total de líneas procesadas: %d. Propiedades añadidas: %d.", lineCount, propertyCount));
    }

    private String getValue(String[] values, int index) {
        return (values.length > index) ? values[index].trim() : " ";
    }

    private void handleIOException(IOException e, String filePath) {
        throw new RuntimeException("Error al cargar los datos desde el archivo: " + filePath, e);
    }

    public Manager getManager() {
        return manager;
    }
}
