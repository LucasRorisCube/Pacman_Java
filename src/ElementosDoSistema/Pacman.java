package ElementosDoSistema;

import java.util.ArrayList;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import Engine.Mapa;

/** Classe que representa opacman, o personagem controlavel pelo usuario
* @author Lucas Alves Roris
* @since jan 2022
*/
public class Pacman extends Personagem {
    
    /** Construtor da classe do pacman
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public Pacman(){
        super("Imagens/pacman.png",30,30);
    }
    
    /** Metodo que faz o pacman voltar para a sua posicao original
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void resetarPosicao(){
        this.setPos(350);
    }
    
    int rotation = 0;
    
    /** Método que desenha o elemento no mapa
     * @param gc é o GraphicsContext usado na interface gráfica
     * @param mapa é o mapa atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    @Override
    public void drawInMap(GraphicsContext gc, Mapa mapa){
        
        
        switch(this.direcaoParaAndar){
            case 0:
                this.rotation = 180;
                break;
            case 1:
                this.rotation = 0;
                break;
            case 2:
                this.rotation = 90;
                break;
            case 3:
                this.rotation = 270;
                break;
        }
        
        ImageView iv = new ImageView(this.getImage());
        iv.setRotate(rotation);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(params, null);
 
        gc.drawImage(rotatedImage, mapa.posicoes.get(this.getPos()).getCoordX()-15,mapa.posicoes.get(this.getPos()).getCoordY()-15);

    }
    
    /** Método que faz o pacman andar
     * @param mapa é o mapa atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void andar(Mapa mapa){
        ArrayList<Integer> vizinhos = mapa.posicoes.get(this.getPos()).getVizinhos();
        
        if(direcaoParaAndar != -1 && vizinhos.get(direcaoParaAndar) != -1){
            this.setPos(vizinhos.get(direcaoParaAndar));
        }
    }
}
