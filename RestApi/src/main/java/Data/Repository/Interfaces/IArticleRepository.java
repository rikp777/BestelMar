package Data.Repository.Interfaces;

import Interfaces.model.IArticle;

public interface IArticleRepository extends ICrudRepository<IArticle> {
    IArticle getBy(String name);
}
