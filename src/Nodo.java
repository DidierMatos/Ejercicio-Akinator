import java.io.Serializable;

public class Nodo implements Serializable{
      
      private Nodo padre;
      private Nodo derecho;
      private Nodo izquierdo;
      private String texto;
      private String rutaImagen;
    
      public Nodo (String texto){
        derecho= null;
        izquierdo= null;
        padre= null;
        this.texto= texto;
        this.rutaImagen = null;
      }
     
       public Nodo (){
        derecho= null;
        izquierdo= null;
        padre= null;
        texto= null;
        this.rutaImagen = null;
      }
       
       public Nodo(String texto, String ruta){
        derecho = null;
        izquierdo = null;
        padre = null;
        this.texto = texto;
        this.rutaImagen = ruta;
       }
     
      public String getTexto(){
            return texto;
      }
    
      public Nodo getPadre(){
            return padre;
      }
     
      public Nodo getDerecho(){
            return derecho;
      }
     
      public Nodo getIzquierdo(){
            return izquierdo;
      }
     
      public void setTexto(String texto){
            this.texto=  texto;
      }
      
      public void setPadre(Nodo padre){
            this.padre= padre;
      }
      
      public void setDerecho(Nodo derecho){
            this.derecho= derecho;
      }
     
      public void setIzquierdo(Nodo izquierdo){
            this.izquierdo= izquierdo;
      }
      
      public String getImagen(){
          return this.rutaImagen;
      }
      
      @Override
      public String toString(){
            return texto;
      }
}