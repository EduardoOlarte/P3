package test;

import java.util.Comparator;
import structure.NariTree;

public class Test {
    public static void main(String[] args) {
        Comparator<Integer> comparator = Integer::compareTo;

        NariTree<Integer> tree = new NariTree<>(comparator, 10);

        System.out.println("Añadiendo nodos al árbol:");
        tree.add(5, 10);    
        tree.add(15, 10);   
        tree.add(3, 5);     
        tree.add(7, 5);    

        System.out.println("\nPrueba de contains:");
        System.out.println("Contiene 10: " + tree.contains(10));
        System.out.println("Contiene 7: " + tree.contains(7));
        System.out.println("Contiene 20: " + tree.contains(20));

        System.out.println("\nPrueba de remove:");
        System.out.println("Eliminando 7 de 5: " + tree.remove(5, 7));
        System.out.println("Contiene 7 después de eliminar: " + tree.contains(7));

        System.out.println("Eliminando 20: " + tree.remove(10, 20));

        System.out.println("\nPrueba de iterator:");
        for (Integer value : tree) {
            System.out.println(value);
        }
    }
}
