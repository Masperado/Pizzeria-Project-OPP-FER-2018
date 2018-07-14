import {IngredientsModel} from './ingredients.model';

export class PizzaModel {
  constructor(public id: number, public name: string, public price: number, public description: string, public pictureUrl: string, public pizzaStatus: string, public file: string, public averageGrade: number, public ingredients: IngredientsModel[], public token: string) {
  }
}
