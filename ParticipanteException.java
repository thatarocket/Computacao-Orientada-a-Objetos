public class ParticipanteException extends Exception{

    private static final long serialVersionUID = 11111111111111111L;
    private String msg;
    // constrói um objeto ParticipanteException com a mensagem passada por parâmetro
    public ParticipanteException(String msg){
        super(msg);
        this.msg = msg;
    }

    // contrói um objeto ParticanteException com mensagem e a causa dessa exceção, utilizado para encadear exceptions
    public String getMessage(){
        return this.msg;
    }
}
