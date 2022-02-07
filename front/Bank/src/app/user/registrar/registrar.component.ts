import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../Services/user.service';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.css']
})
export class RegistrarComponent implements OnInit {

  profileForm = new FormGroup({
    userName: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl('')
  });
  constructor(private userService: UserService,
    private router:Router) { }

  ngOnInit(): void {
  }


  Registrar(){
    const user = this.profileForm.value;
    console.log(user);
    this.userService.save(user).subscribe(data=>{
      alert(data.messa);
      this.router.navigate(["/"]);
    })
  }
}