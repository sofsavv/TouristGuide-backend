package raf.web.turistickivodic.repositories.user;

import raf.web.turistickivodic.entities.User;

import java.util.List;

public interface UserRepository {

    public User findUser(String email);
    public User addUser(User user);
    public List<User> allUsers();
    public User editUser(String email, User user);
    public void deleteUser(String user);
    public void changeStatus(String user);

}
