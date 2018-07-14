import {Component, OnInit} from '@angular/core';
import {OrderService} from '../../services/order.service';
import {OrderModel} from '../../models/order.model';
import {ShortPizzaQuantity} from '../../models/shortOrder.model';
import {UserService} from '../../services/user.service';
import {Observable} from 'rxjs/Rx';
import {Token} from '../../models/token.model';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  currentOrder: OrderModel;
  sub: any;
  orderInProgress = false;
  currentOrderStatus: string;
  currentOrderId: number;
  interval: any;
  blockedOrders = false;
  userToken: Token;

  constructor(private orderService: OrderService, private userService: UserService) {
  }

  ngOnInit() {
    this.userService.userLoaded.subscribe((data) => {
      this.userToken = this.userService.token;
    });

    this.orderService.getCurrentOrders().subscribe((data) => {
        if (data.length === 1) {
          this.currentOrder = data[0];
          this.orderInProgress = true;
          localStorage.setItem('orderInProgress', JSON.stringify(this.orderInProgress));
          this.currentOrderId = this.currentOrder.orderId;
          localStorage.setItem('currentOrderId', JSON.stringify(this.currentOrderId));
          this.currentOrderStatus = this.currentOrder.orderStatus;
          localStorage.setItem('currentOrderStatus', JSON.stringify(this.currentOrderStatus));

          this.orderService.currentOrder = this.currentOrder;
          this.orderService.generateLongOrder();

          this.sub = this.orderService.change.subscribe(data => {
            this.currentOrder = this.orderService.longCurrentOrder;
          });
          this.interval = Observable.interval(5000).subscribe(x => {
            this.checkStatus();
          });
        }

      }
    );

    if (localStorage.orderInProgress) {
      this.orderInProgress = JSON.parse(localStorage.orderInProgress);
      this.currentOrderStatus = JSON.parse(localStorage.currentOrderStatus);
      this.currentOrderId = JSON.parse(localStorage.currentOrderId);
      this.interval = Observable.interval(5000).subscribe(x => {
        this.checkStatus();
      });
    }

    this.orderService.init();
    this.sub = this.orderService.change.subscribe(data => {
      this.currentOrder = this.orderService.longCurrentOrder;
    });

  }

  add(id: number) {
    this.orderService.addPizza(new ShortPizzaQuantity(id, 1));
  }

  remove(id: number) {
    this.orderService.removePizza(id, false);
  }

  removeAll(id: number) {
    this.orderService.removePizza(id, true);
  }

  order() {
    this.orderService.order().subscribe(data => {
      if (data.orderOK === false) {
        this.blockedOrders = true;
      } else {
        this.orderInProgress = true;
        localStorage.setItem('orderInProgress', JSON.stringify(this.orderInProgress));
        this.currentOrderId = data.order.id;
        localStorage.setItem('currentOrderId', JSON.stringify(this.currentOrderId));
        this.currentOrderStatus = data.order.status;
        localStorage.setItem('currentOrderStatus', JSON.stringify(this.currentOrderStatus));

        this.interval = Observable.interval(5000).subscribe(x => {
          this.checkStatus();
        });
      }
    });
  }

  checkStatus() {
    this.orderService.getOrder(this.currentOrderId).subscribe(data => {
      this.currentOrderStatus = data.orderStatus;
      localStorage.setItem('currentOrderStatus', JSON.stringify(this.currentOrderStatus));
      if (this.currentOrderStatus === 'PAID') {
        this.orderInProgress = false;
        this.currentOrderId = null;
        this.currentOrderStatus = null;
        localStorage.removeItem('orderInProgress');
        localStorage.removeItem('currentOrderId');
        localStorage.removeItem('currentOrderStatus');
        this.interval.unsubscribe();
        this.orderService.clear();
      }
    });
  }

}
