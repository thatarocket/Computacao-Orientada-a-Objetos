import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.*;

public class MarcadorDeReuniao {
    /* Classe que têm, como objetivo, marcar uma reunião. De forma que, se torne posssível 
    a organização dos horários de cada participante, possibilitando a visualização
    qual o melhor horário para se marcar uma reunião */
    
    //Atributos da classe
    private Reuniao reuniao;
    
    public MarcadorDeReuniao() {
        this.reuniao = new Reuniao();
    }

    public Reuniao getReuniao(){
        return this.reuniao;
    }

   /************************************************************************************
    * Define os participantes da reunião. Recebe as datas e as listas dos participantes*
    *                                                                                  *
    * -> Adiciona as datas de inicio e fim que a reuniao será realizada.               *
    ************************************************************************************/
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes) throws DataException{

        if(this.reuniao == null) this.reuniao = new Reuniao();
        if(dataInicial.isAfter(dataFinal)) throw new DataException("  OPS! A data de inicio e fim da reuniao nao esta em ordem cronologica.\n");

        this.reuniao.setInicio(dataInicial);
        this.reuniao.setFim(dataFinal);
    }

    public void cancelarReuniao(){
        LocalDate n = null;
        this.reuniao.setInicio(n);
        this.reuniao.setFim(n);
    }
   

   /*********************************************************************************************
    * Define a disponibilidade de cada pessoa para a reunião. Recebe uma String do participante *
    * e a data inicial e final em que está com horário disponível(dia e horario)                *
    *                                                                                           *
    *-> Adiciona um Participante na reuniao.                                                    *
    ********************************************************************************************/
    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
   
        // Verifica a existencia da Reuniao
        if(this.reuniao == null){
            this.reuniao = new Reuniao();
        }
                
        Participante obj_participante = new Participante(inicio, fim, participante);
        this.reuniao.setAgendaParticipantes(obj_participante);
    }

    /***********************************************************************************************************
    Mostra quais horários foram escolhidos pelos funcionários e destaca os que mais de uma pessoa está disponível.
    ************************************************************************************************************ */
    public void mostraSobreposicao() {

        ArrayList <LocalDateTime> inicio = new ArrayList<LocalDateTime>(); // horarios iniciais de todos os participantes
        ArrayList <LocalDateTime> fim = new ArrayList<LocalDateTime>(); // horarios finais de todos os participantes

        if(this.reuniao.getAgendaParticipantes().size() <= 0){
            System.out.println(" ATENCAO: Nao ha sobreposicoes de horarios.");
            return;
        }
        
        else {
            for(Participante participante : this.reuniao.getAgendaParticipantes()){
                inicio.add(LocalDateTime.of(participante.getInicio().getYear(), participante.getInicio().getMonthValue(), participante.getInicio().getDayOfMonth(), participante.getInicio().getHour(), participante.getInicio().getMinute()));
                fim.add(LocalDateTime.of(participante.getFim().getYear(), participante.getFim().getMonth(), participante.getFim().getDayOfMonth(), participante.getFim().getHour(), participante.getFim().getMinute()));
            }
        
            LocalDateTime start = inicio.get(0);
            LocalDateTime finish = fim.get(0);
            
            for(Participante participante : this.reuniao.getAgendaParticipantes()){ //ANO
                if(participante.getInicio().getYear() > start.getYear()) start = participante.getInicio();
                if(participante.getFim().getYear() < finish.getYear()) finish = participante.getFim();
            }
            
            for(Participante participante : this.reuniao.getAgendaParticipantes()){ //TEMPO
                if(participante.getInicio().getHour() < start.getHour()) start = LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), participante.getInicio().getHour(), start.getMinute());
                if(participante.getFim().getHour() > finish.getHour()) finish = LocalDateTime.of(finish.getYear(), finish.getMonth(), finish.getDayOfMonth(), participante.getFim().getHour(), finish.getMinute());
            }
                    
            System.out.println("============================================================");
        
            //Verifica se todos os participantes podem estar presente no horario determinado
            boolean semDisp = false; //sem horarios em conjunto
            for(Participante participante : this.reuniao.getAgendaParticipantes()){ //TEMPO
                if(participante.getInicio().isBefore(start) && (participante.getFim().isBefore(finish) || participante.getFim().isEqual(finish))){
                    System.out.println("  ATENCAO: " + participante.getNome() + " nao possui disponibilidade no mesmo horario que os outros participantes.");
                    semDisp = true;
                }
                else if(participante.getInicio().isAfter(finish) && (participante.getFim().isAfter(finish) || participante.getInicio().isEqual(finish))){
                    System.out.println( "  ATENCAO: "  + participante.getNome()  + " nao possui disponibilidade no mesmo horario que os outros participantes." );
                    semDisp = true;
                }
            }
            if(!semDisp) {
                System.out.println( "INICIO DA DISPONIBILIDADE: " +  start.getDayOfMonth() + "/" + start.getMonthValue() + "/" + start.getYear() + " as " + start.getHour() + "h" + start.getMinute() + "min" );
                System.out.println( "FIM DA DISPONIBILIDADE: " +  finish.getDayOfMonth() + "/" + finish.getMonthValue() + "/" + finish.getYear() + " as " + finish.getHour() + "h" + finish.getMinute() + "min");
                System.out.println( "============================================================");
            }
            else {
            System.out.println("============================================================"); 
            }
        }
    }
}
