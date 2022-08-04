package Engine;

import ElementosDoSistema.Fruta;
import ElementosDoSistema.FantasmaInteligente;
import ElementosDoSistema.Pacman;
import ElementosDoSistema.PowerPill;
import ElementosDoSistema.PacDot;
import ElementosDoSistema.Fantasma;
import ElementosDoSistema.Elemento;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** Classe que controla o andar do jogo
* @author Lucas Alves Roris
* @since jan 2022
*/
public final class Engine {
    
    // Mapa do jogo
    private Mapa mapa;
    
    // Personagem controlavel
    private Pacman pacman = new Pacman();
    
    // Inimigos
    private FantasmaInteligente Blinky;
    private FantasmaInteligente Pinky;
    private Fantasma Clyde;
    private Fantasma Inky;
    
    // Timers para controlar as velocidades
    private long timerBlinky;
    private long timerPinky;
    private long timerClyde;
    private long timerInky;
    
    private long timerPowerPill;
    
    private long timerPacman;
    
    // Nivel inicial do jogo
    private int nivel;
    
    // Quantidade de pacDots restantes atualmente
    private int pacDotsRestantes;
    // Quantidade de powerPills restantes atualmente
    private int powerPillsRestantes;
    
    // Contem todos os elementos do jogo
    private ArrayList<Elemento> elementosDoJogo;
    
    // Contem os sons do jogo
    private Som sons;
    
    // Pontuacao atual do jogador
    private int pontuacao;
    
    // Vidas restantes
    int vidas;
    
    /** Construtor da classe que cria todos os elementos e insere no mapa para processir com o jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public Engine(){
        
        // Cria o mapa
        this.mapa = new Mapa();
        
        // Cria o pacman
        this.pacman = new Pacman();

        // Cria os fantasmas
        this.Blinky = new FantasmaInteligente("blinky",this.pacman, 210);
        this.Pinky = new FantasmaInteligente("pinky",this.pacman, 211);
        this.Clyde = new Fantasma("clyde",209);
        this.Inky = new Fantasma("inky",208);
        
        // Define o nivel inicial como 1
        this.nivel = 1;
        
        // Inicia o jogo com 194 pacDots
        this.pacDotsRestantes = 194;
        
        // Inicia o jogo com 4 powerPills
        this.powerPillsRestantes = 4;
        
        // Inicializa os elementos do jogo
        this.elementosDoJogo = new ArrayList<>();
        
        this.inicializaElementos();
        
        // Inicializa todos os sons do jogo
        this.sons = new Som();
        this.sons.tocaSomIntro();
        
        // Inicializa o jogo com 3 vidas
        this.vidas = 3;
        
        // Inicializa a pontuacao
        this.pontuacao = 0;
        
    }
    
    /** Método que verifica o fim do jogo
     * @author Lucas Alves Roris
     * @return bool, true se o usuario tiver 0 vidas, e false caso não
     * @since jan 2022
     */
    public boolean isGameOver(){
        return (this.vidas == 0);
    }
    
    /** Método executavel quando o nivel atual foi vencido, indo para o proximo nivel, reinicializando as variaveis
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void passaNivel(){
        this.nivel += 1;
        
        // Diminui o tempo da power pill em 1 segundo por nivel
        if(this.tempoEfeitoPowerPill > 2000){
            this.tempoEfeitoPowerPill -= 1000;
        }
        this.inicializaElementos();
        
    }
    
    private int flagFruta = 0;
    /** Método que verifica a hora certa de colocar as frutas no mapa
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void verificaCondicoesColocarFruta(){
        
        // Verifica se ja esta na hora de colocar uma fruta
        if(this.flagFruta == 0 && (this.pacDotsRestantes == 135 || this.pacDotsRestantes == 58)){
            
            // Decide qual fruta e qual pontuacao
            Fruta fruta = null;
            switch(nivel%3){
                case 0:
                    fruta = new Fruta("Imagens/laranja.png",200*this.nivel-100);
                    break;
                case 1:
                    fruta = new Fruta("Imagens/cereja.png",200*this.nivel-100);
                    break;
                case 2:
                    fruta = new Fruta("Imagens/morango.png",200*this.nivel-100);
                    break;
            }
            
            // Sorteia uma posicao valida aleatoria 
            int randPos = (int) (Math.random() * this.mapa.posicoes.size());
            while(this.mapa.posicoes.get(randPos).getChar() == '#' || this.mapa.posicoes.get(randPos).getChar() == 'r'){
                randPos = (int) (Math.random() * this.mapa.posicoes.size());
            }
            
            // Adiciona a fruta ao tabuleiro
            fruta.setPos(randPos);
            this.elementosDoJogo.add(fruta);
            
            this.flagFruta = 1;
        } else {
            
            if(pacDotsRestantes == 134 || pacDotsRestantes == 57){
                this.flagFruta = 0;
            }
            
        }
        
    }
    
    public ArrayList<Elemento> getElementosDoJogo(){
        return this.elementosDoJogo;
    }
    
    /** Método que faz, caso necessario, um fantasma normal andar, só não é usado pelo pacman e o blinky
     * @param fantasma é o fantasma a verificar se pode andar ou não
     * @param timerFantasma é um timer que simboliza a sua velocidade
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void andarFantasmaNormal(Fantasma fantasma, long timerFantasma){
        
        if(System.currentTimeMillis() - timerFantasma >= 500){

                fantasma.andar(this.mapa);
                timerFantasma = System.currentTimeMillis();
        }
    }
    
    /** Método que faz o pacman andar, caso necessario
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void andarPacman(){
        
        if(System.currentTimeMillis() - this.timerPacman >= 300){

                this.pacman.andar(this.mapa);
                this.timerPacman = System.currentTimeMillis();
        }
    }
    
    private long tempoEfeitoPowerPill = 10000;
    
    private int fantasmasAzuis = 0;
    private int flagVidaExtra = 0;
    
    /** Método principal que faz os elementos interagirem no mapa
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void processaJogo(){
                    
        if(this.pacDotsRestantes == 0 && this.powerPillsRestantes == 0){
            this.passaNivel();
        }

        // Verifica se o numero de pacdots restantes atende os requisitos para aparecer uma fruta
        this.verificaCondicoesColocarFruta();

        // Conta o tempo de efeito da power pill
        if(this.fantasmasAzuis > 0 && System.currentTimeMillis() - timerPowerPill >= this.tempoEfeitoPowerPill){
            this.fantasmasAzuis = 0;
            Blinky.setStatus(1);
            Pinky.setStatus(1);
            Clyde.setStatus(1);
            Inky.setStatus(1);
        }
        
        // Blinky andou
        if(System.currentTimeMillis() - this.timerBlinky >= 400-100*(1-this.pacDotsRestantes/194)){

            this.Blinky.andar(mapa);
            this.timerBlinky = System.currentTimeMillis();
        }

        // Pinky andou
        if(System.currentTimeMillis() - this.timerPinky >= 400){

            this.Pinky.andar(mapa);
            this.timerPinky = System.currentTimeMillis();
        }

        // Clyde andou
        if(System.currentTimeMillis() - timerClyde >= 400){

            this.Clyde.andar(mapa);
            this.timerClyde = System.currentTimeMillis();
        }

        // Inky andou
        if(System.currentTimeMillis() - timerInky >= 400){

            this.Inky.andar(mapa);
            this.timerInky = System.currentTimeMillis();
        }
        
        for(Elemento elem : elementosDoJogo){

            if(elem.getPos() == pacman.getPos() && elem.getFoiComido() == 0){

                if(elem instanceof PacDot){

                    this.sons.tocaSomPacDot();
                    this.pontuacao += 10;
                    ((PacDot) elem).setFoiComido(1);
                    this.pacDotsRestantes -= 1;

                }
                if(elem instanceof Fruta){

                    this.sons.tocaSomFruta();
                    this.pontuacao += ((Fruta) elem).getPontuacao();
                    ((Fruta) elem).setFoiComido(1);

                }
                if(elem instanceof PowerPill){

                    this.sons.tocaSomPowerPill();
                    this.Blinky.setStatus(0);
                    this.Pinky.setStatus(0);
                    this.Clyde.setStatus(0);
                    this.Inky.setStatus(0);

                    ((PowerPill) elem).setFoiComido(1);

                    this.timerPowerPill = System.currentTimeMillis();
                    this.fantasmasAzuis = 1;
                    this.pontuacao += 50;
                    
                    this.powerPillsRestantes -= 1;

                }
                if(elem instanceof Fantasma){

                    if(((Fantasma) elem).getStatus() == 1){
                        this.sons.tocaSomMorte();
                        this.vidas -= 1;
                        this.pacman.resetarPosicao();
                        this.Blinky.resetarPosicao();
                        this.Pinky.resetarPosicao();
                        this.Clyde.resetarPosicao();
                        this.Inky.resetarPosicao();

                    } else {

                        this.sons.tocaSomComeFantasma();
                        ((Fantasma) elem).morreu();

                        this.pontuacao += 200*Math.pow(2, this.fantasmasAzuis-1);

                        if(this.fantasmasAzuis != 4)
                            this.fantasmasAzuis += 1;
                        //((Fantasma) elem).resetarPosicao();

                    }
                }
            }
        }

        // Pacman andou
        this.andarPacman();
        
        if(this.flagVidaExtra == 0 && this.pontuacao >= 10000){
            this.vidas += 1;
            this.flagVidaExtra = 1;
        }

    }
    
    public int getPontuacao(){
        return this.pontuacao;
    }
    
    public int getVidas(){
        return this.vidas;
    }
    
    public int getNivel(){
        return this.nivel;
    }
    
    /** Método que inicializa/reinicializa as variaveis principais do sistema
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void inicializaElementos(){
        
        // Inicializa as posicoes de cada personagem
        this.pacman.resetarPosicao();
        this.Blinky.resetarPosicao();
        this.Pinky.resetarPosicao();
        this.Clyde.resetarPosicao();
        this.Inky.resetarPosicao();

        this.elementosDoJogo.clear();
        for(int i = 0 ; i < this.mapa.posicoes.size() ; i++){

            switch(this.mapa.posicoes.get(i).getChar()){
                case '.':
                    PacDot pacdot = new PacDot();
                    pacdot.setPos(i);
                    this.elementosDoJogo.add(pacdot);
                    break;
                case 'O':
                    PowerPill powerPill = new PowerPill();
                    powerPill.setPos(i);
                    this.elementosDoJogo.add(powerPill);
                    break;
            }

        }

        // Insere os personagens como elementos do jogo
        this.elementosDoJogo.add(pacman);

        this.elementosDoJogo.add(Blinky);
        this.elementosDoJogo.add(Pinky);
        this.elementosDoJogo.add(Clyde);
        this.elementosDoJogo.add(Inky);

        this.Blinky.setStatus(1);
        this.Pinky.setStatus(1);
        this.Clyde.setStatus(1);
        this.Inky.setStatus(1);

        this.pacDotsRestantes = 194;

        this.powerPillsRestantes = 4;
        
        // Inicializa os timers com tempos distintos para deixxar mais dinamico
        this.timerBlinky = System.currentTimeMillis() + (int) (Math.random() * 5000);
        this.timerPinky = System.currentTimeMillis() + (int) (Math.random() * 5000);
        this.timerClyde = System.currentTimeMillis() + (int) (Math.random() * 5000);
        this.timerInky = System.currentTimeMillis() + (int) (Math.random() * 5000);
        this.timerPacman = System.currentTimeMillis();
    }
    
    public Mapa getMapa(){
        return this.mapa;
    }
    
    /** Método que possibilita a leitura do teclado a qualquer momento do jogo
     * @param scene é a scene atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void ativaLeituraDoTeclado(Scene scene){
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent e) {
                if (e.getCode() == KeyCode.RIGHT) {
                    Engine.this.pacman.setDirecaoParaAndar(0);
                }
                if (e.getCode() == KeyCode.LEFT) {
                    Engine.this.pacman.setDirecaoParaAndar(1);
                }
                if (e.getCode() == KeyCode.UP) {
                    Engine.this.pacman.setDirecaoParaAndar(2);
                }
                if (e.getCode() == KeyCode.DOWN) {
                    Engine.this.pacman.setDirecaoParaAndar(3);
                }
            }
        });
    }

}
