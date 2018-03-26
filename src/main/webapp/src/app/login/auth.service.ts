import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';

import {environment} from '../../environments/environment';

@Injectable()
export class AuthService {

  constructor(private http: HttpClient) {
  }

  attemptAuth(username: string, password: string): Observable<any> {
    const credentials = {username: username, password: password};
    console.log('attempAuth ::');
    return this.http.post(environment.api + '/token/generate-token', credentials);
  }
}
