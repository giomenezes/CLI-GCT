package exe.gigi.objeto;

public class Funcionario {
    private Integer idFuncionario;
    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private Integer fkEmpresa;

    public Funcionario () {}

    public Funcionario (String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return """
            
            +--------------------------------------+
              Funcionário: %d            
            +--------------------------------------+
              Nome: %s                     
              Cargo: %s
              Email: %s
            +--------------------------------------+
                """.formatted(idFuncionario, nome, cargo, email);
    }
}
