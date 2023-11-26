package exe.gigi.objeto;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.processos.Processo;
import exe.gigi.dao.FuncionarioDao;
import exe.gigi.dao.MaquinaDao;
import exe.gigi.dao.ServidorDao;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner leitor;
    private final Scanner leitorString;
    private final FuncionarioDao funcionarioDao;
    private final ServidorDao servidorDao;
    private MaquinaDao maquinaDao;
    private final Looca looca;

    public Menu(Scanner leitor, Scanner leitorString, FuncionarioDao funcionarioDao, ServidorDao servidorDao, MaquinaDao maquinaDao) {
        this.leitor = leitor;
        this.leitorString = leitorString;
        this.funcionarioDao = funcionarioDao;
        this.servidorDao = servidorDao;
        this.maquinaDao = maquinaDao;
        this.looca = new Looca();
    }

    public Funcionario fazerLogin() {
        System.out.println("Digite o seu email: ");
        String email = leitorString.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = leitorString.nextLine();

        Funcionario funcionario = new Funcionario(email, senha);

        if (funcionarioDao.getFuncionarioPorLogin(funcionario).isEmpty()) {
            System.out.println("Usuário inválido");
            return null;
        }

        funcionario = funcionarioDao.getFuncionarioPorLogin(funcionario).get(0);

        return funcionario;
    }

    public void exibirMenuInicial(Funcionario funcionario, Servidor servidor) {
        System.out.println(
            """
            +------------------------------------------------------+
              Grey Cloud Transactions
              Bem vindo, %s!              
            +------------------------------------------------------+
            | 1) Listar usuários cadastrados                       |
            | 2) Escolher servidor                                 |
            | 3) Listar informações do servidor                    |
            | 4) Verificar dados em uso                            |
            | 5) Verificar volumes                                 |
            | 6) Verificar processos                               |
            |                                                      |
            | 0) Sair                                              |
            +------------------------------------------------------+""".formatted(funcionario.getNome()));

        if (servidor != null) {
            System.out.print("  Servidor escolhido: ");
            System.out.println(servidor);
        }
    }

    public Integer solicitarOpcaoInt() {
        System.out.println("Selecione uma opção:");
        return leitor.nextInt();
    }

    public String solicitarOpcaoString() {
        System.out.println("Selecione uma opção:");
        return leitorString.nextLine();
    }

    public void exibirMensagemSair () { System.out.println("Saindo... ");
    System.exit(0);}

    public void opcaoInvalida () { System.out.println("Opção inválida"); }

    public void listarFuncionarios() {
        System.out.println(funcionarioDao.listar());
    }
    public Servidor escolherServidor() {
        List<Servidor> servidores = servidorDao.listar();
        System.out.println(servidores);

        String codigoServidor = this.solicitarOpcaoString();
        for (Servidor servidorDaVez : servidores) {
            if (servidorDaVez.getCodigo().equalsIgnoreCase(codigoServidor)){
                Servidor servidor = servidorDaVez;
                return servidor;
            }
        }

        System.out.println("Código inválido!");
        return null;
    }

    public void listarInformacoes(Servidor servidor) {
        Maquina maquina = new Maquina(looca);

        if (servidor != null) {
            System.out.println("""
                +------------------------------------------------------+
                  Servidor: %s
                +------------------------------------------------------+
                  ID: %s
                  Nome: %s
                  Microarquitetura: %s
                  Qtd. CPUs lógicas: %d
                  Qtd. CPUs físicas: %d
                  
                  Total de memória: %.2fGB
                  Qtd. de volumes: %d
                +------------------------------------------------------+""".formatted(servidor.getCodigo(), maquina.getIdentificadorCpu(), maquina.getProcessador(), maquina.getMicroarquitetura(),
                    maquina.getQuantidadeCpuLogicas(), maquina.getQuantidadeCpuFisicas(), maquina.getMemoriaTotal(), maquina.getQuantidadeVolume()));
            return;
        }
        System.out.println("Por favor, selecione um servidor primeiro.");
    }

    public void verificarDados(Servidor servidor) {
        Maquina maquina = new Maquina(looca);

        if (servidor != null) {
            System.out.println("""
                +------------------------------------------------------+
                  Servidor: %s
                +------------------------------------------------------+
                  CPU: %.2f%%
                  Frequência: %s
                  
                  Memória: %.2fGB
                  Memória disponível: %.2fGB
                  
                  Armazenamento disponível: %.2f%%
                  
                  Qtd. de processos: %d
                +------------------------------------------------------+""".formatted(servidor.getCodigo(), maquina.getUsoCpu(servidor.getIdServidor()),
                    maquina.getFrequenciaCpu(), maquina.getMemoriaEmUso(servidor.getIdServidor()), maquina.getMemoriaDisponivel(),
                    maquina.getVolumeDisponivel(), maquina.getQuantidadeProcessos()));

            maquinaDao.inserirCPU(maquina, servidor.getIdServidor());
            maquinaDao.inserirRAM(maquina, servidor.getIdServidor());
            maquinaDao.inserirDisco(maquina);
            return;
        }
        System.out.println("Por favor, selecione um servidor primeiro.");
    }

    public void verificarVolumes() {
        Maquina maquina = new Maquina(looca);

        for (Volume volumeAtual :
                maquina.getVolumes()) {
            System.out.println(volumeAtual);
        }
    }

    public void verificarProcessos(Servidor servidor) {
        Maquina maquina = new Maquina(looca);

        for (Processo processoAtual :
                maquina.getProcessos()) {
            System.out.println(processoAtual);
        }

        maquinaDao.inserirProcesso(maquina, servidor);
    }
}
