import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MarcadorDeReuniao {
    /* Classe que têm, como objetivo, marcar uma reunião. De forma que, se torne posssível 
    a organização dos horários de cada participante, possibilitando a visualização
    qual o melhor horário para se marcar uma reunião */
    
    //Atributos da classe
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private LocalDateTime tempoInicial;
    private LocalDateTime tempoFinal;
    private Collection<String> listaDeParticipantes;
    private String participante;

    private Reuniao reuniao;

    /**********************************************************************************
    * Define os participantes da reunião. Recebe as datas e as listas dos participantes*
    ***********************************************************************************/
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        
        //1) Deixar mais especifico a Collection do parametro
        //   - Criar algum identificador para cada participante, pois so com o nome
        //     nao conseguimos identificar se ha duas pessoas diferentes com o mesmo nome
        //     
        //   - Identificador: ID? CPF?? E-mail???


        //this.reuniao = new Reuniao(dataInicial, dataFinal, listaDeParticipantes);
    }
   

    /*******************************************************************************************
    * Define a disponibilidade de cada pessoa para a reunião. Recebe uma String do participante *
    * e a data inicial e final em que está com horário disponível(dia e horario)
    ********************************************************************************************/
    public void indicaDisponibilidade(String participante, LocalDateTime inicio, LocalDateTime fim) {
        
        // 1. Verificar a existencia do Participante na Reuniao
        // 2. Verificar se o horario "inicio" vem antes que o horario "fim"
        // 3. Verificar se o horario de inicio e fim condizem com o horario que sera realizado a reuniao
        
        // this.participante = participante;
        // this.inicio = inicio;
        // this.fim = fim;
    }

    /***********************************************************************************************************
    Mostra quais horários foram escolhidos pelos funcionários e destaca os que mais de uma pessoa está disponível.
    ************************************************************************************************************ */
    public void mostraSobreposicao() {
        // ih rapaz
        //vou ver se acho algo que me ajude

        //Formato do LocalDateTime:
        //   ex: 2007-12-03T10:15:30 -> ano-mes-diaThora:minuto:segundo
        
        
        // Verificar na sequencia: Ano -> Mes -> dia -> Hora -> Minuto

        //metodos da biblioteca Time:
        // isAfter() , isBefore()


        //PRINT:
        //  usar a biblioteca DateTimeFormatter e o metodo .ofPattern -> DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }
   
}
