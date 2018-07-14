import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {Subscription} from 'rxjs/Subscription';
import {UserModel} from '../../models/user.model';
import {Router} from '@angular/router';
import {OrderService} from '../../services/order.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  userLoggedIn: boolean = false;
  sub: Subscription;
  user: UserModel;
  regularHeader = true;
  employeeHeader = false;
  adminHeader = false;

  constructor(private userService: UserService, private router: Router, private orderService: OrderService) {
  }

  ngOnInit() {
    this.userService.init();

    this.sub = this.userService.changeHappened.subscribe(() => {
      if (this.userService.userLoggedIn) {
        this.userLoggedIn = this.userService.userLoggedIn;
        this.user = this.userService.getProfile();
        if (this.user.role === 'ADMIN') {
          this.regularHeader = false;
          this.employeeHeader = false;
          this.adminHeader = true;
        } else if (this.user.role === 'EMPLOYEE') {
          this.regularHeader = false;
          this.employeeHeader = true;
          this.adminHeader = false;
        } else {
          this.regularHeader = true;
          this.employeeHeader = false;
          this.adminHeader = false;
        }
      } else {
        this.userLoggedIn = false;
      }
    });
  }

  logout() {
    localStorage.clear();
    this.userService.logout();
    this.regularHeader = true;
    this.employeeHeader = false;
    this.adminHeader = false;
    this.router.navigate(['/']);
  }

}
