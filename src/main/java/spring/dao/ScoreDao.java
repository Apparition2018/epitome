package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spring.model.Score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * ScoreDao
 * JdbcTemplate 会将底层的异常统一转换成一些运行时异常 (RuntimeException)，然后抛出
 *
 * @author AresultSetenal
 * created on 2020/11/27 1:54
 */
@Repository
public class ScoreDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ScoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Score score) {
        String sql = "INSERT INTO score VALUES(id, ?, ?, ?)";
        Object[] args = {score.getName(), score.getCourse(), score.getScore()};
        jdbcTemplate.update(sql, args);
    }

    public Score findById(int id) {
        String sql = "SELECT id, name, course, score FROM score WHERE id=?";
        Object[] args = {id};
        return jdbcTemplate.queryForObject(sql, args, new ScoreRowMapper());
    }

    public List<Score> findAll() {
        String sql = "SELECT id, name, course, score FROM score";
        return jdbcTemplate.query(sql, new ScoreRowMapper());
    }

    public void update(Score score) {
        String sql = "UPDATE score SET name=?, course=?, score=? WHERE id=?";
        Object[] args = {score.getName(), score.getCourse(), score.getScore(), score.getId()};
        jdbcTemplate.update(sql, args);
    }

    public void delete(int id) {
        String sql = "DELETE FROM score WHERE id=?";
        Object[] args = {id};
        jdbcTemplate.update(sql, args);
    }

    /**
     * 告诉 JdbcTemplate 如何处理 ResultSet
     */
    private static class ScoreRowMapper implements RowMapper<Score> {
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
