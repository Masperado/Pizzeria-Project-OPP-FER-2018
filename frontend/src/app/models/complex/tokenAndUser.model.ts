import {Token} from '../token.model';
import {UserModel} from '../user.model';

export class TokenAndUserModel {
  constructor(public token: string, public user: UserModel) {
  }
}
