package model;

public class PessoaModel {
    private Integer codigo;
    private String nome;
    private String endereco;
    private String sexo;
    private String dataNascimento;
    private String estadoCivil;
    private byte registroAtivo;

    public  PessoaModel(String nome, String endereco, String sexo, String dataNascimento, String estadoCivil, byte registroAtivo){
        setRegistroAtivo(registroAtivo);
        setEstadoCivil(estadoCivil);
        setDataNascimento(dataNascimento);
        setEndereco(endereco);
        setNome(nome);
        setSexo(sexo);
    }

    public PessoaModel(){

    }
    public  PessoaModel(Integer codigo, String nome, String endereco, String sexo, String dataNascimento, String estadoCivil, byte registroAtivo) {
        this(nome, endereco, sexo, dataNascimento, estadoCivil, registroAtivo);
        setCodigo(codigo);
    }
        public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setRegistroAtivo(byte registroAtivo) {
        this.registroAtivo = registroAtivo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public byte getRegistroAtivo() {
        return registroAtivo;
    }
}
