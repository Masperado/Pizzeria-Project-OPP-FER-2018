export class ShortOrderModel {
  constructor(public pizzas: ShortPizzaQuantity[], public token: string) {
  }
}

export class ShortPizzaQuantity {
  constructor(public id: number, public quantity: number) {
  }
}




