package com.devsuperior.uri2611.repositories;

//INNER JOIN ou JOIN - Quando você só quer registros que têm correspondência nas duas tabelas ao mesmo tempo
//LEFT JOIN: Quando você quer manter todos os registros da tabela principal, mesmo se não houver correspondência na outra
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2611.dto.MovieMinDto;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projection.MovieMinProjection;

public interface MovieRepository extends JpaRepository<Movie, Long>{
	// SQL nativo
	@Query(nativeQuery = true, value="SELECT m.id, m.name "
			+ "FROM movies m "
			+ "INNER JOIN genres g ON m.id_genres = g.id "  
			+ "WHERE g.description = :genreName")
	List<MovieMinProjection> search1(String genreName);
	
	
	// JPQL		   Busca novos objetos desse tipo, passa o construtor(id, name)
	@Query("SELECT new com.devsuperior.uri2611.dto.MovieMinDto(obj.id, obj.name) "
			+ "FROM Movie obj "  
			+ "WHERE obj.genre.description = :genreName") // acesso via objeto 'Movie' (Movie-> Genre-> description)
	List<MovieMinDto> search2(String genreName);
}
