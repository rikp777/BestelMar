package Logic;

import Data.MessageType;
import Data.Repository.Interfaces.IUserRepository;
import Data.Repository.OrderRepository;
import Data.Repository.UserRepository;
import Interfaces.model.IOrder;
import Interfaces.model.IUser;
import Logic.Models.Message;
import models.User;

import java.util.List;

public class UserLogic {
    private UserRepository _userRepository;
    private OrderRepository _orderRepository;
    public UserLogic() {
        this._userRepository = new UserRepository();
        this._orderRepository = new OrderRepository();
    }


    public Message checkAuth(String email, String password) {
        boolean success = _userRepository.checkAuth(email, password);
        Message message = new Message();

        if(success){
            IUser userAuth = _userRepository.getBy(email);
            if(userAuth.getBlocked()){
                message.setMessage("User has been blocked");
                message.setMessageType(MessageType.Warning);
            }else{
                message.setMessage("Successfully login");
                message.setMessageType(MessageType.Success);
            }
        }else{
            message.setMessage("Wrong credentials");
            message.setMessageType(MessageType.Warning);
        }
        return message;
    }


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
}
