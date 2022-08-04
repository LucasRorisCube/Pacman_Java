package Engine;

import java.util.ArrayList;

/** Classe que representa o unico mapa do jogo
* @author Lucas Alves Roris
* @since jan 2022
*/
public class Mapa {
    
    private final ArrayList<String> templateMapaS;
    private final ArrayList<Character> templateMapa;
    
    public ArrayList<Node> posicoes;
    
    /** Construtor da classe que pega o template do mapa e gera um grafo para auxiliar na movimentacao dos personagens
     * @author Lucas Alves Roris
     * @since jan 2022
     */
    public Mapa(){
        
        this.templateMapaS = new ArrayList<>();
        this.templateMapa = new ArrayList<>();
        this.posicoes = new ArrayList<>();
        
        // Tempate de mapa tirado da internet;
        this.templateMapaS.add("####################");
        this.templateMapaS.add("#........##........#");
        this.templateMapaS.add("#O##.###.##.###.##O#");
        this.templateMapaS.add("#..................#");
        this.templateMapaS.add("#.##.#.######.#.##.#");
        this.templateMapaS.add("#....#...##...#....#");
        this.templateMapaS.add("####.###.##.###.####");
        this.templateMapaS.add("####.#........#.####");
        this.templateMapaS.add("####.#.##rr##.#.####");
        this.templateMapaS.add("####.#.#rrrr#.#.####");
        this.templateMapaS.add(".......#rrrr#.......");
        this.templateMapaS.add("####.#.#rrrr#.#.####");
        this.templateMapaS.add("####.#.######.#.####");
        this.templateMapaS.add("####.#........#.####");
        this.templateMapaS.add("####.#.######.#.####");
        this.templateMapaS.add("#........##........#");
        this.templateMapaS.add("#O##.###.##.###.##O#");
        this.templateMapaS.add("#..#............#..#");
        this.templateMapaS.add("##.#.#.######.#.#.##");
        this.templateMapaS.add("#....#...##...#....#");
        this.templateMapaS.add("#.######.##.######.#");
        this.templateMapaS.add("#..................#");
        this.templateMapaS.add("####################");
        
        int currentCoordX = 15;
        int currentCoordY = 10;
        
        // Transforma o mapa em um array de characteres
        for(int i = 0 ; i < this.templateMapaS.size() ; i++){
            for( int j = 0 ; j < this.templateMapaS.get(i).length() ; j++){
                this.templateMapa.add(this.templateMapaS.get(i).charAt(j));
            }
        }

        for(int i = 0 ; i < this.templateMapa.size() ; i++){
            // Caso seja uma posicao impossivel de um personagme estar, nao tem vizinhos
            if('#' == this.templateMapa.get(i) || 'i' == this.templateMapa.get(i)){
                Node node = new Node(currentCoordX,currentCoordY,-1,-1,-1,-1,-1,-1,-1,-1, this.templateMapa.get(i));
                posicoes.add(node);
            
            } else {
                int vizinhoR = -1;
                int vizinhoL = -1;
                int vizinhoU = -1;
                int vizinhoD = -1;
                
                int vizinhoFR = -1;
                int vizinhoFL = -1;
                int vizinhoFU = -1;
                int vizinhoFD = -1;
                
                if(!( i >= 20 && '#' == this.templateMapa.get(i-20) )){
                    if(!('r' == this.templateMapa.get(i-20))){
                        vizinhoU = i-20;
                    }
                    vizinhoFU = i-20;
                }
                
                if(!(i+20 < this.templateMapa.size() && '#' == this.templateMapa.get(i+20))){
                    if(!('r' == this.templateMapa.get(i+20))){
                        vizinhoD = i+20;
                    }
                    vizinhoFD = i+20;
                }
                
                if((i+1)%20 == 0){
                    vizinhoR = i-19;
                } else {
                    if(!('#' == this.templateMapa.get(i+1))){
                        if(!('r' == this.templateMapa.get(i+1))){
                            vizinhoR = i+1;
                        }
                        vizinhoFR = i+1;
                    }
                }
                
                if(i%20 == 0){
                    vizinhoL = i+19;
                } else {
                    if(!('#' == this.templateMapa.get(i-1))){
                        if(!('r' == this.templateMapa.get(i-1))){
                            vizinhoL = i-1;
                        }
                        vizinhoFL = i-1;
                    }
                }
                
                Node node = new Node(currentCoordX,currentCoordY,vizinhoR,vizinhoL,vizinhoU,vizinhoD,vizinhoFR,vizinhoFL,vizinhoFU,vizinhoFD,this.templateMapa.get(i));
                posicoes.add(node);

            }
            
            currentCoordX += 35;
            if(currentCoordX >= 700){
                currentCoordX = 15;
                currentCoordY += 35;
            }
            
        }
        
    }
    
    
}
