
import java.util.*;
import java.time.LocalDate;


public class Reuniao {
    
    //Atributos da classe
    private List<String> participantes; //Colecao de participantes que estarao na reuniao
    private LocalDate inicioReuniao; //data que ira comecar a reuniao
    private LocalDate finalReuniao; //data que ira encerrar a reuniao

    //Construtor da classe
    public Reuniao(LocalDate inicioReuniao, LocalDate finalReuniao, List<String> lista_participantes){

        this.inicioReuniao = inicioReuniao;
        this.finalReuniao = finalReuniao;
        this.participantes = lista_participantes;
    }


}
