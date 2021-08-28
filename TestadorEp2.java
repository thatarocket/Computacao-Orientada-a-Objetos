import java.util.*;
import java.time.*;

public class TestadorEp2 {

	public static int errorsCount = 0;

	public static void main(String[] args) {

		case1();
		case2();
		case3();
		case4();
		case5();
		case6();

		System.out.println("Errors: " + errorsCount + "/10");
	}

	public static void case1() {

		MarcadorDeReuniao mr = new MarcadorDeReuniao();
		List<String> p = List.of("Clovis", "Jeremias", "Robson");

		System.out.println("----------------------------- Teste 1 -----------------------------");

		// Clovis: inicio: 16/08/2021 10:00:00 fim:16/08/2021 12:00:00
		// Jeremias: inicio: 16/08/2021 16:00:00 fim: 16/08/2021 17:30:00
		// Clovis: inicio: 16/08/2021 15:00:00 fim: 16/08/2021 18:15:00
		// Robson: inicio: 14/08/2021 10:36:00 fim: 15/08/2021 8:00:00

		try {
			mr.indicaDisponibilidadeDe("Clovis", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			mr.indicaDisponibilidadeDe("Jeremias", LocalDateTime.of(2021, 8, 16, 16, 0, 0), LocalDateTime.of(2021, 8, 16, 17, 30, 0));
			mr.indicaDisponibilidadeDe("Clovis", LocalDateTime.of(2021, 8, 16, 15, 0, 0), LocalDateTime.of(2021, 8, 16, 18, 15, 0));
			mr.indicaDisponibilidadeDe("Robson", LocalDateTime.of(2021, 8, 14, 10, 36, 0), LocalDateTime.of(2021, 8, 15, 8, 0, 0));

			mr.mostraSobreposicao();

			mr.marcarReuniaoEntre(LocalDate.of(2021, 8, 16), LocalDate.of(2021, 8, 19), p);
		}
		catch(Throwable e){
			e.printStackTrace();
			errorsCount++;
			System.out.println("---ERRO NO TESTE 01---");
		}
	}

	public static void case2() {
		
		MarcadorDeReuniao mr = new MarcadorDeReuniao();
		List<String> p = List.of("Clovis", "Jeremias", "Robson", "Mariana");
		System.out.println("----------------------------- Teste 2 -----------------------------");

		try {
			mr.mostraSobreposicao();
			mr.marcarReuniaoEntre(LocalDate.of(2021, 8, 16), LocalDate.of(2021, 8, 19), p);
			mr.mostraSobreposicao();
			
		}
		catch(Throwable e){
			e.printStackTrace();
			errorsCount++;
			System.out.println("---ERRO NO TESTE 02---");

		}

		System.out.println("----------------------------- Teste 3 -----------------------------");

		try {
			mr.indicaDisponibilidadeDe("Clovis", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			mr.indicaDisponibilidadeDe("Jeremias", LocalDateTime.of(2021, 8, 16, 16, 0, 0), LocalDateTime.of(2021, 8, 16, 17, 30, 0));
			mr.indicaDisponibilidadeDe("Clovis", LocalDateTime.of(2021, 8, 16, 15, 0, 0), LocalDateTime.of(2021, 8, 16, 18, 15, 0));
			mr.indicaDisponibilidadeDe("Robson", LocalDateTime.of(2021, 8, 14, 10, 36, 0), LocalDateTime.of(2021, 8, 15, 8, 0, 0));
			mr.indicaDisponibilidadeDe("Robson", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));

			mr.mostraSobreposicao();

			mr.marcarReuniaoEntre(LocalDate.of(2021, 6, 12), LocalDate.of(2021, 8, 10), p);
		}
		catch(Throwable e){
			e.printStackTrace();
			errorsCount++;
			System.out.println("---ERRO NO TESTE 03---");
		}
	}

	public static void case3(){

		GerenciadorDeSalas gs = new GerenciadorDeSalas();
		MarcadorDeReuniao mr = new MarcadorDeReuniao();
		List<String> p = List.of("Clovis", "Jeremias", "Robson");

		System.out.println("----------------------------- Teste 4 -----------------------------");

		try {

			mr.indicaDisponibilidadeDe("Clovis", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			mr.indicaDisponibilidadeDe("Jeremias", LocalDateTime.of(2021, 8, 16, 16, 0, 0), LocalDateTime.of(2021, 8, 16, 17, 30, 0));
			mr.indicaDisponibilidadeDe("Clovis", LocalDateTime.of(2021, 8, 16, 15, 0, 0), LocalDateTime.of(2021, 8, 16, 18, 15, 0));
			mr.indicaDisponibilidadeDe("Robson", LocalDateTime.of(2021, 8, 14, 10, 36, 0), LocalDateTime.of(2021, 8, 15, 8, 0, 0));

			mr.marcarReuniaoEntre(LocalDate.of(2021, 8, 16), LocalDate.of(2021, 8, 19), p);
		}
		catch(Throwable e){
			e.printStackTrace();
			errorsCount++;
			System.out.println("---ERRO NO TESTE 04---");
		}

		System.out.println("----------------------------- Teste 5 -----------------------------");

		try {

			gs.adicionaSalaChamada("Sala Roberta", 42, "Sim");
			gs.adicionaSalaChamada("Sala Nina", 3, "Pspspspsps");
			gs.adicionaSalaChamada("Sala Dora", 666, "The Destroyer");
			// gs.adicionaSala(new Sala("Sala Dora", 666, "The Destroyer"));
			
			System.out.println(gs.listaDeSalas());
			Reserva r1 = gs.reservaSalaChamada("Sala Roberta", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			
			Reserva r2 = gs.reservaSalaChamada("Sala Roberta", LocalDateTime.of(2021, 8, 16, 8, 0, 0), LocalDateTime.of(2021, 8, 16, 9, 0, 0));
			
			Reserva r3 = gs.reservaSalaChamada("Sala Roberta", LocalDateTime.of(2021, 8, 16, 17, 0, 0), LocalDateTime.of(2021, 8, 16, 18, 0, 0));
	
			Reserva r4 = gs.reservaSalaChamada("Sala Nina", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			Reserva r5 = gs.reservaSalaChamada("Sala Dora", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			System.out.println(gs.listaDeSalas());
			
			gs.imprimeReservasDaSala("Sala Roberta");
			gs.cancelaReserva(r2);
			System.out.println(gs.listaDeSalas());
		}
		catch(Throwable e){
			e.printStackTrace();
			errorsCount++;
			System.out.println("---ERRO NO TESTE 05---");

		}
	}

	public static void case4(){

		try{
			System.out.println("----------------------------- Teste 6 -----------------------------");
			GerenciadorDeSalas gs = new GerenciadorDeSalas();
			
			Reserva r6 = gs.reservaSalaChamada("Sala Clovis", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			System.out.println("NAO DEU EXCEPTION");
			errorsCount++;
			System.out.println("---ERRO NO TESTE 06---");


			System.out.println("Reserva Cancelada");
			gs.cancelaReserva(r6);
		}
		catch(Throwable e){
			System.out.println("EXCEPTION");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static void case5() {

		System.out.println("----------------------------- Teste 7 -----------------------------");

		GerenciadorDeSalas gs = new GerenciadorDeSalas();
		MarcadorDeReuniao mr = new MarcadorDeReuniao();
		List<String> p = List.of("Clovis", "Jeremias", "Robson");

		try {
			gs.adicionaSalaChamada("Sala Roberta", 42, "Sim");
			gs.adicionaSalaChamada("Sala Nina", 3, "Pspspspsps");
		
			Reserva r1 = gs.reservaSalaChamada("Sala Roberta", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			System.out.println("ERRO 1");
			// Reserva r2 = gs.reservaSalaChamada("Sala Roberta", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			// System.out.println("ERRO 2");
			// Reserva r3 = gs.reservaSalaChamada("Sala Roberta", LocalDateTime.of(2021, 8, 16, 11, 0, 0), LocalDateTime.of(2021, 8, 16, 11, 30, 0));
			// System.out.println("ERRO 3");
			gs.imprimeReservasDaSala("Sala Roberta");

			System.out.println("----------------------------- Teste 8 -----------------------------");

			Reserva r4 = gs.reservaSalaChamada("Sala Nina", LocalDateTime.of(2021, 8, 17, 13, 45, 0), LocalDateTime.of(2021, 8, 17, 11, 0, 0));
			Reserva r5 = gs.reservaSalaChamada("Sala Nina", LocalDateTime.of(2021, 8, 17, 12, 0, 0), LocalDateTime.of(2021, 8, 17, 12, 45, 0));

			gs.imprimeReservasDaSala("Sala Nina");
		}
		catch(Throwable e){
			e.printStackTrace();
			errorsCount++;
			System.out.println(" !!! ERRO NO TESTE 08 !!!!");

		}
	}

	public static void case6() {

		System.out.println("----------------------------- Teste 9 -----------------------------");

		MarcadorDeReuniao mr = new MarcadorDeReuniao();
		List<String> p = List.of("Clovis", "Jeremias", "Robson");

		try{
			mr.indicaDisponibilidadeDe("Clovis", LocalDateTime.of(2021, 8, 16, 10, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 0, 0));
			mr.indicaDisponibilidadeDe("Jeremias", LocalDateTime.of(2021, 8, 16, 11, 0, 0), LocalDateTime.of(2021, 8, 16, 12, 30, 0));
			mr.indicaDisponibilidadeDe("Clovis", LocalDateTime.of(2021, 8, 16, 10, 50, 0), LocalDateTime.of(2021, 8, 16, 11, 25, 0));
			mr.indicaDisponibilidadeDe("Robson", LocalDateTime.of(2021, 8, 14, 10, 37, 0), LocalDateTime.of(2021, 8, 17, 8, 0, 0));
			mr.indicaDisponibilidadeDe("Mariana", LocalDateTime.of(2021, 8, 14, 10, 37, 0), LocalDateTime.of(2021, 8, 17, 8, 0, 0));

			mr.mostraSobreposicao();

			mr.marcarReuniaoEntre(LocalDate.of(2021, 8, 16), LocalDate.of(2021, 8, 17), p);
		}
		catch(Throwable e){
			e.printStackTrace();
			errorsCount++;
			System.out.println(" !!!!! ERRO NO TESTE 09 !!!!");
		}

		System.out.println("----------------------------- Teste 10 -----------------------------");

		try{
			mr.mostraSobreposicao();
		}
		catch(Throwable e){
			e.printStackTrace();
			errorsCount++;
			System.out.println("---ERRO NO TESTE 10---");
		}
	}
}