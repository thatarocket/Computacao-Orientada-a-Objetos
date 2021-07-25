import java.util.Scanner;

public class Main{

    void help(){
        System.out.println("Escolha o comando desejado:");
        System.out.println("Adicionar sala de reunião - Digite: S");
        System.out.println("Remover sala de reunião - Digite: R");
        System.out.println("Reservar sala de reunião - Digite: E");
        System.out.println("Cancelar sala de reunião - Digite: C");
        System.out.println("Imprimir as reservas de sala existentes - Digite: I");
        System.out.println("Criar participante - Digite: P");
        System.out.println("Marcar reuniao - Digite: M");
        System.out.println("Registrar disponibilidade - Digite: D");
        System.out.println("Mostrar sobreposicao de horários - Digite: O");
        System.out.println("Ver os comandos disponiveis novamente - Digite: H");
        System.out.println("Finalizar o programa: F");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String comando = input.nextLine();
        help();
        switch(comando) {
            case "S": pensarEmComoUsar(); break;
            case "R": pensarEmComoUsar(); break;
            case "E": pensarEmComoUsar(); break;
            case "C": pensarEmComoUsar(); break;
            case "I": pensarEmComoUsar(); break;
            case "M": pensarEmComoUsar(); break;
            case "O": pensarEmComoUsar(); break;
            case "P": pensarEmComoUsar(); break;
            case "H": help(); break;
            case "F": return; 
            default:
                System.out.println("ERRO: Insira um comando valido");
                help();
                comando = input.nextLine();
                break;
          comando = input.nextLine();
        }

        //   - Identificador: ID? -> 6 numeros. Verificar se tem repetido, ou 000000, ou negativo

    }
}