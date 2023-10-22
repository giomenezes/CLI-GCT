package exe.gigi.main;

import com.github.britooo.looca.api.core.Looca;
import exe.gigi.conexao.Conexao;
import exe.gigi.dao.FuncionarioDao;
import exe.gigi.dao.MaquinaDao;
import exe.gigi.dao.ServidorDao;
import exe.gigi.objeto.Funcionario;
import exe.gigi.objeto.Menu;
import exe.gigi.objeto.Servidor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Scanner leitor = new Scanner(System.in);
        Scanner leitorString = new Scanner(System.in);

        FuncionarioDao funcionarioDao = new FuncionarioDao(con);
        ServidorDao servidorDao = new ServidorDao(con);
        MaquinaDao maquinaDao = new MaquinaDao(con);
        Servidor servidor = null;

        Menu menu = new Menu(leitor, leitorString, funcionarioDao, servidorDao, maquinaDao);

        Funcionario funcionario;

        do {
            System.out.println("Realize seu login:");
            funcionario = menu.fazerLogin();
        } while (funcionario == null);

        do {
            menu.exibirMenuInicial(funcionario, servidor);
            switch (menu.solicitarOpcaoInt()) {
                case 1 -> menu.listarFuncionarios();
                case 2 -> servidor = menu.escolherServidor();
                case 3 -> menu.listarInformacoes(servidor);
                case 4 -> menu.verificarDados(servidor);
                case 5 -> menu.verificarVolumes();
                case 6 -> menu.verificarProcessos();
                case 0 -> menu.exibirMensagemSair();
                default -> menu.opcaoInvalida();
            }
        } while(true);


    }
}