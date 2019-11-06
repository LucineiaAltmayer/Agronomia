/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.raca;

import tela.manutencao.raca.ManutencaoRaca;
import dao.raca.DaoRaca;
import javax.swing.JOptionPane;
import modelo.raca.Raca;
import tela.manutencao.raca.ManutencaoRaca;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 8
 */
public class ControladorRaca {

    public static void inserir(ManutencaoRaca man){
        Raca objeto = new Raca();
        objeto.setCod_touro((TipoProduto)man.jcbTipo.getSelectedItem());
        objeto.setNome(man.jtfNomeRaca.getText());
        
        boolean resultado = DaoRaca.inserir(objeto);
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

   public static void alterar(ManutencaoRaca man){
        Raca objeto = new Raca();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigoRaca.getText()));
        objeto.setNome(man.jtfNomeRaca.getText());
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        
        boolean resultado = DaoRaca.alterar(objeto);
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
public static void excluir(ManutencaoRaca man){
        Raca objeto = new Raca();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigoRaca.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoRaca.excluir(objeto);
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
        modelo.addColumn("Nome");
        modelo.addColumn("Codigo do Touro");
        List<Raca> resultados = DaoRaca.consultar();
        for (Raca objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getCod_touro());
            
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
public static void atualizaCampos(ManutencaoRaca man, int pk){ 
        Raca objeto = DaoRaca.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigoRaca.setText(objeto.getCodigo().toString());
        man.jtfNomeRaca.setText(objeto.getNome());
        man.jcbTipo.setSelectedItem(objeto.getCod_touro());
        
        man.jtfCodigoRaca.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
