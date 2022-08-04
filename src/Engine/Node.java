package Engine;

import java.util.ArrayList;

/** Classe que representa um nó do mapa
* @author Lucas Alves Roris
* @since jan 2022
*/
public class Node {
    
    private final char character;
    
    private int comido;
    
    private final int coordX;
    private final int coordY;
    
    private final ArrayList<Integer> vizinhos;
    private final ArrayList<Integer> vizinhosParaFantasmas;
    
    /** Construtor da classe que cria um mó do grafo ao ser percorrido pelos elementos
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    Node(int coordX, int coordY, int vizinhosR, int vizinhosL, int vizinhosU, int vizinhosD, int vizinhosParaFantasmasR, int vizinhosParaFantasmasL, int vizinhosParaFantasmasU, int vizinhosParaFantasmasD, char character){
        
        this.coordX = coordX;
        this.coordY = coordY;
        
        this.vizinhos = new ArrayList<>();
        this.vizinhos.add(vizinhosR);
        this.vizinhos.add(vizinhosL);
        this.vizinhos.add(vizinhosU);
        this.vizinhos.add(vizinhosD);
        
        this.vizinhosParaFantasmas = new ArrayList<>();
        this.vizinhosParaFantasmas.add(vizinhosParaFantasmasR);
        this.vizinhosParaFantasmas.add(vizinhosParaFantasmasL);
        this.vizinhosParaFantasmas.add(vizinhosParaFantasmasU);
        this.vizinhosParaFantasmas.add(vizinhosParaFantasmasD);
        
        this.character = character;
        
        this.comido = 0;
    }
    
    public int getCoordX(){
        return this.coordX;
    }
    
    public int getCoordY(){
        return this.coordY;
    }
    
    public char getChar(){
        return this.character;
    }
    
    public void setComido(){
        this.comido = 1;
    }
    
    public int getComido(){
        return this.comido;
    }
    
    public ArrayList<Integer> getVizinhos(){
        return this.vizinhos;
    }
    
    public ArrayList<Integer> getVizinhosFantasmas(){
        return this.vizinhosParaFantasmas;
    }
}
