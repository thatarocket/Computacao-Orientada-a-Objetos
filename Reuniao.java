
import java.util.*;
import java.time.LocalDate;


public class Reuniao {
    
    //Atributos da classe
    private LocalDate inicioReuniao; //data que ira comecar a reuniao
    private LocalDate finalReuniao; //data que ira encerrar a reuniao
    private HashMap <String, String> dadosParticipantes; //recebe na primeira posicao o ID e em segundo o NOME do participante
    private List <Participante> agendaParticipantes; //lista de participantes

    public void setParticipantes(HashMap<String, String> participantes){
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
        if(this.agendaParticipantes == null) this.agendaParticipantes = new LinkedList<>();
        this.agendaParticipantes.add(participante);
    }
    
    public HashMap<String, String> getParticipantes() {
        if(this.dadosParticipantes == null) this.dadosParticipantes = new HashMap<>();
        return this.dadosParticipantes;
    }

    public List<Participante> getAgendaParticipantes() {
        if(this.agendaParticipantes == null) this.agendaParticipantes = new LinkedList<>();
        return this.agendaParticipantes;
    }

    public LocalDate getInicioReuniao() {
        return this.inicioReuniao;
    }

    public LocalDate getFinalReuniao() {
        return this.finalReuniao;
    }
}
