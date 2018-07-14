import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {UserModel} from '../../models/user.model';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  registrationSuccess: boolean = false;
  wrongInput = '';
  formInvalid = false;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
  }

  onSignup(form: NgForm) {
    const firstName: string = form.value.firstName;
    const lastName: string = form.value.lastName;
    const email: string = form.value.email;
    const password: string = form.value.password;
    const passwordConfirm: string = form.value.passwordConfirm;
    const address: string = form.value.address;
    const phone: string = form.value.phone;
    const username: string = form.value.username;

    let gender: string = 'U';

    if (form.value.male) {
      gender = 'M';
    }

    if (form.value.female) {
      gender = 'F';
    }

    if (firstName.length === 0 || lastName.length === 0 || email.length === 0 ||
      password.length === 0 || passwordConfirm.length === 0 || address.length === 0
      || phone.length === 0 || username.length === 0) {
      this.formInvalid = true;
      this.wrongInput = 'Niste unijeli sva polja!';
    } else {
      if (!(password === passwordConfirm)) {
        this.formInvalid = true;
        this.wrongInput = 'Lozinke se ne podudaraju!';
      } else {
        let regexp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
        let test = regexp.test(email);
        if (test === false) {
          this.formInvalid = true;
          this.wrongInput = 'Email nije ispravan!';
        } else {
          regexp = new RegExp('^\\+[1-9]{1}[0-9]{3,14}$');
          const regexp2 = new RegExp('0[0-9]{8,9}'),
            test1 = regexp.test(phone),
            test2 = regexp2.test(phone);
          if (test1 === false && test2 === false) {
            this.formInvalid = true;
            this.wrongInput = 'Broj telefona nije ispravan!';
          } else {
            this.formInvalid = false;
          }
        }
      }
    }

    form.resetForm();

    if (!this.formInvalid) {


      const user: UserModel = new UserModel(username, password, address, email, firstName, lastName, gender, phone, 'USER', null);

      this.userService.registration(user).subscribe(data => {
        this.registrationSuccess = true;
      });
    }
  }

}
