package Data.Context.Interfaces;

import Interfaces.model.IArticle;

public interface IArticleContext extends ICrudContext<IArticle>{
    IArticle read(String name);
}
