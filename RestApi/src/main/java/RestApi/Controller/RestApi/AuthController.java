package RestApi.Controller.RestApi;


import Data.Context.MemoryContext.UserContextMemory;
import Data.Repository.UserRepository;
import Factory.Factory;
import logic.Interfaces.IUserLogic;
import logic.UserLogic;
import RestApi.VOModels.VOAuthUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private IUserLogic userLogic = Factory.UserLogic();

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody VOAuthUser user) {

        //Message message = userLogic.checkAuth(user.getEmail(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("blep");
    }
}
