package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Funcionario;

public class FuncionarioDAO {

	public void create(Funcionario funcionario) {
		Connection con = ConnectionDatabase.getConnection();	
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO Funcionario VALUES(?, ?, ?, ?, ?)");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCargo());
			stmt.setString(3, funcionario.getCpf());
			stmt.setString(4, funcionario.getSenha());
			stmt.setString(5, funcionario.getNivel());

			stmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao inserir: "+ e);
		} finally {
			ConnectionDatabase.closeConnection(con);
		}


	}

	public ArrayList<Funcionario> read() {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> funcionarios = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Funcionario");
			rs = stmt.executeQuery();

			while(rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getString(1));
				funcionario.setNome(rs.getString(2));
				funcionario.setCargo(rs.getString(3));
				funcionario.setCpf(rs.getString(4));
				funcionario.setSenha(rs.getString(5));
				funcionario.setNivel(rs.getString(6));

				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler os dados: "+ e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return funcionarios;
	}


	public void update(Funcionario funcionario) {
		Connection con = ConnectionDatabase.getConnection();	
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("UPDATE FUNCIONARIO SET nome = ?,"
					+ " cargo = ?, cpf =?, senha = ?, nivel = ? where idFuncionario = ?");
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCargo());
			stmt.setString(3, funcionario.getCpf());
			stmt.setString(4, funcionario.getSenha());
			stmt.setString(5, funcionario.getNivel());
			stmt.setString(6, funcionario.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar: " + e);
		} finally {
			ConnectionDatabase.closeConnection(con);
		}
	}


	public void delete(String cpf) {
		Connection con = ConnectionDatabase.getConnection();	
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("delete from Funcionario where cpf = ?  ");
			stmt.setString(1, cpf);

			stmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao excluir: "+ e);	
		}finally {
			ConnectionDatabase.closeConnection(con);
		}


	}

	public ArrayList<Funcionario> autenticarUser(String user, String password) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Funcionario> funcionarios = new ArrayList<>();

		try {
			stmt = con.prepareStatement("select * from Funcionario where cpf = ? and senha = ?");
			stmt.setString(1, user);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();

			while(rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getString(1));
				funcionario.setNome(rs.getString(2));
				funcionario.setCargo(rs.getString(3));
				funcionario.setCpf(rs.getString(4));
				funcionario.setSenha(rs.getString(5));
				funcionario.setNivel(rs.getString(6));

				funcionarios.add(funcionario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler os dados: "+ e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		return funcionarios;
	}
	

}
