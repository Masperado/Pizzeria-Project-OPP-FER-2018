import {Component, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {UserService} from '../../../services/user.service';
import {LoyaltyModel} from '../../../models/loyalty.model';

@Component({
  selector: 'app-admin-loyalty',
  templateUrl: './admin-loyalty.component.html',
  styleUrls: ['./admin-loyalty.component.scss']
})
export class AdminLoyaltyComponent implements OnInit {

  currentNumber: number;
  loyaltyProgram: LoyaltyModel;
  changeSuccess = false;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getLoyalty().subscribe((data) => {
      this.currentNumber = data.totalPizzasToGetFreeOne;
      this.loyaltyProgram = data;
    });
  }

  onSubmit(form: NgForm) {

    let newNumber = form.value.number;
    this.loyaltyProgram.totalPizzasToGetFreeOne = newNumber;
    this.userService.changeLoyalty(this.loyaltyProgram).subscribe((data =>{
      this.changeSuccess = true;
      form.resetForm();
      this.ngOnInit();
    }));
  }

}
