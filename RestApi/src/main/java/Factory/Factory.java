package Factory;

import Data.Context.Interfaces.IArticleContext;
import Data.Context.Interfaces.IOrderContext;
import Data.Context.Interfaces.ITableContext;
import Data.Context.Interfaces.IUserContext;
import Data.Context.MemoryContext.ArticleContextMemory;
import Data.Context.MemoryContext.OrderContextMemory;
import Data.Context.MemoryContext.TableContextMemory;
import Data.Context.MemoryContext.UserContextMemory;
import Data.Context.SQLContext.ArticleContextSQL;
import Data.Context.SQLContext.OrderContextSQL;
import Data.Context.SQLContext.TableContextSQL;
import Data.Context.SQLContext.UserContextSQL;
import Data.Repository.ArticleRepository;
import Data.Repository.OrderRepository;
import Data.Repository.TableRepository;
import Data.Repository.UserRepository;
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



    public static IUserLogic userLogic(ContextType contextType){
        return new UserLogic(new UserRepository(getUserContext(contextType)));
    }
    public static IUserLogic userLogic(){
        return new UserLogic(new UserRepository(getUserContext(contextTypeStatic)));
    }
    private static IUserContext getUserContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new UserContextSQL();
            case MEMORY:
                return new UserContextMemory();
        }
        throw new IllegalArgumentException("User Context type none existent");
    }





    public static ITableLogic tableLogic(ContextType contextType){
        return new TableLogic(new TableRepository(getTableContext(contextType)));
    }
    public static ITableLogic tableLogic(){
        return new TableLogic(new TableRepository(getTableContext(contextTypeStatic)));
    }
    private static ITableContext getTableContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new TableContextSQL();
            case MEMORY:
                return new TableContextMemory();
        }
        throw new IllegalArgumentException("Table Context type none existent");
    }





    public static IArticleLogic ArticleLogic(ContextType contextType){
        return new ArticleLogic(new ArticleRepository(getArticleContext(contextType)));
    }
    public static IArticleLogic ArticleLogic(){
        return new ArticleLogic(new ArticleRepository(getArticleContext(contextTypeStatic)));
    }
    private static IArticleContext getArticleContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new ArticleContextSQL();
            case MEMORY:
                return new ArticleContextMemory();
        }
        throw new IllegalArgumentException("Article Context type none existent");
    }





    public static IOrderLogic OrderLogic(ContextType contextType){
        return new OrderLogic(new OrderRepository(getOrderContext(contextType)));
    }
    public static IOrderLogic OrderLogic(){
        return new OrderLogic(new OrderRepository(getOrderContext(contextTypeStatic)));
    }
    private static IOrderContext getOrderContext(ContextType contextType){
        switch (contextType){
            case SQL:
                return new OrderContextSQL();
            case MEMORY:
                return new OrderContextMemory();
        }
        throw new IllegalArgumentException("Order Context type none existent");
    }
}
