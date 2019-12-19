//package RestApi.Config;
//
//import Interfaces.model.IUser;
//import models.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
//
//public class CustomSecurityUser extends User implements UserDetails {
//    String username;
//    int id;
//
//    public CustomSecurityUser(User user){
//        super();
//        this.setId(user.getId());
//        this.setPassword(user.getPassword());
//        this.setEmail(user.getEmail());
//
//        this.username = user.getEmail();
//        this.id = user.getId();
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    public String getUsername() {
//        return this.getEmail();
//    }
//
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    public boolean isEnabled() {
//        return true;
//    }
//}
