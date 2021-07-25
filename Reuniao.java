
import java.util.*;
import java.time.LocalDate;


public class Reuniao {
    
    //Atributos da classe
    private LocalDate inicioReuniao; //data que ira comecar a reuniao
    private LocalDate finalReuniao; //data que ira encerrar a reuniao
    private HashMap <String, String> dadosParticipantes; //recebe na primeira posicao o ID e em segundo o NOME do participante
    private List <Participante> agendaParticipantes; //lista de participantes

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

    //Adiciona o "participante" na lista agentaParticipantes
    public void setAgendaParticipantes(Participante participante){
        this.agendaParticipantes.add(participante);
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
