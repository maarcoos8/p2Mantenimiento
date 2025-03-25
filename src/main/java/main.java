import java.util.Comparator;

import org.mps.tree.BinarySearchTree;

public class main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(comparator);
        tree.insert(8);
        tree.insert(6);
        tree.insert(7);
        tree.insert(11);
        tree.insert(12);
        tree.insert(10);
        tree.insert(5);
        System.out.println(tree.render());

        System.out.println("Valor minimo: " + tree.minimum());
        System.out.println("Valor maximo: " + tree.maximum());

        System.out.println("Contiene 3: " + tree.contains(3));
        System.out.println("Contiene 11: " + tree.contains(11));

        System.out.println("Tamaño: " + tree.size()); 

        System.out.println("Depth: " + tree.depth());

        tree.removeBranch(6);
        System.out.println(tree.render());

        
    }
}
