package hello.hellospring.repository;

import hello.hellospring.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {
    Board save(Board board);
    Board update(Board board);
    Board findById(Long id);
    List<Board> findByTitle(String title);
    List<Board> findByAuthor(String author);
}
