import {Component, OnInit} from '@angular/core';
import {OrderModel} from '../../../models/order.model';
import {EmployeeService} from '../../../services/employee.service';
import {DatePipe} from '@angular/common';
import {Observable} from 'rxjs/Rx';

@Component({
  selector: 'app-employee-orders',
  templateUrl: './employee-orders.component.html',
  styleUrls: ['./employee-orders.component.scss']
})
export class EmployeeOrdersComponent implements OnInit {
  currentOrders: OrderModel[];
  pastOrders: OrderModel[];
  interval: any;

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this.employeeService.getPastEmployeeOrders().subscribe((data) => {
      this.pastOrders = data;
    });

    this.employeeService.getCurrentEmployeeOrders().subscribe((data) => {
      this.currentOrders = data;
    });

    this.interval = Observable.interval(5000).subscribe(x => {
      this.employeeService.getCurrentEmployeeOrders().subscribe((data) => {
        this.currentOrders = data;
      });
      this.employeeService.getPastEmployeeOrders().subscribe((data) => {
        this.pastOrders = data;
      });
    });
  }

}
