import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MatTableDataSource} from '@angular/material';
import {UserService} from "./user.service";
import {User} from "./user";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  displayedColumns = ['id', 'username', 'salary', 'age'];

  dataSource = new MatTableDataSource();

  constructor(private router: Router, private userService: UserService) {

  }

  ngOnInit() {
    this.userService.getUsers().subscribe(value => this.dataSource.data = value);
    // const users: User[] = [
    //   {id: 1, username: 'heitor', password: '1234', salary: 5000, age: 23}
    // ];
    // this.dataSource.data = users;
  }
}
