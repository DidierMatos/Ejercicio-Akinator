import java.io.Serializable;

public class Arbol implements Serializable {
      
      private Nodo raiz;
      private int size;
      private Nodo temporalRecorrido= new Nodo();
      private Nodo temporalRespuesta= new Nodo();
      private int nivel;
      private boolean ramaIzquierdaDerecha;
      private boolean arbolInicializado;
      private int nivelRamaIzquierda;
      private int nivelRamaDerecha;

      public Arbol(){
            raiz= null;
            size= 0;
            temporalRecorrido= raiz;
            temporalRespuesta= raiz;
            nivel= 0;
            arbolInicializado= false;
            nivelRamaDerecha= 0;
            nivelRamaIzquierda= 0;
      }
 
      public Nodo recorrerAdivinador(int ladoDelArbol){
            Nodo n= new Nodo();
            try{
                  if(raiz != null){
                        if(nivel == 0){
                              temporalRecorrido = raiz;
                              n= temporalRecorrido;
                        }else{
                              if(!isHoja(temporalRecorrido)){   // El nodo es de tipo pregunta
                                    switch(ladoDelArbol){
                                          case 1:{
                                                temporalRecorrido= temporalRecorrido.getDerecho();
                                                n= temporalRecorrido;
                                                ramaIzquierdaDerecha= true;
                                                break;}
                                          case -1:{
                                                temporalRecorrido= temporalRecorrido.getIzquierdo();
                                                n= temporalRecorrido;
                                                ramaIzquierdaDerecha= false;
                                                break;}
                                          case 0:{
                                                n.setTexto(temporalRecorrido.getIzquierdo().getTexto()+ "/" + temporalRecorrido.getDerecho().getTexto());
                                                break;}
                                    }
                              }else
                                    n= temporalRecorrido;   // El nodo es de tipo respuesta (Hoja)
                        }
                        nivel++;
                  }else
                        System.out.println("\t[ No existe ningún nodo aún ]");
            }catch(Exception e){
                  System.err.println("\t[ No se pudo mostrar el nodo ] " + e);
            }
            return n;
      }

      public Nodo recorrerAdivinador(){
            return recorrerAdivinador(1);
      } 

      public boolean añadirPregunta(String valor){            // TRUE: lado derecho del árbol, FALSE: lado izquierdo del árbol
            boolean bandera= true;
            try{
                  Nodo n= new Nodo(valor);
                  if(raiz == null){
                        raiz= n;
                        temporalRespuesta= raiz;
                        nivelRamaDerecha++;
                        nivelRamaIzquierda++;
                  }else{
                        Nodo temporal= temporalRecorrido.getPadre();
                        n.setDerecho(temporalRecorrido);
                        temporalRecorrido.setPadre(n);
                        temporalRecorrido= temporalRecorrido.getPadre();
                        temporalRespuesta= temporalRecorrido;
                        if(ramaIzquierdaDerecha == true){
                              temporal.setDerecho(n);
                              nivelRamaDerecha++;
                        }else{
                              temporal.setIzquierdo(n);
                              nivelRamaIzquierda++;
                        }
                        n.setPadre(temporal);
                  }
                  size++;
            }catch(Exception e){
                  bandera= false;
                  System.err.println("\t[ No se pudo añadir la pregunta ]");
            }
            return bandera;
      }

      public boolean añadirRespuesta(String valor, String ruta){         // Las respuestas siempre se añaden del lado izquierdo del nodo
            boolean bandera= true;
            try{
                  if(raiz == null){
                        System.err.println("\t[ Cree primero una pregunta ]");
                  }else{
                        Nodo nuevo= new Nodo(valor,ruta);
                        if(temporalRespuesta.getDerecho() == null)
                              temporalRespuesta.setDerecho(nuevo);
                        else
                              temporalRespuesta.setIzquierdo(nuevo);
                        nuevo.setPadre(temporalRespuesta);
                        size++;
                  }
                  arbolInicializado= true;
            }catch(Exception e){
                  bandera= false;
                  System.err.println("\t[ No se pudo añadir la respuesta ] " + e);
            }
            return bandera;
      }
   
      public void resetTemporalRecorrido(){
            temporalRecorrido= raiz;
            nivel= 0;
      }

      public void preOrder (Nodo nodo)
      {
          if (nodo != null)
          {
              System.out.print(nodo.getTexto());
              preOrder (nodo.getIzquierdo());
              preOrder (nodo.getDerecho());
          }
      }

      public void preOrder ()
      {
          preOrder (raiz);
          System.out.println();
      }

      public void inOrder (Nodo nodo)
      {
          if (nodo != null)
          {    
              inOrder (nodo.getIzquierdo());
              System.out.print(nodo.getTexto());
              inOrder (nodo.getDerecho());
          }
      }

      public void inOrder ()
      {
          inOrder (raiz);
          System.out.println();
      }

      public void postOrder (Nodo nodo)
      {
          if (nodo != null)
          {
              postOrder (nodo.getIzquierdo());
              postOrder (nodo.getDerecho());
              System.out.print(nodo.getTexto());
          }
      }
 
      public void postOrder ()
      {
          postOrder (raiz);
          System.out.println();
      }

      public int size(){
            return size;
      }
 
      public int getNivelRecorridoActual(){
            return nivel;
      }

      public boolean isHoja(Nodo nodo){
            return nodo.getDerecho()== null && nodo.getIzquierdo()== null;
      }
 
      public boolean borrarArbol(){
            
            boolean bandera= true;
            try{
                  raiz= null;
            }catch(Exception e){
                  bandera= false;
            }
            return bandera;
      }

      public boolean arbolInicializado(){
            return arbolInicializado;
      }
 
      public int obtenerNivelRamaIzquierda(){
            
            return nivelRamaIzquierda;
      }
 
      public int obtenerNivelRamaDerecha(){
            
            return nivelRamaDerecha;
      }
   
      public int obtenerNivelArbol(){
            
            int nivelMayor;
            
            if(obtenerNivelRamaIzquierda() > obtenerNivelRamaDerecha()){
                  nivelMayor= obtenerNivelRamaIzquierda();
                  return nivelMayor;
            }else
                  nivelMayor= obtenerNivelRamaDerecha();
            return nivelMayor;
      }
      
      public String getTemporalRecorrido(){
            return temporalRecorrido.getTexto();
      }
}