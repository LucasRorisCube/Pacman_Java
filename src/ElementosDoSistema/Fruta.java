package ElementosDoSistema;

import javafx.scene.canvas.GraphicsContext;
import Engine.Mapa;

/** Classe que representa uma fruta do mapa, valendo uma pontuacao maior que um pacdot
* @author Lucas Alves Roris
* @since jan 2022
*/
public class Fruta extends Elemento{
    
    private final int pontuacao;
    
    /** Construtor da classe da fruta
     * @param URLImage é o caminho para achar a imagem
     * @param pontuacao é a pontuação que essa fruta terá
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public Fruta(String URLImage, int pontuacao){
        super(URLImage, 30, 30);
        this.pontuacao = pontuacao;
    }
    
    public int getPontuacao(){
        return this.pontuacao;
    }
    
    /** Método que desenha a fruta no mapa
     * @param gc é o GraphicsContext usado na interface gráfica
     * @param mapa é o mapa atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    @Override
    public void drawInMap(GraphicsContext gc, Mapa mapa){
        if(this.getFoiComido() == 0)
            gc.drawImage(this.getImage(), mapa.posicoes.get(pos).getCoordX()-15,mapa.posicoes.get(pos).getCoordY()-15);
    }
}
