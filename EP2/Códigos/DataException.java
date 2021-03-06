public class DataException extends Exception{
    
    private static final long serialVersionUID = 22222222222222L;
    private String msg;
    
    // constrói um objeto ParticipanteException com a mensagem passada por parâmetro
    public DataException(String msg){
        super(msg);
        this.msg = msg;
    }

    // contrói um objeto ParticanteException com mensagem e a causa dessa exceção, utilizado para encadear exceptions
    public String getMessage(){
        return this.msg;
    }
}
