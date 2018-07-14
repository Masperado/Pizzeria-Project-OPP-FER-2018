import {Injectable} from '@angular/core';
import {UserModel} from '../models/user.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment';
import {ShortUserModel} from '../models/shortuser.model';
import {Token} from '../models/token.model';
import {Subject} from 'rxjs/Subject';
import {MessageModel} from '../models/message.model';
import {LoyaltyModel} from '../models/loyalty.model';
import {TokenAndUserModel} from '../models/complex/tokenAndUser.model';
import {OrderModel} from '../models/order.model';
import {UserService} from './user.service';


@Injectable()
export class EmployeeService {


  private apiBaseUrl = environment.apiUrl;


  constructor(private http: HttpClient, private userService: UserService) {
  }


  getPastEmployeeOrders(): Observable<OrderModel[]> {
    const token: Token = this.userService.token;
    return this.http.post<OrderModel[]>(this.apiBaseUrl + '/employee/pastOrders', token);
  }

  getCurrentEmployeeOrders(): Observable<OrderModel[]> {
    const token: Token = this.userService.token;
    return this.http.post<OrderModel[]>(this.apiBaseUrl + '/employee/currentOrders', token);
  }

  accept(id: number) {
    this.http.post<MessageModel>(this.apiBaseUrl + '/employee/accept/' + id, this.userService.token).subscribe(data => {
      if (data.message === 'Accepted') {

      }
    });
  }

  delivering(id: number) {
    this.http.post<MessageModel>(this.apiBaseUrl + '/employee/deliver/' + id, this.userService.token).subscribe(data => {
      if (data.message === 'Delivered') {

      }
    });
  }

  paid(id: number) {
    this.http.post<MessageModel>(this.apiBaseUrl + '/employee/paid/' + id, this.userService.token).subscribe(data => {
      if (data.message === 'Paid') {

      }
    });
  }

  notPaid(id: number) {
    this.http.post<MessageModel>(this.apiBaseUrl + '/employee/notPaid/' + id, this.userService.token).subscribe(data => {
      if (data.message === 'Not paid') {

      }
    });
  }

  blockOrdering() {
    return this.http.post<MessageModel>(this.apiBaseUrl + '/employee/block', this.userService.token);
  }

  openOrdering() {
    return this.http.post<MessageModel>(this.apiBaseUrl + '/employee/open', this.userService.token);
  }

  getPizzeriaStatus(): Observable<MessageModel> {
    return this.http.get<MessageModel>(this.apiBaseUrl + '/pizzeriaStatus');
  }

  getEmployees(): Observable<UserModel[]> {
    return this.http.post<UserModel[]>(this.apiBaseUrl + '/admin/employees', this.userService.token);
  }

  registration(user: UserModel): Observable<UserModel> {
    let send: TokenAndUserModel = new TokenAndUserModel(this.userService.token.token, user);
    return this.http.post<UserModel>(this.apiBaseUrl + '/admin/employee', send);
  }

  edit(user: UserModel): Observable<UserModel> {
    let send: TokenAndUserModel = new TokenAndUserModel(this.userService.token.token, user);
    return this.http.post<UserModel>(this.apiBaseUrl + '/admin/employee/' + user.id, send);
  }
}
