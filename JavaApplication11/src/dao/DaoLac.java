/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Lactacao;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrador
 */
public class DaoLac {
     public static boolean inserir(Lactacao objeto) {
        String sql = "INSERT INTO lactacao (fim, inicio, obs, brinco) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getFim()));
            ps.setDate(2, Date.valueOf(objeto.getInicio()));
            ps.setString(3, objeto.getObs());
            ps.setInt(7, objeto.getTipo().getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static boolean alterar(Lactacao objeto) {
        String sql = "UPDATE lactacao SET fim=?, inicio=?, obs=?, brinco=? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(objeto.getFim()));
            ps.setDate(2, Date.valueOf(objeto.getInicio())); 
            ps.setString(3, objeto.getObs());
            ps.setInt(4, objeto.getTipo().getCodigo());
            ps.setInt(5, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static boolean excluir(Lactacao objeto) {
        String sql = "DELETE FROM lactacao WHERE codigo=?";
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
      public static List<Lactacao> consultar() {
        List<Lactacao> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, fim, inicio, obs, brinco FROM produto";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lactacao objeto = new Lactacao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setObs(rs.getString("obs"));
                objeto.setFim(rs.getDate("fim").toLocalDate());
                objeto.setInicio(rs.getDate("inicio").toLocalDate());
                objeto.setTipo(DaoTipoProduto.consultar(rs.getInt("cod_tipo")));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
       public static Lactacao consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, fim, inicio, obs, brinco FROM lactacao WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Lactacao objeto = new Lactacao();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setFim(rs.getDate("fim").toLocalDate());
                objeto.setInicio(rs.getDate("inicio").toLocalDate());
                objeto.setObs(rs.getString("obs"));
                objeto.setTipo(DaoTipoProduto.consultar(rs.getInt("cod_tipo"))); 
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
