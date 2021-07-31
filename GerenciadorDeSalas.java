import java.time.LocalDateTime;
import java.util.*;

// >>> Classe que reserva as salas para as nossas reuniões <<<

/*============================================= OBSERVACOES ================================================
    Um objeto da classe GerenciadorDeSalas deve receber um LOCAL. Logo, cada GerenciadorDeSalas gerencia
    salas para apenas UM determinado local.

    (podemos criar uma List de GerenciadorDeSalas no Main para o caso de haver reservas em locais distintos)
============================================================================================================*/

public class GerenciadorDeSalas {

    //atributos da classe
    private List<Sala> lista_salas; //lista de salas existentes
    private List<Reserva> lista_reservas; //lista de reservas realizadas
    private String local; //local da reserva

    //construtor da classe
    public GerenciadorDeSalas(String local){
        
        this.local = local;
        this.lista_salas = new LinkedList<>();
        this.lista_reservas = new  LinkedList<>();
    }


    /****************************************************
    * Deve receber o nome da sala, a capacidade máxima  *
    * da sala, e uma descrição.                         *
    *****************************************************/
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao){

        try{
            //verifica se a sala a ser adicionada ja existe, pois so adicionamos NOVAS salas
            for(Sala sala : this.lista_salas){
                if(sala.getNome().equals(nome)) throw new SalaException(" OPS! Esta sala ja foi criada anteriormente.");
            }
        }
        catch(SalaException e){
            System.err.println(e.getMessage());
        }

        Sala nova_sala = new Sala(nome, this.local, capacidadeMaxima, descricao);
        adicionaSala(nova_sala);
    }


    /****************************************************
    * Deve receber o nome da sala, a ser removida.      *
    *****************************************************/
    public void removeSalaChamada(String nomeDaSala) throws SalaException {
       
        boolean salaExiste = false;

        //verifica se a sala a ser removida existe
        for(Sala sala : this.lista_salas){
            if(sala.getNome().equals(nomeDaSala)){
                salaExiste = true;
                this.lista_salas.remove(sala);
            }
        }
        if(salaExiste == false) throw new SalaException( " OPS! A sala " + nomeDaSala + " nao existe.");
        else System.out.println("  A sala "  +  nomeDaSala + " no local "  +" foi removida." );
        
        for(Reserva reserva : this.lista_reservas){
            if(reserva.getNome().equals(nomeDaSala)){
                System.out.println("  A reserva da data " + reserva.getInicio().getDayOfMonth() + "/" + reserva.getInicio().getMonthValue() + "/" + reserva.getInicio().getYear() +
                                    " ate "  + reserva.getFim().getDayOfMonth() + "/" + reserva.getFim().getMonthValue() + "/" + reserva.getFim().getYear()  + " foi " +
                                    "cancelada."); 
                this.lista_reservas.remove(reserva);
            }
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

    public void adicionaReserva(Reserva novaReserva){
        this.lista_reservas.add(novaReserva);
    }

  
    /*************************************************************
    * Deve receber um nome de sala, um LocalDateTime que indica  *
    * o início da reserva e um outro LocalDateTime para indicar  *
    * o final da reserva. O método deve devolver uma instância   *
    * de Reserva.                                                *
    **************************************************************/
    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) throws SalaException, ReservaException {

        boolean existe_sala = false;
        for(Sala sala : this.lista_salas){
            if(sala.getNome().equals(nomeDaSala)) existe_sala = true;
        }

        if(existe_sala == false) throw new SalaException( "  OPS! A sala " + nomeDaSala  +" nao foi encontrada.");

        //verifica se esta sala ja possui uma reserva no horario desejado
        for(Reserva reserva : this.lista_reservas){
            if(reserva.getNome().equals(nomeDaSala)){
                if((dataInicial.isBefore(reserva.getInicio()) && dataFinal.isBefore(reserva.getInicio())) ||
                   (dataFinal.isAfter(reserva.getFim()) && dataFinal.isAfter(reserva.getFim()))) continue;

                else throw new ReservaException( "  OPS! A sala "  + nomeDaSala +" ja possui possui uma reserva no horario desejado." );
            }
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

        try{
            boolean reservaExiste = false;

            //verifica se a sala a ser removida existe
            for(Reserva reserva : this.lista_reservas){
                if(reserva.equals(cancelada)){
                    reservaExiste = true;
                    this.lista_reservas.remove(cancelada);
                }
            }
            if(reservaExiste == false) throw new ReservaException(" OPS! Esta reserva inexiste.");
        }
        catch(ReservaException e){
            System.err.println(e.getMessage());
        }
    }


    /******************************************************************
    * Deve devolver uma Colletion de objetos Reserva que representam  *
    * as reservas da respectiva sala.                                 *
    *******************************************************************/
    public Collection<Reserva> reservasParaSala(String nomeSala){

        Collection<Reserva> col_reservas = new LinkedList<>();
        
        for(Reserva reserva : this.lista_reservas){
            if(reserva.getNome().equals(nomeSala)) col_reservas.add(reserva);
        }

        return col_reservas;
    }


   /*********************************************************
    * Deve receber uma String com o nome da sala e imprime  *
    * todas as suas reservas.                               *
    *********************************************************/
    public void imprimeReservasDaSala(String nomeSala){
        
        if(this.lista_reservas.size() == 0){
            System.out.println( "Nao existem reservas para a sala " + nomeSala + ".");
            return;
        }

        boolean existeReserva = false;
        for(Reserva reserva : this.lista_reservas){
            if(reserva.getNome().equals(nomeSala)){
                System.out.println(reserva.toString());
                existeReserva = true;
            }
        }
        if(existeReserva == false) System.out.println("Nao existem reservas para a sala " + nomeSala + ".");
    }

    public List<Reserva> getListaReservas(){
        return this.lista_reservas;
    }

}
