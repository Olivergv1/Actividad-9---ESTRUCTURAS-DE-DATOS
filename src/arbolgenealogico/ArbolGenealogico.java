package arbolgenealogico;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

// clase principal del arbol genealogico
public class ArbolGenealogico {
    
    public static void main(String[] args) {
        // creamos la raiz del arbol (maria, la madre)
        Nodo maria = new Nodo("Maria");
        
        // creamos una instancia de la clase MetodosArbolGenealogico
        MetodosArbolGenealogico arbol = new MetodosArbolGenealogico( maria);

        
        Nodo oliver = new Nodo("Oliver");
        Nodo mario = new Nodo("Mario");
        Nodo brandon = new Nodo("Brandon");
        Nodo beto = new Nodo("Beto");
        Nodo diego = new Nodo("Diego");
        Nodo anya = new Nodo("Anya");

        maria.hijos = new Nodo[]{oliver, mario, brandon, beto, diego};
        beto.hijos = new Nodo[]{anya};

        // creamos un lector de entrada para la interaccion con el usuario
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            int opcion;
            do {
                // menu interactivo que se mostrara en pantalla
                
                System.out.println("\n----------Menu----------");
                System.out.println("1. Mostrar el arbol");
                System.out.println("2. Saber si el arbol esta vacio");
                System.out.println("3. Mostrar recorrido en orden");
                System.out.println("4. Mostrar recorrido pre-orden");
                System.out.println("5. Mostrar recorrido post-orden");
                System.out.println("6. Profundidad del arbol");
                System.out.println("7. Eliminar un nodo");
                System.out.println("8. Agregar un nuevo nodo");
                System.out.println("9. Salir");
                System.out.print("Ingrese su opcion: ");
                
                // se lee la opcion ingresada por el usuario
                opcion = Integer.parseInt(reader.readLine());
                
                // y se ejecuta la accion correspondiente segun la opcion seleccionada
                switch (opcion) {
                                     // mostrar el arbol
                    case 1:
                        arbol.mostrarArbol();
                        System.out.println("___________________________________________");
                        break;
                        
                    case 2:
                                    // saber si el arbol esta vacio
                        System.out.println("El arbol esta vacio: " + arbol.arbolVacio());
                        System.out.println("____________________________________________");
                        break;
                        
                    case 3:
                                    // mostrar recorrido en orden
                        arbol.recorridoEnOrden(maria, 0);
                        System.out.println("____________________________________________");
                    break;
                    
                    case 4:          // mostrar recorrido preorden
                        arbol.recorridoPreOrden(maria, 0);
                        System.out.println("_____________________________________________");
                        break;
                        
                    case 5:
                                    // mostrar recorrido postorden
                        arbol.recorridoPostOrden(maria,0);
                        System.out.println("_______________________________________________");
                        break;
                        
                    case 6:        // profundidad del arbol
                        System.out.println("La profundidad del arbol es: " + arbol.profundidadArbol(maria));
                        System.out.println("_______________________________________________");
                        break;
                        
                    case 7:
                                    // eliminar un nodo
                        System.out.print("Ingrese el nombre del nodo a eliminar: ");
                        String nombreNodo = reader.readLine();
                        arbol.eliminarNodo(maria, nombreNodo);
                        System.out.println("_______________________________________________");
                    break;
                
                    case 8:
                                // agregar un nuevo nodo al arbol
                        System.out.print("Ingrese el nombre del nuevo nodo: ");
                        String nombreNuevoNodo = reader.readLine();
                        
                        System.out.print("Ingrese el nombre del nodo existente que sera el padre del este nuevo nodo: ");
                        String nombreNodoPadre = reader.readLine();
                    
                        Nodo nodoPadre = arbol.buscarNodoPorNombre(arbol.raiz, nombreNodoPadre);
                        if (nodoPadre != null) {
                            arbol.agregarNodo(nodoPadre, nombreNuevoNodo);
                            System.out.println("Nuevo nodo agregado con exito.");
                            System.out.println("_______________________________________________");
                        } else {
                            System.out.println("Nodo padre no encontrado.");
                            System.out.println("_______________________________________________");
                        }
                        break; 
                    
                    case 9:
                                // salir del programa
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                               // si se ingresa una opcion no valida se le permitira al suuario intentarlo otra ves
                        System.out.println("Opcion no valida. Intente nuevamente.");
                }
            } while (opcion != 9); // continua el bucle hasta que se seleccione la opcion 9 (salir)
        } catch (IOException | NumberFormatException e) {

            e.printStackTrace();   // capturar excepciones de entrada/salida y permite visualizar los errores 
        }
        
    }    
}