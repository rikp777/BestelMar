package logic;

import Data.DTO.UserDto;
import Factory.Factory;
import Factory.ContextType;
import Interfaces.model.IUser;
import logic.Interfaces.IUserLogic;
import org.apache.derby.impl.sql.catalog.SYSCOLUMNSRowFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserLogicTest{

    private IUserLogic _userLogic;
    private String authUser;
    private List<UserDto> users = new ArrayList<>();

    @BeforeEach
    void setUp(){
        _userLogic = Factory.userLogic(ContextType.MEMORY);
        authUser = "rikpeeters@hotmail.com";
        users.add(new UserDto(1, "rikpeeters@hotmail.com", "Rik", "Peeters", true));
        users.add(new UserDto(2, "janpieters@hotmail.com", "Jan", "Pieters", true));
    }

    @Test
    void add(){
        IUser user = users.get(0);

        _userLogic.add(user);

        int expected = 1;
        int actual = _userLogic.getAll().size();
        assertEquals(expected, actual);

        IUser userD = _userLogic.getAll().get(0);

        assertEquals(userD.getEmail(), user.getEmail());
        assertEquals(userD.getId(), user.getId());
        assertEquals(userD.getBlocked(), user.getBlocked());
        assertEquals(userD.getFirstName(), user.getFirstName());
    }
    @Test
    void edit(){
        IUser userNew = users.get(0);
        IUser userUpdated = new UserDto(1, "pietersjan@hotmail.com", "Pieters", "Jan", false);

        _userLogic.add(userNew);
        _userLogic.edit(userUpdated);

        int expected = 1;
        int actual = _userLogic.getAll().size();
        assertEquals(expected, actual);

        IUser insertedUser = _userLogic.getAll().get(0);

        assertEquals(insertedUser.getEmail(), userUpdated.getEmail());
        assertEquals(insertedUser.getId(), userUpdated.getId());
        assertEquals(insertedUser.getBlocked(), userUpdated.getBlocked());
        assertEquals(insertedUser.getFirstName(), userUpdated.getFirstName());
    }
    @Test
    void delete(){
        IUser userNew = users.get(0);

        _userLogic.add(userNew);

        int expected = 1;
        int actual = _userLogic.getAll().size();
        assertEquals(expected, actual);

        _userLogic.remove(userNew);

        expected = 0;
        actual = _userLogic.getAll().size();
        assertEquals(expected, actual);
    }


    @Test
    void getById(){
        IUser userNew = users.get(0);
        _userLogic.add(userNew);

        IUser user = _userLogic.getBy(userNew.getId());

        assertEquals(user.getEmail(), userNew.getEmail());
        assertEquals(user.getId(), userNew.getId());
        assertEquals(user.getBlocked(), userNew.getBlocked());
        assertEquals(user.getFirstName(), userNew.getFirstName());
    }

    @Test
    void getByName(){
        IUser userNew = users.get(0);
        _userLogic.add(userNew);

        IUser user = _userLogic.getBy(userNew.getEmail());

        assertEquals(user.getEmail(), userNew.getEmail());
        assertEquals(user.getId(), userNew.getId());
        assertEquals(user.getBlocked(), userNew.getBlocked());
        assertEquals(user.getFirstName(), userNew.getFirstName());
    }
    @Test
    void getByEntity(){
        IUser userNew = users.get(0);
        _userLogic.add(userNew);

        IUser user = _userLogic.getBy(userNew);

        assertEquals(user.getId(), userNew.getId());
        assertEquals(user.getEmail(), userNew.getEmail());
        assertEquals(user.getBlocked(), userNew.getBlocked());
        assertEquals(user.getFirstName(), userNew.getFirstName());
    }


    @Test
    void getAll(){
        _userLogic.add(users.get(0));
        _userLogic.add(users.get(1));

        int expected = 2;
        int actual = _userLogic.getAll().size();

        assertEquals(expected, actual);
    }

    @Test
    void Login_CorrectCredentials_shouldBeTrue(){
        IUser userNew = users.get(0);
        userNew.setPassword("password");

        System.out.println("password = " + userNew.getPassword());
        _userLogic.add(userNew);

        boolean expected = true;
        boolean actual = _userLogic.login(userNew.getEmail(), userNew.getPassword());

        assertEquals(expected, actual);
    }
    @Test
    void Login_FallsCredentials_shouldBeFalse(){
        IUser userNew = users.get(0);
        userNew.setPassword("password");

        _userLogic.add(userNew);

        boolean expected = false;
        boolean actual = _userLogic.login(userNew.getEmail(), "sdfla");

        assertEquals(expected, actual);
    }
}
