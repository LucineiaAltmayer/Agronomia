/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Pessoa;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author 8
 */
public class DaoPes {
    public static boolean inserir(Pessoa objeto) {
        String sql = "INSERT INTO pessoa (email, senha, usuario, nome ) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, objeto.getEmail());
            ps.setString(2, objeto.getSenha());
            ps.setString(3, objeto.getUsuario());
            ps.setString(4, objeto.getNome());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public static boolean alterar(Pessoa objeto) {
        String sql = "UPDATE pessoa SET email=?, senha=?, usuario=?, nome=? WHERE codigo=?";
        try {
            PreparedStatement ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setString(4, objeto.getNome()); 
            ps.setString(1, objeto.getEmail());
            ps.setString(2, objeto.getSenha());
            ps.setString(3, objeto.getUsuario());
            ps.setInt(5, objeto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
      public static boolean excluir(Pessoa objeto) {
        String sql = "DELETE FROM pessoa WHERE codigo=?";
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
      public static List<Pessoa> consultar() {
        List<Pessoa> resultados = new ArrayList<>();
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo,email, senha, usuario, nome FROM pessoa";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa objeto = new Pessoa();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setEmail(rs.getString("email"));
                objeto.setSenha(rs.getString("senha"));
                objeto.setUsuario(rs.getString("usuarip"));
                 objeto.setNome(rs.getString("nome"));
                
                resultados.add(objeto);//não mexa nesse, ele adiciona o objeto na lista
            }
            return resultados;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
}
      public static Pessoa consultar(int primaryKey) {
        //editar o SQL conforme a entidade
        String sql = "SELECT codigo, nome, email, senha, usuario FROM pessoa WHERE codigo=?";
        PreparedStatement ps;
        try {
            ps = conexao.Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, primaryKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pessoa objeto = new Pessoa();
                //definir um set para cada atributo da entidade, cuidado com o tipo
                objeto.setCodigo(rs.getInt("codigo"));
                objeto.setNome(rs.getString("nome"));
                objeto.setEmail(rs.getString("email"));
                objeto.setSenha(rs.getString("senha"));
                objeto.setUsuario(rs.getString("usuario"));
                return objeto;//não mexa nesse, ele adiciona o objeto na lista
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
