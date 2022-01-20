package mk.ukim.finki.wp.lab2022.repository;

import mk.ukim.finki.wp.lab2022.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCategoryRepository  extends JpaRepository<NewsCategory, Long> {

}
