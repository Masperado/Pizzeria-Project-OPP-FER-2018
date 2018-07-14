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
public class EmployeeService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public void save(User user) {
        Role employeeRole = Role.EMPLOYEE;
        user.setRole(employeeRole);
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }

    @Transactional
    public User findByUsername(String username) {
        User employee = userRepository.findByUsername(username);

        if (employee == null) {
            return null;
        }

        if (employee.getRole() != Role.EMPLOYEE) {
            return null;
        }

        return employee;
    }

    @Transactional
    public List<User> removeByUsername(String username) {
        if (findByUsername(username) != null) {
            return userRepository.deleteByUsername(username);
        }
        return null;
    }

    @Transactional
    public List<User> removeById(Integer id) {
        return userRepository.deleteById(id);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll().stream().filter(e -> e.getRole().equals(Role.EMPLOYEE)).collect(Collectors.toList());
    }

    @Transactional
    public User findOne(Integer id) {
        User user = userRepository.findOne(id);
        if (user == null || !user.getRole().equals(Role.EMPLOYEE)) {
            return null;
        }
        return user;
    }

    @Transactional
    public void removeEmployee(User employee) {
        if (employee == null || !employee.getRole().equals(Role.EMPLOYEE)) {
            return;
        }
        userRepository.delete(employee);
    }

    @Transactional
    public void updateEmployee(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User id not defined!");
        }

        User oldUser = userRepository.getOne(user.getId());
        if (oldUser == null || !user.getRole().equals(Role.EMPLOYEE)) {
            throw new UsernameNotFoundException("Not found username while updating");
        }

        userRepository.save(user);
    }

    @Transactional
    public void setOrdersOfEmployeeToNull(User employee) {
        List<Order> orders = orderRepository.findAllByEmployee(employee);
        for (int i = 0, size = orders.size(); i < size; i++) {
            Order order = orders.get(i);
            order.setEmployee(null);
            orderRepository.save(order);
        }
    }
}
