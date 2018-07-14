export class OrderModel {
  constructor(public pizzas: PizzaQuantity[], public token: string, public orderDate: Date, public orderId: number, public hasGrade: boolean, public orderStatus: string, public userName:string, public employeeName: string, public orderOK: boolean) {
  }
}

export class PizzaQuantity {
  constructor(public id: number, public quantity: number, public name: string, public price:number, public grade:number) {
  }
}


