import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.*;

public class MarcadorDeReuniao {
    /* Classe que têm, como objetivo, marcar uma reunião. De forma que, se torne posssível 
    a organização dos horários de cada participante, possibilitando a visualização
    qual o melhor horário para se marcar uma reunião */
    
    //Atributos da classe
    private Reuniao reuniao;
    
   /************************************************************************************
    * Define os participantes da reunião. Recebe as datas e as listas dos participantes*
    *                                                                                  *
    * -> Adiciona as datas de inicio e fim que a reuniao será realizada.               *
    ************************************************************************************/
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) throws DataException, ParticipanteException{
      
        if(this.reuniao.getParticipantes().size() == 0) throw new ParticipanteException ("  OPS! Você quer marcar uma reunião sem participantes.\n");
        if(!dataInicial.isBefore(dataFinal)) throw new DataException("  OPS! A data de inicio e fim da reunião nao esta em ordem cronologica.\n");

        //checa se todos os participantes tem horario disponivel no horario da reuniao
        for(String participante : listaDeParticipantes){
            String [] separaDados = participante.split("\\*");
            for(Participante p : this.reuniao.getAgendaParticipantes()){
                if(p.getNome().equals(separaDados[0]) && this.reuniao.getParticipantes().containsKey(separaDados[1])){
                    if(p.getInicio().toLocalDate().isAfter(dataFinal) || (p.getFim().toLocalDate().isBefore(dataInicial))) throw new DataException("  OPS! " + p.getNome() + " com ID = " + p.getID() + " não possuí disponibilidade para estar na reunião." );
                }
            }
        }

        this.reuniao.setInicio(dataInicial);
        this.reuniao.setFim(dataFinal);
    }
   

   /*********************************************************************************************
    * Define a disponibilidade de cada pessoa para a reunião. Recebe uma String do participante *
    * e a data inicial e final em que está com horário disponível(dia e horario)                *
    *                                                                                           *
    *-> Adiciona um Participante na reuniao.                                                    *
    ********************************************************************************************/
    public void indicaDisponibilidade(String participante, LocalDateTime inicio, LocalDateTime fim) throws ParticipanteException{
    //OBS: String participante recebe: nome+"*"+id
        String [] separaDados = participante.split("\\*");
        
        // Verifica a existencia do Participante na Reuniao
        try{
            if(this.reuniao == null){
                this.reuniao = new Reuniao();
            }

            else{
                //verifica se esse participante ja foi adicionado na reuniao
                for (Map.Entry<String,String> id : this.reuniao.getParticipantes().entrySet()) {
                    if(id.getKey().equals(separaDados[1])) throw new ParticipanteException("  OPS! O participante " + id.getValue() + " com ID = "+ id.getKey() + " ja foi adicionado na reuniao.");
                }
            }

            if(inicio.isBefore(fim) == false) throw new DataException("  OPS! As datas de disponibilidade do participante não estão em ordem cronoçógina.");
            
            this.reuniao.getParticipantes().put(separaDados[1], separaDados[0]);
            Participante obj_participante = new Participante(inicio, fim, separaDados[0], separaDados[1]);
            this.reuniao.setAgendaParticipantes(obj_participante);
        }
    }

    /***********************************************************************************************************
    Mostra quais horários foram escolhidos pelos funcionários e destaca os que mais de uma pessoa está disponível.
    ************************************************************************************************************ */
    public void mostraSobreposicao() {

        HashMap<String, LocalDateTime> inicio = new HashMap<>(); // horarios iniciais de todos os participantes
        HashMap<String, LocalDateTime> fim = new HashMap<>(); // horarios finais de todos os participantes

        //inicializa os hashs
        for(Participante participante : this.reuniao.getAgendaParticipantes()){
            inicio.put(participante.getID(), LocalDateTime.of(participante.getInicio().getYear(), participante.getInicio().getMonthValue(), participante.getInicio().getDayOfMonth(), participante.getInicio().getHour(), participante.getInicio().getMinute()));
            fim.put(participante.getID(), LocalDateTime.of(participante.getFim().getYear(), participante.getFim().getMonth(), participante.getFim().getDayOfMonth(), participante.getFim().getHour(), participante.getFim().getMinute()));
        }
        
        LocalDateTime start = inicio.get(this.reuniao.getAgendaParticipantes().get(0).getID());
        LocalDateTime finish = fim.get(this.reuniao.getAgendaParticipantes().get(0).getID());

        for(Participante participante : this.reuniao.getAgendaParticipantes()){ //ANO
            if(participante.getInicio().getYear() > start.getYear()) start = participante.getInicio();
            if(participante.getFim().getYear() < finish.getYear()) finish = participante.getFim();
        }
        
        for(Participante participante : this.reuniao.getAgendaParticipantes()){ //TEMPO
            if(participante.getInicio().getHour() < start.getHour()) start = LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), participante.getInicio().getHour(), start.getMinute());
            if(participante.getFim().getHour() > finish.getHour()) finish = LocalDateTime.of(finish.getYear(), finish.getMonth(), finish.getDayOfMonth(), participante.getFim().getHour(), finish.getMinute());
        }
                   
        System.out.println("============================================================");

        System.out.println( "INICIO DA DISPONIBILIDADE: " +  start.getDayOfMonth() + "/" + start.getMonthValue() + "/" + start.getYear() + " as " + start.getHour() + "h" + start.getMinute() + "min" );
        System.out.println( "FIM DA DISPONIBILIDADE: " +  finish.getDayOfMonth() + "/" + finish.getMonthValue() + "/" + finish.getYear() + " as " + finish.getHour() + "h" + finish.getMinute() + "min");
        
        System.out.println( "============================================================");
       
        //Verifica se todos os participantes podem estar presente no horario determinado
        for(Participante participante : this.reuniao.getAgendaParticipantes()){ //TEMPO
            if(participante.getInicio().isBefore(start) && (participante.getFim().isBefore(finish) || participante.getFim().isEqual(finish))){
                System.out.println("  ATENÇÃO: " + participante.getNome() +  " com ID "  + participante.getID()  + " nao possui disponibilidade no mesmo horario que os outros participantes.");
            }
            else if(participante.getInicio().isAfter(finish) && (participante.getFim().isAfter(finish) || participante.getInicio().isEqual(finish))){
                System.out.println( "  ATENÇÃO: "  + participante.getNome()  + " com ID "  + participante.getID()  + " nao possui disponibilidade no mesmo horario que os outros participantes." );
            }
        }
    }
}
