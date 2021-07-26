import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MarcadorDeReuniao {
    /* Classe que têm, como objetivo, marcar uma reunião. De forma que, se torne posssível 
    a organização dos horários de cada participante, possibilitando a visualização
    qual o melhor horário para se marcar uma reunião */
    
    //Atributos da classe
    private Reuniao reuniao;

    
    /**********************************************************************************
    * Define os participantes da reunião. Recebe as datas e as listas dos participantes*
    ***********************************************************************************/
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
      
        try{
            HashMap<String, String> dados_participantes = new HashMap<>();
            boolean listaVazia = true;
            boolean datasEmSequencia = false;

            for(String participante : listaDeParticipantes){
                String [] separaDados = participante.split("*");
                dados_participantes.put(separaDados[0], separaDados[1]);
            }

            if(dados_participantes.size() == 0) throw new ParticipanteException ("ERRO marcarReuniaoEntre: Lista de participantes vazia.");
            else listaVazia = false;
            if(!dataInicial.isBefore(dataFinal)) throw new DataException("ERRO marcarReuniaoEntre: Datas incorretas para marcar reunião");
            else datasEmSequencia = true;
            if(listaVazia == false && datasEmSequencia == true) this.reuniao = new Reuniao(dataInicial, dataFinal, dados_participantes);
        }
        catch(ParticipanteException e){
            System.err.println(e.getMessage());
        }
        catch(DataException e){
            System.err.println(e.getMessage());
        }
    }
   

    /*******************************************************************************************
    * Define a disponibilidade de cada pessoa para a reunião. Recebe uma String do participante *
    * e a data inicial e final em que está com horário disponível(dia e horario)
    ********************************************************************************************/
    public void indicaDisponibilidade(String participante, LocalDateTime inicio, LocalDateTime fim) {
    //OBS: String participante recebe: nome+"*"+id

        String [] separaDados = participante.split("*");

        // Verifica a existencia do Participante na Reuniao
        try{
            boolean participante_valido = false;

            for (Map.Entry<String,String> id : this.reuniao.getParticipantes().entrySet()) {
                if(id.getKey().equals(separaDados[1])){
                    participante_valido = true;
                    this.reuniao.getParticipantes().put(separaDados[0], separaDados[1]);
                    Participante obj_participante = new Participante(inicio, fim, separaDados[0], separaDados[1]);
                    
                    if(!inicio.isBefore(fim)) throw new DataException("ERRO indicaDisponibilidade: Datas incorretas para marcar reunião");
                    else this.reuniao.setAgendaParticipantes(obj_participante);
                }
            }
            if(participante_valido == false) throw new ParticipanteException("ERRO indicaDisponibilidade(): Participante inexistente.");
        }
        catch(ParticipanteException e){
            System.err.println(e.getMessage());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    /***********************************************************************************************************
    Mostra quais horários foram escolhidos pelos funcionários e destaca os que mais de uma pessoa está disponível.
    ************************************************************************************************************ */
    public void mostraSobreposicao() {
        

        //Formato do LocalDateTime:
        //   ex: 2007-1203T10:15:30 -> ano-mes-diaThora:minuto:segundo
        
        //
        //
        // Verificar na sequencia: Ano -> Mes -> dia -> Hora -> Minuto
        
        
        
        //metodos da biblioteca Time:
        // isAfter() , isBefore()


        //PRINT:
        //  usar a biblioteca DateTimeFormatter e o metodo .ofPattern -> DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }
   
}
