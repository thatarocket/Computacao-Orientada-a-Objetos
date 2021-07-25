import java.util.*;
import java.time.LocalDate;


public class Main{

    public static void help(){
        System.out.println("============== Escolha o comando desejado ================");
        System.out.println("> Adicionar sala de reunião - Digite: S");
        System.out.println("> Remover sala de reunião - Digite: R");
        System.out.println("> Reservar sala de reunião - Digite: E");
        System.out.println("> Cancelar sala de reunião - Digite: C");
        System.out.println("> Imprimir as reservas de sala existentes - Digite: I");
        System.out.println("> Adicionar um participante na reuniao - Digite: P");
        System.out.println("> Marcar reuniao - Digite: M");
        System.out.println("> Registrar disponibilidade - Digite: D");
        System.out.println("> Mostrar sobreposicao de horários - Digite: O");
        System.out.println("> Ver os comandos disponiveis novamente - Digite: H");
        System.out.println("> Finalizar o programa: F");
        System.out.println("===========================================================");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Collection <String> listaDeParticipantes = new LinkedList<>();
        MarcadorDeReuniao marcador = new MarcadorDeReuniao();
        LocalDate inicio_reuniao;
        LocalDate fim_reuniao;
        GerenciadorDeSalas gerenciador;
        String comando = input.nextLine();
        help();

        while(!comando.equals("F")){
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

                        gerenciador.adicionaReserva(gerenciador.reservaSalaChamada(nomeSala, dataInicial, dataFinal));
                        break;

                // case "C": pensarEmComoUsar(); break;
                case "I": 
                            System.out.print(">>> Digite o nome do participante: ");
                            String nome_participante = input.nextLine();
                            System.out.print("\n>>> Horario INICIAL de disponibilidade (dd/mm/yyyy - hh:mm:ss): ");
                            
                            // marcador.indicaDisponibilidade(participante, inicio, fim);
                            break;
                case "M":
                            System.out.println("======== ESCOLHA UM HORARIO PARA A REUNIAO ========");
                            marcador.mostraSobreposicao();
                            System.out.println("===================================================");
                            System.out.print("Horario de inicio da reuniao (dd/mm/yyyy): ");
                            String inicio = input.nextLine();
                            System.out.print("\nHorario de termino da reuniao (dd/mm/yyyy): ");
                            String fim = input.nextLine();
                            System.out.println();
                            String[]separa_inicio = inicio.split("/");
                            String[]separa_fim = inicio.split("/");
                            inicio_reuniao = LocalDate.of(Integer.parseInt(separa_inicio[2]), Integer.parseInt(separa_inicio[1]), Integer.parseInt(separa_inicio[0]));
                            fim_reuniao = LocalDate.of(Integer.parseInt(separa_fim[2]), Integer.parseInt(separa_fim[1]), Integer.parseInt(separa_fim[0]));
                            //of(int year, int month, int dayOfMonth)
                            marcador.marcarReuniaoEntre(inicio_reuniao, fim_reuniao, listaDeParticipantes);
                            break;
                // case "O": pensarEmComoUsar(); break;
                // case "P": pensarEmComoUsar(); break;
                // case "H": help(); break;
        
                default:
                    System.out.println("ERRO: O comando inserido é invalido. Por favor, digite um comando válido.");
                    help();
                    System.out.print("Comando: ");
                    comando = input.nextLine();
                    System.out.println();
                    break;
                
                comando = input.nextLine();
            }
        }


        //   - Identificador: ID? -> 6 numeros. Verificar se tem repetido, ou 000000, ou negativo

    }
}