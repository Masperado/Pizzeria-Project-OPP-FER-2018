import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs/Observable';
import {PizzaModel} from '../models/pizza.model';
import {AuthHttp} from 'angular2-jwt';
import {Response} from '@angular/http';
import 'rxjs/add/operator/map';
import {Message} from '@angular/compiler/src/i18n/i18n_ast';
import {IngredientsModel} from '../models/ingredients.model';
import {UserService} from './user.service';


@Injectable()
export class PizzaService {

  private apiBaseUrl = environment.apiUrl;


  constructor(private http: HttpClient, private userService: UserService) {

  }

  getOfferedPizzas(): Observable<PizzaModel[]> {
    return this.http.get<PizzaModel[]>(this.apiBaseUrl + '/offer/pizza');
  }

  getPizzas(): Observable<PizzaModel[]> {
    return this.http.post<PizzaModel[]>(this.apiBaseUrl + '/admin/pizzas', this.userService.token);
  }

  getPizza(id: number): Observable<PizzaModel> {
    return this.http.get<PizzaModel>(this.apiBaseUrl + '/pizza/' + id);
  }

  getIngredients(): Observable<IngredientsModel[]> {
    return this.http.get<IngredientsModel[]>(this.apiBaseUrl + '/ingredient');
  }

  addPizza(pizza: PizzaModel): Observable<PizzaModel> {
    pizza.token = this.userService.token.token;
    return this.http.post<PizzaModel>(this.apiBaseUrl + '/admin/pizza', pizza);
  }

  editPizza(pizza: PizzaModel): Observable<PizzaModel> {
    pizza.token = this.userService.token.token;
    console.log(pizza);
    return this.http.post<PizzaModel>(this.apiBaseUrl + '/admin/pizza/edit/' + pizza.id, pizza);
  }

  putInOffer(id: number): Observable<any> {
    return this.http.post(this.apiBaseUrl + '/admin/pizza/addToOffers/' + id, this.userService.token);
  }

  outOfOffer(id: number): Observable<any> {
    return this.http.post(this.apiBaseUrl + '/admin/pizza/delete/' + id, this.userService.token);
  }


}
