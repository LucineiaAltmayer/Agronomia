/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import tela.manutencao.ManutencaoInse;
import dao.DaoInse;
import javax.swing.JOptionPane;
import modelo.Inseminacao;
import tela.manutencao.ManutencaoInse;
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
public class ControladorInse {

   public static void inserir(ManutencaoInse man){
        Inseminacao objeto = new Inseminacao();
        objeto.setObs(man.jtfObsInse.getText());
        objeto.setData_inseminacao(LocalDate.parse(man.jtfDataInse.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setData_parto(LocalDate.parse(man.jtfDataParto.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setSituacao(Integer.parseInt(man.jtfSituacao.getText()));
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        boolean resultado = DaoInse.inserir(objeto);
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

    public static void alterar(ManutencaoInse man){
        Inseminacao objeto = new Inseminacao();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigoInse.getText()));
        objeto.setObs(man.jtfObsInse.getText());
        objeto.setData_inseminacao(LocalDate.parse(man.jtfDataInse.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setData_parto(LocalDate.parse(man.jtfDataParto.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setSituacao(Integer.parseInt(man.jtfSituacao.getText()));
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        boolean resultado = DaoInse.alterar(objeto);
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

    public static void excluir(ManutencaoInse man){
        Inseminacao objeto = new Inseminacao();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigoInse.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoInse.excluir(objeto);
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
        modelo.addColumn("Observação");
        modelo.addColumn("Situação");
        modelo.addColumn("Data da Inseminação");
        modelo.addColumn("Data do Parto");
        modelo.addColumn("Codigo do Touro");
        modelo.addColumn("Brinco da Vaca");
        List<Inseminacao> resultados = DaoInse.consultar();
        for (Inseminacao objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getObs());
            linha.add(objeto.getSituacao());
            linha.add(objeto.getData_inseminacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getData_parto().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getCod_touro());
            linha.add(objeto.getBrinco());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }
    public static void atualizaCampos(ManutencaoInse man, int pk){ 
        Inseminacao objeto = DaoInse.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigoInse.setText(objeto.getCodigo().toString());
        man.jtfObsInse.setText(objeto.getObs());
        man.jtfSituacao.setText(objeto.getSituacao().toString());
        man.jtfDataInse.setText(objeto.getData_inseminacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jtfDataParto.setText(objeto.getData_parto().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jcbTipo.setSelectedItem(objeto.getBrinco());
        man.jcbTipo.setSelectedItem(objeto.getCod_touro());
        
        man.jtfCodigoInse.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
}
