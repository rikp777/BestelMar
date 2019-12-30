package logic;

import Data.Repository.Interfaces.IUserRepository;
import Interfaces.model.IUser;
import logic.Interfaces.IUserLogic;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserLogic implements IUserLogic {
    private IUserRepository _userRepository;

    public UserLogic(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public boolean login(String email, String password) {
        return _userRepository.checkAuth(email, password);
    }
//    public Message checkAuth(String email, String password) {
//        boolean success = _userRepository.checkAuth(email, password);
//        Message message = new Message();
//
//        if(success){
//            IUser userAuth = _userRepository.getBy(email);
//            if(userAuth.getBlocked()){
//                message.setMessage("User has been blocked");
//                message.setMessageType(MessageType.Warning);
//            }else{
//                message.setMessage("Successfully login");
//                message.setMessageType(MessageType.Success);
//            }
//        }else{
//            message.setMessage("Wrong credentials");
//            message.setMessageType(MessageType.Warning);
//        }
//        return message;
//    }


    public boolean add(IUser entity) {
        return _userRepository.add(entity);
    }
    public boolean edit(IUser entity) {
        return _userRepository.edit(entity);
    }
    public boolean remove(IUser entity) {
        return _userRepository.remove(entity);
    }



    public IUser getBy(int id) {
        return _userRepository.getBy(id);
    }
    public IUser getBy(String email) {
        return _userRepository.getBy(email);
    }
    public IUser getBy(IUser entity) {
        return _userRepository.getBy(entity);
    }



    public List<IUser> getAll() {
        return _userRepository.getAll();
    }



    public static boolean checkRight(String authUser, Right hasRight){
        AtomicBoolean success = new AtomicBoolean(false);

//        new UserRepository().getBy(authUser).getRights().forEach(r -> {
//            if(r.getName().equals(hasRight.toString())){ ;
//                success.set(true);
//            }
//        });

        return success.get();
    }
}
