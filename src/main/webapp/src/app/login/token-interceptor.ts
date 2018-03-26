import {Injectable} from '@angular/core';
import {
  HttpInterceptor, HttpRequest, HttpHandler, HttpUserEvent, HttpErrorResponse
} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Router} from '@angular/router';
import 'rxjs/add/operator/do';
import {TokenStorage} from "./token-storage";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpUserEvent<any>> {
    console.log('>>>>> interceptor >>>>>');

    let authReq = req;
    const token: string = this.token.getToken();

    console.log('token: ', token);
    if (token != null) {
      const header = {headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token)};
      authReq = req.clone(header);

      console.log('header: ', header);
      console.log('new request: ', authReq);
    }

    console.log("Sending request with new header now ...");

    return next.handle(authReq).do(
      (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            this.router.navigate(['user']);
          }
        }
      }
    );
  }
}
