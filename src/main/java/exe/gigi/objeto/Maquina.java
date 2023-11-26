package exe.gigi.objeto;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.processos.Processo;

import java.util.List;

public class Maquina {
    private Looca looca;
    private Processador cpu;
    private Memoria memoria;
    private List<Volume> volumes;
    private List<Processo> processos;

    public Maquina(Looca looca) {
        this.looca = looca;
        this.cpu = looca.getProcessador();
        this.memoria = looca.getMemoria();
        this.volumes = looca.getGrupoDeDiscos().getVolumes();
        this.processos = looca.getGrupoDeProcessos().getProcessos();
    }

    public String getIdentificadorCpu() {
        return cpu.getIdentificador();
    }

    public String getProcessador() {
        return cpu.getNome();
    }

    public String getMicroarquitetura() {
        return cpu.getMicroarquitetura();
    }

    public Double getUsoCpu(Integer id) {
        return cpu.getUso() + (cpu.getUso() * (id / 100));
    }

    public String getFrequenciaCpu() {
        String cpuFreq = String.valueOf(cpu.getFrequencia());
        return cpuFreq;
    }

    public Integer getQuantidadeCpuLogicas() {
        return cpu.getNumeroCpusLogicas();
    }

    public Integer getQuantidadeCpuFisicas() {
        return cpu.getNumeroCpusFisicas();
    }

    public Double getMemoriaDisponivel() {
        return this.conversorGB(memoria.getDisponivel());
    }

    public Double getMemoriaEmUso(Integer id) {
        return this.conversorGB(memoria.getEmUso()) + (this.conversorGB(memoria.getEmUso()) * (id / 100));
    }

    public Double getMemoriaTotal() {
        return this.conversorGB(memoria.getTotal());
    }

    public Double getVolumeDisponivel() {
        Double volumeTotal = 0.0;
        Double volumeDisponivel = 0.0;

        for (Volume volumeAtual :
                volumes) {
            volumeTotal += volumeAtual.getTotal();
            volumeDisponivel += volumeAtual.getDisponivel();
        }

        return (volumeDisponivel * 100)/volumeTotal;
    }

    public Integer getQuantidadeVolume() {
        return volumes.size();
    }

    public Integer getQuantidadeProcessos() {
        return processos.size();
    }

    public Double conversorGB(Long valor) {
        return valor * Math.pow(10, -9);
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public List<Processo> getProcessos() {
        return processos;
    }
}
