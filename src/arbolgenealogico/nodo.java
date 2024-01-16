package arbolgenealogico;

// clase que representa un nodo en el arbol genealogico
class Nodo {
    String nombre;  // nombre del nodo
    Nodo[] hijos;   // arreglo de nodos hijos

    // constructor para inicializar un nodo con un nombre
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.hijos = new Nodo[0];  // inicializamos el arreglo de hijos como vacio
    }
}
