package com.example.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll() {
        return jdbcTemplate.query("SELECT id, name, raiting FROM movie", BeanPropertyRowMapper.newInstance(Movie.class));
    }

    public Movie getById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, name, raiting FROM movie WHERE id = ?", BeanPropertyRowMapper.newInstance(Movie.class), id);
    }

    public int save(List<Movie> movies) {
        movies.forEach(movie -> jdbcTemplate
                .update("INSERT INTO movie(name, raiting) VALUES(?, ?)",
                        movie.getName(), movie.getRaiting()
                        ));


        return 1;
    }

    public int update(Movie movie) {
        return jdbcTemplate.update("UPDATE movie SET name=?, raiting=? WHERE id=?",
                movie.getName(),movie.getRaiting(),movie.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM movie WHERE id=?", id);
    }

}
