import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserService} from './user.service';
import {environment} from '../../environments/environment';
import {MessageModel} from '../models/message.model';
import {GradeModel} from '../models/complex/grade.model';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class GradeService {

  private apiBaseUrl = environment.apiUrl;


  constructor(private http: HttpClient, private userService: UserService) {
  }

  gradeOrder(grade: GradeModel) : Observable<MessageModel> {
    return this.http.post<MessageModel>(this.apiBaseUrl + '/user/gradePizza', grade);
  }

}
