import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../register/entity/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  public registrar(user: User){
    let urlEndpoint: string = "http://localhost:8080/store/user";
    

    return this.http.post<User>(urlEndpoint, user);
  }
}
