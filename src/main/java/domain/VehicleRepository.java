package domain;

import conn.ConnectionFactory;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class VehicleRepository {

    private List<Vehicle> vehicleList;

    public VehicleRepository() {
    }

    public static List<AutoMaker> findByAutomaker(String name) {
        log.info("Finding Automaker by name '{}' ", name);
        List<AutoMaker> autoMakers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindByAutomaker(conn, name);
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

    private static PreparedStatement createPreparedStatementFindByAutomaker(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM auto_dealer.automaker WHERE name like ? ;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }

    public static Optional<Vehicle> findByModel(String model) {
        log.info("Finding Vehicle by name '{}' ", model);
        Optional<Vehicle> optionalVehicle = Optional.empty();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindByModel(conn, model);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                log.info("Here '{}' ",rs);

                AutoMaker autoMaker = AutoMaker
                        .builder()
                        .id(rs.getInt("automaker_id"))
                        .name(rs.getString("name"))
                        .build();

                VehicleService vehicleService = new VehicleService();
                return Optional.of(vehicleService.createVehicle(
                        rs.getInt("id"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getString("year"),
                        autoMaker,
                        VehicleTypeEnum.valueOf(rs.getString("vehicle_type"))));

            }
        } catch (SQLException e) {
            log.error("Error trying to find all automakers by model", e);
        }
        return optionalVehicle;
    }

    private static PreparedStatement createPreparedStatementFindByModel(Connection conn, String model) throws SQLException {
        String sql = """
                SELECT *\s
                FROM auto_dealer.vehicle
                JOIN auto_dealer.automaker
                ON auto_dealer.vehicle.automaker_id = auto_dealer.automaker.id
                WHERE model like ?
                """;
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", model));
        return ps;
    }

    public static List<Vehicle> findAll() {
        log.info("Finding all vehicles ");
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindAll(conn);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                AutoMaker autoMaker = AutoMaker
                        .builder()
                        .id(rs.getInt("automaker_id"))
                        .name(rs.getString("name"))
                        .build();
                VehicleService vehicleService = new VehicleService();
                Vehicle vehicle = vehicleService.createVehicle(
                        rs.getInt("id"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getString("year"),
                        autoMaker,
                        VehicleTypeEnum.valueOf(rs.getString("vehicle_type")));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            log.error("Error trying to find all vehicles", e);
        }
        return vehicles;
    }

    private static PreparedStatement createPreparedStatementFindAll(Connection conn) throws SQLException {
        String sql = """
                SELECT *\s
                FROM auto_dealer.vehicle
                JOIN auto_dealer.automaker
                where auto_dealer.vehicle.automaker_id = auto_dealer.automaker.id
                """;
        return conn.prepareStatement(sql);
    }

    public static void delete(Integer id) {
        log.info("Deleting vehicle with id '{}' ", id);
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementDelete(conn, id)) {
            ps.execute();

        } catch (SQLException e) {
            log.error("Error trying to delete Vehicle with id", e);
        }
    }

    private static PreparedStatement createPreparedStatementDelete(Connection conn, Integer id) throws SQLException {
        String sql = "DELETE FROM `auto_dealer`.`vehicle` WHERE (`id` = ?);\n";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public static void replaceVehicle(Vehicle vehicle) {
        log.info("Replacing Vehicle '{}'", vehicle);
        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement ps = createPreparedStatementReplaceVehicle(conn, vehicle);
            ps.execute();
        } catch (SQLException e) {
            log.error("Error trying to update vehicle '{}'", vehicle.getId(), e);
        }
    }

    private static PreparedStatement createPreparedStatementReplaceVehicle(Connection conn, Vehicle vehicle) throws SQLException {
        String sql = "UPDATE auto_dealer.vehicle SET model = ?, color = ?, year = ?, vehicle_type = ? WHERE id = ?;";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, vehicle.getModel());
        ps.setString(2, vehicle.getColor());
        ps.setString(3, vehicle.getYear());
        ps.setString(4, String.valueOf(vehicle.getVehicleTypeEnum()));
        ps.setLong(5, vehicle.getId());
        System.out.println(sql);
        return ps;
    }
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
}
