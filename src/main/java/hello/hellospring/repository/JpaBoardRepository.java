package hello.hellospring.repository;

import hello.hellospring.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaBoardRepository extends JpaRepository<Board,Long>, BoardRepository {
    @Override
    default List<Board> findByTitle(String title){
        return null;
    };

    @Override
    default List<Board> findByAuthor(String author) {
        return null;
    }
}
