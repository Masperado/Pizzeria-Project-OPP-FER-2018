import {Component, OnInit} from '@angular/core';
import {UserModel} from '../../models/user.model';
import {NgForm} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {ShortUserModel} from '../../models/shortuser.model';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  sub: Subscription;
  wrong: boolean = false;

  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    if (this.userService.userLoggedIn) {
      this.router.navigate(['/']);
    }

    this.sub = this.userService.changeHappened.subscribe(() => {
      if (this.userService.loggedUser) {
        if (this.userService.loggedUser.role === 'USER') {
          this.router.navigate(['/']);
        } else if (this.userService.loggedUser.role === 'EMPLOYEE') {
          this.router.navigate(['/employee']);
        } else if (this.userService.loggedUser.role === 'ADMIN') {
          this.router.navigate(['/admin']);
        }
      }
      this.wrong = this.userService.loginError;
    });
  }

  onLogin(form: NgForm) {
    const password: string = form.value.password;
    const username: string = form.value.username;

    const user: ShortUserModel = new ShortUserModel(username, password);

    this.userService.login(user);


  }


}
