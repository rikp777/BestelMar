package RestApi.Controller.RestApi;

import Factory.Factory;
import RestApi.VOModels.VOArticle;
import logic.Interfaces.IArticleLogic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {
    private IArticleLogic articleLogic = Factory.ArticleLogic();


    @PostMapping("/article")
    public ResponseEntity create(@RequestBody VOArticle article, @RequestHeader(value="Authorization") String auth) {
        if(articleLogic.getBy(article.getName()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Article Already exists");
        }
        System.out.println(auth);
        if(articleLogic.add(article, auth)){
            return ResponseEntity.status(HttpStatus.CREATED).body(article);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }

    @PutMapping("/article/{id}")
    public ResponseEntity update(@RequestBody VOArticle article, @RequestHeader(value="Authorization") String auth){
        if(articleLogic.edit(article, auth)){
            return ResponseEntity.status(HttpStatus.CREATED).body(article);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity delete(@PathVariable int id, @RequestHeader(value="Authorization") String auth){
        if(articleLogic.getBy(id).getName() != null) {
            articleLogic.remove(articleLogic.getBy(id), auth);
            return ResponseEntity.status(HttpStatus.OK).body("Article deleted Successfully");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Article not found");
    }

    @GetMapping("/article")
    public ResponseEntity read(){
        if(articleLogic.getAll() != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    articleLogic.getAll()
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No article found");
    }

    @GetMapping("/article/{id}")
    public ResponseEntity read(@PathVariable int id){
        if(articleLogic.getBy(id) != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    articleLogic.getBy(id)
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Article not found");
    }
}
