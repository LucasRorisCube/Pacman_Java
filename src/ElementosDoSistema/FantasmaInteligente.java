package ElementosDoSistema;

import Engine.Mapa;

/** Classe que representa um fantasma com inteligencia
* @author Lucas Alves Roris
* @since jan 2022
*/
public class FantasmaInteligente extends Fantasma{
    
    private final Pacman pacmanParaPerseguir;
    
    /** Construtor da classe do fantasma inteligente
     * @param nome é o nome do fantasma
     * @param pacmanParaPerseguir é o pacman que vai ser perseguido
     * @param posToReset é a posicao que ele voltara ao morrer
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public FantasmaInteligente(String nome, Pacman pacmanParaPerseguir, int posToReset){
        super(nome, posToReset);
        this.pacmanParaPerseguir = pacmanParaPerseguir;
    }
    
    private int flagAux = 0;
    
    /** Método que faz o fantasma andar
     * @param mapa é o mapa atual do jogo
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    @Override
    public void andar(Mapa mapa){
        
        if(this.getStatus() == 1){
            
            this.perseguir(mapa, pacmanParaPerseguir.pos);
            this.flagAux = 0;
            
        } else {
            
            if(this.flagAux == 0){
                this.pathToFollow.clear();
                this.flagAux = 1;
            }
            
            if(this.pathToFollow.isEmpty()){
                int randPos = (int) (Math.random() * mapa.posicoes.size());
                while(mapa.posicoes.get(randPos).getChar() == '#' || mapa.posicoes.get(randPos).getChar() == 'i'){
                    randPos = (int) (Math.random() * mapa.posicoes.size());
                }
                
                this.perseguir(mapa, randPos);
            }
            
            
        }
        
        
        int posToGo = this.pathToFollow.get(this.pathToFollow.size()-1);
        this.pathToFollow.remove(this.pathToFollow.size()-1);
        
        this.mudaDirecaoImagem(mapa, posToGo);
        
        this.setPos(posToGo);
    }
}
