package mk.ukim.finki.wp.lab2022.repository;

import mk.ukim.finki.wp.lab2022.model.News;
import mk.ukim.finki.wp.lab2022.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository  extends JpaRepository<News, Long>{
    List<News> findByPriceLessThanAndType(Double price, NewsType type);
    List<News> findByPriceLessThan(Double price);
    List<News> findByType(NewsType type);
    void deleteById(Long id);
}
