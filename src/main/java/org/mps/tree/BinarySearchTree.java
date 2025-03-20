package src.main.java.org.mps.tree;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

public class BinarySearchTree<T> implements BinarySearchTreeStructure<T> {
    private Comparator<T> comparator;
    private T value;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    public String render(){
        String render = "";

        if (value != null) {
            render += value.toString();
        }

        if (left != null || right != null) {
            render += "(";
            if (left != null) {
                render += left.render();
            }
            render += ",";
            if (right != null) {
                render += right.render();
            }
            render += ")";
        }

        return render;
    }

    public BinarySearchTree(Comparator<T> comparator) {
        // TODO
        this.comparator = comparator;
        this.value = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public void insert(T value) {
        // TODO
        if (this.value == null) {
            this.value = value;
        } else {
            if (comparator.compare(value, this.value) < 0) {
                if (left == null) {
                    left = new BinarySearchTree<>(comparator);
                }
                left.insert(value);
            } else if (comparator.compare(value, this.value) > 0) {
                if (right == null) {
                    right = new BinarySearchTree<>(comparator);
                }
                right.insert(value);
            }
        }
    }

    @Override
    public boolean isLeaf() {
        // TODO
        if (value == null) {
            throw new BinarySearchTreeException("The binary tree is empty");
        }
        if (left == null && right == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        // TODO
        if (Objects.equals(value, this.value)) {
            return true;
        } else if (comparator.compare(value, this.value) < 0) {
            if (left != null) {
                return left.contains(value);
            }
        } else if (comparator.compare(value, this.value) > 0) {
            if (right != null) {
                return right.contains(value);
            }
        }
        return false;
    }

    @Override
    public T minimum() {
        // TODO
        if (left == null) {
            return value;
        }
        return left.minimum();
    }

    @Override
    public T maximum() {
        // TODO
        if (right == null) {
            return value;
        }
        return right.maximum();
    }

    @Override
    public void removeBranch(T value){
        // TODO

    }

    @Override
    public int size() {
        //TODO
        return 0;
    }

    @Override
    public int depth() {
        // TODO
        return 0;
    }

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)
}
