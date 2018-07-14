import { Component, OnInit } from '@angular/core';
import {UserModel} from '../../../models/user.model';
import {UserService} from '../../../services/user.service';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.scss']
})
export class AdminUserComponent implements OnInit {

  userList: UserModel[];
  editing = false;
  userRegistered = false;
  userEdited = false;
  currentuser: UserModel;
  formInvalid = false;
  wrongInput = '';

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getUsers().subscribe(data => {
      this.userList = data;
    });
  }

  addUser(form: NgForm) {
    const firstName: string = form.value.firstName;
    const lastName: string = form.value.lastName;
    const email: string = form.value.email;
    let password: string = form.value.password;
    let passwordConfirm: string = form.value.passwordConfirm;
    const address: string = form.value.address;
    const phone: string = form.value.phoneNumber;
    const username: string = form.value.username;

    let gender: string = 'U';

    if (form.value.male) {
      gender = 'M';
    }

    if (form.value.female) {
      gender = 'F';
    }

    if (!password) {
      password = this.currentuser.password;
      passwordConfirm = this.currentuser.password;
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


      let user: UserModel = new UserModel(username, password, address, email, firstName, lastName, gender, phone, 'USER', null);

      if (this.editing) {
        user.id = this.currentuser.id;
        this.userService.editAdmin(user).subscribe(data => {
          this.userRegistered = false;
          this.userEdited = true;
          this.editing = false;
          this.ngOnInit();
        });
      } else {
        this.userService.registration(user).subscribe(data => {
          this.userRegistered = true;
          this.userEdited = false;
          this.editing = false;
          this.ngOnInit();
        });
      }
    }
  }


  edit(user: UserModel, form: any) {
    form.resetForm();
    this.editing = true;
    this.userRegistered = false;
    this.userEdited = false;
    this.currentuser = user;

    form.form.patchValue(user);
    form.form.patchValue({password: ''});
  }

  create(form: any) {
    this.editing = false;
    this.userRegistered = false;
    this.userEdited = false;
    this.currentuser = null;
    form.resetForm();
  }
}
