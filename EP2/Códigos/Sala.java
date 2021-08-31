public class Sala {
    
    //atributos da classe
    private String nome;
    private String local;
    private int capacidade;
    private String observacoes;

    //construtor da classe
    public Sala(String nome, String local, int capacidade, String observacoes){
        this.nome = nome;
        this.local = local;
        this.capacidade = capacidade;
        this.observacoes = observacoes;
    }

    public String getNome(){
        return this.nome;
    }

    public String getLocal(){
        return this.local;
    }

    public int getCapacidade(){
        return this.capacidade;
    }

    public String getObservacoes(){
        return this.observacoes;
    }

}
