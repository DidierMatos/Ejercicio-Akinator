
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

      private static Arbol arbol = new Arbol();
      private static Nodo nodo;
      private static Archivo<Arbol> archivo;

      
      public static void main(String[] args) throws FileNotFoundException, IOException {

            int opcionMenu, opcionMenu2;
            String nombreArchivoDatos = "Tree.tree";
            String rutaArchivo = System.getProperty("user.dir") + "\\" + nombreArchivoDatos;

            Scanner lecturaTeclado = new Scanner(System.in);
            archivo = new Archivo<>(nombreArchivoDatos);

            File file = new File(rutaArchivo);
            if (file.exists()) {
                  arbol = archivo.deserializar();
                  arbol.preOrder();
            } else {
                  arbol.añadirPregunta("¿Es una animal doméstico?");
                  arbol.añadirRespuesta("GATO","");
                  arbol.añadirRespuesta("LOBO","");
                  archivo.crearArchivoVacio();
            }

            do {
                  System.out.println("\n ESCOJA UNA OPCION: ");
                  System.out.println(" ----------------------------------------------------");
                  System.out.println(" [ 1 ]  Jugar");
                  System.out.println(" [ 2 ] Salir");
                  System.out.print("\n ESCOJA UNA OPCION: ");
                  opcionMenu = lecturaTeclado.nextInt();
                  System.out.println(" -----------------------------------------------------\n");
                  switch (opcionMenu) {
                        case 1: {
                              do {
                                    nodo = arbol.recorrerAdivinador();
                                    juego();
                                    System.out.println("\n ¿Volver a jugar?");
                                    System.out.println(" [ 1 ]  Si, quiero jugar");
                                    System.out.println(" [ 2 ] No, ya es suficiente");
                                    System.out.print("\n ESCOJA UNA OPCION: ");
                                    opcionMenu2 = lecturaTeclado.nextInt();
                              } while (opcionMenu2 != 2);
                              break;
                        }
                        case 2:
                              break;
                  }
            } while (opcionMenu < 1 || opcionMenu > 2);
      }

   
      public static void juego() throws FileNotFoundException {

            int opcionMenu;
            String preguntaUsuario, respuestaUsuario;
            Scanner lecturaTeclado = new Scanner(System.in);
            while (nodo.getDerecho() != null && nodo.getIzquierdo() != null) {
                  do {
                        System.out.println(nodo.getTexto() + "\n");
                        System.out.println(" [ 1 ]  Sí");
                        System.out.println(" [ 2 ] No");
                        //System.out.println(" [ 3 ] No sé");
                        System.out.print("\n ESCOJA UNA OPCION: ");
                        opcionMenu = lecturaTeclado.nextInt();
                        System.out.println(" -------------------------");
                        switch (opcionMenu) {
                              case 1: {
                                    nodo = arbol.recorrerAdivinador(1);
                                    break;
                              }
                              case 2: {
                                    nodo = arbol.recorrerAdivinador(-1);
                                    break;
                              }
                              /*case 3: {
                                    nodo = arbol.recorrerAdivinador(0);
                                    break;
                              }*/
                          
                        }
                  } while (opcionMenu < 1 || opcionMenu > 3);
            }
            do {
                  System.out.println("\n ¿Estabas pensando en un: " + nodo.getTexto().toUpperCase()+ "?" + "\n");
                  System.out.println(" [ 1 ]  Sí");
                  System.out.println(" [ 2 ] No");
                  System.out.print("\n ESCOJA UNA OPCION: ");
                  opcionMenu = lecturaTeclado.nextInt();
                  System.out.println(" -------------------------");
                  lecturaTeclado.nextLine();
                  switch (opcionMenu) {
                        case 1: {
                              System.out.println(" Felicidades !!\n Bien hecho!!!");
                              break;
                        }
                        case 2: {
                              System.out.print(" Ingrese el animal que pensó: ");
                              respuestaUsuario = lecturaTeclado.nextLine();
                              System.out.print(" Ingrese una característica verdadera para un(a) " + nodo.getTexto().toUpperCase() + ", pero falsa para un " + respuestaUsuario.toUpperCase() + ": ");
                              preguntaUsuario = lecturaTeclado.nextLine();
                              
                              //System.out.println("Temp recorrido: " + arbol.getTemporalRecorrido());
                              
                              arbol.añadirPregunta("¿" + preguntaUsuario + "?");
                              arbol.añadirRespuesta(respuestaUsuario,"");
                             
                              break;
                        }
                  }
                  //System.out.println("Temp recorrido: " + arbol.getTemporalRecorrido());
                  arbol.resetTemporalRecorrido();
                  //System.out.println("Temp recorrido: " + arbol.getTemporalRecorrido());
                  archivo.serializar(arbol);
                  arbol = archivo.deserializar();
            
            } while (opcionMenu < 1 || opcionMenu > 2);
      }
}

class CopiarArchivo {

      static final Logger LOGGER = Logger.getAnonymousLogger();

      public CopiarArchivo(String origenArchivo, String destinoArchivo) {
            try {
                  Path origenPath = Paths.get(origenArchivo);
                  Path destinoPath = Paths.get(destinoArchivo);
           
                  Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
                  System.out.println("\t[ Copiado exitoso ]");
            } catch (FileNotFoundException ex) {
                  LOGGER.log(Level.SEVERE, ex.getMessage());
            } catch (IOException ex) {
                  LOGGER.log(Level.SEVERE, ex.getMessage());
            }
      }
}
