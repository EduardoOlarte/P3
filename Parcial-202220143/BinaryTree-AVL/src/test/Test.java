package test;

import java.util.Iterator;

import model.BinaryTree;

import java.util.Comparator;

public class Test {
	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(Comparator.naturalOrder());

		System.out.println("add:");
		tree.add(5);
		tree.add(3);
		tree.add(7);
		tree.add(2);
		tree.add(4);
		tree.add(6);
		tree.add(8);

		System.out.println(" contiene el 3? " + tree.contains(3));
		System.out.println(" contiene el 9? " + tree.contains(9));

		System.out.println("\n está vacío? " + tree.isEmpty());

		System.out.println("\n in-order:");
		//System.out.println("\n " + tree.showIterator());

		Iterator<Integer> iterator = tree.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}

		System.out.println("\n\n remove:");
		System.out.println("remover 3: " + tree.remove(3));
		System.out.println("contiene el 3? " + tree.contains(3));

		System.out.println("\n in-order actualizado :");
		//System.out.println("\n " + tree.showIterator());

		iterator = tree.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + " ");
		}

		System.out.println("\n\n remover 9" + tree.remove(9));
		tree.printTree();
	}
}