package mk.ukim.finki.wp.lab2022.service.impl;

import mk.ukim.finki.wp.lab2022.model.NewsCategory;
import mk.ukim.finki.wp.lab2022.model.exceptions.InvalidNewsCategoryIdException;
import mk.ukim.finki.wp.lab2022.repository.NewsCategoryRepository;
import mk.ukim.finki.wp.lab2022.service.NewsCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsCategoryServiceImpl implements NewsCategoryService {

    private final NewsCategoryRepository newsCategoryRepository;

    public NewsCategoryServiceImpl(NewsCategoryRepository newsCategoryRepository) {
        this.newsCategoryRepository = newsCategoryRepository;
    }


    @Override
    public NewsCategory findById(Long id) {
        return this.newsCategoryRepository.findById(id).orElseThrow(InvalidNewsCategoryIdException::new);
    }

    @Override
    public List<NewsCategory> listAll() {
        return this.newsCategoryRepository.findAll();
    }

    @Override
    public NewsCategory create(String name) {
        NewsCategory newsCategory = new NewsCategory(name);
        this.newsCategoryRepository.save(newsCategory);
        return newsCategory;
    }
}
