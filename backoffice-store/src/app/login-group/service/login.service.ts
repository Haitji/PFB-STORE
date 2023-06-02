import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public login(user: string,pass: string){
    let urlEndpoint: string = "http://localhost:8080/store/users/login";
    
    const body = {
      nick: user,
      password: pass
    };

    return this.http.post(urlEndpoint,body,{ observe: 'response' }).pipe(
      map((response: HttpResponse<any>) => {
        return response.status;
      }),
    );
  }
}
