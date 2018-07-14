import {Component, OnInit} from '@angular/core';
import {LoyaltyModel} from '../../models/loyalty.model';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-loyalty',
  templateUrl: './loyalty.component.html',
  styleUrls: ['./loyalty.component.scss']
})
export class LoyaltyComponent implements OnInit {
  loyalty: LoyaltyModel;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.userService.getLoyalty().subscribe((data) => {
      this.loyalty = data;
    });

  }

}
