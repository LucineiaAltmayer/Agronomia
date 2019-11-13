/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Leite;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import DaoPes;
import DaoVaca;
/**
 *
 * @author 8
 */
public class DaoLeite {
    public static boolean inserir(Leite objeto) {
        String sql = "INSERT INTO producao_leite (turno, total,	data, obs, brinco,cod_pessoa) VALUES (?, ?,?,?,?,?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getTurno());
            ps.setDouble(2, objeto.getTotal());
            ps.setDate(3, Date.valueOf(objeto.getData()));
            ps.setString(4, objeto.getObs());
            ps.setInt(5, objeto.getTipo().getCodigo());
            ps.setInt(6, objeto.getTipo().getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean alterar(Leite objeto) {
        String sql = "UPDATE producao_leite SET turno=?, total=?,data=?, obs=?, brinco=?,cod_pessoa = ? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, objeto.getTurno());
            ps.setDouble(2, objeto.getTotal());
            ps.setDate(3, Date.valueOf(objeto.getData()));
            ps.setString(4, objeto.getObs());
            ps.setInt(5, objeto.getTipo().getCodigo());
            ps.setInt(6, objeto.getTipo().getCodigo());
            ps.setInt(7, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public static boolean excluir(Leite objeto) {
        String sql = "DELETE FROM producao_leite WHERE codigo=?";
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
    public static List<Leite> consultar() {
        List<Leite> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, turno, total,data, obs, brinco,cod_pessoa FROM producao_leite";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Leite objeto = new Leite();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setTurno(rs.getInt("turno"));
                objeto.setTotal(rs.getDouble("total"));
                objeto.setData(rs.getDate("data").toLocalDate());
                objeto.setObs(rs.getString("obs"));
                objeto.setTipo(DaoTipoProduto.consultar(rs.getInt("brinco")));
                objeto.setTipo(DaoTipoProduto.consultar(rs.getInt("cod_pessoa")));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
    public static Leite consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, turno, total,data, obs, brinco,cod_pessoa FROM producao_leite WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Leite objeto = new Leite();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
               objeto.setTurno(rs.getInt("turno"));
                objeto.setTotal(rs.getDouble("total"));
                objeto.setData(rs.getDate("data").toLocalDate());
                objeto.setObs(rs.getString("obs"));
                objeto.setTipo(DaoTipoProduto.consultar(rs.getInt("brinco")));
                objeto.setTipo(DaoTipoProduto.consultar(rs.getInt("cod_pessoa")));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
