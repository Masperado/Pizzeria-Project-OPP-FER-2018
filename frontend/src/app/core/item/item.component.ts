import {Component, OnInit} from '@angular/core';
import {PizzaModel} from '../../models/pizza.model';
import {PizzaService} from '../../services/pizza.service';
import {ActivatedRoute, Router} from '@angular/router';
import {OrderService} from '../../services/order.service';
import {OrderModel, PizzaQuantity} from '../../models/order.model';
import {ShortOrderModel, ShortPizzaQuantity} from '../../models/shortOrder.model';
import {NgForm} from '@angular/forms';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  pizza: PizzaModel;
  id: number;
  sub: any;

  constructor(private router: Router, private userService: UserService, private pizzaService: PizzaService, private route: ActivatedRoute, private orderService: OrderService) {

  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {

      this.id = +params['id'];

      this.pizzaService.getPizza(this.id).subscribe((data) => {
        this.pizza = data;
      });


    });
  }

  order(form: NgForm) {
    if (!this.userService.userLoggedIn) {
      this.router.navigate(['/login']);
    } else {

      let quantity: number = +form.value.order;
      if (!quantity) {
        quantity = 1;
      }

      this.orderService.addPizza(new ShortPizzaQuantity(this.id, quantity));
    }
  }

}
