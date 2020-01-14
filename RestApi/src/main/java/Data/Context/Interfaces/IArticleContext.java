package Data.Context.Interfaces;

import Data.DTO.ArticleDto;
import Interfaces.model.IArticle;

public interface IArticleContext extends ICrudContext<ArticleDto>{
    ArticleDto read(String name);
}
