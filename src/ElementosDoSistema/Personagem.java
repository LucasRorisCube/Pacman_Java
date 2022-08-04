package ElementosDoSistema;

/** Classe que representa um elemento que pode se movimentar
* @author Lucas Alves Roris
* @since jan 2022
*/
public class Personagem extends Elemento {
    
    protected int direcaoParaAndar = -1;
    
    /** Construtor da classe que inicialia o elemento com a sua imagem e tamanho
     * @param URLImage é o caminho até a imagem
     * @param tamX é o tamanho do elemento no eixo X
     * @param tamY é o tamanho do elemento no eixo Y
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    Personagem(String URLImage, int tamX, int tamY){
        super(URLImage,tamX,tamY);
    }
    
    public void setDirecaoParaAndar(int direcao){
        this.direcaoParaAndar = direcao;
    }   
    
}
