package InterfaceGrafica;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import Engine.Engine;
import ElementosDoSistema.Elemento;

/** Classe que constroi toda a interface grafica do jogo
* @author Lucas Alves Roris
* @since jan 2022
*/
public class TelaJogo extends Application {
    
    private final int tamX = 700;
    private final int tamY = 900;
    private GraphicsContext gc;
    private Scene scene;
    
    Image imgFundo = new Image("Imagens/fundo.png", 700, 800, false, false);
    Image imagePacDot = new Image("Imagens/pacDot.png",5,5,false,false);
    
    Engine engine;
    
    /** Método que inicializa a interface grafica e a engine
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    @Override
    public void start(Stage primaryStage){

        // Criando variáveis necessárias para a tela
        Group root = new Group();
        scene = new Scene(root, tamX, tamY);
        Canvas canvas = new Canvas(tamX, tamY);        
        gc = canvas.getGraphicsContext2D();          
        root.getChildren().add(canvas);
        
        // Customizando a tela
        primaryStage.setTitle("Pacman");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("Imagens/icone.png"));

        // Cria a engine para controlar o jogo
        this.engine = new Engine();
        
        // Sincroniza a engine com o cenario
        engine.ativaLeituraDoTeclado(scene);
        
        // Desenha um fundo preto
        gc.fillRect(0, 0, this.tamX, this.tamY);
        
        // Inicia o loop do jogo
        game_loop(primaryStage);
        
    }
    
    /** Método principal que roda o loop do jogo
     * @author Lucas Alves Roris
     * @param primaryStage é o stage atual do jogo
     * @since jan 2022
     */
    public void game_loop(Stage primaryStage){
        
        // Inicia a animacao do jogo
        AnimationTimer an;
        an = new AnimationTimer() 
        {   
            
            private long timerToEnd = -1;
            /** Método que fica em loop, fazendo o jogo avançar
            * @author Lucas Alves Roris
            * @since jan 2022
            */
            @Override
            public void handle(long now) 
            {   
                
                // Enquanto o jogo não tiver terminado
                if(!engine.isGameOver()){
                    
                    // A engine processa o jogo
                    engine.processaJogo();
                    
                    // Printa o fundo
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, tamX, tamY);
                    gc.drawImage(imgFundo, 0, 0);

                    // Printa todos os elementos
                    for(Elemento elem : engine.getElementosDoJogo()){
                        elem.drawInMap(gc, engine.getMapa());
                    }
                    
                    // Print as informacoes
                    gc.setFont(new Font("Verdana", 35));
                    gc.setFill(Color.WHITE);
                    gc.fillText("Pontuacao: " + String.valueOf(engine.getPontuacao()) + "  Vidas: " + String.valueOf(engine.getVidas()) + " Nivel:  " + String.valueOf(engine.getNivel()), 20, 860);
                    
                } else {
                    
                    // Se o jogo acabou, exibe a tela de game over
                    gc.setFill(Color.GRAY);
                    gc.setStroke(Color.YELLOW);
                    
                    gc.strokeRoundRect(95, 195, 510, 410,100,100);
                    gc.fillRoundRect(100, 200, 500, 400,100,100);
                    gc.setFont(new Font("Arial", 40));
                    gc.setFill(Color.WHITE);
                    gc.fillText("Voce perdeu!", 225, 300);
                    gc.fillText("Pontuacao maxima: " + String.valueOf(engine.getPontuacao()) , 140, 400);
                    gc.fillText("Obrigado por jogar", 175, 500);
                    
                    if(this.timerToEnd == -1){
                        this.timerToEnd = System.currentTimeMillis();
                    } else {
                        if(System.currentTimeMillis() - this.timerToEnd > 3000){
                            System.exit(0);
                        }
                    }
                    

                }
            }
        };
        
        // Inicia a animacao
        an.start();
        primaryStage.show();
    }
    
}