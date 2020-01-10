package logic.Interfaces;

import Interfaces.model.IArticle;

public interface IArticleLogic extends ILogic<IArticle> {
    IArticle getBy(String name);

    boolean add(IArticle entity, String auth);
    boolean edit(IArticle entity, String auth);
    boolean remove(IArticle entity, String auth);

}
