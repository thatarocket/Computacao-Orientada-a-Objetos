import java.time.*;

public class Horario {

    //transforma a String DATA ("dd/mm/aaaa") em LocalDate
    public LocalDate formata_data (String data){

        String[]separa = data.split("/");
        LocalDate data_formatada = LocalDate.of(Integer.parseInt(separa[2]), Integer.parseInt(separa[1]), Integer.parseInt(separa[0]));
        return data_formatada;
    }

    //transforma a String tempo ("hh:mm:ss") e data ("dd/mm/aaaa") em LocalDateTime
    public LocalDateTime formata_tempo (String tempo, String data){
        
        String[]separa = tempo.split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(separa[0]), Integer.parseInt(separa[1]), Integer.parseInt(separa[2]));
        LocalDateTime tempo_formatado = LocalDateTime.of(formata_data(data), time);
        return tempo_formatado;
    }


    
}
