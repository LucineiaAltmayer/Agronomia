/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Vaca;
import java.sql.Date; 
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import dao.DaoRaca;
import java.sql.Types;
/**
 *
 * @author 8
 */
public class DaoVaca {
     public static boolean inserir(Vaca objeto) {
        String sql = "INSERT INTO vaca (situacao, obs, origem, nascimento, brinco_mae, cod_raca, brinco) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getSituacao());
            ps.setString(2, objeto.getObs());
            ps.setInt(3, objeto.getOrigem());
            ps.setDate(4, Date.valueOf(objeto.getNascimento()));
              if (objeto.getBrinco() == null){
                ps.setNull(5, Types.INTEGER);
            }else{
                ps.setInt(5, objeto.getMae().getBrinco());    
            };
                        if (objeto.getRaca() == null){
                ps.setNull(5, Types.INTEGER);
            }else{
                ps.setInt(5, objeto.getRaca().getCodigo());    
            };
            ps.setInt(7, objeto.getBrinco());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static boolean alterar(Vaca objeto) {
        String sql = "UPDATE vaca SET situacao=?, obs=?, origem=?, nascimento=?, brinco_mae=?, cod_raca=? WHERE brinco=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getSituacao());
            ps.setString(2, objeto.getObs());
            ps.setInt(3, objeto.getOrigem());
            ps.setDate(4, Date.valueOf(objeto.getNascimento()));
              if (objeto.getBrinco() == null){
                ps.setNull(5, Types.INTEGER);
            }else{
                ps.setInt(5, objeto.getMae().getBrinco());    
            };
                        if (objeto.getRaca() == null){
                ps.setNull(5, Types.INTEGER);
            }else{
                ps.setInt(5, objeto.getRaca().getCodigo());    
            };
            ps.setInt(7, objeto.getBrinco());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
     public static boolean excluir(Vaca objeto) {
        String sql = "DELETE FROM vaca WHERE brinco=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getBrinco());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static List<Vaca> consultar() {
        List<Vaca> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT brinco, situacao, obs, origem, nascimento, brinco_mae, cod_raca FROM vaca";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaca objeto = new Vaca();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setBrinco(rs.getInt("brinco"));
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setObs(rs.getString("obd"));
                objeto.setOrigem(rs.getInt("oringem"));
                objeto.setNascimento(rs.getDate("nascimento").toLocalDate());
                objeto.setMae(DaoVaca.consultar(rs.getInt("brinco_mae")));
                objeto.setRaca(DaoRaca.consultar(rs.getInt("cod_raca")));
                
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
      public static Vaca consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT brinco, situacao, obs, origem, nascimento, brinco_mae, cod_raca WHERE brinco=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vaca objeto = new Vaca();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setBrinco(rs.getInt("brinco"));
                objeto.setSituacao(rs.getInt("situacao"));
                objeto.setObs(rs.getString("obs"));
                objeto.setOrigem(rs.getInt("origem"));
                objeto.setNascimento(rs.getDate("nascimento").toLocalDate());
                objeto.setMae(DaoVaca.consultar(rs.getInt("brinco_mae")));
                objeto.setRaca(DaoRaca.consultar(rs.getInt("cod_raca")));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}

