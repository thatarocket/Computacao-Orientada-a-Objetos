import java.time.LocalDateTime;
import java.util.*;
import java.io.IOException;

// >>> Classe que o objetivo é reservar as salas para as nossas reuniões <<<

public class GerenciadorDeSalas {
    
    //atributos da classe
    List<Sala> lista_salas = new LinkedList<>(); //lista de salas
    List<Reserva> lista_reservas = new  LinkedList<>(); //lista de reservas marcadas
    String local; //local da reserva

    //construtor da classe
    public GerenciadorDeSalas(List<Reserva> lista_reservas, String local){
        
        this.lista_reservas = lista_reservas;
        this.local = local;
    }

    /****************************************************
    * Deve receber o nome da sala, a capacidade máxima  *
    * da sala, e uma descrição.                         *
    *****************************************************/
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao){
        
        Sala nova_sala = new Sala(nome, this.local, capacidadeMaxima, descricao);
        this.lista_salas.add(nova_sala);
    }


    /****************************************************
    * Deve receber o nome da sala, a ser removida.      *
    *****************************************************/
    public void removeSalaChamada(String nomeDaSala){
        
        //remove da lista de Salas a sala com nome "nomeDaSala"
        for(Sala sala : this.lista_salas){
            if(sala.nome.equals(nomeDaSala)) this.lista_salas.remove(sala);
        }
    }


    /******************************************************
    * Deve devolver uma instancia de List com objetos do  *
    * do tipo Sala.                                       *
    *******************************************************/
    public List<Sala> listaDeSalas(){

        return this.lista_salas;
    }


    /********************************************
    * Deve receber uma instancia de Sala        *
    *********************************************/
    public void adicionaSala(Sala novaSala){

        this.lista_salas.add(novaSala);
    }


    /*************************************************************
    * Deve receber um nome de sala, um LocalDateTime que indica  *
    * o início da reserva e um outro LocalDateTime para indicar  *
    * o final da reserva. O método deve devolver uma instância   *
    * de Reserva.                                                *
    **************************************************************/
    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal){

        try{
            boolean existe_sala = false;
            for(Sala sala : this.lista_salas){
                if(sala.nome.equals(nomeDaSala)) existe_sala = true;
            }
            if(!existe_sala) throw new IOException();
        }
        catch(IOException e){
            System.out.println("ERRO: sala inexistente ou ja ocupada.");
        }

        Reserva nova_reserva = new Reserva(nomeDaSala, dataInicial, dataFinal);
        this.lista_reservas.add(nova_reserva);

        return nova_reserva;
    }


    /****************************************************
    * Deve receber um objeto do tipo Reserva e cancela  *
    * esta reserva.                                     *
    *****************************************************/
    public void cancelaReserva(Reserva cancelada){

        this.lista_reservas.remove(cancelada);
    }


    /******************************************************************
    * Deve devolver uma Colletion de objetos Reserva que representam  *
    * as reservas da respectiva sala.                                 *
    *******************************************************************/
    public Collection<Reserva> reservasParaSala(String nomeSala){

        Collection<Reserva> col_reservas = new LinkedList<>();
        
        for(Reserva reserva : this.lista_reservas){
            if(reserva.nome.equals(nomeSala)) col_reservas.add(reserva);
        }

        return col_reservas;
    }


    /********************************************************
    * Deve receber uma String com o nome da sala e imprime  *
    * todas as suas reservas.                               *
    *********************************************************/
    public void imprimeReservasDaSala(String nomeSala){

        for(Reserva reserva : this.lista_reservas){
            if(reserva.nome.equals(nomeSala)) System.out.println(reserva.toString());
        }
    }

}
