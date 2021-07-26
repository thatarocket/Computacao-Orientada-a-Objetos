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

    
   /************************************************************************************
    * Define os participantes da reunião. Recebe as datas e as listas dos participantes*
    *                                                                                  *
    * -> Adiciona as datas de inicio e fim que a reuniao será realizada.               *
    ************************************************************************************/
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
      
        try{
            if(this.reuniao.getParticipantes().size() == 0) throw new ParticipanteException ("OPS! Você quer marcar uma reunião sem participantes.");
            if(!dataInicial.isBefore(dataFinal)) throw new DataException("OPS! A data de inicio e fim da reunião nao esta em ordem cronologica.");
            this.reuniao.setInicio(dataInicial);
            this.reuniao.setFim(dataFinal);

            //checa se todos os participantes tem horario disponivel no horario da reuniao
            for(String participante : listaDeParticipantes){
                String [] separaDados = participante.split("*");
                for(Participante p : this.reuniao.getAgendaParticipantes()){
                    if(p.getNome().equals(separaDados[0]) && this.reuniao.getParticipantes().containsKey(separaDados[1])){
                        if(!p.getInicio().toLocalDate().isBefore(dataInicial)) throw new DataException("OPS! O participante " + p.getNome() + " com ID = " + p.getID() +" não possuí disponibilidade para estar na reunião.");
                    }
                }
            }
        }
        catch(ParticipanteException e){
            System.err.println(e.getMessage());
        }
        catch(DataException e){
            System.err.println(e.getMessage());
        }
    }
   

   /*********************************************************************************************
    * Define a disponibilidade de cada pessoa para a reunião. Recebe uma String do participante *
    * e a data inicial e final em que está com horário disponível(dia e horario)                *
    *                                                                                           *
    *-> Adiciona um Participante na reuniao.                                                    *
    ********************************************************************************************/
    public void indicaDisponibilidade(String participante, LocalDateTime inicio, LocalDateTime fim) {
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
                    if(id.getKey().equals(separaDados[1])) throw new ParticipanteException("OPS! O participante " + id.getValue() + " com ID = "+ id.getKey() + " ja foi adicionado na reuniao.");
                }
            }
            
            this.reuniao.getParticipantes().put(separaDados[0], separaDados[1]);
            Participante obj_participante = new Participante(inicio, fim, separaDados[0], separaDados[1]);

            if(!inicio.isBefore(fim)) throw new DataException("ERRO indicaDisponibilidade: Datas incorretas para marcar reunião");
            else{
                this.reuniao.setAgendaParticipantes(obj_participante);
            }
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
