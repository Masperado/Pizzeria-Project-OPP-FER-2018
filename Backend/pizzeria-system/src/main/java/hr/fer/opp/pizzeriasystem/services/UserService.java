package hr.fer.opp.pizzeriasystem.services;

import hr.fer.opp.pizzeriasystem.models.Order;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.models.User;
import hr.fer.opp.pizzeriasystem.repository.OrderRepository;
import hr.fer.opp.pizzeriasystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void save(User user) {
        Role userRole = Role.USER;

        user.setPassword(user.getPassword());
        user.setRole(userRole);
        userRepository.save(user);
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public void removeUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User id not defined!");
        }
        User oldUser = userRepository.getOne(user.getId());
        if (oldUser == null) {
            throw new UsernameNotFoundException("Not found username while updating");
        }

        userRepository.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll().stream().filter(e -> e.getRole().equals(Role.USER)).collect(Collectors.toList());
    }
    @Transactional
    public List<User> findAllNotFiltered() {
        return userRepository.findAll();
    }


    @Transactional
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public void setOrdersOfUserToNull(User user) {
        List<Order> orders = orderRepository.findAllByUser(user);
        for (int i = 0, size = orders.size(); i < size; i++) {
            Order order = orders.get(i);
            order.setUser(null);
            orderRepository.save(order);
        }
    }
    
}
