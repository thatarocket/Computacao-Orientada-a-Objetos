public class ReuniaoException extends Exception{

    private static final long serialVersionUID = 44444444444444444L;
    private String msg;
    // constrói um objeto ReuniaoException com a mensagem passada por parâmetro
    public ReuniaoException(String msg){
        super(msg);
        this.msg = msg;
    }

    // contrói um objeto ReuniaoException com mensagem e a causa dessa exceção, utilizado para encadear exceptions
    public String getMessage(){
        return this.msg;
    }
}
