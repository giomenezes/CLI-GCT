package exe.gba.main;

import exe.gba.conexao.Conexao;
import exe.gba.dao.FuncionarioDao;
import exe.gba.objeto.Menu;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Scanner leitor = new Scanner(System.in);
        Scanner leitorString = new Scanner(System.in);

        FuncionarioDao funcionarioDao = new FuncionarioDao(con);

        Menu menu = new Menu(leitor, leitorString, funcionarioDao);

        do {
            System.out.println("Faça seu login");
        } while (!menu.fazerLogin());

        Integer opcaoEscolhida;

        do {
            menu.exibirMenuInicial();
            opcaoEscolhida = menu.solicitarOpcaoInt();

            switch (opcaoEscolhida) {
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    menu.exibirMensagemSair();
                    break;
                default:
                    menu.opcaoInvalida();
            }
        } while (opcaoEscolhida != 0);
    }
}