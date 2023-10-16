package exe.gba.objeto;

import exe.gba.dao.FuncionarioDao;

import java.util.Scanner;

public class Menu {
    private Scanner leitor;
    private Scanner leitorString;
    private FuncionarioDao funcionarioDao;

    public Menu(Scanner leitor, Scanner leitorString, FuncionarioDao funcionarioDao) {
        this.leitor = leitor;
        this.leitorString = leitorString;
        this.funcionarioDao = funcionarioDao;
    }

    public Boolean fazerLogin() {
        System.out.println("Digite o seu email: ");
        String email = leitorString.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = leitorString.nextLine();

        Funcionario funcionario = new Funcionario(email, senha);

        if (funcionarioDao.getFuncionarioPorLogin(funcionario).isEmpty()) {
            System.out.println("Usuário inválido");
            return false;
        }
        System.out.println("Login efetuado, indo para o menu... ");
        return true;
    }

    public void exibirMenuInicial() {
        System.out.println(
            """
            +--------------------------------------+
            | StockSafe Solutions                  |
            +--------------------------------------+
            | 1) Verificar Dados                   |
            | 2) Mudar configurações de exibição   |
            |                                      |
            | 0) Sair                              |
            +--------------------------------------+
                """);
    }

    public Integer solicitarOpcaoInt() {
        System.out.println("Selecione uma opção:");
        return leitor.nextInt();
    }

    public String solicitarOpcaoString() {
        System.out.println("Selecione uma opção:");
        return leitorString.nextLine();
    }

    public void exibirMensagemSair () { System.out.println("Saindo... "); }

    public void opcaoInvalida () { System.out.println("Opção inválida"); }
}
