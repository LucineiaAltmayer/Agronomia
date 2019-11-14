/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import tela.manutencao.ManutencaoLac;
import dao.DaoLac;
import dao.DaoVaca;
import javax.swing.JOptionPane;
import modelo.Lactacao;
import tela.manutencao.ManutencaoLac;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Vaca;
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
        objeto.setVaca((Vaca)man.jcbVaca.getSelectedItem());
        
        boolean resultado = DaoLac.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
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
        objeto.setVaca((Vaca)man.jcbVaca.getSelectedItem());
        
        boolean resultado = DaoLac.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
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
            if (man.listagem != null) {
     atualizarTabela(man.listagem.tabela); //atualizar a tabela da listagem
}
man.dispose();//fechar a tela da manutenção
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
    public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Observações");
        modelo.addColumn("Fim");
        modelo.addColumn("Inicio");
        modelo.addColumn("Brinco");
        List<Lactacao> resultados = DaoLac.consultar();
        for (Lactacao objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getObs());
            linha.add(objeto.getFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getVaca());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    
    public static void atualizaCampos(ManutencaoLac man, int pk){ 
        Lactacao objeto = DaoLac.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodLac.setText(objeto.getCodigo().toString());
        man.jtfObsLac.setText(objeto.getObs());
        man.jtfFimLac.setText(objeto.getFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfInicioLac.setText(objeto.getFim().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jcbVaca.setSelectedItem(objeto.getVaca());
        
        man.jtfCodLac.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
    public static void atualizaComboVaca(ManutencaoLac man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoVaca.consultar().toArray());
        man.jcbVaca.setModel(defaultComboBoxModel);
}
}
