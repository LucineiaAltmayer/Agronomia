/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.inse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.inseminacao.Inseminacao;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DaoTouro;
import DaoVaca;
/**
 *
 * @author 8
 */
public class DaoInse {
    public static boolean inserir(Inseminacao objeto) {
        String sql = "INSERT INTO inseminacao (data_parto, data_inseminacao, situacao, obs, cod_touro, brinco) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getData_parto()));
            ps.setDate(2, Date.valueOf(objeto.getData_inseminacao()));
            ps.setInt(3, objeto.getSituacao());
            ps.setString(4, objeto.getObs());
            ps.setInt(5, objeto.getCod_touro().getCodigo());
            ps.setInt(6, objeto.getBrinco().getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean alterar(Inseminacao objeto) {
        String sql = "UPDATE inseminacao SET data_parto=?, data_inseminacao=?, situacao=?, obs=?, cod_touro=?, brinco=?, codigo=? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getData_parto()));
            ps.setDate(2, Date.valueOf(objeto.getData_inseminacao()));
            ps.setInt(3, objeto.getSituacao());
            ps.setString(4, objeto.getObs()); 
            ps.setInt(5, objeto.getCod_touro().getCodigo());
            ps.setInt(6, objeto.getBrinco().getCodigo());
            ps.setInt(7, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static boolean excluir(Inseminacao objeto) {
        String sql = "DELETE FROM inseminacao WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static List<Inseminacao> consultar() {
        List<Inseminacao> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, data_parto, data_inseminacao, situacao, obs, cod_touro, brinco FROM inseminacao";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inseminacao objeto = new Inseminacao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setData_parto(rs.getDate("data_parto").toLocalDate());
                objeto.setData_inseminacao(rs.getDate("data_inseminacao").toLocalDate());
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setObs(rs.getString("obs"));
                objeto.setCod_touro(DaoTouro.consultar(rs.getInt("cod_touro")));
                objeto.setBrinco(DaoVaca.consultar(rs.getInt("brinco")));
                
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
      public static Inseminacao consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, data_parto, data_inseminacao, situacao, obs, cod_touro, brinco FROM inseminacao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inseminacao objeto = new Inseminacao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setData_parto(rs.getDate("data_parto").toLocalDate());
                objeto.setData_inseminacao(rs.getDate("data_inseminacao").toLocalDate());
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setObs(rs.getString("obs"));
                objeto.setCod_touro(DaoTouro.consultar(rs.getInt("cod_touro")));
                objeto.setBrinco(DaoVaca.consultar(rs.getInt("brinco")));
                
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
