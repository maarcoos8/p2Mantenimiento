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

    @Override
    public void removeValue(T value) {
        if (value == null) {
            throw new BinarySearchTreeException("El valor no puede ser nulo.");
        }

        if (!this.contains(value)) {
            throw new BinarySearchTreeException("El valor no existe en el árbol");
        }
        if (comparator.compare(value, this.value) < 0) {
            this.left.removeValue(value);
        } else if (comparator.compare(value, this.value) > 0) {
            this.right.removeValue(value);
        } else {
            // Caso de nodo hoja
            if (this.isLeaf()) {
                this.value = null;
            }
            // Caso de nodo con solo hijo derecho
            else if (this.left == null) {
                this.value = this.right.value;
                this.left = this.right.left;
                this.right = this.right.right;
            }
            // Caso de nodo con solo hijo izquierdo
            else if (this.right == null) {
                this.value = this.left.value;
                this.right = this.left.right;
                this.left = this.left.left;
            }
            // Caso de nodo con dos hijos
            else {
                T successor = this.right.minimum();
                this.value = successor;
                this.right.removeValue(successor);
            }
        }
    }

    @Override
    public List<T> inOrder(){
        if (value == null) {
            throw new BinarySearchTreeException("El árbol está vacío");
        }
        List<T> list = new ArrayList<>();
        if(left != null){
            list.addAll(left.inOrder());
        }
        list.add(value);
        if(right != null){
            list.addAll(right.inOrder());
        }
        return list;
    }

    @Override
    public void balance() {
        if (value == null) {
            throw new BinarySearchTreeException("El árbol está vacío");
        }
        List<T> values = inOrder();
        BinarySearchTree<T> balancedTree = buildBalancedTree(values, 0, values.size() - 1);
        // Actualizamos el árbol
        this.value = balancedTree.value;
        this.left = balancedTree.left;
        this.right = balancedTree.right;
    }

    private BinarySearchTree<T> buildBalancedTree(List<T> values, int start, int end) {
        if (start > end) {
            return null;
        }
        //Buscamos el medio
        int mid = (start + end) / 2;
        T midValue = values.get(mid);
        // Crear un arbol nuevo con el valor que va en el medio
        BinarySearchTree<T> res = new BinarySearchTree<>(comparator);
        res.value = midValue;
        // Hacemos recursivamente sus árboles izquierdo y derecho
        res.left = buildBalancedTree(values, start, mid - 1);
        res.right = buildBalancedTree(values, mid + 1, end);

        return res;
        }
    }
