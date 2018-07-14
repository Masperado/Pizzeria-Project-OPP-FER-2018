import {AfterViewInit, Component, OnInit} from '@angular/core';
import {PizzaService} from '../../services/pizza.service';
import {PizzaModel} from '../../models/pizza.model';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {UserModel} from '../../models/user.model';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  pizzaList: PizzaModel[];

  constructor(private pizzaService: PizzaService, private router: Router, private userService: UserService) {
  }

  ngOnInit() {

    this.userService.changeHappened.subscribe(data => {
      let user: UserModel = this.userService.loggedUser;
      if (user) {
        if (user.role === 'EMPLOYEE') {
          this.router.navigate(['/employee']);
        }
        if (user.role === 'ADMIN') {
          this.router.navigate(['/admin']);
        }
      }
    });


    this.loadPizzaList();
  }


  loadPizzaList() {
    this.pizzaService.getOfferedPizzas().subscribe(responseData => this.pizzaList = responseData);
  }


}
