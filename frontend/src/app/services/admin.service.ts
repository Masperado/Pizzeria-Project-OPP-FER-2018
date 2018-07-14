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


@Injectable()
export class AdminService {


  private apiBaseUrl = environment.apiUrl;


  constructor(private http: HttpClient) {
  }


  private _userLoggedIn = false;

  changeHappened: Subject<any> = new Subject<any>();

  private _loggedUser: UserModel;

  private _token: Token;

  init() {

    this._token = new Token(localStorage._token);

    if (this._token.token) {
      this.http.post<UserModel>(this.apiBaseUrl + '/admin/pizza', this._token).subscribe(data => {
        // this.reload(data);
      });
    }
  }


  get token(): Token {
    return this._token;
  }


  set token(value: Token) {
    this._token = value;
  }

  get userLoggedIn(): boolean {
    return this._userLoggedIn;
  }


  set userLoggedIn(value: boolean) {
    this._userLoggedIn = value;
  }

  get loggedUser(): UserModel {
    return this._loggedUser;
  }

  set loggedUser(value: UserModel) {
    this._loggedUser = value;
  }

  addPizza(user: UserModel): Observable<UserModel> {
    let body: TokenAndUserModel = new TokenAndUserModel(this.token.token, user);

    return this.http.post<UserModel>(this.apiBaseUrl + '/admin/pizza', body);
  }

  addUser(user: UserModel): Observable<UserModel> {
    let body: TokenAndUserModel = new TokenAndUserModel(this.token.token, user);

    return this.http.post<UserModel>(this.apiBaseUrl + '/admin/user', body);
  }

  addEmployee(user: UserModel): Observable<UserModel> {
    let body: TokenAndUserModel = new TokenAndUserModel(this.token.token, user);

    return this.http.post<UserModel>(this.apiBaseUrl + '/admin/employee', body);
  }

}
