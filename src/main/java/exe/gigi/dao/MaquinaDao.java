package exe.gigi.dao;

import exe.gigi.objeto.Maquina;
import exe.gigi.objeto.Servidor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDao {
    private JdbcTemplate con;
    public MaquinaDao(JdbcTemplate con) { this.con = con;}

    public void inserirCPU(Maquina maquina) {
        con.update("""
                INSERT INTO registro (valor_registro, data_registro, fk_componente, fk_medida) values (%.2f, now(), 1, 1);
                """.formatted(maquina.getUsoCpu()));
    }

    public void inserirRAM(Maquina maquina) {
        con.update("""
                INSERT INTO registro (valor_registro, data_registro, fk_componente, fk_medida) values (%.2f, now(), 2, 2);
                """.formatted(maquina.getMemoriaEmUso()));
    }

    public void inserirDisco(Maquina maquina) {
        Double armazenamentoUtilizado = 100.0 - maquina.getVolumeDisponivel();

        con.update("""
                INSERT INTO registro (valor_registro, data_registro, fk_componente, fk_medida) values (%.2f, now(), 1, 3);
                """.formatted(armazenamentoUtilizado));
    }

}
