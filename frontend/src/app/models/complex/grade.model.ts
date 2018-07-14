export class GradeModel {
  constructor(public grades: Grade[], public orderId: number, public token: string) {
  }
}

export class Grade {
  constructor(public grade: number, public pizzaId: number) {
  }
}
