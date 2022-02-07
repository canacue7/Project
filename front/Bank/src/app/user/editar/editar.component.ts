import { Component, Input, OnInit } from '@angular/core';
import { User } from '../Models/User';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.css']
})
export class EditarComponent implements OnInit {

  @Input() user: User={
    userName:"",
    password:"",
    jwt:"",
    firstName:"",
    lastName:""

  }

  constructor(private userService: UserService, ) { }

  ngOnInit(): void {
  }

}
