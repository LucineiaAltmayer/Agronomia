/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.time.LocalDate;
/**
 *
 * @author 8
 */
public class Inseminacao {
    private Integer codigo;
    private LocalDate data_parto;   
    private LocalDate data_inseminacao;
    private Integer situacao;
    private String obs;
    private Bovinos touro;
    private Vaca vaca;

    @Override
    public String toString() {
        return data_inseminacao.toString();
    }

    
    public Bovinos getTouro() {
        return touro;
    }

    public void setTouro(Bovinos touro) {
        this.touro = touro;
    }

    public Vaca getVaca() {
        return vaca;
    }

    public void setVaca(Vaca vaca) {
        this.vaca = vaca;
    }

    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData_parto() {
        return data_parto;
    }

    public void setData_parto(LocalDate data_parto) {
        this.data_parto = data_parto;
    }

    public LocalDate getData_inseminacao() {
        return data_inseminacao;
    }

    public void setData_inseminacao(LocalDate data_inseminacao) {
        this.data_inseminacao = data_inseminacao;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
