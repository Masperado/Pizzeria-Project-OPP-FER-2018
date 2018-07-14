import {Injectable} from '@angular/core';
import {UserModel} from '../models/user.model';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment';
import {ShortUserModel} from '../models/shortuser.model';
import {Token} from '../models/token.model';
import {Subject} from 'rxjs/Subject';
import {MessageModel} from '../models/message.model';
import {LoyaltyModel} from '../models/loyalty.model';
import {TokenAndUserModel} from '../models/complex/tokenAndUser.model';


@Injectable()
export class UserService {


  private apiBaseUrl = environment.apiUrl;


  constructor(private http: HttpClient) {
  }

  loginError = false;

  private _userLoggedIn = false;

  changeHappened: Subject<any> = new Subject<any>();

  private _loggedUser: UserModel;

  private _token: Token;

  userLoaded: Subject<any> = new Subject<any>();

  init() {

    this._token = new Token(localStorage._token);

    if (this._token.token) {
      this.http.post<UserModel>(this.apiBaseUrl + '/user/profile', this._token).subscribe(data => {
        this.reload(data);
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

  registration(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(this.apiBaseUrl + '/registration', user);
  }

  login(user: ShortUserModel) {
    this.http.post<Token>(this.apiBaseUrl + '/userlogin', user)
      .subscribe(
        data => {
          this._token = data;
          localStorage.setItem('_token', this._token.token);
          this.http.post<UserModel>(this.apiBaseUrl + '/user/profile', this._token)
            .subscribe(
              data => {
                this.reload(data);
                this.loginError = false;
                this.changeHappened.next();
              });
        },
        (err: HttpErrorResponse) => {
          this.loginError = true;
          this.changeHappened.next();
        });
  }

  getProfile(): UserModel {
    return this.loggedUser;
  }

  getLoyalty(): Observable<LoyaltyModel> {
    return this.http.post<LoyaltyModel>(this.apiBaseUrl + '/user/loyaltyProgram', this.token);
  }

  logout() {
    this.http.post<MessageModel>(this.apiBaseUrl + '/userlogout', this.token).subscribe(data => {
      if (data.message === 'Odlogirani!') {


        this.token = undefined;
        this.loggedUser = undefined;
        this.userLoggedIn = false;
        localStorage.removeItem('_token');
        this.changeHappened.next();
      }
    });
  }

  editProfile(user: UserModel): Observable<UserModel> {
    let body: TokenAndUserModel = new TokenAndUserModel(this.token.token, user);

    return this.http.post<UserModel>(this.apiBaseUrl + '/user/edit', body);
  }

  reload(user: UserModel) {
    this.loggedUser = user;
    this._userLoggedIn = true;
    this.changeHappened.next();
    this.userLoaded.next();
  }

  getUsers(): Observable<UserModel[]> {
    return this.http.post<UserModel[]>(this.apiBaseUrl + '/admin/users', this.token);
  }

  editAdmin(user: UserModel): Observable<UserModel> {
    let send: TokenAndUserModel = new TokenAndUserModel(this.token.token, user);
    return this.http.post<UserModel>(this.apiBaseUrl + '/admin/user/' + user.id, send);
  }

  changeLoyalty(loyalty: LoyaltyModel): Observable<LoyaltyModel> {
    loyalty.token = this.token.token;
    return this.http.post<LoyaltyModel>(this.apiBaseUrl + '/admin/loyaltyChange', loyalty);
  }

}
