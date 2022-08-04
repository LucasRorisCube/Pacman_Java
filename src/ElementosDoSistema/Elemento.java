package ElementosDoSistema;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Engine.Mapa;

/** Classe que representa um elemento do jogo, como personagens ou frutas
* @author Lucas Alves Roris
* @since jan 2022
*/
public class Elemento {
    
    private Image ownImage;
    protected int pos;
    private int foiComido;
    
    /** Construtor da classe que inicialia o elemento com a sua imagem e tamanho
     * @param URLImage é o caminho até a imagem
     * @param tamX é o tamanho do elemento no eixo X
     * @param tamY é o tamanho do elemento no eixo Y
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    Elemento(String URLImage, int tamX, int tamY){
        this.ownImage = new Image(URLImage,tamX,tamY,false,false);
        this.foiComido = 0;

    }
    
    public int getPos(){
        return this.pos;
    }
    
    public void setPos(int pos){
        this.pos = pos;
    }
    
    public int getFoiComido(){
        return this.foiComido;
    }
    
    public void setFoiComido(int foiComido){
        this.foiComido = foiComido;
    }
    
    public Image getImage(){
        return this.ownImage;
    }
    
    public void setImage(Image image){
        this.ownImage = image;
    }
    
    /** Método que desenha o elemento no mapa
     * @param gc é o GraphicsContext usado na interface gráfica
     * @param mapa é o mapa atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void drawInMap(GraphicsContext gc, Mapa mapa){
        if(this.foiComido == 0)
            gc.drawImage(ownImage, mapa.posicoes.get(pos).getCoordX(),mapa.posicoes.get(pos).getCoordY());
    }
}
