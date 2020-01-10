package Factory;

import Data.Context.Interfaces.*;
import Data.Context.MemoryContext.*;
import Data.Context.SQLContext.*;
import Data.Repository.*;
import logic.ArticleLogic;
import logic.Interfaces.IArticleLogic;
import logic.Interfaces.IOrderLogic;
import logic.Interfaces.ITableLogic;
import logic.Interfaces.IUserLogic;
import logic.OrderLogic;
import logic.TableLogic;
import logic.UserLogic;

public class Factory {
    private static ContextType contextTypeStatic = ContextType.SQL;



    public static IUserLogic UserLogic(ContextType contextType){
        return new UserLogic(new UserRepository(GetUserContext(contextType)));
    }
    public static IUserLogic UserLogic(){
        return new UserLogic(new UserRepository(GetUserContext(contextTypeStatic)));
    }
    private static IUserContext GetUserContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new UserContextSQL();
            case MEMORY:
                return new UserContextMemory();
        }
        throw new IllegalArgumentException("User Context type none existent");
    }





    public static ITableLogic TableLogic(ContextType contextType){
        return new TableLogic(new TableRepository(GetTableContext(contextType)));
    }
    public static ITableLogic TableLogic(){
        return new TableLogic(new TableRepository(GetTableContext(contextTypeStatic)));
    }
    private static ITableContext GetTableContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new TableContextSQL();
            case MEMORY:
                return new TableContextMemory();
        }
        throw new IllegalArgumentException("Table Context type none existent");
    }





    public static IArticleLogic ArticleLogic(ContextType contextType){
        return new ArticleLogic(new ArticleRepository(GetArticleContext(contextType)));
    }
    public static IArticleLogic ArticleLogic(){
        return new ArticleLogic(new ArticleRepository(GetArticleContext(contextTypeStatic)));
    }
    private static IArticleContext GetArticleContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new ArticleContextSQL();
            case MEMORY:
                return new ArticleContextMemory();
        }
        throw new IllegalArgumentException("Article Context type none existent");
    }





    public static IOrderLogic OrderLogic(ContextType contextType){
        return new OrderLogic(
                new OrderRepository(GetOrderContext(contextType)),
                new TableRepository(GetTableContext(contextType)),
                new ArticleOrderRepository(getArticleOrderContext(contextType))
        );
    }
    public static IOrderLogic OrderLogic(){
        return new OrderLogic(
                new OrderRepository(GetOrderContext(contextTypeStatic)),
                new TableRepository(GetTableContext(contextTypeStatic)),
                new ArticleOrderRepository(getArticleOrderContext(contextTypeStatic))
        );
    }
    private static IOrderContext GetOrderContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new OrderContextSQL();
            case MEMORY:
                return new OrderContextMemory();
        }
        throw new IllegalArgumentException("Order Context type none existent");
    }




    private static IArticleOrderContext getArticleOrderContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new ArticleOrderContextSQL();
            case MEMORY:
                return new ArticleOrderContextMemory();
        }
        throw new IllegalArgumentException("ArticleOrder Context type none existent");
    }

}
