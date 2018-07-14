import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserService} from './user.service';
import {Token} from '../models/token.model';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs/Observable';
import {OrderModel, PizzaQuantity} from '../models/order.model';
import {ShortOrderModel, ShortPizzaQuantity} from '../models/shortOrder.model';
import {PizzaService} from './pizza.service';
import {Subject} from 'rxjs/Subject';
import {MessageModel} from '../models/message.model';

@Injectable()
export class OrderService {

  private apiBaseUrl = environment.apiUrl;
  private _currentOrder: ShortOrderModel;
  private _longCurrentOrder: OrderModel;
  change: Subject<any> = new Subject<any>();

  constructor(private http: HttpClient, private userService: UserService, private pizzaService: PizzaService) {
  }

  init() {
    if (localStorage._currentOrder) {
      this.currentOrder = JSON.parse(localStorage._currentOrder);
      this._currentOrder.token = this.userService.token.token;
    }

    if (!this.currentOrder) {
      let pizzas: ShortPizzaQuantity[] = new Array<ShortPizzaQuantity>();
      this.currentOrder = new ShortOrderModel(pizzas, this.userService.token.token);
      localStorage.setItem('_currentOrder', JSON.stringify(this.currentOrder));
    }
    this.generateLongOrder();
  }


  get longCurrentOrder(): OrderModel {
    return this._longCurrentOrder;
  }

  set longCurrentOrder(value: OrderModel) {
    this._longCurrentOrder = value;
  }

  get currentOrder(): ShortOrderModel {
    return this._currentOrder;
  }

  set currentOrder(value: ShortOrderModel) {
    this._currentOrder = value;
  }

  getPastOrders(): Observable<OrderModel[]> {
    const token: Token = this.userService.token;
    return this.http.post<OrderModel[]>(this.apiBaseUrl + '/user/pastOrders', token);
  }

  getCurrentOrders(): Observable<OrderModel[]> {
    const token: Token = this.userService.token;
    return this.http.post<OrderModel[]>(this.apiBaseUrl + '/user/currentOrders', token);
  }

  getOrder(id: number): Observable<OrderModel> {
    const token: Token = this.userService.token;
    return this.http.post<OrderModel>(this.apiBaseUrl + '/user/orders/' + id, token);
  }

  addPizza(pizza: ShortPizzaQuantity) {
    let exist = false;
    for (let currentPizza of this.currentOrder.pizzas) {
      if (currentPizza.id === pizza.id) {
        currentPizza.quantity += pizza.quantity;
        exist = true;
        break;
      }
    }
    if (!exist) {
      this.currentOrder.pizzas.push(pizza);
    }
    let survivingPizzas: ShortPizzaQuantity[] = new Array<ShortPizzaQuantity>();

    for (let currentPizza of this.currentOrder.pizzas) {
      if (currentPizza.quantity > 0) {
        survivingPizzas.push(currentPizza);
      }
    }

    this.currentOrder.pizzas = survivingPizzas;

    localStorage.setItem('_currentOrder', JSON.stringify(this.currentOrder));
    this.generateLongOrder();
  }

  removePizza(id: number, all: boolean) {

    for (let currentPizza of this.currentOrder.pizzas) {
      if (currentPizza.id === id) {
        if (all) {
          currentPizza.quantity = 0;
        } else {
          currentPizza.quantity--;
        }
        break;
      }
    }

    let survivingPizzas: ShortPizzaQuantity[] = new Array<ShortPizzaQuantity>();

    for (let currentPizza of this.currentOrder.pizzas) {
      if (currentPizza.quantity > 0) {
        survivingPizzas.push(currentPizza);
      }
    }

    this.currentOrder.pizzas = survivingPizzas;


    localStorage.setItem('_currentOrder', JSON.stringify(this.currentOrder));
    this.generateLongOrder();
  }

  generateLongOrder() {
    if (!this.currentOrder.token) {
      this.currentOrder.token = this.userService.token.token;
    }

    this.longCurrentOrder = new OrderModel(new Array<PizzaQuantity>(), null, null, null, null, null, null, null, null);
    for (let pizza of this._currentOrder.pizzas) {
      this.pizzaService.getPizza(pizza.id).subscribe((data) => {
        this.longCurrentOrder.pizzas.push(new PizzaQuantity(pizza.id, pizza.quantity, data.name, data.price, 0));
        this.longCurrentOrder.pizzas.sort((a, b) => (a.name.localeCompare(b.name)));
        this.change.next();
      });
    }
    this.change.next();

  }

  order(): Observable<any> {
    return this.http.post<any>(this.apiBaseUrl + '/user/order', this.currentOrder);
  }

  clear() {
    let pizzas: ShortPizzaQuantity[] = new Array<ShortPizzaQuantity>();
    this.currentOrder = new ShortOrderModel(pizzas, this.userService.token.token);
    localStorage.setItem('_currentOrder', JSON.stringify(this.currentOrder));
    this.generateLongOrder();
  }


}
