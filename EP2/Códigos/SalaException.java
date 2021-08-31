public class SalaException extends Exception {
    private static final long serialVersionUID = 55555555555L;
    private String msg;
    // constrói um objeto ParticipanteException com a mensagem passada por parâmetro
    public SalaException(String msg){
        super(msg);
        this.msg = msg;
    }

    // contrói um objeto ParticanteException com mensagem e a causa dessa exceção, utilizado para encadear exceptions
    public String getMessage(){
        return this.msg;
    }
}
