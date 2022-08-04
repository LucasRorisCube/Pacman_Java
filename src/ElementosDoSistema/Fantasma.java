package ElementosDoSistema;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Engine.Mapa;

/** Classe que representa um fantasma
* @author Lucas Alves Roris
* @since jan 2022
*/
public class Fantasma extends Personagem {
    
    private int status;
    private final String nome;
    private final int posToReset;
    protected ArrayList<Integer> pathToFollow;
    
    /** Construtor da classe que criar o fantasma
     * @param nome é o nome da imagem do fantasma
     * @param posToReset é a posicao que ele vai voltar quando morrer
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public Fantasma(String nome, int posToReset){
        
        super("Imagens/"  + nome + "R.png", 30, 30);
        this.nome = nome;
        this.status = 1;
        this.pathToFollow = new ArrayList<>();
        this.posToReset = posToReset;
        
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    
    public int getStatus(){
        return this.status;
    }
    
    /** Método que sinaliza que o fantasma morreu, então ele o reseta
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void morreu(){
        this.resetarPosicao();
    }
    
    public void resetarPosicao(){
        this.setPos(this.posToReset);
        this.pathToFollow.clear();
    }
    
    /** Método que faz o fantasma andar
     * @param mapa é o mapa atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public void andar(Mapa mapa){
        
        
        if(this.pathToFollow.isEmpty()){
            int randPos = (int) (Math.random() * mapa.posicoes.size());
            while(mapa.posicoes.get(randPos).getChar() == '#' || mapa.posicoes.get(randPos).getChar() == 'i'){
                randPos = (int) (Math.random() * mapa.posicoes.size());
            }
            
            this.perseguir(mapa, randPos);
        }
        int posToGo = this.pathToFollow.get(this.pathToFollow.size()-1);
        this.pathToFollow.remove(this.pathToFollow.size()-1);
        
        this.mudaDirecaoImagem(mapa, posToGo);
        
        this.setPos(posToGo);
    }
    
    /** Método que muda a imagem com relacao a direcao andada
     * @param mapa é o mapa atual do jogo
     * @param posToGo é a proxima posicao a ir
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    protected void mudaDirecaoImagem(Mapa mapa, int posToGo){
        
        ArrayList<Integer> vizinhos = mapa.posicoes.get(this.getPos()).getVizinhosFantasmas();
        for( int i = 0 ; i < 4 ; i++){
            if(vizinhos.get(i) == posToGo){
                switch(i){
                    case 0:
                        this.setImage(new Image("Imagens/" + this.getNome() + "R.png",30,30,false,false));
                        break;
                    case 1:
                        this.setImage(new Image("Imagens/" + this.getNome() + "L.png",30,30,false,false));
                        break;
                    case 2:
                        this.setImage(new Image("Imagens/" + this.getNome() + "U.png",30,30,false,false));
                        break;
                    case 3:
                        this.setImage(new Image("Imagens/" + this.getNome() + "D.png",30,30,false,false));
                        break;
                }
            }
        }
    }
    
    /** Método que desenha o elemento no mapa
     * @param gc é o GraphicsContext usado na interface gráfica
     * @param mapa é o mapa atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    @Override
    public void drawInMap(GraphicsContext gc, Mapa mapa){
        if(this.getFoiComido() == 0){
            if(this.status == 0){
                gc.drawImage(new Image("Imagens/fantasmaAzul.png",30,30,false,false), mapa.posicoes.get(pos).getCoordX()-15,mapa.posicoes.get(pos).getCoordY()-15);
            } else {
                gc.drawImage(this.getImage(), mapa.posicoes.get(pos).getCoordX()-15,mapa.posicoes.get(pos).getCoordY()-15);
            }
        }
    }
    
    /** Método que determina o menor caminho entre o fantasma e o ponto escolhido
     * @param mapa é o mapa atual do jogo
     * @param posToGo é a posicao que se quer ir
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    protected void perseguir(Mapa mapa, int posToGo){
        
        int prev[] = new int[mapa.posicoes.size()];
        for(int i = 0; i < prev.length; i++){
            prev[i] = -1;
            
        }
        
        boolean visitados[] = new boolean[mapa.posicoes.size()];
        
        if(posToGo == this.pos){
            this.pathToFollow.add(this.pos);
            
            return;
        }
        visitados[this.pos] = true;
        
        LinkedList<Integer> lista = new LinkedList<>();
        lista.add(this.pos);
        
        int achou = 0;
        while(!lista.isEmpty()){
            
            int atual = lista.poll();
            
            if(atual == posToGo){
                achou = 1;
                break;
            }
            visitados[atual] = true;
            
            ArrayList<Integer> vizinhos = mapa.posicoes.get(atual).getVizinhosFantasmas();
            
            for(int posVizinho : vizinhos){
                if(posVizinho != -1 && visitados[posVizinho] == false){
                    lista.add(posVizinho);
                    prev[posVizinho] = atual;
                }
            }
        }
        
        this.pathToFollow.add(posToGo);
        int auxPos = posToGo;
        while(prev[auxPos] != this.pos){
            this.pathToFollow.add(prev[auxPos]);
            auxPos = prev[auxPos];
        }

    }
}
