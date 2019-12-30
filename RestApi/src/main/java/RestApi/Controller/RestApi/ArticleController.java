package RestApi.Controller.RestApi;

import Data.Context.MemoryContext.ArticleContextMemory;
import Data.Repository.ArticleRepository;
import logic.ArticleLogic;
import RestApi.VOModels.VOArticle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {
    private ArticleLogic articleLogic = new ArticleLogic(new ArticleRepository(new ArticleContextMemory()));


    @PostMapping("/article")
    public ResponseEntity create(@RequestBody VOArticle article, @RequestHeader(value="Authorization") String auth) {
        if(articleLogic.getBy(article.getName()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Article Already exists");
        }
        if(articleLogic.add(article)){
            return ResponseEntity.status(HttpStatus.CREATED).body(article);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }

//    @PutMapping("/article/{id}")
//    public ResponseEntity update(@RequestBody Article article){
//        if(articleLogic.edit(article)){
//            return ResponseEntity.status(HttpStatus.CREATED).body(article);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
//    }
//
//    @DeleteMapping("/article/{id}")
//    public ResponseEntity delete(@PathVariable int id){
//        System.out.println(id);
//        System.out.println(articleLogic.getBy(id));
//        if(articleLogic.getBy(id).getName() != null) {
//            articleLogic.remove(articleLogic.getBy(id));
//            return ResponseEntity.status(HttpStatus.OK).body("Article deleted Successfully");
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body("Article not found");
//    }

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
