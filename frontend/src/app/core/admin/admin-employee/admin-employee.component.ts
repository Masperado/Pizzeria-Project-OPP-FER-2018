import {Component, OnInit} from '@angular/core';
import {IngredientsModel} from '../../../models/ingredients.model';
import {NgForm} from '@angular/forms';
import {PizzaModel} from '../../../models/pizza.model';
import {EmployeeService} from '../../../services/employee.service';
import {UserModel} from '../../../models/user.model';

@Component({
  selector: 'app-admin-employee',
  templateUrl: './admin-employee.component.html',
  styleUrls: ['./admin-employee.component.scss']
})
export class AdminEmployeeComponent implements OnInit {

  employeeList: UserModel[];
  editing = false;
  employeeRegistered = false;
  employeeEdited = false;
  currentEmployee: UserModel;
  formInvalid = false;
  wrongInput = '';

  constructor(private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this.employeeService.getEmployees().subscribe(data => {
      this.employeeList = data;
    });
  }

  addEmployee(form: NgForm) {
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
      password = this.currentEmployee.password;
      passwordConfirm = this.currentEmployee.password;
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


      let user: UserModel = new UserModel(username, password, address, email, firstName, lastName, gender, phone, 'EMPLOYEE', null);

      if (this.editing) {
        user.id = this.currentEmployee.id;
        this.employeeService.edit(user).subscribe(data => {
          this.employeeRegistered = false;
          this.employeeEdited = true;
          this.editing = false;
          this.ngOnInit();
        });
      } else {
        this.employeeService.registration(user).subscribe(data => {
          this.employeeRegistered = true;
          this.employeeEdited = false;
          this.editing = false;
          this.ngOnInit();
        });
      }
    }
  }


  edit(employee: UserModel, form: any) {
    form.resetForm();
    this.editing = true;
    this.employeeRegistered = false;
    this.employeeEdited = false;
    this.currentEmployee = employee;

    form.form.patchValue(employee);
    form.form.patchValue({password: ''});
  }

  create(form: any) {
    this.editing = false;
    this.employeeRegistered = false;
    this.employeeEdited = false;
    this.currentEmployee = null;
    form.resetForm();
  }

}
