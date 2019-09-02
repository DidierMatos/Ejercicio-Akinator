import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Archivo <T> {
      
      private FileOutputStream fo;
      private String nombreArchivo;
  
      public Archivo(String nombreArchivo){
            this.nombreArchivo= nombreArchivo;
      }
  
      public boolean crearArchivoVacio(){
            return crearArchivoVacio(false);
      }
   
      public boolean crearArchivoVacio(boolean sobreEscribirDatos){
            boolean bandera= true;
            FileOutputStream tmp = null;
            try{
                  tmp = new FileOutputStream(nombreArchivo, sobreEscribirDatos);
            }catch(FileNotFoundException e){
                  bandera= false;
                  System.err.println("\t[ No se pudo crear el archivo ]");
            }
            fo= tmp;
            return bandera;
      }
   
      public boolean serializar(T objeto) throws FileNotFoundException{
            
            boolean bandera= true;
            
            if(fo == null){
                fo = new FileOutputStream(nombreArchivo, false);
            }
            try{
                  ObjectOutputStream oos = new ObjectOutputStream(fo);
                        oos.writeObject(objeto);
                        oos.flush();
                        System.out.println("Serializado");
            }catch (IOException e){
                  // write stack trace to standard error
                  System.err.println("\t[ El objeto no se pudo guardar ]");
                  bandera= false;
            }
            return bandera;
      }
      public void setNombreArchivo(String nombreArchivo){
            this.nombreArchivo= nombreArchivo;
      }
  
      public T deserializar(){
            
            T objeto= null;
            try{
                  final FileInputStream fis = new FileInputStream(nombreArchivo);
                  try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                        objeto = (T) ois.readObject();
                        ois.close();
                  }
            }catch (IOException | ClassNotFoundException e){
               System.err.println("\t[ El objeto no se pudo leer ]");
            }
            return objeto;
      }
}