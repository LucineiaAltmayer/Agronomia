/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.inseminacao;
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
    private Integer cod_touro;
    private Integer brinco;

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

    public Integer getCod_touro() {
        return cod_touro;
    }

    public void setCod_touro(Integer cod_touro) {
        this.cod_touro = cod_touro;
    }

    public Integer getBrinco() {
        return brinco;
    }

    public void setBrinco(Integer brinco) {
        this.brinco = brinco;
    }
    
}
