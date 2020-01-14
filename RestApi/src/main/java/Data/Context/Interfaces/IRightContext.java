package Data.Context.Interfaces;

import Data.DTO.RightDto;
import Data.DTO.UserDto;
import Interfaces.model.IRight;
import Interfaces.model.IUser;

import java.util.List;

public interface IRightContext {
    RightDto read(int id);
    RightDto read(String name);

    List<RightDto> list(UserDto user);
    List<RightDto> list();
}
