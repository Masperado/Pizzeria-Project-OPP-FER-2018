import {Component, OnInit} from '@angular/core';
import {OrderService} from '../../services/order.service';
import {OrderModel} from '../../models/order.model';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  currentOrders: OrderModel[];
  pastOrders: OrderModel[];

  constructor(private orderService: OrderService) {
  }

  ngOnInit() {
    this.orderService.getPastOrders().subscribe((data) => {
      this.pastOrders = data;
    });

    this.orderService.getCurrentOrders().subscribe((data) => {
      this.currentOrders = data;
    });
  }

}
