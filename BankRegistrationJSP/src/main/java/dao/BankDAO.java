package dao;

import model.Bank;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/bank_db";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Bibinhoz10$";

    private static final String INSERT_BANK_SQL = "INSERT INTO bank (name, birthDate) VALUES (?, ?)";
    private static final String SELECT_BANK_BY_ID = "SELECT id, name, birthDate FROM bank WHERE id = ?";
    private static final String SELECT_ALL_BANKS = "SELECT * FROM bank";
    private static final String DELETE_BANK_SQL = "DELETE FROM bank WHERE id = ?";
    private static final String UPDATE_BANK_SQL = "UPDATE bank SET name = ?, birthDate = ? WHERE id = ?";

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertBank(Bank bank) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BANK_SQL)) {
            preparedStatement.setString(1, bank.getName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(bank.getBirthDate()));
            preparedStatement.executeUpdate();
        }
    }

    public Bank selectBank(int id) throws SQLException {
        Bank bank = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BANK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birthDate");
                bank = new Bank(name, birthDate.toLocalDate());
                bank.setId((long) id);
            }
        }
        return bank;
    }

    public List<Bank> selectAllBanks() throws SQLException {
        List<Bank> banks = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BANKS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birthDate");
                Bank bank = new Bank(name, birthDate.toLocalDate());
                bank.setId((long) id);
                banks.add(bank);
            }
        }
        return banks;
    }

    public boolean deleteBank(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BANK_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateBank(Bank bank) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BANK_SQL)) {
            statement.setString(1, bank.getName());
            statement.setDate(2, java.sql.Date.valueOf(bank.getBirthDate()));
            statement.setLong(3, bank.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
