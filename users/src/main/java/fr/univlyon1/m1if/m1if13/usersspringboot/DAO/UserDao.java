package fr.univlyon1.m1if.m1if13.usersspringboot.DAO;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import fr.univlyon1.m1if.m1if13.usersspringboot.model.User;

public class UserDao implements Dao<User> {

    private static UserDao userDao = new UserDao();

    private Set<User> users = new HashSet<>();

    UserDao() {
        this.save(new User("test", "test"));
    }

    public static UserDao createInstance() {
        return userDao;
    }

    @Override
    public Optional<User> get(String id) {
        User user = null;
        Iterator<User> it = users.iterator();
        while(it.hasNext()) {
            User tmp = it.next();
            if (tmp.getLogin().equals(id)) {
                user = tmp;
                break;
            }
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Set<String> getAll() {
        Set<String> set = new HashSet<>();
        Iterator<User> it = users.iterator();
        while(it.hasNext()) {
            User tmp = it.next();
            set.add(tmp.getLogin());
        }
        return set;
    }

    @Override
    public void save(User t) {
        users.add(t);
    }

    @Override
    public void update(User t, String[] params) {
        t.setLogin(Objects.requireNonNull(
          params[0], "Login cannot be null"));
        t.setPassword(Objects.requireNonNull(
          params[1], "Password cannot be null"));
         
        users.add(t);
    }

    @Override
    public void delete(User t) {
        users.remove(t);
    }

}
