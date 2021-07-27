import java.util.*;
import java.time.LocalDate;
import java.time.*;
import java.io.*;


public class Main{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void help(){
        System.out.println(ANSI_PURPLE + "======================== ESCOLHA UM COMANDO ========================" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  1." + ANSI_RESET  + " Cria uma sala de reuniao " + ANSI_PURPLE + "--------------------- "+ ANSI_YELLOW +"<COMANDO>: S  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  2." + ANSI_RESET + " Remover uma sala de reuniao " + ANSI_PURPLE + "------------------ "+ ANSI_YELLOW +"<COMANDO>: R  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  3." + ANSI_RESET + " Reservar uma sala de reuniao " + ANSI_PURPLE + "----------------- " + ANSI_YELLOW + "<COMANDO>: E  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  4." + ANSI_RESET + " Cancelar uma reunião " + ANSI_PURPLE + "------------------------- " + ANSI_YELLOW + "<COMANDO>: C  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  5." + ANSI_RESET + " Imprimir as reservas das salas " + ANSI_PURPLE + "--------------- " + ANSI_YELLOW + "<COMANDO>: I  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  6." + ANSI_RESET + " Adicionar um participante na reuniao " + ANSI_PURPLE + "--------- " + ANSI_YELLOW + "<COMANDO>: P  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  7." + ANSI_RESET + " Marcar uma reuniao " + ANSI_PURPLE + "--------------------------- " + ANSI_YELLOW + "<COMANDO>: M  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  8." + ANSI_RESET + " Mostrar sobreposicao de horarios " + ANSI_PURPLE + "------------- " + ANSI_YELLOW + "<COMANDO>: O  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "|  9." + ANSI_RESET + ANSI_RED + " Finalizar o programa " + ANSI_PURPLE + "------------------------- " + ANSI_YELLOW + "<COMANDO>: F  "+ ANSI_PURPLE +"|"+ ANSI_RESET);
        System.out.println(ANSI_PURPLE + "====================================================================" + ANSI_RESET);
    }

    public static int help2(){
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_CYAN + "<<< Gostaria de rever a tabela de" + ANSI_BLUE + " COMANDOS" + ANSI_CYAN +" ??? Digite"+ ANSI_YELLOW + " 1 "+ ANSI_CYAN +"ou "+ ANSI_YELLOW +"2 "+ANSI_CYAN+">>>" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "        "+ ANSI_GREEN + "SIM "+ANSI_CYAN + ": "+ANSI_YELLOW +"1"+ANSI_RED +"    NAO "+ ANSI_CYAN +":"+ANSI_YELLOW+" 2"+ ANSI_RESET);
        System.out.print(ANSI_CYAN + "        >>> " + ANSI_RESET);
        int resp = 0;
        while(resp != 1 || resp != 2){
            try{
                resp = sc.nextInt();
                sc.nextLine();
                if(resp != 1 && resp != 2){
                    System.out.println(ANSI_RED + "OPS! Este comando nao eh valido." + ANSI_RESET);
                    System.out.print(ANSI_BLUE + ">>> Digite novamente: " + ANSI_RESET);
                }
                else break;
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println(ANSI_RED + "OPS! Este comando nao eh valido." + ANSI_RESET);
                System.out.print(ANSI_BLUE + ">>>Digite novamente: " + ANSI_RESET);
            }
        }
        System.out.println();
        return resp;
    }

    //transforma a String DATA ("dd/mm/aaaa") em LocalDate
    public static LocalDate formata_data (String data){

        String[]separa = data.split("/");
        LocalDate data_formatada = LocalDate.of(Integer.parseInt(separa[2]), Integer.parseInt(separa[1]), Integer.parseInt(separa[0]));
        return data_formatada;
    }

    //transforma a String tempo ("hh:mm:ss") e data ("dd/mm/aaaa") em LocalDateTime
    public static LocalDateTime formata_tempo (String tempo, String data){
        
        String[]separa = tempo.split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(separa[0]), Integer.parseInt(separa[1]), Integer.parseInt(separa[2]));
        LocalDateTime tempo_formatado = LocalDateTime.of(formata_data(data), time);
        return tempo_formatado;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Collection <String> listaDeParticipantes = new LinkedList<>();
        MarcadorDeReuniao marcador = new MarcadorDeReuniao();
        LocalDate inicio_reuniao;
        LocalDate fim_reuniao;


        System.out.print("Onde serao realizadas as reunioes? ");
        String local = input.nextLine();
        System.out.println();

        GerenciadorDeSalas gerenciador = new GerenciadorDeSalas(local);
        String comando = "";
        help();

        while(!comando.equals("F")){
            System.out.print(ANSI_YELLOW + "<INSIRA UM COMANDO> : " + ANSI_RESET);
            comando = input.nextLine();
            System.out.println();

            //COLOCAR VERIFICACOES EM CADA METODO, CASO PRECISE VERIFICAR SE JA FOI CRIADO SALAS/PARTICIPANTES/REUNIOES
            switch(comando) {
                case "S": //CRIA UMA SALA DE REUNIAO e ADICIONA NA LISTA DE SALAS QUE PODEM SER RESERVADAS
                        boolean inputCorreto = false;
                        int maxPessoas = 0;
                        System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=--=--=-=-=-=-= CRIANDO UMA SALA DE REUNIAO =-=-=-=-=-=-=--=--=-=-=-=-=-=" + ANSI_RESET);
                        System.out.print(ANSI_CYAN + "  1. Qual sera o nome da sala? " + ANSI_RESET);
                        String nome_sala = input.nextLine();
                        System.out.print(ANSI_CYAN + "\n  2. Qual eh a capacidade máxima de pessoas nessa sala? " + ANSI_RESET);
                        while(inputCorreto == false){
                            try{
                                maxPessoas = input.nextInt();
                                if(maxPessoas > 0) inputCorreto = true;
                                else throw new InputMismatchException();
                            }
                            catch(InputMismatchException e){
                                input.nextLine();
                                System.out.print(ANSI_RED + "\n  OPS! A capacidade maxima de pessoas digitada não eh um numero inteiro positivo.\n" + ANSI_BLUE + "   >>>> Digite outro valor: " + ANSI_RESET);
                            }
                        }
                        input.nextLine();
                        while(maxPessoas <= 0) throw new InputMismatchException();
                        System.out.print(ANSI_CYAN + "\n  3. Escreva uma breve descricao da sala em apenas uma linha: " + ANSI_RESET);
                        String descricao = input.nextLine();
                        System.out.println();
                        gerenciador.adicionaSalaChamada(nome_sala, maxPessoas, descricao);
                        System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=--=--=-=-=-= " + ANSI_GREEN + "SALA DE REUNIAO CRIADA COM SUCESSO " + ANSI_CYAN + "=-=-=-=-=-=-=--=--=-=-=-=\n" + ANSI_RESET);
                        
                        if(help2() == 1) help();
                        break;
                        
                case "R": //REMOVE UMA SALA DE REUNIÃO EXISTENTE
                        System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=-= REMOVENDO UMA SALA DE REUNIAO =-=-=-=-=-=-=-=-=-=-=-=-=-=" + ANSI_RESET);
                        System.out.print(ANSI_CYAN + "  1. Digite o nome da sala que sera removida: " + ANSI_RESET);
                        String nomeSala = input.nextLine();
                        try{
                            gerenciador.removeSalaChamada(nomeSala);
                            System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=" + ANSI_GREEN + " SALA DE REUNIAO REMOVIDA COM SUCESSO "+ ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=-=\n" + ANSI_RESET);
                        }
                        catch(IOException e){
                            System.out.println(ANSI_RED + "  OPS! A sala " + ANSI_RESET +  nomeSala + ANSI_RED + " nao existente." + ANSI_RESET);
                            System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=" + ANSI_RED + " SALA DE REUNIAO NÃO FOI REMOVIDA "+ ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=\n" + ANSI_RESET);
                        }

                        if(help2() == 1) help();
                        break;                

                case "E": //RESERVA UMA SALA DE REUNIAO e ADICIONA A RESERVA NA LISTA DE RESERVAS
                        System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=-=-=-= RESERVANDO UMA SALA DE REUNIAO =-=-=-=-=-=-=-=-=-=" + ANSI_RESET);
                        System.out.print(ANSI_CYAN + "\n1. Qual eh o nome da sala a ser reservada? " + ANSI_RESET);
                        String nome_Sala = input.nextLine();
                        System.out.print(ANSI_CYAN + "\n2. Qual sera o horario que a sala sera utilizada?\nPor favor, utilize o seguinte formato < dd/mm/yyyy - hh:mm:ss | dd/mm/yyyy - hh:mm:ss > : \n" + ANSI_RESET);
                        
                        boolean entradaCorreta = false;
                        String data_1 = "";
                        String tempo_1 = "";
                        String data_2 = "";
                        String tempo_2 = "";

                        while(entradaCorreta == false){
                            try{
                                String horario_reserva = input.nextLine();
                                data_1 = horario_reserva.substring(0, 9);
                                tempo_1 = horario_reserva.substring(13, 20);
                                data_2 = horario_reserva.substring(24, 33);
                                tempo_2 = horario_reserva.substring(37, 44);
                                entradaCorreta = true;
                                System.out.println();
                            }
                            catch (IndexOutOfBoundsException e){
                                System.out.println(ANSI_RED + "  OPS! O horario digitado não está com o formato padrao pedido." + ANSI_RESET);
                                System.out.println(ANSI_BLUE + "  >>> Insira novamente um horário para a reunião:   " + ANSI_RESET);
                            }
                        }
                        try{
                            gerenciador.adicionaReserva(gerenciador.reservaSalaChamada(nome_Sala,  formata_tempo(tempo_1, data_1), formata_tempo(tempo_2, data_2)));
                            System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=-= " + ANSI_GREEN + "RESERVA DE SALA REALIZADA COM SUCESSO " + ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" + ANSI_RESET);
                        }
                        catch(SalaException e){
                            System.err.println(e.getMessage());
                            System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=-= " + ANSI_RED + "A RESERVA DE SALA NAO FOI REALIZADA " + ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" + ANSI_RESET);
                        }
                        catch(DataException e){
                            System.err.println(e.getMessage());
                            System.out.println(ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=-= " + ANSI_RED + "A RESERVA DE SALA NAO FOI REALIZADA " + ANSI_CYAN + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" + ANSI_RESET);
                        }

                        if(help2() == 1) help();
                        break;

                case "C": // CANCELA UMA REUNIÃO, ANTERIORMENTE MARCADA
                        //lembrar de garantir que nao tem salas de nomes iguais
                        System.out.println("=-=-=-=-=-=-=-=-=-= CANCELANDO UMA REUNIAO MARCADA =-=-=-=-=-=-=-=-=-=");
                        System.out.println("1. Digite o nome da reuniao que sera cancelada ");
                        String nomeDaSala = input.nextLine();
                        for(Reserva reserva : gerenciador.getListaReservas()){
                            if(reserva.getNome().equals(nomeDaSala)) gerenciador.cancelaReserva(reserva);
                        }

                        if(help2() == 1) help();
                        break;
                        
                case "I": //IMPRIMIR AS RESERVAS DAS SALAS 
                        for(Reserva reserva : gerenciador.getListaReservas()){
                            gerenciador.imprimeReservasDaSala(reserva.getNome()); 
                        }
                        
                        if(help2() == 1) help();
                        break;
                
                case "M": //MOSTRA A SOBREPOSIÇÃO DE HORARIOS E MARCA UMA REUNIAO A PARTIR DELA
                            if(listaDeParticipantes.size() == 0) System.out.println("OPS! Nao existe participantes na reuniao para mostrar a sobreposicao de horarios.\n");
                            else{
                                System.out.println("======== ESCOLHA UM HORARIO PARA A REUNIAO ========");
                                marcador.mostraSobreposicao();
                                System.out.println("===================================================");
                                System.out.print("1. Horario de inicio da reuniao (dd/mm/yyyy): ");
                                String inicio = input.nextLine();
                                System.out.print("\n2. Horario de termino da reuniao (dd/mm/yyyy): ");
                                String fim = input.nextLine();
                                System.out.println();

                                inicio_reuniao = formata_data(inicio);
                                fim_reuniao = formata_data(fim);
                                
                                marcador.marcarReuniaoEntre(inicio_reuniao, fim_reuniao, listaDeParticipantes);
                            }

                            if(help2() == 1) help();
                            break;

                case "O":
                        System.out.println("======== SOBREPOSICOES DOS HORÁRIOS DE REUNIAO ========");
                        marcador.mostraSobreposicao();
                        System.out.println("=======================================================");
                        
                        if(help2() == 1) help();
                        break;
                
                case "P": //ADICIONA UM PARTICIPANTE NA REUNIAO
                        System.out.println("=-=-=-=-=-=-=- COLETANDO DADOS DO PARTICIPANTE -=-=-=-=-=-=-=-=");
                        System.out.print("1. NOME DO PARTICIPANTE: ");
                        String nome = input.nextLine();
                        System.out.print("\n2. CRIE UM ID DE 6 NUMEROS: ");
                        String id = input.nextLine();
                        System.out.print("\n3. HORARIO DE DISPONIBILIDADE -> OBS: utilize o formato\n< dd/mm/yyyy - hh:mm:ss | dd/mm/yyyy - hh:mm:ss >\n");
                        System.out.print("Horario: ");
                        String horario = input.nextLine();
                        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

                        String data1 = horario.substring(0, 10);
                        String tempo1 = horario.substring(13, 20);
                        String data2 = horario.substring(24, 34);
                        String tempo2 = horario.substring(37, 44);
                        
                        String nome_id = nome + "*" + id;
                        marcador.indicaDisponibilidade(nome_id, formata_tempo(tempo1, data1), formata_tempo(tempo2, data2));
                        
                        if(help2() == 1) help();
                        break;
                
                case "F": break;

                default:
                    System.out.println("ERRO: O comando inserido eh invalido. Por favor, digite um comando valido.");
                    System.out.print(ANSI_YELLOW + "<REINSIRA UM COMANDO> : " + ANSI_RESET);
                    comando = input.nextLine();
                    System.out.println();
                    break;
            }
        }
    }
}