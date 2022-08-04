package Engine;

import java.net.URISyntaxException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/** Classe que executa todos os sons do jogo
* @author Lucas Alves Roris
* @since jan 2022
*/
public class Som {
    
    private Media somIntro;
    private Media somComeuPacDot;
    private Media somComeuPowerPill;
    private Media somComeuFruta;
    private Media somComeuFantasma;
    private Media somPacmanMorreu;
    
    /** Construtor da classe que pega os arquivos de audio e se prepara para executa-los
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    Som(){
        try{
            
            // Inicializa o som ao entrar no jogo
            this.somIntro = new Media(getClass().getResource("/Sons/intro.wav").toURI().toString());
            
            // Som ao comer um pacdot
            this.somComeuPacDot = new Media(getClass().getResource("/Sons/pacdot.wav").toURI().toString());
            
            // Som ao comer uma powerpill
            this.somComeuPowerPill = new Media(getClass().getResource("/Sons/powerPill.wav").toURI().toString());
            
            // Som ao comer uma fruta
            this.somComeuFruta = new Media(getClass().getResource("/Sons/fruta.wav").toURI().toString());
            
            // Som ao comer um fantasma
            this.somComeuFantasma = new Media(getClass().getResource("/Sons/comeFantasma.wav").toURI().toString());
            
            // Som ao morrer
            this.somPacmanMorreu = new Media(getClass().getResource("/Sons/morte.wav").toURI().toString());
            
        } catch (URISyntaxException e){
            
        }
    }
    
    /** Método que toca o som de introducao
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void tocaSomIntro(){
        (new MediaPlayer(this.somIntro)).play();
    }
    
    /** Método que toca o som quando o pacman come um pacdot
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void tocaSomPacDot(){
        (new MediaPlayer(this.somComeuPacDot)).play();

    }
    
    /** Método que toca o som quando o pacman come uma powerpill
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void tocaSomPowerPill(){
        (new MediaPlayer(this.somComeuPowerPill)).play();
    }
    
    /** Método que toca o som quando o pacman come uma fruta
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void tocaSomFruta(){
        (new MediaPlayer(this.somComeuFruta)).play();
    }
    
    /** Método que toca o som quando o pacman come um fantasma
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void tocaSomComeFantasma(){
       (new MediaPlayer(this.somComeuFantasma)).play();
    }
    
    /** Método que toca o som quando o pacman morre
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void tocaSomMorte(){
        (new MediaPlayer(this.somPacmanMorreu)).play();
    }
    
    
}
