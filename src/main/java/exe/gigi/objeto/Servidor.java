package exe.gigi.objeto;

public class Servidor {
    private Integer idServidor;
    private String nome;
    private String codigo;
    private String descricao;

    public Servidor() {
    }

    public Integer getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(Integer idServidor) {
        this.idServidor = idServidor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return """
                
        +------------------------------------------------------+
          Servidor: %s            
        +------------------------------------------------------+
          Nome: %s                     
          Descrição: %s
        +------------------------------------------------------+
        """.formatted(codigo, nome, descricao);
    }
}
