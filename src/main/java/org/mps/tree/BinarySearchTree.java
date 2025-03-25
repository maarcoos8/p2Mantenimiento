package org.mps.tree;

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
        if(comparator == null){
            throw new BinarySearchTreeException("El comparador es nulo");
        }
        this.comparator = comparator;
        this.value = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public void insert(T value) {
        if (value == null) {
            throw new BinarySearchTreeException("El valor es nulo");
        }
        if(this.contains(value)){
            throw new BinarySearchTreeException("El valor ya existe en el arbol");
        }
        if (this.value == null) {
            this.value = value;
        } else {
            if (comparator.compare(value, this.value) < 0) {
                if (this.left == null) {
                    this.left = new BinarySearchTree<>(comparator);
                }
                this.left.insert(value);
            } else {
                if (this.right == null) {
                    this.right = new BinarySearchTree<>(comparator);
                }
                this.right.insert(value);
            }
        }
    }

    @Override
    public boolean isLeaf() {
        if (value == null) {
            throw new BinarySearchTreeException("El árbol está vacío");
        }
        return (left == null && right == null);
    }

    @Override
    public boolean contains(T value) {
        if (value == null) {
            throw new BinarySearchTreeException("El valor es nulo");
        }
        if(this.value == null){
            return false;
        }
        if (Objects.equals(value, this.value)) {
            return true;
        } else if (comparator.compare(value, this.value) < 0) {
            if (left != null) {
                return left.contains(value);
            }
        } else {
            if (right != null) {
                return right.contains(value);
            }
        }
        return false;
    }

    @Override
    public T minimum() {
        if (value == null) {
            throw new BinarySearchTreeException("El árbol está vacío");
        }
        if (left == null) {
            return value;
        }
        return left.minimum();
    }

    @Override
    public T maximum() {
        if (value == null) {
            throw new BinarySearchTreeException("El árbol está vacío");
        }
        if (right == null) {
            return value;
        }
        return right.maximum();
    }

    @Override
    public void removeBranch(T value){
        if(value == null){
            throw new BinarySearchTreeException("El valor es nulo");
        }
        if(!this.contains(value)){
            throw new BinarySearchTreeException("El valor no está contenido en el arbol");
        }
        if(Objects.equals(value, this.value)){
            this.value = null;
            this.left = null;
            this.right = null;
        } else if(comparator.compare(value, this.value) < 0){
            this.left.removeBranch(value);
        } else {
            this.right.removeBranch(value);
        }
    }

    @Override
    public int size() {
        if(this.value == null){
            return 0;
        }
        int size = 1;
        if(this.right != null){
            size += this.right.size();
        }
        if(this.left != null){
            size += this.left.size();
        }
        return size;
    }

    @Override
    public int depth() {
        if(this.value == null){
            return 0;
        }
        int leftDepth = 0;
        int rightDepth = 0;
        if(this.left != null){
            leftDepth = this.left.depth();
        }
        if(this.right != null){
            rightDepth = this.right.depth();
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)
}
