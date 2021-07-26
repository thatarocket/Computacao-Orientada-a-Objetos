import java.util.*;
import java.time.LocalDate;
import java.time.*;


public class Main{

    public static void help(){
        System.out.println("========================= ESCOLHA UM COMANDO =========================");
        System.out.println(">> Adicionar sala de reunião -------------------------- <COMANDO>: S");
        System.out.println("<< Remover sala de reunião ---------------------------- <COMANDO>: R");
        System.out.println(">> Reservar sala de reunião --------------------------- <COMANDO>: E");
        System.out.println("<< Cancelar sala de reunião --------------------------- <COMANDO>: C");
        System.out.println(">> Imprimir as reservas das salas --------------------- <COMANDO>: I");
        System.out.println("<< Adicionar um participante na reuniao --------------- <COMANDO>: P");
        System.out.println(">> Marcar uma reuniao --------------------------------- <COMANDO>: M");
        System.out.println(">> Mostrar sobreposicao de horários ------------------- <COMANDO>: O");
        System.out.println("<< Ver os comandos disponiveis novamente -------------- <COMANDO>: H");
        System.out.println(">> Finalizar o programa ------------------------------- <COMANDO>: F");
        System.out.println("========================================================================");
    }

    public static LocalDate formata_data (String data){

        String[]separa = data.split("/");
        LocalDate data_formatada = LocalDate.of(Integer.parseInt(separa[2]), Integer.parseInt(separa[1]), Integer.parseInt(separa[0]));
        return data_formatada;
    }

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
        GerenciadorDeSalas gerenciador;
        String comando = "";
        

        while(!comando.equals("F")){
            help();
            System.out.print("<INSIRA UM COMANDO> : ");
            comando = input.nextLine();
            System.out.println();
            switch(comando) {
                // case "S": pensarEmComoUsar(); break;
                // case "R": pensarEmComoUsar(); break;
                case "E":
                        System.out.print("Onde será o local da reuniao? ");
                        String local = input.nextLine();
                        gerenciador = new GerenciadorDeSalas(local);
                        System.out.println("\nQual é o nome da sala a ser reservada? ");
                        String nomeSala = input.nextLine();
                        System.out.print("\nHorario que a sala será utilizada (dd/mm/yyyy - hh:mm:ss | dd/mm/yyyy - hh:mm:ss): ");

                        //gerenciador.adicionaReserva(gerenciador.reservaSalaChamada(nomeSala, dataInicial, dataFinal));
                        break;

                // case "C": pensarEmComoUsar(); break;
                case "I": 
                            System.out.print(">>> Digite o nome do participante: ");
                            String nome_participante = input.nextLine();
                            System.out.print("\n>>> Horario INICIAL de disponibilidade (dd/mm/yyyy - hh:mm:ss): ");
                            
                            // marcador.indicaDisponibilidade(participante, inicio, fim);
                            break;
                
                case "M":
                            if(listaDeParticipantes.size() == 0) System.out.println("OPS! Não existe participantes na reunião para mostrar a sobreposição de horários.\n");
                            else{
                                System.out.println("======== ESCOLHA UM HORARIO PARA A REUNIAO ========");
                                marcador.mostraSobreposicao();
                                System.out.println("===================================================");
                                System.out.print("Horario de inicio da reuniao (dd/mm/yyyy): ");
                                String inicio = input.nextLine();
                                System.out.print("\nHorario de termino da reuniao (dd/mm/yyyy): ");
                                String fim = input.nextLine();
                                System.out.println();

                                inicio_reuniao = formata_data(inicio);
                                fim_reuniao = formata_data(fim);
                                
                                //of(int year, int month, int dayOfMonth)
                                marcador.marcarReuniaoEntre(inicio_reuniao, fim_reuniao, listaDeParticipantes);
                            }
                            break;

                // case "O": pensarEmComoUsar(); break;
                
                case "P":
                        System.out.println("=-=-=-=-=-=-=- COLETANDO DADOS DO PARTICIPANTE -=-=-=-=-=-=-=-=");
                        System.out.print("NOME DO PARTICIPANTE: ");
                        String nome = input.nextLine();
                        System.out.print("\nCRIE UM ID DE 6 NÚMEROS: ");
                        String id = input.nextLine();
                        System.out.print("\nHORARIO DE DISPONIBILIDADE -> OBS: utilize o formato\n< dd/mm/yyyy - hh:mm:ss | dd/mm/yyyy - hh:mm:ss >\n");
                        System.out.print("Horario: ");
                        String horario = input.nextLine();
                        System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

                        String data1 = horario.substring(0, 9);
                        String tempo1 = horario.substring(13, 20);
                        String data2 = horario.substring(24, 33);
                        String tempo2 = horario.substring(37, 44);
                        
                        String nome_id = nome + "*" + id;
                        marcador.indicaDisponibilidade(nome_id, formata_tempo(tempo1, data1), formata_tempo(tempo2, data2));
                        break;
                
                // case "H": help(); break;
                case "F": break;

                default:
                    System.out.println("ERRO: O comando inserido é invalido. Por favor, digite um comando válido.");
                    help();
                    System.out.print("<REINSIRA UM COMANDO> : ");
                    comando = input.nextLine();
                    System.out.println();
                    break;
            }
        }


        //   - Identificador: ID? -> 6 numeros. Verificar se tem repetido, ou 000000, ou negativo

    }
}