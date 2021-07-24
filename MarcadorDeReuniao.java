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
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.listaDeParticipantes = listaDeParticipantes;
    }
   

    /*******************************************************************************************
    * Define a disponibilidade de cada pessoa para a reunião. Recebe uma String do participante *
    * e a data inicial e final em que está com horário disponível(dia e horario)
    ********************************************************************************************/
    public void indicaDisponibilidade(String participante, LocalDateTime inicio, LocalDateTime fim) {
        this.participante = participante;
        this.inicio = inicio;
        this.fim = fim;
    }

    /***********************************************************************************************************
    Mostra quais horários foram escolhidos pelos funcionários e destaca os que mais de uma pessoa está disponível.
    ************************************************************************************************************ */
    public void mostraSobreposicao() {
        // ih rapaz
        //vou ver se acho algo que me ajude

    }
   
}
