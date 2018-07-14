import {Component, OnInit} from '@angular/core';
import {EmployeeService} from '../../../services/employee.service';

@Component({
  selector: 'app-control',
  templateUrl: './control.component.html',
  styleUrls: ['./control.component.scss']
})
export class ControlComponent implements OnInit {

  status: string;

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this.employeeService.getPizzeriaStatus().subscribe(data => {
      this.status = data.message;
    });
  }

  block() {
    this.employeeService.blockOrdering().subscribe(data => {
      this.ngOnInit();
    });
  }

  open() {
    this.employeeService.openOrdering().subscribe((data => {
      this.ngOnInit();
    }));
  }


}
