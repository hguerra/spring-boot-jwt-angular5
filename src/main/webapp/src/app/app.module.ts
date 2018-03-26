import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';

import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import {HttpClientModule, HttpClient} from '@angular/common/http';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import {AppComponent} from './app.component';
import {UserComponent} from './user/user.component';
import {LoginComponent} from './login/login.component';
import {CustomMaterialModule} from "./core/custom.material.module";
import {AppRoutingModule} from "./core/app.routing.module";
import {UserService} from "./user/user.service";
import {AuthService} from "./login/auth.service";
import {TokenStorage} from "./login/token-storage";
import {TokenInterceptor} from "./login/token-interceptor";

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    CustomMaterialModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [
    HttpClient,
    TokenStorage,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    AuthService,
    UserService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
