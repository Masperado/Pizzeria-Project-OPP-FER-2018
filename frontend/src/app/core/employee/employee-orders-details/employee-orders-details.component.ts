import {Component, OnInit} from '@angular/core';
import {Grade, GradeModel} from '../../../models/complex/grade.model';
import {NgForm} from '@angular/forms';
import {PizzaService} from '../../../services/pizza.service';
import {OrderModel} from '../../../models/order.model';
import {OrderService} from '../../../services/order.service';
import {ActivatedRoute} from '@angular/router';
import {EmployeeService} from '../../../services/employee.service';

@Component({
  selector: 'app-employee-orders-details',
  templateUrl: './employee-orders-details.component.html',
  styleUrls: ['./employee-orders-details.component.scss']
})
export class EmployeeOrdersDetailsComponent implements OnInit {

  sub: any;
  id: number;
  order: OrderModel;

  constructor(private employeeService: EmployeeService, private pizzaService: PizzaService, private orderService: OrderService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];

      this.orderService.getOrder(this.id).subscribe((data) => {
        this.order = data;
        this.setNameAndPrice();
      });

    });
  }

  setNameAndPrice() {
    for (let pizza of this.order.pizzas) {
      this.pizzaService.getPizza(pizza.id).subscribe((data) => {
        pizza.name = data.name;
        pizza.price = data.price;
      });
    }
  }

  accept() {
    this.employeeService.accept(this.order.orderId);
    this.order.orderStatus = 'ACCEPTED';
  }

  deliver() {
    this.employeeService.delivering(this.order.orderId);
    this.order.orderStatus = 'DELIVERING';
  }

  paid() {
    this.employeeService.paid(this.order.orderId);
    this.order.orderStatus = 'PAID';
  }

  notPaid() {
    this.employeeService.notPaid(this.order.orderId);
    this.order.orderStatus = 'NOT_PAID';
  }

}
