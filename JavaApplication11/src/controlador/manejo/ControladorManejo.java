/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.manejo;

import tela.manutencao.manejo.ManutencaoManejo;
import dao.manejo.DaoManejo;
import javax.swing.JOptionPane;
import modelo.manejo.Manejo;
import tela.manutencao.manejo.ManutencaoManejo;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 8
 */
public class ControladorManejo {

    public static void inserir(ManutencaoManejo man){
        Manejo objeto = new Manejo();
        objeto.setObservacoes(man.jtfObsManejo.getText());
        objeto.setData(LocalDate.parse(man.jtfDataManejo.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        boolean resultado = DaoManejo.inserir(objeto);
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

    public static void alterar(ManutencaoManejo man){
        Manejo objeto = new Manejo();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigoManejo.getText()));
        objeto.setObservacoes(man.jtfObsManejo.getText());
        objeto.setData(LocalDate.parse(man.jtfDataManejo.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        boolean resultado = DaoManejo.alterar(objeto);
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

   public static void excluir(ManutencaoManejo man){
        Manejo objeto = new Manejo();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigoManejo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoManejo.excluir(objeto);
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
        modelo.addColumn("Observaçôes");
        modelo.addColumn("Data");
        List<Manejo> resultados = DaoManejo.consultar();
        for (Manejo objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getObservacoes());
            linha.add(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
public static void atualizaCampos(ManutencaoManejo man, int pk){ 
        Manejo objeto = DaoManejo.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigoManejo.setText(objeto.getCodigo().toString());
        man.jtfObsManejo.setText(objeto.getObservacoes());
        man.jtfDataManejo.setText(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        man.jtfCodigoManejo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
    
}
