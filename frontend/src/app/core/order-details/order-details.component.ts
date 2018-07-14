import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {OrderModel} from '../../models/order.model';
import {OrderService} from '../../services/order.service';
import {PizzaService} from '../../services/pizza.service';
import {NgForm} from '@angular/forms';
import {Grade, GradeModel} from '../../models/complex/grade.model';
import {UserService} from '../../services/user.service';
import {GradeService} from '../../services/grade.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.scss']
})
export class OrderDetailsComponent implements OnInit {

  sub: any;
  id: number;
  order: OrderModel;

  constructor(private orderService: OrderService, private gradeService: GradeService, private route: ActivatedRoute, private pizzaService: PizzaService, private userService: UserService) {
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

  onGrade(form: NgForm) {
    console.log(form.value);
    let gradeArray: Grade[] = this.makeArray(form.value);

    let grade: GradeModel = new GradeModel(gradeArray, this.order.orderId, this.userService.token.token);
    this.gradeService.gradeOrder(grade).subscribe((data) => {
      console.log(data.message);
      this.orderService.getOrder(this.id).subscribe((data2) => {
        this.order = data2;
        this.setNameAndPrice();

      });
    });
  }

  makeArray(form: Object) {
    let gradeArray: Grade[] = new Array < Grade >();

    for (let key in form) {
      let value = form[key];
      gradeArray.push(new Grade(+value, +key));
    }

    return gradeArray;
  }

}
