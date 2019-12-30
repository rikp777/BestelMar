package RestApi.Controller.RestApi;

import Data.Context.MemoryContext.UserContextMemory;
import Data.Repository.UserRepository;
import Interfaces.model.IUser;
import logic.UserLogic;
import RestApi.VOModels.VOUser;
import models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
public class UserController {
    private UserLogic userLogic = new UserLogic(new UserRepository(new UserContextMemory()));

    @PostMapping("/user")
    public ResponseEntity create(@RequestBody User user){
        if(userLogic.getBy(user) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Already exists");
        }
        if(userLogic.add(user)){
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity update(@RequestBody User user){
        if(userLogic.edit(user)){
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity delete(@PathVariable int id){
        if(userLogic.getBy(id) != null) {
            userLogic.remove(userLogic.getBy(id));
            return ResponseEntity.status(HttpStatus.OK).body("User status changed");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found");
    }

    @GetMapping("/user")
    public ResponseEntity read(){
        if(userLogic.getAll() != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    userLogic.getAll()
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No users found");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity read(@PathVariable int id){
        if(userLogic.getBy(id) != null){
            IUser user = userLogic.getBy(id);


            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    setResource(user)
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("User not found");
    }


    public VOUser setResource(IUser user){
        VOUser voUser = new VOUser();


        voUser.setId(user.getId());
        voUser.setEmail(user.getEmail());
        voUser.setFirstName(user.getFirstName());
        voUser.setLastName(user.getLastName());
        voUser.setBlocked(user.getBlocked());

        voUser.setOrderLink("/order/user/" + user.getId());
        voUser.setRolesLink("/roles/user/" + user.getId());

        return voUser;
    }
}
