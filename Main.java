import java.util.*;
import java.time.*;
import java.io.*;

public class Main{


    public static void help(){
        System.out.println("======================== ESCOLHA UM COMANDO ========================");
        System.out.println("|  1." + " Cria uma sala de reuniao " + "--------------------- "+ "<COMANDO>: S  "+"|");
        System.out.println("|  2." + " Remover uma sala de reuniao " + "------------------ "+ "<COMANDO>: R  " +"|");
        System.out.println("|  3." + " Reservar uma sala de reuniao " + "----------------- " +  "<COMANDO>: E  " +"|");
        System.out.println("|  4." +" Cancelar uma reuniao " +  "------------------------- " + "<COMANDO>: C  " +"|");
        System.out.println("|  5." + " Imprimir as reservas das salas " + "--------------- " +  "<COMANDO>: I  " +"|");
        System.out.println("|  6." + " Adicionar um participante na reuniao " + "--------- " + "<COMANDO>: P  " +"|");
        System.out.println("|  7." + " Marcar uma reuniao " + "--------------------------- " +  "<COMANDO>: M  "+ "|");
        System.out.println("|  8." + " Mostrar sobreposicao de horarios " + "------------- " +  "<COMANDO>: O  "+ "|");
        System.out.println("|  9." + " Finalizar o programa " + "------------------------- " + "<COMANDO>: F  "+"|");
        System.out.println("====================================================================");
    }

    public static int help2(){
        Scanner sc = new Scanner(System.in);
        System.out.println( "<<< Gostaria de rever a tabela de"  + " COMANDOS"  +"? Digite" + " 1 " +"ou " +"2 "+">>>" );
        System.out.println("   " + "SIM " + ": " +"1" +"    NAO " +":"+" 2");
        System.out.print( "   >>> " );
        int resp = 0;
        while(resp != 1 || resp != 2){
            try{
                resp = sc.nextInt();
                sc.nextLine();
                if(resp != 1 && resp != 2){
                    System.out.println( "OPS! Este comando "+ "nao eh valido"  +"." );
                    System.out.print( ">>> Digite novamente: ");
                }
                else break;
            }
            catch(InputMismatchException e){
                sc.nextLine();
                System.out.println( "OPS! Este comando "   + "nao eh valido"  +"." );
                System.out.print( ">>>Digite novamente: " );
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

        System.out.println("*=*=*=*=*=*=*=*=*=*=*= "  + " INICIANDO O MARCADOR DE REUNIAO"  + " =*=*=**=*=*=*=*=*=*=*=*=*\n");
        System.out.print( ">>>> Em qual local serao realizadas as reunioes? " );
        String local = input.nextLine();
        System.out.println();

        GerenciadorDeSalas gerenciador = new GerenciadorDeSalas(local);
        String comando = "";
        help();
        boolean aux = true;

        while(!comando.equals("F")){
            if(aux == true){
                System.out.print( "<INSIRA UM COMANDO> : ");
                comando = input.nextLine();
                System.out.println();
            }

            //COLOCAR VERIFICACOES EM CADA METODO, CASO PRECISE VERIFICAR SE JA FOI CRIADO SALAS/PARTICIPANTES/REUNIOES
            switch(comando) {
                case "S": //CRIA UMA SALA DE REUNIAO e ADICIONA NA LISTA DE SALAS QUE PODEM SER RESERVADAS
                        boolean inputCorreto = false;
                        int maxPessoas = 0;
                        System.out.println("=-=-=-=-=-=-=--=--=-=-=-=-= CRIANDO UMA SALA DE REUNIAO =-=-=-=-=-=-=--=--=-=-=-=-=-=");
                        System.out.print("  1. Qual sera o nome da sala? " );
                        String nome_sala = input.nextLine();
                        System.out.print( "\n  2. Qual eh a capacidade maxima de pessoas nessa sala? ");
                        while(inputCorreto == false){
                            try{
                                System.out.print("\n >>> ");
                                maxPessoas = input.nextInt();
                                input.nextLine();
                                if(maxPessoas > 0) inputCorreto = true;
                                else System.out.println(" OPS! Parece que a capacidade máxima de pessoas esta incorreta. Digite novamente: ");
                            }
                            catch(InputMismatchException e){
                                input.nextLine();
                                System.out.print( "\n  OPS! A capacidade maxima de pessoas digitada nao eh um " +  "numero inteiro positivo" + ". Digite outro valor: " );
                            }
                        }

                        System.out.println();
                        System.out.print("  3. Escreva uma breve descricao da sala em apenas uma linha: ");
                        String descricao = input.nextLine();
                        System.out.println();
                        gerenciador.adicionaSalaChamada(nome_sala, maxPessoas, descricao);
                        System.out.println("=-=-=-=-=-=-=--=--=-=-=-= "  + "SALA DE REUNIAO CRIADA COM SUCESSO "  + "=-=-=-=-=-=-=--=--=-=-=-=\n");
                        
                        if(help2() == 1) help();
                        aux = true;
                        break;
                        
                case "R": //REMOVE UMA SALA DE REUNIÃO EXISTENTE
                        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-= REMOVENDO UMA SALA DE REUNIAO =-=-=-=-=-=-=-=-=-=-=-=-=-=" );
                        System.out.print("  1. Digite o nome da sala que sera removida: ");
                        String nomeSala = input.nextLine();
                        try{
                            gerenciador.removeSalaChamada(nomeSala);
                            System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=" + " SALA DE REUNIAO REMOVIDA COM SUCESSO "+ "=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                        }
                        catch(SalaException e){
                            System.out.println(e.getMessage());
                            System.out.println( "=-=-=-=-=-=-=-=-=-=-=-="  + " SALA DE REUNIAO NAO FOI REMOVIDA " + "=-=-=-=-=-=-=-=-=-=-=-=\n" );
                        }

                        if(help2() == 1) help();
                        aux = true;
                        break;                

                case "E": //RESERVA UMA SALA DE REUNIAO e ADICIONA A RESERVA NA LISTA DE RESERVAS
                        System.out.println( "=-=-=-=-=-=-=-=-=-= RESERVANDO UMA SALA DE REUNIAO =-=-=-=-=-=-=-=-=-=" );
                        System.out.print("\n1. Qual eh o nome da sala a ser reservada? " );
                        String nome_Sala = input.nextLine();
                        System.out.print("\n2. Qual sera o horario que a sala sera utilizada?\nPor favor, utilize o seguinte formato < "+ "dd/mm/yyyy - hh:mm:ss | dd/mm/yyyy - hh:mm:ss"+" > : \n" );
                        
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
                                formata_tempo(tempo_1, data_1);
                                data_2 = horario_reserva.substring(24, 33);
                                tempo_2 = horario_reserva.substring(37, 44);
                                formata_tempo(tempo_2, data_2);
                                entradaCorreta = true;
                                System.out.println();
                            }
                            catch (IndexOutOfBoundsException e){
                                System.out.println("  OPS! O horario digitado nao esta com o formato padrao pedido." );
                                System.out.println( "  >>> Insira novamente um horario para a reuniao:   ");
                                System.out.print("  >>> ");
                            }
                            catch(DateTimeException e){
                                System.out.println("  OPS! O horario digitado nao eh valido." );
                                System.out.println("  >>> Insira novamente um horario para a reuniao:   ");
                                System.out.print("  >>> ");
                            }
                        }

                        try{
                            gerenciador.reservaSalaChamada(nome_Sala,  formata_tempo(tempo_1, data_1), formata_tempo(tempo_2, data_2));
                            System.out.println( "=-=-=-=-=-=-=-=-=-= " + "RESERVA DE SALA REALIZADA COM SUCESSO " +  "=-=-=-=-=-=-=-=-=-=-=\n");
                        }
                        catch(ReservaException e){
                            System.err.println(e.getMessage());
                            System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=-= " +  "A RESERVA DE SALA NAO FOI REALIZADA " +  "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" );
                        }
                        catch(SalaException e){
                            System.err.println(e.getMessage());
                            System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=-= " +  "A RESERVA DE SALA NAO FOI REALIZADA "  + "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" );
                        }

                        if(help2() == 1) help();
                        aux = true;
                        break;

                case "C": // CANCELA UMA REUNIÃO, ANTERIORMENTE MARCADA
                        //lembrar de garantir que nao tem salas de nomes iguais
                        System.out.println("=-=-=-=-=-=-=-=-=-= CANCELANDO UMA REUNIAO MARCADA =-=-=-=-=-=-=-=-=-=" );
                        System.out.print( "  1. Qual eh o nome da sala que a reuniao iria ser realizada? " );
                        String nomeDaSala = input.nextLine();
                        System.out.println( "\n  2. Para qual horario estava marcado a reuniao?" );
                        System.out.println("  Por favor, utilize o seguinte formato < " +"dd/mm/yyyy - hh:mm:ss | dd/mm/yyyy - hh:mm:ss" + " > :");

                        boolean entrada_Correta = false;
                        String data01 = "";
                        String tempo01 = "";
                        String data02 = "";
                        String tempo02 = "";

                        while(entrada_Correta == false){
                            try{
                                String horarioMarcado = input.nextLine();
                                data01 = horarioMarcado.substring(0, 9);
                                tempo01 = horarioMarcado.substring(13, 20);
                                formata_tempo(tempo01, data01);
                                data02 = horarioMarcado.substring(24, 33);
                                tempo02 = horarioMarcado.substring(37, 44);
                                formata_tempo(tempo02, data02);
                                entrada_Correta = true;
                                System.out.println();
                            }
                            catch (IndexOutOfBoundsException e){
                                System.out.println( "  OPS! O horario digitado nao esta com o formato padrao pedido.");
                                System.out.println( "  >>> Insira novamente um horário para a reunião:   ");
                                System.out.print("  >>> ");
                            }
                            catch(DateTimeException e){
                                System.out.println( "  OPS! O horario digitado nao eh valido.");
                                System.out.println( "  >>> Insira novamente um horario para a reuniao:   " );
                                System.out.print("  >>> ");
                            }
                        }

                        boolean existe = false;
                        //checa se exitia uma reuniao marcado no horario informado
                        for(Reserva reserva : gerenciador.getListaReservas()){
                            if(reserva.getNome().equals(nomeDaSala)){
                                if(reserva.getFim().isEqual(formata_tempo(tempo01, data01)) && reserva.getFim().isEqual(formata_tempo(tempo02, data02))){
                                    existe = true;
                                    gerenciador.cancelaReserva(reserva);
                                }
                            }
                        }
                        if(existe == true) System.out.println("=-=-=-=-=-=-=-=-=-=" + "REUNIAO CANCELADA COM SUCESSO" +" =-=-=-=-=-=-=-=-=-=\n");

                        else{
                            System.out.println(" OPS! Parece que nao ha nenhuma reuniao marcada para este horario na sala "+nomeDaSala +". Logo, \nnao eh possivel cancelar essa reuniao.");
                            System.out.println( "=-=-=-=-=-=-=-=-=-=" + " NAO FOI POSSIVEL CANCELAR A REUNIAO" +" =-=-=-=-=-=-=-=-=-=\n" );
                        }

                        if(help2() == 1) help();
                        aux = true;
                        break;
                
                case "I": //IMPRIMIR AS RESERVAS DAS SALAS 
                        System.out.println("=-=-=-=-=-=-=-=-=-=-=-= RESERVAS DE CADA SALA =-=-=-=-=-=-=-=-=-=-=" );
                        if(gerenciador.getListaReservas().size() == 0) System.out.println( "  OPS! Nao existem salas reservadas ainda." );
                        
                        for(Reserva reserva : gerenciador.getListaReservas()){
                            System.out.println( ">>>>>>>>>>>>>>>>>>>>>>. SALA "  + reserva.getNome()  +" .<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                
                            gerenciador.imprimeReservasDaSala(reserva.getNome());
            
                            for(Sala sala : gerenciador.listaDeSalas()){
                                if(sala.getNome() == reserva.getNome()){
                                    System.out.println(">> Descricao da sala: " + sala.getObservacoes());
                                    System.out.println(">> Capacidade máxima suportada: " + sala.getCapacidade());
                                }
                            }                           
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<" );
                        }                                                                                              
                        System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                        if(help2() == 1) help();
                        aux = true;
                        break;
                
                case "M": //MOSTRA A SOBREPOSIÇÃO DE HORARIOS E MARCA UMA REUNIAO A PARTIR DELA
                        System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= MARCANDO UMA REUNIAO =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" );
                        if(listaDeParticipantes.size() == 0) System.out.println("  OPS! Nao existe participantes na reuniao para mostrar a sobreposicao de horarios.\n" );
                        else{                                                                                              
                            System.out.print("  1. Data de inicio da reuniao < " +"dd/mm/yyyy" +  " >: ");
                            boolean dataValida = false;
                            String inicio = "";

                            while(dataValida == false){
                                try{
                                    inicio = input.nextLine();
                                    formata_data(inicio);
                                    dataValida = true;
                                }
                                catch(DateTimeException e){
                                    System.out.println( "  OPS! A data digitada nao eh valida.");
                                    System.out.println( "  Por favor, digite uma data valida para o inicio da reuniao: " );
                                    System.out.print( "  >>> ");
                                }
                                catch (IndexOutOfBoundsException e){
                                    System.out.println( "  OPS! A data digitada nao esta com o formato padrao pedido." );
                                    System.out.println( "  >>> Insira novamente uma data de inicio para a reuniao:  " );
                                    System.out.print( "  >>> ");
                                }
                                catch(NumberFormatException e){
                                    System.out.println( "  OPS! A data digitada nao esta com o formato padrao pedido." );
                                    System.out.println( "  >>> Insira novamente uma data de inicio para a reuniao:  " );
                                    System.out.print( "  >>> ");
                                }
                            }

                            System.out.print( "\n  2. Data de termino da reuniao < "  +"dd/mm/yyyy" +" >: ");
                            dataValida = false;
                            String fim = "";
                            while(dataValida == false){
                                try{
                                    fim = input.nextLine();
                                    formata_data(fim);
                                    dataValida = true;
                                }
                                catch(DateTimeException e){
                                    System.out.println("  OPS! A data digitada nao eh valida.");
                                    System.out.println( "  Por favor, digite uma data valida para o fim da reuniao:");
                                    System.out.print( "  >>> ");
                                }
                                catch (IndexOutOfBoundsException e){
                                    System.out.println( "  OPS! A data digitada nao esta com o formato padrao pedido." );
                                    System.out.println( "  >>> Insira novamente uma data para a reuniao:   " );
                                    System.out.print( "  >>> ");
                                }
                                catch(NumberFormatException e){
                                    System.out.println( "  OPS! A data digitada nao esta com o formato padrao pedido." );
                                    System.out.println( "  >>> Insira novamente uma data de inicio para a reuniao:  " );
                                    System.out.print( "  >>> ");
                                }
                            }
                                
                            System.out.println();

                            inicio_reuniao = formata_data(inicio);
                            fim_reuniao = formata_data(fim);
                            try{
                                marcador.marcarReuniaoEntre(inicio_reuniao, fim_reuniao, listaDeParticipantes);
                                
                                boolean msg = false;
                                //checa se todos os participantes tem horario disponivel no horario da reuniao
                                for(String participante : listaDeParticipantes){
                                    String [] separaDados = participante.split("\\*");
                                    for(Participante p : marcador.getReuniao().getAgendaParticipantes()){
                                        if(p.getNome().equals(separaDados[0]) && marcador.getReuniao().getParticipantes().containsKey(separaDados[1])){
                                            if(p.getInicio().toLocalDate().isAfter(fim_reuniao) || (p.getFim().toLocalDate().isBefore(inicio_reuniao))) msg = true;
                                        }
                                    }
                                }
                                
                                if(msg == true){
                                    System.out.println("  OPS! Nao sao todos os participantes que estao disponiveis no horario da reuniao.");
                                    System.out.println("  >>> Gostaria de marcar a reuniao mesmo assim? Digite um dos comandos a baixo:");
                                    System.out.println("   " + "SIM " + ": " +"1" +"    NAO " +":"+" 2");
                                    System.out.print( "   >>> " );
                                    int resp = 0;
                                    while(resp != 1 && resp != 2){
                                        
                                        try{
                                            resp = input.nextInt();
                                            input.nextLine();    
                                            if(resp != 1 && resp != 2){
                                                System.out.println( "OPS! Este comando "+ "nao eh valido"  +"." );
                                                System.out.print( ">>> Digite novamente: ");
                                            }
                                        }
                                        catch(InputMismatchException e1){ 
                                            input.nextLine();                                           
                                            System.out.println( "OPS! Este comando "   + "nao eh valido"  +"." );
                                            System.out.print( ">>>Digite novamente: " );
                                        }
                                    }
                                    if(resp == 2){ //remove a reuniao
                                        marcador.cancelarReuniao();
                                        System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="  + " A REUNIAO NAO PODE SER MARCADA " +"=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                                    }
                                    else  System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= " + "REUNIAO MARCADA COM SUCESSO" +" =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                                }

                                else System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= " + "REUNIAO MARCADA COM SUCESSO" +" =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                            }
                            catch(DataException e){
                                System.err.println(e.getMessage());
                                System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="  + " A REUNIAO NAO PODE SER MARCADA " +"=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                            }
                            catch(ParticipanteException e){
                                System.err.println(e.getMessage());
                                System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="  + " A REUNIAO NAO PODE SER MARCADA " +"=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
                            }            
                        }        

                    if(help2() == 1) help();
                    aux = true;
                    break;

                case "O":
                        System.out.println( "=-=-=-=-=-=-=- SOBREPOSICAO DOS HORARIOS DE REUNIAO -=-=-=-=-=-=-=-=" );
                        if(listaDeParticipantes.size() == 0) System.out.println("  OPS! Nao existe participantes na reuniao para mostrar a sobreposicao de horarios.\n" );
                        else marcador.mostraSobreposicao();
                        System.out.println( "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n" );
                        
                        if(help2() == 1) help();
                        aux = true;
                        break;
                
                case "P": //ADICIONA UM PARTICIPANTE NA REUNIAO
                        System.out.println( "=-=-=-=-=-=-=- COLETANDO DADOS DO PARTICIPANTE -=-=-=-=-=-=-=-=" );
                        System.out.print( "  1. Nome do participante: ");
                        String nome = input.nextLine();
                        System.out.print( "\n  2. Crie um ID de 6 numeros: " );
                        boolean ehNum = false;
                        String id = "";
                        while(ehNum == false){
                            try{
                                id = input.nextLine();
                                String [] separa = id.split("");
                                for(String s : separa){
                                    int n = Integer.parseInt(s);
                                }
                                for(String participante : listaDeParticipantes){
                                    String[]sep = participante.split("\\*");
                                    if(sep[1].equals(id)) throw new ParticipanteException( "  OPS! Esse ID " +  "ja existe"+ ". Por favor, insira outro ID: " );
                                }
                                if(separa.length != 6) throw new ParticipanteException( "  OPS! Esse ID " +  "nao possui 6 numeros" +  ". Por favor, insira outro ID: ");
                                ehNum = true;
                            }
                            catch(NumberFormatException e){
                                System.out.println("  OPS! Esse ID " +  "nao possui apenas numeros inteiros" +  ". Por favor, insira outro ID: " );
                                System.out.print( "  >>>  " );
                            }
                            catch(ParticipanteException e){
                                System.err.println(e.getMessage());
                                System.out.print( "  >>>  ");
                            }
                        }

                        System.out.print( "\n  3. Horario de disponibilidade\nPor favor, utilize o formato < " + "dd/mm/yyyy - hh:mm:ss | dd/mm/yyyy - hh:mm:ss"  + " > :\n");
                        String data1 = "";
                        String tempo1 = "";
                        String data2 = "";
                        String tempo2 = "";
                        boolean entrada__Correta = false;
                        while(entrada__Correta == false){
                            try{
                                String horario = input.nextLine();
                                data1 = horario.substring(0, 10);
                                tempo1 = horario.substring(13, 20);
                                formata_tempo(tempo1, data1);
                                data2 = horario.substring(24, 34);
                                tempo2 = horario.substring(37, 44);
                                formata_tempo(tempo2, data2);
                                if(formata_tempo(tempo1, data1).isBefore(formata_tempo(tempo2, data2)) == false) new DateTimeException("  OPS! As datas de disponibilidade do participante nao estao em ordem cronologina.");
                                entrada__Correta = true;
                                System.out.println();
                            }
                            catch (IndexOutOfBoundsException e){
                                System.out.println( "  OPS! O horario digitado nao esta com o formato padrao pedido." );
                                System.out.println( "  >>> Insira novamente um horario para a reuniao:   " );
                            }
                            catch(DateTimeException e){
                                System.out.println( "  OPS! O horario digitado nao eh valido.");
                                System.out.println( "  >>> Insira novamente um horario para a reuniao:   " );
                            }
                        }

                        System.out.println( "\n=-=-=-=-=-=-=-=-=-= "  + "DADOS COLETADOS COM SUCESSO"  + " =-=-=-=-=-=-=-=-=-=\n");

                        
                        String nome_id = nome + "*" + id;
                        listaDeParticipantes.add(nome_id);
                        try{
                            marcador.indicaDisponibilidade(nome_id, formata_tempo(tempo1, data1), formata_tempo(tempo2, data2));
                        }
                        catch(ParticipanteException e){
                            System.err.println(e.getMessage());
                            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + " O PARTICIPANTE NAO FOI ADICIONADO " +"=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                        }
                        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=" + " O PARTICIPANTE FOI ADICIONADO COM SUCESSO " +"=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

                        if(help2() == 1) help();
                        aux = true;
                        break;
                
                case "F": break;

                default:
                    System.out.println( "  OPS! O comando inserido "  + "eh invalido" + ". Por favor, digite um comando valido." );
                    System.out.print( "<REINSIRA UM COMANDO> : " );
                    comando = input.nextLine();
                    System.out.println();
                    aux = false;
                    break;
            }
        }
    }
}