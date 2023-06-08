import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../model/order.model';
import { Observable } from 'rxjs';
import { CarritoItem } from '../../carritoCompra/model/carritoItem.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  
  
  constructor(private http: HttpClient) { }

  public getOrderByUser(userName: string) :Observable<Order[]>{
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userName+"/orders"
    return this.http.get<Order[]>(urlEndpoint);
  }

  public getItemsByOrder(userName: string, orderId: string) :Observable<CarritoItem[]>{
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userName+"/orders/"+orderId;
    return this.http.get<CarritoItem[]>(urlEndpoint);
  }
  public cancelOrder(orderId: number) {
    let urlEndpoint: string = "http://localhost:8080/store/users/orders/"+orderId;
    return this.http.delete(urlEndpoint);
  }
  public removeItemFromOrder(orderId: string, itemId: number) {
    let urlEndpoint: string = "http://localhost:8080/store/users/orders/"+orderId+"/shoppingCart/"+itemId;
    return this.http.put(urlEndpoint,null);
  }
}
