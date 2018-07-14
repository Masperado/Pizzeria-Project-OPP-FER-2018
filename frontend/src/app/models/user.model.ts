import {Optional} from '@angular/core';

export class UserModel {

  constructor(public username: string, public password: string,
              public address: string, public email: string,
              public firstName: string, public lastName: string,
              public gender: string, public phoneNumber: string,
              public role: string, public id: string) {
  }


}
