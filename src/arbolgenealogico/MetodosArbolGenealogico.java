package arbolgenealogico;

// clase que contiene los metodos para operar en el arbol genealogico
public class MetodosArbolGenealogico {
    
    
    Nodo raiz;   // constructor de la clase MetodosArbolGenealogico
    public MetodosArbolGenealogico(Nodo raiz) {
        this.raiz = raiz;
    }

    // metodo para mostrar el arbol genealogico
    
    public void mostrarArbol() {
            System.out.println("Arbol Genealogico.");
            mostrarArbolRecursivo(raiz, 0);  
    }
    
    // metodo recursivo para mostrar el arbol genealogico 
    
    private void mostrarArbolRecursivo(Nodo nodo, int nivel) {
        // se utiliza "  " repetidas nivel veces para crear la indentacion segun el nivel
        String indentacion = "  ".repeat(nivel);
        
        // se imprime el nombre del nodo y su nivel en la jerarquia con indentacion    
        System.out.println(indentacion + "- " + nodo.nombre + " ---> en el nivel " + nivel);
        
        // ee recorren recursivamente los hijos del nodo
        for (Nodo hijo : nodo.hijos) {
            mostrarArbolRecursivo(hijo, nivel + 1);
        }
    }
    
    // metodo que mustra en pantalla si el arbol esta vacio
    public boolean arbolVacio() {
        return raiz == null;
    }

    
    // metodo para realizar un recorrido en orden del arbol genealogico
    
    public void recorridoEnOrden(Nodo nodo, int nivel) {
        if (nodo != null ) {
            
            // calcula la posicion media para dividir los hijos en subarbol izquierdo y derecho
            int mitad = nodo.hijos.length / 2;
            
            // recorre el subarbol izquierdo
            for (int i = 0; i < mitad; i++) {
                recorridoEnOrden(nodo.hijos[i], nivel + 1);
            }
            
            // imprime el nombre del nodo actual con indentacion
            
            String indentacion = "  ".repeat(nivel);
            System.out.println(indentacion + "- " + nodo.nombre);
            
            // recorre el subarbol derecho
            
            for (int i = mitad; i < nodo.hijos.length; i++) {
                recorridoEnOrden(nodo.hijos[i], nivel + 1); 
            }
        }
    }

    // metodo para realizar un recorrido en preorden del arbol genealogico
    
    public void recorridoPreOrden(Nodo nodo, int nivel) {
        if (nodo != null) {
            
            // imprime el nombre del nodo actual con indentacion
            String indentacion = "  ".repeat(nivel);
            System.out.println(indentacion + "- " + nodo.nombre);
            
            // recorre recursivamente los hijos del nodo (subarbol izquierdo y derecho)
            for (Nodo hijo : nodo.hijos) {
                recorridoPreOrden(hijo, nivel + 1); 
                
            }
        }
    }

    // metodo para realizar un recorrido en postorden en el arbol genealogico
    
    public void recorridoPostOrden(Nodo nodo, int nivel) {
        if (nodo != null) {
            // recorre recursivamente los hijos del nodo (subarbol izquierdo y derecho)
            for (Nodo hijo : nodo.hijos) {
                recorridoPostOrden(hijo, nivel + 1); 
            }
            
            // imprime el nombre del nodo actual con indentacion
            String indentacion = "  ".repeat(nivel);
            System.out.println(indentacion + "- " + nodo.nombre);
            }
        }

    // metodo para calcula la profundidad del arbol genealogico    
    public int profundidadArbol(Nodo nodo) {
        if (nodo == null) {
            return 0;  // si el nodo es nulo, la profundidad es 0
        } else {
            int maxProfundidad = 0;
            
            // itera sobre los hijos del nodo para calcular la profundidad maxima
            for (Nodo hijo : nodo.hijos) {
                int profundidadHijo = profundidadArbol(hijo);
                maxProfundidad = Math.max(maxProfundidad, profundidadHijo);
            }
            
            // la profundidad del arbol es 1 mas la profundidad maxima de los hijos
            return 1 + maxProfundidad;
        }     
    }

    
    // metodo para eliminar un nodo del arbol
    
    public void eliminarNodo(Nodo padre, String nombre) {
        if (padre != null) {
            for (int i = 0; i < padre.hijos.length; i++) {
                if (padre.hijos[i].nombre.equals(nombre)) {
                    
                    // encontramos el nodo y lo eliminamos
                    Nodo[] nuevosHijos = new Nodo[padre.hijos.length - 1];
                    
                    // copiamos los nodos antes del nodo a eliminar
                    System.arraycopy(padre.hijos, 0, nuevosHijos, 0, i);
                    
                    // copiamos los nodos despues del nodo a eliminar
                     System.arraycopy(padre.hijos, i + 1, nuevosHijos, i, padre.hijos.length - i - 1);
                     
                    // asignamos el nuevo arreglo de hijos al padre
                    padre.hijos = nuevosHijos;
                    
                    // imprime un mensaje de exito al eliminar el nodo
                    System.out.println("El nodo '" + nombre + "' ha sido eliminado correctamente.");
                    return;     // despues de eliminar el nodo se retorna
                }
            }
        }
    }
    

    // metodo para agregar un nuevo nodo al arbol

    public void agregarNodo(Nodo padre, String nombreNuevoNodo) { 
        if (padre != null) {
            // creamos el nuevo nodo
            Nodo nuevoNodo = new Nodo(nombreNuevoNodo);
            
            // creamos un nuevo arreglo para los hijos del padre
            Nodo[] nuevosHijos = new Nodo[padre.hijos.length + 1];
            
            // copiamos los nodos existentes del padre al nuevo arreglo
            System.arraycopy(padre.hijos, 0, nuevosHijos, 0, padre.hijos.length);
            
            // agregamos el nuevo nodo al final del nuevo arreglo
            nuevosHijos[padre.hijos.length] = nuevoNodo;
            
            // asignamos el nuevo arreglo de hijos al padre
            padre.hijos = nuevosHijos;
        }
    }   
    
    // metodo que busca un nodo en el arbol genealogico por su nombre
    
    public Nodo buscarNodoPorNombre(Nodo nodo, String nombre) {
        if (nodo != null) {
            // compara el nombre del nodo actual con el nombre buscado (ignorando mayusculas/minusculas)
            if (nodo.nombre.equalsIgnoreCase(nombre)) {
                return nodo; // nodo encontrado
            }
        
          // buscar en los hijos recursivamente
          for (Nodo hijo : nodo.hijos) {
              Nodo nodoEncontrado = buscarNodoPorNombre(hijo, nombre);
              if (nodoEncontrado != null) {
                  return nodoEncontrado; // nodo encontrado en algun subarbol
                }
            }
        }
        return null; // nodo no encontrado
    }
      
}
    
