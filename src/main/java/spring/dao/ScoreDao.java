package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.stereotype.Repository;
import spring.model.Score;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * ScoreDao
 * <p>JdbcTemplate 会将底层的异常统一转换成一些运行时异常 (RuntimeException)，然后抛出
 *
 * @author Arsenal
 * @since 2020/11/27 1:54
 */
@Repository
public class ScoreDao {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ScoreDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(Score score) {
        String sql = "INSERT INTO score VALUES(id, ?, ?, ?)";
        Object[] args = {score.getName(), score.getCourse(), score.getScore()};
        jdbcTemplate.update(sql, args);
    }

    /** 通过 BeanPropertyRowMapper 查询 */
    public Score findById(int id) {
        String sql = "SELECT id, name, course, score FROM score WHERE id=?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Score.class), args);
    }

    /** 通过自定义 RowMapper 查询 */
    public Score findById2(int id) {
        String sql = "SELECT id, name, course, score FROM score WHERE id=?";
        Object[] args = {id};
        // 自定义 RowMapper
        return jdbcTemplate.queryForObject(sql, new ScoreRowMapper(), args);
    }

    /** 通过 NamedParameterJdbcTemplate 查询，传参是 Map */
    public Score findById3(int id) {
        String sql = "SELECT id, name, course, score FROM score WHERE id=:id";
        Map<String, Object> paramMap = Map.of("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<>(Score.class));
    }

    /** 通过 NamedParameterJdbcTemplate 查询，传参是 BeanPropertySqlParameterSource */
    public Score findById4(int id) {
        String sql = "SELECT id, name, course, score FROM score WHERE id=:id";
        Score score = new Score().setId(id);
        return namedParameterJdbcTemplate.queryForObject(sql, new BeanPropertySqlParameterSource(score), new BeanPropertyRowMapper<>(Score.class));
    }

    public Map<String, Object> findMapById(int id) {
        String sql = "SELECT id, name, course, score FROM score WHERE id=?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    public List<Score> findAll() {
        String sql = "SELECT id, name, course, score FROM score";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Score.class));
    }

    public void update(Score score) {
        String sql = "UPDATE score SET name=?, course=?, score=? WHERE id=?";
        Object[] args = {score.getName(), score.getCourse(), score.getScore(), score.getId()};
        jdbcTemplate.update(sql, args);
    }


    /** @see <a href="https://blog.csdn.net/qq_38737586/article/details/110595655">使用 SpringJdbcTemplate 进行批量操作</a> */
    public void batchUpdate(List<Score> scoreList) {
        if (scoreList.isEmpty()) return;
        DataSource dataSource = jdbcTemplate.getDataSource();
        if (dataSource == null) return;
        BatchSqlUpdate batchSqlUpdate = new BatchSqlUpdate(dataSource, "UPDATE score SET score = ? WHERE id = ?");
        batchSqlUpdate.setBatchSize(1000);
        batchSqlUpdate.setTypes(new int[]{Types.INTEGER, Types.INTEGER});
        for (Score score : scoreList) {
            batchSqlUpdate.update(new Object[]{score.getScore(), score.getId()});
        }
        batchSqlUpdate.flush();
    }

    public void delete(int id) {
        String sql = "DELETE FROM score WHERE id=?";
        Object[] args = {id};
        jdbcTemplate.update(sql, args);
    }

    /** 告诉 JdbcTemplate 如何处理 ResultSet */
    private static class ScoreRowMapper implements RowMapper<Score> {
        @Override
        public Score mapRow(ResultSet resultSet, int index) throws SQLException {
            Score score = new Score();
            score.setId(resultSet.getInt("id"));
            score.setName(resultSet.getString("name"));
            score.setCourse(resultSet.getString("course"));
            score.setScore(resultSet.getInt("score"));
            return score;
        }
    }
}
