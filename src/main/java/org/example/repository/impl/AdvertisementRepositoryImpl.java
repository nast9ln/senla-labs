package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.Advertisement;
import org.example.repository.AdvertisementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AdvertisementRepositoryImpl implements AdvertisementRepository {
    private static final Logger logger = LoggerFactory.getLogger(AdvertisementRepositoryImpl.class);
    private static final String INSERT_ADVERTISEMENT = "insert into advertisement ( person_id, category_id, top_param_id, created_data, cost," +
            "city, header, description, status, main_image_id, is_deleted) values (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_ADVERTISEMENT = "update advertisement set person_id=?, category_id=?, top_param_id=?, " +
            "created_data=?, header=?, cost=?, city=?, description=?, status=?, main_image_id=?, is_deleted=? where id=?";
    public static final String READ_ADVERTISEMENT = "select * from advertisement where id=?";
    public static final String READ_ADVERTISEMENT_BY_PERSON_ID = "select * from advertisement where person_id=?";


    public static final String DELETE_ADVERTISEMENT = "update advertisement set is_deleted=true where id=?";
    private static final String DELETE_ADVERTISEMENT_BY_PERSON_ID = "update advertisement set is_deleted=true where person_id=?";

    private final Connection connection;

    @Override
    public Advertisement create(Advertisement entity) {
        logger.info("create");
        String query = INSERT_ADVERTISEMENT;
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getPersonId());
            statement.setLong(2, entity.getCategoryId());

            if (entity.getTopParamId() == null)
                statement.setNull(3, Types.BIGINT);
            else
                statement.setLong(3, entity.getTopParamId());

            statement.setObject(4, entity.getCreatedDate());
            statement.setInt(5, entity.getCost());
            statement.setString(6, entity.getCity());
            statement.setString(7, entity.getHeader());
            statement.setString(8, entity.getDescription());
            statement.setString(9, entity.getStatus());

            if (entity.getMainImageId() == null)
                statement.setNull(10, Types.BIGINT);
            else
                statement.setLong(10, entity.getMainImageId());

            statement.setBoolean(11, entity.isDeleted());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Advertisement read(Long id) {
        logger.info("read");
        Advertisement advertisement = new Advertisement();
        String query = READ_ADVERTISEMENT;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    advertisement.setId(resultSet.getLong("id"));
                    advertisement.setPersonId(resultSet.getLong("person_id"));
                    advertisement.setCategoryId(resultSet.getLong("category_id"));
                    advertisement.setTopParamId(resultSet.getLong("top_param_id"));
                    advertisement.setCreatedDate(LocalDateTime.parse(resultSet.getString("created_data"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSS]")));
                    advertisement.setCost(resultSet.getInt("cost"));
                    advertisement.setCity(resultSet.getString("city"));
                    advertisement.setHeader(resultSet.getString("header"));
                    advertisement.setDescription(resultSet.getString("description"));
                    advertisement.setStatus(resultSet.getString("status"));
                    advertisement.setMainImageId(resultSet.getLong("main_image_id"));
                    advertisement.setDeleted(resultSet.getBoolean("is_deleted"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return advertisement;
    }

    public List<Advertisement> readByPersonId(Long id) {
        logger.info("read by person id");
        Advertisement advertisement = new Advertisement();
        List<Advertisement> advertisements = new ArrayList<>();
        String query = READ_ADVERTISEMENT_BY_PERSON_ID;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    advertisement.setId(resultSet.getLong("id"));
                    advertisement.setPersonId(resultSet.getLong("person_id"));
                    advertisement.setCategoryId(resultSet.getLong("category_id"));
                    advertisement.setTopParamId(resultSet.getLong("top_param_id"));
                    advertisement.setCreatedDate(LocalDateTime.parse(resultSet.getString("created_data"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSS]")));
                    advertisement.setCost(resultSet.getInt("cost"));
                    advertisement.setCity(resultSet.getString("city"));
                    advertisement.setHeader(resultSet.getString("header"));
                    advertisement.setDescription(resultSet.getString("description"));
                    advertisement.setStatus(resultSet.getString("status"));
                    advertisement.setMainImageId(resultSet.getLong("main_image_id"));
                    advertisement.setDeleted(resultSet.getBoolean("is_deleted"));
                    advertisements.add(advertisement);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return advertisements;
    }

    @Override
    public Advertisement update(Advertisement advertisement) {
        logger.info("update");
        String query = UPDATE_ADVERTISEMENT;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, advertisement.getPersonId());
            statement.setLong(2, advertisement.getCategoryId());
            statement.setObject(3, advertisement.getTopParamId(), Types.BIGINT);
            statement.setObject(4, advertisement.getCreatedDate());
            statement.setString(5, advertisement.getHeader());
            statement.setInt(6, advertisement.getCost());
            statement.setString(7, advertisement.getCity());
            statement.setString(8, advertisement.getDescription());
            statement.setString(9, advertisement.getStatus());
            statement.setObject(10, advertisement.getMainImageId(), Types.BIGINT);
            statement.setBoolean(11, advertisement.isDeleted());
            statement.setLong(12, advertisement.getId());
            statement.executeUpdate();
            return advertisement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("delete");
        String query = DELETE_ADVERTISEMENT;
        try (PreparedStatement deleteAdvStatement = connection.prepareStatement(query)) {
            deleteAdvStatement.setLong(1, id);
            deleteAdvStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByPersonId(Long id) {
        logger.info("delete by person id");
        String query = DELETE_ADVERTISEMENT_BY_PERSON_ID;
        try (PreparedStatement deleteAdvStatement = connection.prepareStatement(query)) {
            deleteAdvStatement.setLong(1, id);
            deleteAdvStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
