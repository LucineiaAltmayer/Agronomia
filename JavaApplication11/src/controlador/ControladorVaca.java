/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import tela.manutencao.ManutencaoVaca;
import dao.DaoVaca;
import javax.swing.JOptionPane;
import modelo.Vaca;
import tela.manutencao.ManutencaoVaca;
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
public class ControladorVaca {

    public static void inserir(ManutencaoVaca man){
        Vaca objeto = new Vaca();
        objeto.setObs(man.jtfObsVaca.getText());
        objeto.setOrigem(Integer.parseInt(man.jtfOrigem.getText()));
        objeto.setSituacao(Integer.parseInt(man.jtfSituacao.getText()));
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        
        boolean resultado = DaoVaca.inserir(objeto);
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

     public static void alterar(ManutencaoVaca man){
        Vaca objeto = new Vaca();
        //definir todos os atributos
        objeto.setBrinco(Integer.parseInt(man.jtfBrinco.getText()));
        objeto.setObs(man.jtfObsVaca.getText());
        objeto.setOrigem(Integer.parseInt(man.jtfOrigem.getText()));
        objeto.setSituacao(Integer.parseInt(man.jtfSituacao.getText()));
        objeto.setNascimento(LocalDate.parse(man.jtfNascimento.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        boolean resultado = DaoVaca.alterar(objeto);
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

    public static void excluir(ManutencaoVaca man){
        Vaca objeto = new Vaca();
        objeto.setBrinco(Integer.parseInt(man.jtfBrinco.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoVaca.excluir(objeto);
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
        modelo.addColumn("Brinco");
        modelo.addColumn("Observação");
        modelo.addColumn("Situalção");
        modelo.addColumn("Origem");
        modelo.addColumn("Brinco da Mãe");
        modelo.addColumn("Código da Raça");
        modelo.addColumn("Nascimento");
        List<Vaca> resultados = DaoVaca.consultar();
        for (Vaca objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getBrinco());
            linha.add(objeto.getObs());
            linha.add(objeto.getSituacao());
            linha.add(objeto.getOrigem());
            linha.add(objeto.getTipo());
            linha.add(objeto.getTipo());
            linha.add(objeto.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
   public static void atualizaCampos(ManutencaoVaca man, int pk){ 
        Vaca objeto = DaoVaca.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfBrinco.setText(objeto.getBrinco().toString());
        man.jtfObsVaca.setText(objeto.getObs());
        man.jtfSituacao.setText(objeto.getSituacao().toString());
        man.jtfOrigem.setText(objeto.getOrigem().toString());
        man.jtfNascimento.setText(objeto.getNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jcbTipo.setSelectedItem(objeto.getTipo());
        man.jcbTipo.setSelectedItem(objeto.getTipo());
        
        man.jtfBrinco.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
   public static void atualizaComboTipo(ManutencaoVaca man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoTipoProduto.consultar().toArray());
        man.jcbTipo.setModel(defaultComboBoxModel);
}
}
