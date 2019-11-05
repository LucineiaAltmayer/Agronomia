/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.touro;
import dao.DaoTouro;
import javax.swing.JOptionPane;
import modelo.touro.Bovinos;
import tela.manutencao.touro.ManutencaoTouro;
import tela.manutencao.touro.ManutencaoTouro;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 8
 */
public class ControladorTouro {

    public static void inserir(ManutencaoTouro man){
        Bovinos objeto = new Bovinos();
        objeto.setNome(man.jtfNome_Touro.getText());
        
        boolean resultado = DaoTouro.inserir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
}

    public static void alterar(ManutencaoTouro man){
        Bovinos objeto = new Bovinos();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo_Touro.getText()));
        objeto.setNome(man.jtfNome_Touro.getText());
        
        boolean resultado = DaoTouro.alterar(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }

   public static void excluir(ManutencaoTouro man){
        Bovinos objeto = new Bovinos();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo_Touro.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoTouro.excluir(objeto);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro!");
        }
    }
public static void atualizarTabela(JTable tabela) {
        DefaultTableModel modelo = new DefaultTableModel();
        //definindo o cabeçalho da tabela
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        List<Bovinos> resultados = DaoTouro.consultar();
        for (Bovinos objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    public static void atualizaCampos(ManutencaoTouro man, int pk){ 
        Bovinos objeto = DaoTouro.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo_Touro.setText(objeto.getCodigo().toString());
        man.jtfNome_Touro.setText(objeto.getNome());
        
        man.jtfCodigo_Touro.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
