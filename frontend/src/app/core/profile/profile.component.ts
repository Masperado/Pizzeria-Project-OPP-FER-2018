import {AfterViewChecked, AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserModel} from '../../models/user.model';
import {UserService} from '../../services/user.service';
import {LoyaltyModel} from '../../models/loyalty.model';
import {Form, FormGroup, NgForm} from '@angular/forms';
import {Router} from '@angular/router';
import {OrderModel} from '../../models/order.model';
import {OrderService} from '../../services/order.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  user: UserModel;
  @ViewChild('f') form: NgForm;

  formInvalid = false;
  wrongInput = '';


  profileChanged = false;


  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    this.userService.getLoyalty().subscribe((data) => {
      this.user = this.userService.getProfile();
      this.enterData();
    });

  }

  enterData() {
    this.form.form.patchValue(this.user);
    this.form.form.patchValue({password: ''});
    this.form.form.patchValue({passwordConfirm: ''});
  }

  onUpdate(form: NgForm) {
    const firstName: string = form.value.firstName;
    const lastName: string = form.value.lastName;
    const email: string = form.value.email;
    let password: string = form.value.password;
    let passwordConfirm: string = form.value.passwordConfirm;
    const address: string = form.value.address;
    const phoneNumber: string = form.value.phoneNumber;
    const username: string = this.user.username;

    const gender: string = this.user.gender;

    if (!password) {
      password = this.user.password;
      passwordConfirm = this.user.password;
    }


    if (firstName.length === 0 || lastName.length === 0 || email.length === 0 ||
      password.length === 0 || passwordConfirm.length === 0 || address.length === 0
      || phoneNumber.length === 0 || username.length === 0) {
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
          console.log(email);
          this.formInvalid = true;
          this.wrongInput = 'Email nije ispravan!';
        } else {
          regexp = new RegExp('^\\+[1-9]{1}[0-9]{3,14}$');
          const regexp2 = new RegExp('0[0-9]{8,9}'),
            test1 = regexp.test(phoneNumber),
            test2 = regexp2.test(phoneNumber);
          if (test1 === false && test2 === false) {
            this.formInvalid = true;
            this.wrongInput = 'Broj telefona nije ispravan!';
          } else {
            this.formInvalid = false;
          }
        }
      }
    }

    if (!this.formInvalid) {
      const userNew: UserModel = new UserModel(username, password, address, email, firstName, lastName, gender, phoneNumber, 'USER', null);

      this.userService.editProfile(userNew).subscribe(data => {
        this.userService.reload(data);
        this.profileChanged = true;
        this.ngOnInit();
      });
    } else {
      this.ngOnInit();
    }


  }

}
