package exe.gigi.dao;

import exe.gigi.objeto.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FuncionarioDao {
    private JdbcTemplate con;

    public FuncionarioDao(JdbcTemplate con) {
        this.con = con;
    }

    public List<Funcionario> listar () {
        return con.query("SELECT id_funcionario, nome, cargo, email FROM funcionario;", new BeanPropertyRowMapper<>(Funcionario.class));
    }

    public List<Funcionario> getFuncionarioPorLogin (Funcionario funcionario) {
        return con.query("SELECT * FROM funcionario WHERE email = ? AND senha = ?", new BeanPropertyRowMapper<>(Funcionario.class), funcionario.getEmail(), funcionario.getSenha());
    }
}
