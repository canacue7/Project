import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { GlobalService } from 'src/app/Shared/Services/global.service';
import { User } from 'src/app/user/Models/User';
import { UserService } from 'src/app/user/Services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  //globalService:GlobalService =new GlobalService();
  @Output() login: EventEmitter<User> = new EventEmitter();

  form: FormGroup;
  
  constructor(
    private userService: UserService,
    private fb: FormBuilder,
    private router: Router,
    private globalService: GlobalService
  ) { }


  ngOnInit(): void {
    this.initForm();
    console.log(this.globalService);
    this.globalService.user.jwt="";
    //this.globalService=new GlobalService();
  }

  initForm() {
    this.form = this.fb.group({
      userName: new FormControl(),
      password: new FormControl()
    });
  }

  signIn() {
    const userName = this.form.controls['userName'].value;
    const password = this.form.controls['password'].value;
    if (userName && password) {
      const user: User = new User();
      //const user: User;
      user.userName = userName;
      user.password = password;

      console.log(user);
      this.userService.login(user).subscribe( resp => {
        if (resp.done) {

          this.login.emit(resp.dato);
          this.globalService.user = resp.dato;
          this.router.navigate(["/listar"]);

        }
      }, error => {
        alert(error.error.messa);
      });
    } else {
      alert('Escriba usuario y contraseña');
    }

  }

  // openSnackBar(message: string, action: string) {
  //   this._snackBar.open(message, action, {
  //     duration: 2000,
  //   });
  // }


}
