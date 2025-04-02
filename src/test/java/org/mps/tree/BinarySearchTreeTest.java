/* Soraya Bennai Sadqi y Marcos Luque Montiel */   
package org.mps.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;

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

    /* Pruebas del constructor */   
    @Nested
    @DisplayName("Constructor")
    class pruebasConstructor {

        @Test
        @DisplayName("Constructor se ejecuta correctamente con un comparador que compara enteros")
        public void constructor_comparadorEnteros_correcto() {
            // Arrange

            // Act
            tree.insert(3);

            // Assert
            assertEquals("3", tree.render());
        }

        @Test
        @DisplayName("constructor lanza una excepcion si el comparador es nulo")
        public void constructor_comparadorNulo_excepcion() {
            // Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                new BinarySearchTree<Integer>(null);
            });
        }
    }


    /* Pruebas del método Insert */
    @Nested
    @DisplayName("Insert")
    class pruebasInsert {

        @Test
        @DisplayName("inserta correctamente el primer nodo en la raíz")
        void inserta_en_raiz() {
        
            // Act
            tree.insert(10);

            // Assert
            assertEquals("10", tree.render());
        }

        @Test
        @DisplayName("inserta un nodo menor a la izquierda")
        void inserta_en_subArbol_izq() {

            // Act
            tree.insert(10);
            tree.insert(5);

            // Assert
            assertEquals("10(5,)", tree.render());
        }

        @Test
        @DisplayName("inserta un nodo mayor a la derecha")
        void inserta_en_subArbol_dcho() {

            // Act
            tree.insert(10);
            tree.insert(15);

            // Assert
            assertEquals("10(,15)", tree.render());
        }

        @Test
        @DisplayName("inserta nodos en ambas ramas")
        void inserta_en_ambos() {

            // Act
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);

            // Assert
            assertEquals("10(5,15)", tree.render());
        }

        @Test
        @DisplayName("inserta múltiples niveles")
        void inserta_multiples_niveles() {

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
        void no_inserta_valor_duplicado() {

        
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
        void insertar_valor_nulo_excepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.insert(null);
            });
        }
        
    }


    /* Pruebas del método Render */     
    @Nested
    @DisplayName("Render")
    class PruebasRender {

        @Test
        @DisplayName("render en árbol vacío devuelve cadena vacía")
        void render_arbol_vacio() {
            
            // Act
            String result = tree.render();

            // Assert
            assertEquals("", result);
        }

        @Test
        @DisplayName("render con un solo nodo muestra solo el valor")
        void render_nodo_raiz() {

            // Act
            tree.insert(10);

            // Assert
            assertEquals("10", tree.render());
        }

        @Test
        @DisplayName("render con subárbol izquierdo")
        void render_subArbol_izq() {
        
            // Act
            tree.insert(10);
            tree.insert(5);

            // Assert
            assertEquals("10(5,)", tree.render());
        }

        @Test
        @DisplayName("render con subárbol derecho")
        void render_subArbol_dcho() {

            // Act
            tree.insert(10);
            tree.insert(15);

            // Assert
            assertEquals("10(,15)", tree.render());
        }

        @Test
        @DisplayName("render con múltiples niveles")
        void render_complejo() {

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


    /* Pruebas del método Isleaf */
    @Nested
    @DisplayName("isLeaf")
    class PruebasIsLeaf {

        @Test
        @DisplayName("lanza excepción si el árbol está vacío")
        void is_leaf_arbol_vacio_excepcion() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);

            // Act + Assert
            assertThrows(BinarySearchTreeException.class, tree::isLeaf);
        }

        @Test
        @DisplayName("retorna true si el nodo no tiene hijos")
        void is_leaf_nodo_sin_hijos() {
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
        void is_leaf_nodo_hijo_izq() {
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
        void is_leaf_nodo_hijo_dch() {
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
        void is_leaf_nodo_ambos() {
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



    /* Pruebas del método Contains */

    @Nested
    @DisplayName("contains")
    class PruebasContains {

        @Test
        @DisplayName("lanza excepción si el valor a buscar es nulo")
        void contains_valor_nulo_excepcion() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);

            // Act + Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.contains(null);
            });
        }

        @Test
        @DisplayName("retorna false si el árbol está vacío")
        void contains_en_arbol_vacio() {
            // Arrange
            BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);

            // Act
            boolean result = tree.contains(5);

            // Assert
            assertFalse(result);
        }

        @Test
        @DisplayName("retorna true si el valor está en el árbol")
        void contiene_valor() {
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
        void no_contiene_valor() {
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

    

    /* Pruebas del método Minimimum */
    @Nested
    @DisplayName("Minimum")
    class PruebasMinimum {

        @Test
        @DisplayName("lanza excepción si el árbol está vacío")
        void min_arbol_vacio_excepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.minimum();
            });
        }

        @Test
        @DisplayName("devuelve el valor mínimo en un árbol con un solo nodo")
        void min_unico_nodo() {
            // Act
            tree.insert(10);

            // Assert
            assertEquals(10, tree.minimum());
        }

        @Test
        @DisplayName("devuelve el valor mínimo en un árbol con varios nodos")
        void min_multiples_nodos() {
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

    /* Pruebas del método Maximum */
    @Nested
    @DisplayName("Maximum")
    class PruebasMaximum {

        @Test
        @DisplayName("lanza excepción si el árbol está vacío")
        void max_arbol_vacio_excepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.maximum();
            });
        }

        @Test
        @DisplayName("devuelve el valor máximo en un árbol con un solo nodo")
        void max_unico_nodo() {
            // Act
            tree.insert(10);

            // Assert
            assertEquals(10, tree.maximum());
        }

        @Test
        @DisplayName("devuelve el valor máximo en un árbol con varios nodos")
        void max_multiples_nodos() {
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

    /* Pruebas del método RemoveBranch */
    @Nested
    @DisplayName("RemoveBranch")
    class PruebasRemoveBranch {

        @Test
        @DisplayName("lanza excepción si el árbol está vacío")
        void removeBranch_arbol_vacio_excepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.removeBranch(10);
            });
        }

        @Test
        @DisplayName("lanza excepción si el valor a eliminar es nulo")
        void removeBranch_valor_nulo_excepcion() {
            // Act y Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.removeBranch(null);
            });
        }

        @Test
        @DisplayName("lanza excepción si el valor no está en el árbol")
        void removeBranch_valor_no_en_arbol_excepcion() {
            // Act
            tree.insert(10);

            // Assert
            assertThrows(BinarySearchTreeException.class, () -> {
                tree.removeBranch(5);
            });
        }

        @Test
        @DisplayName("elimina el nodo raíz en un árbol con un solo nodo")
        void removeBranch_unico_nodo() {
            // Act
            tree.insert(10);
            tree.removeBranch(10);

            // Assert
            assertEquals("", tree.render());
        }

        @Test
        @DisplayName("elimina un nodo hoja")
        void removeBranch_nodo_hoja() {
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
        void removeBranch_nodo_con_hijo() {
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
        void removeBranch_nodo_con_dos_hijos() {
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
        void removeBranch_rama_dcho() {
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

    /* Pruebas del método Size */
    @Nested
    @DisplayName("Size")
    class PruebasSize {

        @Test
        @DisplayName("retorna 0 si el árbol está vacío")
        void size_arbol_vacio() {
            // Act
            int result = tree.size();

            // Assert
            assertEquals(0, result);
        }

        @Test
        @DisplayName("retorna 1 si el árbol tiene un solo nodo")
        void size_unico_nodo() {
            // Act
            tree.insert(10);

            // Assert
            assertEquals(1, tree.size());
        }

        @Test
        @DisplayName("retorna el número de nodos en un árbol con varios nodos")
        void size_multiples_nodos() {
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

    /* Pruebas del método Depth */
    @Nested
    @DisplayName("Depth")
    class PruebasDepth {

        @Test
        @DisplayName("retorna 0 si el árbol está vacío")
        void depth_arbol_vacio() {
            // Act
            int result = tree.depth();

            // Assert
            assertEquals(0, result);
        }

        @Test
        @DisplayName("retorna 1 si el árbol tiene un solo nodo")
        void depth_unico_nodo() {
            // Act
            tree.insert(10);

            // Assert
            assertEquals(1, tree.depth());
        }

        @Test
        @DisplayName("retorna la profundidad del árbol")
        void depth_multiples_nodos() {
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

    /* Pruebas del método removeValue */
    @Nested
    @DisplayName("removeValue")
    class PruebasRemoveVaue{
    
        @Test
        @DisplayName("Lanza excepción si el valor es null")
        public void removeValue_excepcion_si_es_null () {
            assertThrows(BinarySearchTreeException.class,() -> tree.removeValue(null));
        }

        @Test
        @DisplayName("Lanza excepción si el valor no está contenido en el árbol")
        public void removeValue_excepcion_valor_no_estar_en_arbol () {
            tree.insert(10);
            tree.insert(5);

            assertThrows(BinarySearchTreeException.class,() -> tree.removeValue(99));
        }

        @Test
        @DisplayName("Elimina correctamente un nodo hoja (sin hijos)")
        public void removeValue_eliminar_nodo_hoja() {
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);

            tree.removeValue(3);

            assertEquals("10(5(,),15(12,))", tree.render());
        }


        @Test
        @DisplayName("Elimina correctamente un nodo con un solo hijo izquierdo")
        public void removeValue_eliminar_nodo_con_hijo_izq() {
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);
            // 15 tiene un solo hijo izquierdo: 12
            tree.removeValue(15);

            assertEquals("10(5(3,),12)", tree.render());
        }

        @Test
        @DisplayName("Elimina correctamente un nodo con un solo hijo derecho")
        public void removeValue_eliminar_nodo_con_hijo_dcho() {
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(8);
            tree.insert(12);
            // 15 tiene un solo hijo izquierdo: 12
            tree.removeValue(5);

            assertEquals("10(8,15(12,))", tree.render());
        }

        @Test
        @DisplayName("Elimina correctamente un nodo con dos hijos")
        public void removeValue_eliminar_nodo_con_dos_hijos() {
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);

            tree.removeValue(10);
            
            assertEquals("12(5(3,),15(,))", tree.render());
        }
    }

    /* Pruebas del método inOrder */
    @Nested
    @DisplayName("inOrder")
    class PruebasinOrder{
        @Test
        @DisplayName("Lanza excepción si el árbol está vacío")
        public void inOrder_excepcion_arbol_vacio() {
            assertThrows(BinarySearchTreeException.class, () -> tree.inOrder());
        }

        @Test
        @DisplayName("Devuelve correctamente un solo elemento si el árbol tiene un solo nodo")
        public void inOrder_unico_elemento_si_arbol_tiene_un_nodo() {
            tree.insert(10);

            List<Integer> resultado = tree.inOrder();
            
            assertEquals(List.of(10), resultado);
        }

        @Test
        @DisplayName("Devuelve la lista ordenada correctamente si el árbol tiene múltiples nodos")
        public void inOrder_listaOrdenada() {
            tree.insert(10);
            tree.insert(5);
            tree.insert(15);
            tree.insert(3);
            tree.insert(12);

            List<Integer> resultado = tree.inOrder();
            assertEquals(List.of(3, 5, 10, 12, 15), resultado);
        }
        
    }

    /* Pruebas del método balance */
    @Nested
    @DisplayName("balance")
    class Pruebasbalance{
        
        @Test
        @DisplayName("Lanza excepción si el árbol está vacío")
        public void balance_excepcion__arbol_vacio() {
            assertThrows(BinarySearchTreeException.class, () -> tree.balance());
        }

        @Test
        @DisplayName("No cambia el árbol si solo tiene un nodo")
        public void balance_un_nodo() {
            tree.insert(10);
            tree.balance();
            List<Integer> resultado = tree.inOrder();
            
            assertEquals("10", tree.render());
        }

        @Test
        @DisplayName("Reorganiza correctamente un árbol desbalanceado")
        public void balance_arbol_desbalanceado() {
            // Árbol totalmente desbalanceado hacia la derecha
            tree.insert(1);
            tree.insert(2);
            tree.insert(3);
            tree.insert(4);
            tree.insert(5);

            tree.balance();
            
            assertEquals("3(1(,2),4(,5))", tree.render());
        }

        @Test
        @DisplayName("Mantiene el recorrido in-order ordenado después de balancear")
        public void balance_conserva_InOrder() {
            tree.insert(5);
            tree.insert(3);
            tree.insert(7);
            tree.insert(1);
            tree.insert(4);
            tree.insert(6);
            tree.insert(9);

            tree.balance();

            assertEquals("5(3(1,4),7(6,9))", tree.render());
        }
        
    }
}