
import java.time.LocalDateTime;

public class Participante {
    
    //Atributos da classe
    private String nome;
    private LocalDateTime inicio; 
    private LocalDateTime fim; 

    /* Construtor da classe */
    public Participante(LocalDateTime dataInicial, LocalDateTime dataFinal, String nome) {
        this.nome = nome;
        this.inicio = dataInicial;
        this.fim = dataFinal; //pesquisar se pode isso
    }      

    /* Devolve o horario e a data inicial disponivel */
    public LocalDateTime getInicio() {
        return this.inicio;
    }

    /* Devolve o horario e a data final disponivel */
    public LocalDateTime getFim() {
        return this.fim;
    }

    /* Devolve o nome do participante da reuniao */
    public String getNome() {
        return this.nome;
    }
}
