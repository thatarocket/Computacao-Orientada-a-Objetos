
import java.time.LocalDateTime;


public class Reserva{

    //atributos da classe
    public String nome; //sala que vai ser reservada
    public LocalDateTime start; //tempo do inicio da reserva
    public LocalDateTime finish; //tempo do termino da reserva

    //construtor da classe
    public Reserva(String nome, LocalDateTime start, LocalDateTime finish){

        this.nome = nome;
        this.start = start;
        this.finish = finish;
    }

    /*********************************************
    * Cria uma sala e adiciona na lista de Salas *
    **********************************************/
    public Sala criaSala(String nome, String local, int capacidade, String observacoes){
        
        Sala nova_sala = new Sala(nome, local, capacidade, observacoes);
    
        return nova_sala;
    }

    /****************************************
    * Retorna o tempo do inicio da reserva *
    ****************************************/
    public LocalDateTime inicio(){
        return this.start;
    }

    /****************************************
    * Retorna o tempo do termino da reserva *
    *****************************************/
    public LocalDateTime fim(){
        return this.finish;
    }

    public String toString(){
        return "Nome da reserva: " + this.nome + "  Inicio: " + this.start + "  Fim: " + this.finish;
    }

}
