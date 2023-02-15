/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package discretas;

/**
 *
 * @author Yfmay
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JFrame;


public class CaminoHamiltoniano {
  private int[][] grafo;
  private int numVertices;
  private LinkedList<Integer> camino;
  private LinkedList<Integer>[] adyacencia;
  private boolean[] visitado;
  private int pesoMin = Integer.MAX_VALUE;
  private int[] solucion;
  private int[][] matrixAux;
  private boolean[] lluvia;
  private boolean[] lluviafinal;

  public CaminoHamiltoniano(int[][] grafo) {
    this.grafo = grafo;
    this.numVertices = grafo.length;
    this.camino = new LinkedList<>();
    this.matrixAux = new int[numVertices][numVertices];
    this.lluvia = new boolean[numVertices];
    this.lluviafinal = new boolean[numVertices];
  }
  
    private void llenarAdyacencia(){
      adyacencia = new LinkedList[numVertices];
      for (int i = 0; i < numVertices; i++) {
        adyacencia[i] = new LinkedList<>();
      for(int j = 0; j < numVertices; j++){
          if(grafo[i][j] != 0){
              adyacencia[i].add(j);
          }
      }
    }
  }
  
  public void encontrarCaminoHamiltoniano(int verticeInicial, String vector[]){
      llenarAdyacencia();
      visitado = new boolean[numVertices];
      visitado[verticeInicial] = true;
      camino.add(verticeInicial);
      buscarCamino(verticeInicial);
      //imprimirCamino(vector);
  }
  

  
  private void buscarCamino(int vertice){
      if(camino.size() == numVertices){
          int pesoCamino = 0;
          for(int i=0; i<numVertices - 1; i++){
              pesoCamino += matrixAux[camino.get(i)][camino.get(i+1)];
              
          }
          if(pesoCamino < pesoMin){
              pesoMin = pesoCamino;
              solucion = new int[numVertices];
              for(int i = 0; i<numVertices; i++){
                  solucion[i] = camino.get(i);
                  lluviafinal[i] = lluvia[i];
              }
          }
          return;
      }
      
      for(int v : adyacencia[vertice]){
          if(!visitado[v]){
            camino.add(v);
            visitado[v] = true;
            
            cambiarPeso(lluvia(25), vertice, v);
            buscarCamino(v);
            lluvia[vertice] = false;
            matrixAux[vertice][v]= grafo[vertice][v];
            camino.removeLast();
            visitado[v] = false;
              
          }
      }
      
  }
  
    public String toString(String vector[]) {
    String str = "La ruta más corta es la siguiente:\n\n";
    for (int i=0; i<solucion.length; i++) {
        str += vector[solucion[i]] + "  ";
    }
    str += "\n\nTiempo empleado: " + pesoMin + " minutos." + "\nEquivalente a: " + pesoMin/60 + " horas y " + pesoMin%60 + " minutos.";
    return str;
  }
  
  public boolean lluvia(int probabilidad){
      final boolean[] porcentaje = new boolean[100];  
        boolean p = false;
        
        for(int i = 0; i < porcentaje.length - 1; i++)
        {
            porcentaje[i] = false;  
        }
        
        for(int j = 0; j < probabilidad; j++)
        {
            porcentaje[j] = true;   
        }
        
        int aux = porcentaje.length - 1; 
        
        int Porcentaje = (int) (Math.random() * aux); 

        return p = porcentaje[Porcentaje];
  }
  
  public void cambiarPeso(boolean bandera, int vertice, int j){
      if(bandera){
          lluvia[vertice] = true;
          matrixAux[vertice][j] = grafo[vertice][j] + 60 ;
      }else{
          matrixAux[vertice][j] = grafo[vertice][j];
      }
  }
      
  
  public String conLluvia(String[] vector){
    String str = "";
    for (int i=0; i<solucion.length-1;i++){
        str += vector[solucion[i]] + " - " + vector[solucion[i+1]] + " : " + pasarPalabra(lluviafinal[solucion[i]]) + "\n";
    }
    return str;
  }
  
  private String pasarPalabra(boolean bandera){
      String str;
      if(bandera){
          return str = "Llovió";
      }else{
          return str = "No llovió";
      }
  }

    public int getNumVertices() {
        return numVertices;
    }

    public int[] getSolucion() {
        return solucion;
    }

    public boolean[] getLluviaFinal(){
        return lluviafinal;
    }

    public int[][] getMatrixAux() {
        return matrixAux;
    }
    
  public static void main(String[] args) {
            int[][] matrix = {
            {0, 161, 0, 0, 219, 0, 320, 104, 172}, 
            {161, 0, 32, 35, 0, 50, 360, 0, 0},
                {0, 32, 0, 23, 85, 0, 0, 0, 0} ,
                {0, 35, 23, 0, 58, 0, 0, 0, 0},
                {219, 0, 85, 58, 0, 0, 0, 0, 0},
                {0, 50, 0, 0, 0, 0, 402, 0, 0},
                {320, 360, 0, 0, 0, 402, 0, 273, 0}, 
                {104, 0, 0, 0, 0, 0, 273, 0, 79},
                {172, 0, 0, 0, 0, 0, 0, 79, 0}
        };
             
    String[] nombres = {"Bucaramanga","San Gil","Villanueva","Barichara","Galán","Charalá","Málaga","Berlín","Vetas"};
    
//    CaminoHamiltoniano x = new CaminoHamiltoniano(matrix);
    
    View frame = new View(nombres,matrix);
    frame.setVisible(true);
    frame.setSize(700, 550);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

//   x.encontrarCaminoHamiltoniano(0, nombres);
//      System.out.println(x.toString(nombres));
//      
//      System.out.println(x.conLluvia(nombres));     
  }
}

    

