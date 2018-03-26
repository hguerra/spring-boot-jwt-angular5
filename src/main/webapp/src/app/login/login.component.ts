import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material';
import {AuthService} from "./auth.service";
import {TokenStorage} from "./token-storage";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  constructor(private router: Router, public dialog: MatDialog, private authService: AuthService, private token: TokenStorage) {
  }

  ngOnInit() {
  }

  login(): void {
    console.log(`login with ${this.username} and ${this.password}..`);
    this.authService.attemptAuth(this.username, this.password).subscribe(data => {
      console.log('result of auth: ', data);

      this.token.saveToken(data.token);

      console.log('token after save: ', this.token.getToken());

      this.router.navigate(['user']);
    })
  }
}
