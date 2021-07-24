
import java.util.*;
import java.time.LocalDate;


public class Reuniao {
    
    //Atributos da classe
    private List<Participante> lista_participantes; //lista de participantes que estarao na reuniao
    private LocalDate inicioReuniao; //data que ira comecar a reuniao
    private LocalDate finalReuniao; //data que ira encerrar a reuniao

    //Construtor da classe
    public Reuniao(LocalDate inicioReuniao, localDate finalReuniao){

        this.inicioReuniao = inicioReuniao;
        this.finalReuniao = finalReuniao;
        this.lista_participantes = new LinkedList<>();
    }


}
