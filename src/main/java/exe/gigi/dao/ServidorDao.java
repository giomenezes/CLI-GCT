package exe.gigi.dao;

import exe.gigi.objeto.Servidor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ServidorDao {
    private JdbcTemplate con;

    public ServidorDao(JdbcTemplate con) {
        this.con = con;
    }

    public List<Servidor> listar() {
        return con.query("SELECT id_servidor, nome, codigo, descricao, localizacao, status FROM servidor;", new BeanPropertyRowMapper<>(Servidor.class));
    }
}
