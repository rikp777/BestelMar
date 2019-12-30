package logic;

import Data.Context.MemoryContext.ArticleContextMemory;
import Data.Context.MemoryContext.TableContextMemory;
import Data.DTO.ArticleDto;
import Data.DTO.TableDto;
import Data.Repository.ArticleRepository;
import Data.Repository.TableRepository;
import Interfaces.model.IArticle;
import Interfaces.model.ITable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableLogicTest {

    private TableLogic _tableLogic;
    private String authUser;
    private List<TableDto> tables = new ArrayList<>();

    @BeforeEach
    void setUp(){
        _tableLogic = new TableLogic(new TableRepository(new TableContextMemory()));
        authUser = "rikpeeters@hotmail.com";
        tables.add(new TableDto(1, "tafel 1", "Links achter in de hoek bij de kast", true));
        tables.add(new TableDto(2, "tafel 2", "rechts achter", true));
    }

    @Test
    void add(){
        ITable table = tables.get(0);


        _tableLogic.add(table);

        int expected = 1;
        int actual = _tableLogic.getAll().size();
        assertEquals(expected, actual);

        ITable tableD = _tableLogic.getAll().get(0);

        assertEquals(tableD.getName(), tableD.getName());
        assertEquals(tableD.getId(), tableD.getId());
        assertEquals(tableD.getDisabled(), tableD.getDisabled());
        assertEquals(tableD.getDescription(), tableD.getDescription());
    }
    @Test
    void edit(){
        ITable tableNew = tables.get(0);
        ITable tableUpdated = new TableDto(1, "tafel 0", "", false);

        _tableLogic.add(tableNew);
        _tableLogic.edit(tableUpdated);

        int expected = 1;
        int actual = _tableLogic.getAll().size();
        assertEquals(expected, actual);

        ITable insertedTable = _tableLogic.getAll().get(0);

        assertEquals(insertedTable.getName(), tableUpdated.getName());
        assertEquals(insertedTable.getId(), tableUpdated.getId());
        assertEquals(insertedTable.getDisabled(), tableUpdated.getDisabled());
        assertEquals(insertedTable.getDescription(), tableUpdated.getDescription());
    }
    @Test
    void delete(){
        ITable tableNew = tables.get(0);

        _tableLogic.add(tableNew);

        int expected = 1;
        int actual = _tableLogic.getAll().size();
        assertEquals(expected, actual);

        _tableLogic.remove(tableNew);

        expected = 0;
        actual = _tableLogic.getAll().size();
        assertEquals(expected, actual);
    }


    @Test
    void getById(){
        ITable tableNew = tables.get(0);
        _tableLogic.add(tableNew);

        ITable table = _tableLogic.getBy(tableNew.getId());

        assertEquals(table.getId(), tableNew.getId());
        assertEquals(table.getName(), tableNew.getName());
        assertEquals(table.getDisabled(), tableNew.getDisabled());
        assertEquals(table.getDescription(), tableNew.getDescription());
    }

    @Test
    void getByName(){
        ITable tableNew = tables.get(0);
        _tableLogic.add(tableNew);

        ITable table = _tableLogic.getBy(tableNew.getName());

        assertEquals(table.getId(), tableNew.getId());
        assertEquals(table.getName(), tableNew.getName());
        assertEquals(table.getDisabled(), tableNew.getDisabled());
        assertEquals(table.getDescription(), tableNew.getDescription());
    }
    @Test
    void getByEntity(){
        ITable tableNew = tables.get(0);
        _tableLogic.add(tableNew);

        ITable table = _tableLogic.getBy(tableNew);

        assertEquals(table.getId(), tableNew.getId());
        assertEquals(table.getName(), tableNew.getName());
        assertEquals(table.getDisabled(), tableNew.getDisabled());
        assertEquals(table.getDescription(), tableNew.getDescription());
    }


    @Test
    void getAll(){
        _tableLogic.add(tables.get(0));
        _tableLogic.add(tables.get(1));

        int expected = 2;
        int actual = _tableLogic.getAll().size();

        assertEquals(expected, actual);
    }
}
