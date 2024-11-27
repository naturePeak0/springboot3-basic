package naturepeak0.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import naturepeak0.springbootdeveloper.domain.Article;
import naturepeak0.springbootdeveloper.dto.ArticleListViewResponse;
import naturepeak0.springbootdeveloper.dto.ArticleViewResponse;
import naturepeak0.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        log.info("/new-article : id({})", id);

        if (id == null) {
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            log.info("article: {}", article);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> list = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", list);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
}
