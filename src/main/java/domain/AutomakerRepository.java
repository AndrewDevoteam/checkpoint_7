package domain;

import conn.ConnectionFactory;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Log4j2
public class AutomakerRepository {
    public static List<AutoMaker> findByName(String name) {
        log.info("Finding Automaker by name '{}' ", name);
        List<AutoMaker> autoMakers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindByName(conn, name);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                AutoMaker autoMaker = AutoMaker.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
                autoMakers.add(autoMaker);
            }

        } catch (SQLException e) {
            log.error("Error trying to find all automakers by name", e);
        }
        return autoMakers;
    }

    private static PreparedStatement createPreparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM auto_dealer.automaker WHERE name like ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    public static void save(AutoMaker autoMaker) {
        log.info("Saving Automaker '{}' ", autoMaker.getName());
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementSave(conn, autoMaker)) {
            ps.execute();

        } catch (SQLException e) {
            log.error("Error trying to save automaker", e);
        }
    }

    private static PreparedStatement createPreparedStatementSave(Connection conn, AutoMaker autoMaker) throws SQLException {
        String sql = "INSERT INTO `auto_dealer`.`automaker` (`name`) VALUES (?);\n";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, autoMaker.getName());
        return ps;
    }



}
