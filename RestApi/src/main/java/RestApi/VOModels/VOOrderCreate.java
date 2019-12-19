package RestApi.VOModels;

import Interfaces.model.IArticle;
import Interfaces.model.IArticleOrder;
import Interfaces.model.ITable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VOOrderCreate {
    @JsonProperty("table")
    private VOTable table;
    @JsonProperty("articles")
    private List<VOArticle> articles;

    VOOrderCreate(){

    }
    VOOrderCreate(VOTable table, List<VOArticle> articles){
        this.table = table;
        this.articles = articles;
    }

    public VOTable getTable(){
        return this.table;
    }
    public void setTable(VOTable table){
        this.table = table;
    }
    public List<VOArticle> getArticles(){
        return this.articles;
    }
    public void setArticles(List<VOArticle> articles){
        this.articles = articles;
    }
}
