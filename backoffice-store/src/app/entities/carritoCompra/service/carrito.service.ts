import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarritoItem } from '../model/carritoItem.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  

  constructor(private http: HttpClient) { }

  public getCarritoDelUsuario(userName: string):Observable<CarritoItem[]> {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userName+"/shoppingcart"
    return this.http.get<CarritoItem[]>(urlEndpoint);
  }

  public removeItemOnShoppingCart(userName:string,id: number) {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userName+"/shoppingcart/"+id;
    return this.http.delete(urlEndpoint);
  }

  public addItemOnShoppingCart(userName: string, id: number, units: number) {
    const body = {
      id: id,
      units: units
    };
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userName+"/shoppingcart";
    return this.http.post(urlEndpoint, body);
  }

}
