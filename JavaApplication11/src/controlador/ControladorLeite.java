/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import tela.manutencao.ManutencaoLeite;
import dao.DaoLeite;
import javax.swing.JOptionPane;
import modelo.Leite;
import tela.manutencao.ManutencaoLeite;
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
public class ControladorLeite {

    public static void inserir(ManutencaoLeite man){
        Leite objeto = new Leite();
        objeto.setObs(man.jtfObsLeite.getText());
        objeto.setTurno(Integer.parseInt(man.jtfTurno.getText()));
        objeto.setTotal(Double.parseDouble(man.jtfTotal.getText()));
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        
        boolean resultado = DaoLeite.inserir(objeto);
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

    public static void alterar(ManutencaoLeite man){
        Leite objeto = new Leite();
        //definir todos os atributos
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText()));
        objeto.setTurno(Integer.parseInt(man.jtfTurno.getText()));
        objeto.setTotal(Double.parseDouble(man.jtfTotal.getText()));
        objeto.setData(LocalDate.parse(man.jtfData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        objeto.setTipo((TipoProduto)man.jcbTipo.getSelectedItem());
        
        boolean resultado = DaoLeite.alterar(objeto);
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

    public static void excluir(ManutencaoLeite man){
        Leite objeto = new Leite();
        objeto.setCodigo(Integer.parseInt(man.jtfCodigo.getText())); //só precisa definir a chave primeira
        
        boolean resultado = DaoLeite.excluir(objeto);
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
        modelo.addColumn("Data");
        modelo.addColumn("Turno");
        modelo.addColumn("Brinco");
        modelo.addColumn("Código Pessoa");
        modelo.addColumn("Total");
        List<Leite> resultados = DaoLeite.consultar();
        for (Leite objeto : resultados) {
            Vector linha = new Vector();
            
            //definindo o conteúdo da tabela
            linha.add(objeto.getCodigo());
            linha.add(objeto.getObs());
            linha.add(objeto.getTotal());
            linha.add(objeto.getTurno());
            linha.add(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            linha.add(objeto.getBrinco());
            linha.add(objeto.getCod_pessoa());
            modelo.addRow(linha); //adicionando a linha na tabela
        }
        tabela.setModel(modelo);
    }

    public static void atualizaCampos(ManutencaoLeite man, int pk){ 
        Leite objeto = DaoLeite.consultar(pk);
        //Definindo os valores do campo na tela (um para cada atributo/campo)
        man.jtfCodigo.setText(objeto.getCodigo().toString());
        man.jtfObsLeite.setText(objeto.getObs());
        man.jtfTurno.setText(objeto.getTurno().toString());
        man.jtfTotal.setText(objeto.getTotal().toString());
        man.jtfData.setText(objeto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        man.jcbTipo.setSelectedItem(objeto.getTipo());
        man.jcbTipo.setSelectedItem(objeto.getTipo());
        
        man.jtfCodigo.setEnabled(false); //desabilitando o campo código
        man.btnAdicionar.setEnabled(false); //desabilitando o botão adicionar
    }
    public static void atualizaComboTipo(ManutencaoProduto man) {
        DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel(DaoTipoProduto.consultar().toArray());
        man.jcbTipo.setModel(defaultComboBoxModel);
}
}
