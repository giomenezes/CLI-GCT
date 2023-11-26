package exe.gigi.dao;

import com.github.britooo.looca.api.group.processos.Processo;
import exe.gigi.objeto.Maquina;
import exe.gigi.objeto.Servidor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigInteger;

public class MaquinaDao {
    private JdbcTemplate con;
    public MaquinaDao(JdbcTemplate con) { this.con = con;}

    public void inserirCPU(Maquina maquina, Integer id) {
        con.update("""
                INSERT INTO registro (valor_registro, data_registro, fk_componente, fk_medida) values (%.2f, now(), 1, 1);
                """.formatted(maquina.getUsoCpu(id)));
    }

    public void inserirRAM(Maquina maquina, Integer id) {
        con.update("""
                INSERT INTO registro (valor_registro, data_registro, fk_componente, fk_medida) values (%.2f, now(), 2, 2);
                """.formatted(maquina.getMemoriaEmUso(id)));
    }

    public void inserirDisco(Maquina maquina) {
        Double armazenamentoUtilizado = 100.0 - maquina.getVolumeDisponivel();

        con.update("""
                INSERT INTO registro (valor_registro, data_registro, fk_componente, fk_medida) values (%.2f, now(), 1, 3);
                """.formatted(armazenamentoUtilizado));
    }

    public void inserirProcesso(Maquina maquina, Servidor servidor) {
        Integer pid;
        String nome;
        Double uso_cpu;
        Double uso_memoria;
        Double bytes_utilizados;
        Double swap_utilizada;
        Integer fk_servidor = servidor.getIdServidor();

        for (Processo processoAtual : maquina.getProcessos()) {
            pid = processoAtual.getPid();
            nome = processoAtual.getNome();
            uso_cpu = processoAtual.getUsoCpu();
            uso_memoria = processoAtual.getUsoMemoria();
            bytes_utilizados = maquina.conversorGB(processoAtual.getBytesUtilizados());
            swap_utilizada = maquina.conversorGB(processoAtual.getMemoriaVirtualUtilizada());

            con.update("""
            INSERT INTO processo (pid, nome, uso_cpu, uso_memoria, bytes_utilizados, swap_utilizada, data_registro, fk_servidor) VALUES (%d, '%s',%f, %f, %f, %f, now(), %d);
            """.formatted(pid, nome, uso_cpu, uso_memoria, bytes_utilizados, swap_utilizada, fk_servidor));
        }
    }
}
