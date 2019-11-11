/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.pes;

import tela.manutencao.pes.ManutencaoPes;
import dao.pes.DaoPes;
import javax.swing.JOptionPane;
import modelo.pes.Pessoa;
import tela.manutencao.pes.ManutencaoPes;
import java.util.List;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 8
 */
public class ControladorPes {

    public static void inserir(ManutencaoPes man){
        Pessoa objeto = new Pessoa();
        objeto.setEmail(man.jtfEmail.getText());
        objeto.setNome(man.jtfNome.getText());
        objeto.setSenha(man.jtfSenha.getText());
        objeto.setUsuario(man.jtfUsuario.getText());
        
        boolean resultado = DaoPes.inserir(objeto);
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

    public static void alterar(ManutencaoPes man){
        Pessoa objeto = new Pessoa();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigoP.getText()));
        objeto.setNome(man.jtfNome.getText());
        objeto.setEmail(man.jtfEmail.getText());
        objeto.setSenha(man.jtfSenha.getText());
        objeto.setUsuario(man.jtfUsuario.getText());
        
        boolean resultado = DaoPes.alterar(objeto);
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

    public static void excluir(ManutencaoPes man){
        Pessoa objeto = new Pessoa();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigoP.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoPes.excluir(objeto);
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
        modelo.addColumn("E-mail");
        modelo.addColumn("Senha");
        modelo.addColumn("Usuario");
        List<Pessoa> resultados = DaoPes.consultar();
        for (Pessoa objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getNome());
            linha.add(objeto.getEmail());
            linha.add(objeto.getSenha());
            linha.add(objeto.getUsuario());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    public static void atualizaCampos(ManutencaoPes man, int pk){ 
        Pessoa objeto = DaoPes.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigoP.setText(objeto.getCodigo().toString());
        man.jtfNome.setText(objeto.getNome());
        man.jtfEmail.setText(objeto.getEmail());
         man.jtfSenha.setText(objeto.getSenha());
          man.jtfUsuario.setText(objeto.getUsuario());
        
        man.jtfCodigoP.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
