import {Component, OnInit} from '@angular/core';
import {PizzaService} from '../../../services/pizza.service';
import {PizzaModel} from '../../../models/pizza.model';
import {IngredientsModel} from '../../../models/ingredients.model';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-admin-pizza',
  templateUrl: './admin-pizza.component.html',
  styleUrls: ['./admin-pizza.component.scss']
})
export class AdminPizzaComponent implements OnInit {

  pizzaList: PizzaModel[];
  ingredients: IngredientsModel[];
  editing = false;
  pizzaRegistered = false;
  pizzaEdited = false;
  currentPizzaId = 0;

  constructor(private pizzaService: PizzaService) {
  }

  ngOnInit() {
    this.pizzaService.getPizzas().subscribe(data => {
      this.pizzaList = data;
    });
    this.pizzaService.getIngredients().subscribe(data => {
      this.ingredients = data;
    });
  }

  addPizza(form: NgForm) {
    let ingredients: IngredientsModel[] = this.getIngredients(form.value);
    let description = form.value.description;
    let name = form.value.name;
    let pictureUrl = form.value.pictureUrl;
    let price = form.value.price;


    let pizza: PizzaModel = new PizzaModel(null, name, price, description, pictureUrl, 'IN_OFFER', null, null, ingredients, null);

    if (this.editing) {
      pizza.id = this.currentPizzaId;
      this.pizzaService.editPizza(pizza).subscribe(data => {
        this.pizzaRegistered = false;
        this.pizzaEdited = true;
        this.editing = false;
        form.resetForm();
        this.ngOnInit();
      });
    } else {
      this.pizzaService.addPizza(pizza).subscribe(data => {
        this.pizzaRegistered = true;
        this.pizzaEdited = false;
        this.editing = false;
        form.resetForm();
        this.ngOnInit();
      });
    }
  }

  getIngredients(form: any) {
    let ingredients: IngredientsModel[] = new Array<IngredientsModel>();

    for (let key in form) {
      if (+key > 0 && +key < 1000) {
        let value = +form[key];
        if (value === 1) {
          ingredients.push(new IngredientsModel(+key, this.ingredients[+key - 1].name));
        }
      }
    }

    return ingredients;
  }

  edit(pizza: PizzaModel, form: any) {
    form.resetForm();
    this.editing = true;
    this.pizzaRegistered = false;
    this.pizzaEdited = false;
    this.currentPizzaId = pizza.id;

    form.form.patchValue(pizza);
  }

  create(form: any) {
    this.editing = false;
    this.pizzaRegistered = false;
    this.pizzaEdited = false;
    this.currentPizzaId = 0;
    form.resetForm();
  }

  putInOffer(pizza: PizzaModel) {
    this.pizzaService.putInOffer(pizza.id).subscribe(data => {
      this.ngOnInit();
    });

  }

  outOfOffer(pizza: PizzaModel) {
    this.pizzaService.outOfOffer(pizza.id).subscribe(data => {
      this.ngOnInit();
    });
  }

}
