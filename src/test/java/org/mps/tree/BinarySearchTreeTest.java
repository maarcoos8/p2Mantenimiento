package org.mps.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<>(Comparator.<Integer>naturalOrder());
    }

    /* BinarySearchTree */

    @Nested
    @DisplayName("Constructor")
    class pruebasConstructor {

        @Test
        @DisplayName("Constructor se ejecuta correctamente con un comparador que compara enteros")
        public void constructor_comparadorEnteros_seEjecutaCorrectamente() {
            // Arrange

            // Act
            tree.insert(3);

            // Assert
            assertEquals("3", tree.render());
        }

        @Test
        @DisplayName("constructor lanza una excepcion si el comparador es nulo")
        public void constructor_comparadorNulo_lanzaExcepcion() {
            // Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                new BinarySearchTree<Integer>(null);
            });
        }
    }


    /* Insert */
    @Nested
    @DisplayName("Insert")
    class pruebasInsert {

        @Test
        @DisplayName("inserta correctamente el primer nodo en la raíz")
        void insertaEnRaiz() {
        
            // Act
            tree.insert(10);

            // Assert
            assertEquals("10", tree.render());
        }

        @Test
        @DisplayName("inserta un nodo menor a la izquierda")
        void insertaEnSubArbolIzquierdo() {

            // Act
            tree.insert(10);
            tree.insert(5);

            // Assert
            assertEquals("10(5,)", tree.render());
        }

        @Test
        @DisplayName("inserta un nodo mayor a la derecha")
        void insertaEnSubArbolDerecho() {

            // Act
            tree.insert(10);
            tree.insert(15);

            // Assert
            assertEquals("10(,15)", tree.render());
        }

        @Test
        @DisplayName("inserta nodos en ambas ramas")
        void insertaIzquierdaYDerecha() {

            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);

            // Assert
            assertEquals("10(5,15)", tree.render());
        }

        @Test
        @DisplayName("inserta múltiples niveles")
        void insertaMultiplesNiveles() {

            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(3);
            tree.insert(15);
            tree.insert(12);

            // Assert
            assertEquals("10(5(3,),15(12,))", tree.render());
        }
    


        @Test
        @DisplayName("lanza excepción al intentar insertar un valor duplicado")
        void noInsertaValorDuplicado() {

        
            // Act
            tree.insert(10);
            tree.insert(5);
        
            // Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.insert(10); // duplicado
            });
        }
    

        @Test
        @DisplayName("lanzar excepción al intentar insertar un valor nulo")
        void insertarValorNuloLanzaExcepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.insert(null);
            });
        }
        
    }


    /* Render */     
    @Nested
    @DisplayName("Render")
    class PruebasRender {

        @Test
        @DisplayName("render en árbol vacío devuelve cadena vacía")
        void renderArbolVacio() {
            
            // Act
            String result = tree.render();

            // Assert
            assertEquals("", result);
        }

        @Test
        @DisplayName("render con un solo nodo muestra solo el valor")
        void renderNodoRaiz() {

            // Act
            tree.insert(10);

            // Assert
            assertEquals("10", tree.render());
        }

        @Test
        @DisplayName("render con subárbol izquierdo")
        void renderConSubArbolIzquierdo() {
        
            // Act
            tree.insert(10);
            tree.insert(5);

            // Assert
            assertEquals("10(5,)", tree.render());
        }

        @Test
        @DisplayName("render con subárbol derecho")
        void renderConSubArbolDerecho() {

            // Act
            tree.insert(10);
            tree.insert(15);

            // Assert
            assertEquals("10(,15)", tree.render());
        }

        @Test
        @DisplayName("render con múltiples niveles")
        void renderComplejo() {

            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(3);
            tree.insert(15);
            tree.insert(12);

            // Assert
            assertEquals("10(5(3,),15(12,))", tree.render());
        }
    }

    /* Isleaf */
    @Nested
    @DisplayName("isLeaf")
    class PruebasIsLeaf {

        @Test
        @DisplayName("lanza excepción si el árbol está vacío")
        void isLeafArbolVacioLanzaExcepcion() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);

            // Act + Assert
            assertThrows(BinarySearchTreeException.class, tree::isLeaf);
        }

        @Test
        @DisplayName("retorna true si el nodo no tiene hijos")
        void isLeafNodoSinHijos() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);
            tree.insert(10);

            // Act
            boolean result = tree.isLeaf();

            // Assert
            assertTrue(result);
        }


        @Test
        @DisplayName("retorna false si el nodo tiene al menos un hijo en el arbol izquierdo")
        void isLeafNodoConHijoIzquierdo() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);
            tree.insert(10);
            tree.insert(5);

            // Act
            boolean result = tree.isLeaf();

            // Assert
            assertFalse(result);
        }

        @Test
        @DisplayName("retorna false si el nodo tiene al menos un hijo en el arbol derecho")
        void isLeafNodoConHijoDerecho() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);
            tree.insert(10);
            tree.insert(15);

            // Act
            boolean result = tree.isLeaf();

            // Assert
            assertFalse(result);
        }

        @Test
        @DisplayName("retorna false si el nodo tiene dos hijos")
        void isLeafNodoConDosHijo() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);

            // Act
            boolean result = tree.isLeaf();

            // Assert
            assertFalse(result);
        }
    }



    /* Contains */

    @Nested
    @DisplayName("contains")
    class PruebasContains {

        @Test
        @DisplayName("lanza excepción si el valor a buscar es nulo")
        void containsValorNuloLanzaExcepcion() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);

            // Act + Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.contains(null);
            });
        }

        @Test
        @DisplayName("retorna false si el árbol está vacío")
        void containsEnArbolVacio() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);

            // Act
            boolean result = tree.contains(5);

            // Assert
            assertFalse(result);
        }

        @Test
        @DisplayName("retorna true si el valor está en el árbol")
        void contieneValor() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);

            // Act
            boolean result = tree.contains(5);

            // Assert
            assertTrue(result);
        }

        @Test
        @DisplayName("retorna false si el valor no está en el árbol")
        void noContieneValor() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);

            // Act
            boolean result = tree.contains(99);

            // Assert
            assertFalse(result);
        }
    }

    

    /* Minimimum */
    @Nested
    @DisplayName("Minimum")
    class PruebasMinimum {

        @Test
        @DisplayName("lanza excepción si el árbol está vacío")
        void minimumArbolVacioLanzaExcepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.minimum();
            });
        }

        @Test
        @DisplayName("devuelve el valor mínimo en un árbol con un solo nodo")
        void minimumUnicoNodo() {
            // Act
            tree.insert(10);

            // Assert
            assertEquals(10, tree.minimum());
        }

        @Test
        @DisplayName("devuelve el valor mínimo en un árbol con varios nodos")
        void minimumMultiplesNodos() {
            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);

            // Assert
            assertEquals(3, tree.minimum());
        }
    }

    /* Maximum */
    @Nested
    @DisplayName("Maximum")
    class PruebasMaximum {

        @Test
        @DisplayName("lanza excepción si el árbol está vacío")
        void maximumArbolVacioLanzaExcepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.maximum();
            });
        }

        @Test
        @DisplayName("devuelve el valor máximo en un árbol con un solo nodo")
        void maximumUnicoNodo() {
            // Act
            tree.insert(10);

            // Assert
            assertEquals(10, tree.maximum());
        }

        @Test
        @DisplayName("devuelve el valor máximo en un árbol con varios nodos")
        void maximumMultiplesNodos() {
            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);

            // Assert
            assertEquals(15, tree.maximum());
        }
    }

    /* RemoveBranch */
    @Nested
    @DisplayName("RemoveBranch")
    class PruebasRemoveBranch {

        @Test
        @DisplayName("lanza excepción si el árbol está vacío")
        void removeBranchArbolVacioLanzaExcepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.removeBranch(10);
            });
        }

        @Test
        @DisplayName("lanza excepción si el valor a eliminar es nulo")
        void removeBranchValorNuloLanzaExcepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.removeBranch(null);
            });
        }

        @Test
        @DisplayName("lanza excepción si el valor no está en el árbol")
        void removeBranchValorNoEnArbolLanzaExcepcion() {
            // Act
            tree.insert(10);

            // Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.removeBranch(5);
            });
        }

        @Test
        @DisplayName("elimina el nodo raíz en un árbol con un solo nodo")
        void removeBranchUnicoNodo() {
            // Act
            tree.insert(10);
            tree.removeBranch(10);

            // Assert
            assertEquals("", tree.render());
        }

        @Test
        @DisplayName("elimina un nodo hoja")
        void removeBranchNodoHoja() {
            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(12);
            tree.removeBranch(5);

            // Assert
            assertEquals("10(,12)", tree.render());
        }

        @Test
        @DisplayName("elimina un nodo con un hijo")
        void removeBranchNodoConHijo() {
            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.removeBranch(5);

            // Assert
            assertEquals("10(,15)", tree.render());
        }

        @Test
        @DisplayName("elimina un nodo con dos hijos")
        void removeBranchNodoConDosHijos() {
            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);
            tree.removeBranch(5);

            // Assert
            assertEquals("10(,15(12,))", tree.render());
        }

        @Test
        @DisplayName("Elimina una rama del árbol derecho")
        void removeBranchRamaDerecha() {
            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);
            tree.removeBranch(15);

            // Assert
            assertEquals("10(5(3,),)", tree.render());
        } 
    }

    /* Size */
    @Nested
    @DisplayName("Size")
    class PruebasSize {

        @Test
        @DisplayName("retorna 0 si el árbol está vacío")
        void sizeArbolVacio() {
            // Act
            int result = tree.size();

            // Assert
            assertEquals(0, result);
        }

        @Test
        @DisplayName("retorna 1 si el árbol tiene un solo nodo")
        void sizeUnicoNodo() {
            // Act
            tree.insert(10);

            // Assert
            assertEquals(1, tree.size());
        }

        @Test
        @DisplayName("retorna el número de nodos en un árbol con varios nodos")
        void sizeMultiplesNodos() {
            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);

            // Assert
            assertEquals(5, tree.size());
        }
    }

    /* Depth */
    @Nested
    @DisplayName("Depth")
    class PruebasDepth {

        @Test
        @DisplayName("retorna 0 si el árbol está vacío")
        void depthArbolVacio() {
            // Act
            int result = tree.depth();

            // Assert
            assertEquals(0, result);
        }

        @Test
        @DisplayName("retorna 1 si el árbol tiene un solo nodo")
        void depthUnicoNodo() {
            // Act
            tree.insert(10);

            // Assert
            assertEquals(1, tree.depth());
        }

        @Test
        @DisplayName("retorna la profundidad del árbol")
        void depthMultiplesNodos() {
            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);

            // Assert
            assertEquals(3, tree.depth());
        }
    }

}

