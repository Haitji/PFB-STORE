import { Component } from '@angular/core';
import { RegisterService } from '../service/register.service';
import { User } from './entity/user.model';
import { GlobalClassService } from 'src/app/global-service/global-class.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  name:string='';
  lastName:string='';
  phoneNumber:string='';
  username:string='';
  email:string='';
  password:string='';
  passwordConfirm:string='';

  constructor(private registerService:RegisterService){}

  ngOnInit():void {
  }

  Register(){
    if(this.comprobarCampos()){
      if(this.comprobarContraseña()){
        this.registerService.registrar(new User(this.name,this.username,this.lastName,this.phoneNumber,this.email,this.password)).subscribe(
          {
            next:(userRequest)=>{GlobalClassService.setUsuarioNick(userRequest.nick)},
            error:(error)=>{this.handleError(error);alert('El usuario introducido ya existe')}
          }
        )
      }
    }
  }

  private comprobarContraseña(){
    if(this.password === this.passwordConfirm){     
      return true;
    }else{
      alert('La contraseña de confirmación no coincide');
      return false;
    }
  }

  private comprobarCampos(){
    if(this.name.length===0||this.lastName.length===0||this.phoneNumber.length===0||this.username.length===0||this.email.length===0||this.password.length===0||this.passwordConfirm.length===0){
      alert('No puede haber campos vacios');
      return false;
    }else{
      if(this.phoneNumber.length < 9){
        alert('El numero de telefono debe tener 9 cifras');
        return false;
      }else{
        return true;
      }    
    }
  }
  handleError(error: any):void {
    console.log(error)
  }

  restrictNonNumeric(event: KeyboardEvent) {
    const allowedKeys = [8, 9, 13, 27, 46];
    const allowedChars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']; 

    if (allowedKeys.includes(event.keyCode)) {
      return; 
    }
    if (!allowedChars.includes(event.key)) {
      event.preventDefault(); 
    }
  }
}
