import { Component } from '@angular/core';
import { LoginService } from '../service/login.service';
import { GlobalClassService } from 'src/app/global-service/global-class.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  username: string='';
  password: string='';
  constructor(private loginService: LoginService,private router:Router){}

  ngOnInit():void {

  }
  
  login() {
    this.loginService.login(this.username, this.password).subscribe(
      (status: number) => {
        if(status === 200){
          GlobalClassService.setUsuarioNick(this.username);
          console.log(GlobalClassService.usuarioNick);
          this.router.navigate(['']);
        } 
      },
      (error) => {
        if (error.status === 404) {
          console.log('Usuario no existe');   
          alert('Usuario no existe');
        }
        if (error.status === 401) {
          console.log('Contraseña incorrecta');
          alert('Contraseña incorrecta');
        }
      }
    );
  }

}
