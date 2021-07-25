
import java.util.*;

import javax.print.DocFlavor.STRING;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Reuniao {
    
    //Atributos da classe
    private LocalDate inicioReuniao; //data que ira comecar a reuniao
    private LocalDate finalReuniao; //data que ira encerrar a reuniao
    private HashMap <String, String> dadosParticipantes; //recebe na primeira posicao o ID e em segundo o NOME do participante
    
    //Construtor da classe
    public Reuniao(LocalDate inicioReuniao, LocalDate finalReuniao, HashMap<String,String> lista_participantes){

        this.inicioReuniao = inicioReuniao;
        this.finalReuniao = finalReuniao;
        this.dadosParticipantes = lista_participantes;
        
    }

    public void setParticipantes(HashMap<String, String>participantes){
        this.dadosParticipantes = participantes;
    }

    public void setInicio(LocalDate inicio){ 
        this.inicioReuniao = inicio;
    }
    
    public void setFim(LocalDate fim) {
        this.finalReuniao = fim;
    }
    
    public HashMap<String, String> getParticipantes() {
        return this.dadosParticipantes;
    }

    public LocalDate getInicioReuniao() {
        return this.inicioReuniao;
    }

    public LocalDate getFinalReuniao() {
        return this.finalReuniao;
    }
}
