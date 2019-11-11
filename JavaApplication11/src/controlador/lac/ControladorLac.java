/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.lac;

import tela.manutencao.lac.ManutencaoLac;
import dao.lac.DaoLac;
import javax.swing.JOptionPane;
import modelo.lac.Lactacao;
import tela.manutencao.lac.ManutencaoLac;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Administrador
 */
public class ControladorLac {

    public static void inserir(ManutencaoLac man){
        Lactacao objeto = new Lactacao();
        objeto.setObs(man.jtfObsLac.getText());
        objeto.setFim(LocalDate.parse(man.jtfFimLac.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setInicio(LocalDate.parse(man.jtfInicioLac.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        
        boolean resultado = DaoLac.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoLac man){
        Lactacao objeto = new Lactacao();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodLac.getText()));
        objeto.setObs(man.jtfObsLac.getText());
        objeto.setFim(LocalDate.parse(man.jtfFimLac.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setInicio(LocalDate.parse(man.jtfInicioLac.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        
        boolean resultado = DaoLac.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

    public static void excluir(ManutencaoLac man){
        Lactacao objeto = new Lactacao();
        objeto.setCodigo(Integer.parseInt(man.jtfCodLac.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoLac.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    
}
