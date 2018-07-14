package hr.fer.opp.pizzeriasystem.controllers;

import hr.fer.opp.pizzeriasystem.models.*;
import hr.fer.opp.pizzeriasystem.models.complexModels.OrderAndMessage;
import hr.fer.opp.pizzeriasystem.models.complexModels.PizzaIdQuantity;
import hr.fer.opp.pizzeriasystem.models.complexModels.PizzaList;
import hr.fer.opp.pizzeriasystem.models.enums.OrderStatus;
import hr.fer.opp.pizzeriasystem.models.enums.Role;
import hr.fer.opp.pizzeriasystem.services.*;
import hr.fer.opp.pizzeriasystem.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrderController {

    // TODO: Kako dohvatiti narudđbe ako si zaposlenik

    @Autowired
    private OrderService orderService;

    @Autowired
    private PizzaOrderService pizzaOrderService;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LoyaltyProgramService loyaltyProgramService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private GradeService gradeService;

    //prosle kupceve narudzbe
    @PostMapping("/user/pastOrders")
    public ResponseEntity<List<PizzaList>> getPastUserOrders(@RequestBody Token token) {

        User user = securityService.loggedIn(token.getToken());

        if (user==null  || user.getRole()!= Role.USER){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Order> orders = orderService.findPastUserOrders(user);

        List<PizzaList> pizzaLists = getPizzaList(orders);

        return new ResponseEntity<>(pizzaLists,HttpStatus.OK);
    }

    //trenutne kupceve narudzbe
    @PostMapping("/user/currentOrders")
    public ResponseEntity<List<PizzaList>> getCurrentUserOrders(@RequestBody Token token) {

        User user = securityService.loggedIn(token.getToken());

        if (user==null  || user.getRole()!= Role.USER){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Order> orders = orderService.findCurrentUserOrders(user);
        List<PizzaList> pizzaLists = getPizzaList(orders);

        return new ResponseEntity<>(pizzaLists,HttpStatus.OK);
    }

    @PostMapping("/admin/orders")
    public ResponseEntity<List<PizzaList>> getAllOrders(@RequestBody Token token)
    {
        User user = securityService.loggedIn(token.getToken());

        if (user==null  || user.getRole()!= Role.ADMIN){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }



        List<PizzaList> pizzaLists = getPizzaList(orderService.findAll());

        return new ResponseEntity<>(pizzaLists,HttpStatus.OK);
    }

    //sve kupceve narudzbe
    @PostMapping("/user/orders")
    public ResponseEntity<List<PizzaList>> getUserOrders(@RequestBody Token token) {

        User user = securityService.loggedIn(token.getToken());

        if (user==null  || user.getRole()!= Role.USER){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        List<Order> orders = orderService.findAllByUser(user);
        List<PizzaList> pizzaLists = getPizzaList(orders);

        return new ResponseEntity<>(pizzaLists, HttpStatus.OK);
    }

    @PostMapping("/user/orders/{id}")
    public ResponseEntity<PizzaList> getOneOrder(@RequestBody Token token, @PathVariable Integer id) {

        User user = securityService.loggedIn(token.getToken());

        if (user==null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Order order = orderService.findOne(id);
        if (user.getRole()!=Role.EMPLOYEE && !order.getUser().getId().equals(user.getId())) {
            return new ResponseEntity<PizzaList>(HttpStatus.FORBIDDEN);
        }
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        List<PizzaList> pizzaLists = getPizzaList(orders);

        return new ResponseEntity<PizzaList>(pizzaLists.get(0), HttpStatus.OK);
    }

    //prikazuje sve ordere koji su placeni ili ne placeni
    @PostMapping("/employee/pastOrders")
    public ResponseEntity<List<PizzaList>> getPastEmployeeOrders(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Order> orders = orderService.findAllPastOrders();
        List<PizzaList> pizzaLists = getPizzaList(orders);

        return new ResponseEntity<>(pizzaLists, HttpStatus.OK);
    }

    //prikazuje sve tekuce ordere
    @PostMapping("/employee/currentOrders")
    public ResponseEntity<List<PizzaList>> getCurrentEmployeeOrders(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Order> orders = orderService.findAllCurrentOrders();
        List<PizzaList> pizzaLists = getPizzaList(orders);

        return new ResponseEntity<>(pizzaLists, HttpStatus.OK);
    }

    //prikazuje sve ordere
    @PostMapping("/employee/orders")
    public ResponseEntity<List<PizzaList>> getEmployeeOrders(@RequestBody Token token) {
        User user = securityService.loggedIn(token.getToken());

        if (user == null || user.getRole() != Role.EMPLOYEE) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        List<Order> orders = orderService.findAll();
        List<PizzaList> pizzaLists = getPizzaList(orders);

        return new ResponseEntity<>(pizzaLists, HttpStatus.OK);
    }

    //narucivanje
    @PostMapping("/user/order")
    public ResponseEntity<OrderAndMessage> orderPizzas(@RequestBody PizzaList plao) {

        User user = securityService.loggedIn(plao.getToken());

        if (user==null  || user.getRole()!= Role.USER){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        OrderAndMessage orderAndMessage = new OrderAndMessage();
        Order orderForm = new Order();
        orderAndMessage.setOrder(orderForm);


        if (!EmployeeController.ORDERS_OPEN) {
            String message= "";
            message = EmployeeController.STORE_OPEN ? "Narudžbe su trenutno blokirane!" : "Pizzeria trenutno ne radi!";
            orderAndMessage.setMessage(message);
            orderAndMessage.setOrderOK(false);
            return new ResponseEntity<>(orderAndMessage, HttpStatus.OK);
        }

        Integer sumOfPizzas = plao.getPizzas().stream().mapToInt(PizzaIdQuantity::getQuantity).sum();
        if (sumOfPizzas == 0) {
            orderAndMessage.setMessage("Niste naručili niti jednu pizzu");
            orderAndMessage.setOrderOK(false);
            return new ResponseEntity<OrderAndMessage>(orderAndMessage, HttpStatus.OK);
        }


        orderForm.setUser(user);
        orderForm.setStatus(OrderStatus.ORDERED);
        orderForm.setDateCreatedAt(new Date());
        orderForm.setEmployee(Util.selectRandomEmployee(employeeService.findAll()));

        List<PizzaIdQuantity> pizzas = plao.getPizzas();

        List<Pizza> pizzaList = new ArrayList<>();
        pizzas.forEach(e -> pizzaList.add(pizzaService.findOne(e.getId())));
        double totalPrice = Util.calculateTotalPrice(pizzaList, pizzas);


        orderForm.setPrice(totalPrice);

        LoyaltyProgram loyaltyProgram = loyaltyProgramService.findByUser(user);

        loyaltyProgram.setPizzasRemainingUntilFree(loyaltyProgram.getPizzasRemainingUntilFree() - sumOfPizzas);
        if (loyaltyProgram.getPizzasRemainingUntilFree() <= 0) {
            Double discount = pizzaList.get(0).getPrice();
            orderForm.setPrice(orderForm.getPrice() - discount);
            loyaltyProgram.setPizzasRemainingUntilFree(loyaltyProgram.getTotalPizzasToGetFreeOne());

            orderAndMessage.setMessage("Prešli ste prag programa vjernosti i ostvarili popust od " + discount + " kuna!");
            orderAndMessage.setOrderOK(true);
        }else {
            orderAndMessage.setMessage("Narudžba je uspješno zaprimljena!");
            orderAndMessage.setOrderOK(true);
        }

        loyaltyProgramService.save(loyaltyProgram);
        orderService.save(orderForm);

        addPizzaOrders(pizzas, orderForm);
        return new ResponseEntity<>(orderAndMessage, HttpStatus.OK);
    }

    private void addPizzaOrders(List<PizzaIdQuantity> pizzas, Order orderForm) {
        if (pizzas == null || pizzas.size() == 0) {
            return;
        }

        for (PizzaIdQuantity piq : pizzas) {
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setNumberOfPizzas(piq.getQuantity());
            pizzaOrder.setPizza(pizzaService.findOne(piq.getId()));
            pizzaOrder.setOrder(orderForm);
            pizzaOrderService.save(pizzaOrder);
        }
    }

    private List<PizzaList> getPizzaList(List<Order> orders) {
        List<PizzaList> pizzaLists = new ArrayList<>();
        for (Order o : orders) {
            List<Grade> grade = gradeService.findAllByOrder(o);
            PizzaList pizzaList = new PizzaList();
            pizzaList.setOrderStatus(o.getStatus());
            pizzaList.setPrice(o.getPrice());
            pizzaList.setOrderDate(o.getDateCreatedAt());
            pizzaList.setOrderId(o.getId());
            pizzaList.setUserName(o.getUser().getUsername());
            pizzaList.setEmployeeName(o.getEmployee().getUsername());
            if (grade.size() != 0) {
                pizzaList.setHasGrade(true);
            }else {
                pizzaList.setHasGrade(false);
            }
            List<PizzaOrder> poList = pizzaOrderService.findByOrderId(o);
            List<PizzaIdQuantity> piqList = new ArrayList<>();
            pizzaList.setPizzas(piqList);
            for (PizzaOrder po : poList) {
                PizzaIdQuantity piq = new PizzaIdQuantity();
                piq.setId(po.getPizza().getId());
                piq.setQuantity(po.getNumberOfPizzas());
                piqList.add(piq);
            }
            pizzaLists.add(pizzaList);
        }

        return pizzaLists;
    }

}
