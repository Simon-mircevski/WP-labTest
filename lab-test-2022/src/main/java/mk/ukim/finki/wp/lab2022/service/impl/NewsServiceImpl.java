package mk.ukim.finki.wp.lab2022.service.impl;

import mk.ukim.finki.wp.lab2022.model.News;
import mk.ukim.finki.wp.lab2022.model.NewsCategory;
import mk.ukim.finki.wp.lab2022.model.NewsType;
import mk.ukim.finki.wp.lab2022.model.exceptions.InvalidNewsCategoryIdException;
import mk.ukim.finki.wp.lab2022.model.exceptions.InvalidNewsIdException;
import mk.ukim.finki.wp.lab2022.repository.NewsCategoryRepository;
import mk.ukim.finki.wp.lab2022.repository.NewsRepository;
import mk.ukim.finki.wp.lab2022.service.NewsCategoryService;
import mk.ukim.finki.wp.lab2022.service.NewsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    private final NewsCategoryRepository newsCategoryRepository;
    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository,
                           NewsCategoryRepository newsCategoryRepository) {
        this.newsRepository = newsRepository;
        this.newsCategoryRepository = newsCategoryRepository;
    }

    @Override
    public List<News> listAllNews() {
        return this.newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        News news = this.newsRepository.findById(id).orElseThrow(InvalidNewsIdException::new);
        return news;
    }

    @Override
    public News create(String name, String description, Double price, NewsType type, Long category) {
        NewsCategory newsCategory = this.newsCategoryRepository.findById(category).orElseThrow(()-> new InvalidNewsCategoryIdException());
        News news = new News(name,description,price,type,newsCategory);
        return this.newsRepository.save(news);
    }

    @Override
    @Transactional
    public News update(Long id, String name, String description, Double price, NewsType type, Long category) {
        this.newsRepository.deleteById(id);
        NewsCategory newsCategory = this.newsCategoryRepository.findById(category).orElseThrow(()-> new InvalidNewsCategoryIdException());
        News news = new News(name,description,price,type,newsCategory);
        return this.newsRepository.save(news);
    }

    @Override
    public News delete(Long id) {
        News news = this.newsRepository.findById(id).orElseThrow(InvalidNewsIdException::new);
        this.newsRepository.delete(news);
        return news;
    }

    @Override
    public News like(Long id) {
        News news = this.newsRepository.findById(id)
                .orElseThrow(InvalidNewsIdException::new);
        Integer likes = news.getLikes();
        ++likes;
        news.setLikes(likes);
        return news;
    }

    @Override
    public List<News> listNewsWithPriceLessThanAndType(Double price, NewsType type) {
        if(price!=null && type!=null){
            return this.newsRepository.findByPriceLessThanAndType(price,type);
        }
        else if(price!=null ){
            return this.newsRepository.findByPriceLessThan(price);
        }
        else if(type!=null){
            return this.newsRepository.findByType(type);
        }
        else{
            return this.newsRepository.findAll();
        }
    }
}
