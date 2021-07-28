
import java.time.LocalDateTime;

public class Reserva{

    //atributos da classe
    private String nome; //sala que vai ser reservada
    private LocalDateTime start; //tempo do inicio da reserva
    private LocalDateTime finish; //tempo do termino da reserva

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

    public String toString(){
        return  ">> Inicio: " + this.start.getDayOfMonth() + "/" + this.start.getMonthValue() +  "/" + this.start.getYear() +
                " as " + this.start.getHour() + "h" + this.start.getMinute() + "min"
                + "  Fim: " + this.finish.getDayOfMonth() + "/" + this.finish.getMonthValue() +  "/" + this.finish.getYear() + 
                " as " + this.finish.getHour() + "h" + this.finish.getMinute() + "min";
    }

    public String getNome(){
        return this.nome;
    }

    public LocalDateTime getInicio(){
        return this.start;
    }

    public LocalDateTime getFim(){
        return this.finish;
    }

}
