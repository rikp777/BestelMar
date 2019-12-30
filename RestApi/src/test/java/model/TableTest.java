package model;

import Data.DTO.ArticleDto;
import Data.DTO.TableDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTest {

    private static List<TableDto> tables = new ArrayList<>();

    @BeforeAll
    static void setUp(){
        tables.add(new TableDto(1, "Tafel 1", "Links achter in de hoek bij de kast", true));
        tables.add(new TableDto(2, "Tafel 2", "Rechts bij de balie voor de plant", false));
        tables.add(new TableDto(3, "Tafel 3", "Rechts bij de balie voor de plant", false));
    }

    @Test
    void getName(){
        TableDto table = tables.get(0);
        assertEquals("Tafel 1", table.getName());
    }
    @Test
    void getDescription(){
        TableDto table = tables.get(0);
        assertEquals("Links achter in de hoek bij de kast", table.getDescription());
    }
    @Test
    void getDisabled(){
        TableDto table = tables.get(0);
        assertEquals(true, table.getDisabled());
    }
}
