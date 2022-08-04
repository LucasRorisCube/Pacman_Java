package ElementosDoSistema;

import javafx.scene.canvas.GraphicsContext;
import Engine.Mapa;

/** Classe que representa uma powerpill, um elemento capaz de, por um periodo de tempo, derrotar os fantasmas
* @author Lucas Alves Roris
* @since jan 2022
*/
public class PowerPill extends Elemento{
    
    /** Construtor da classe do powerPill
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public PowerPill(){
        super("Imagens/powerpill.png", 20, 20);
    }
    
    /** Método que desenha o elemento no mapa
     * @param gc é o GraphicsContext usado na interface gráfica
     * @param mapa é o mapa atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    @Override
    public void drawInMap(GraphicsContext gc, Mapa mapa){
        if(this.getFoiComido() == 0)
            gc.drawImage(this.getImage(), mapa.posicoes.get(pos).getCoordX()-10,mapa.posicoes.get(pos).getCoordY()-10);
    }
}
