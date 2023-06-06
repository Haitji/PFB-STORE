import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemService {


  constructor(private http: HttpClient) { }

  getAllFavoriteItem(userName:string) : Observable<Item[]>{
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userName+"/favoritos";
    return this.http.get<Item[]>(urlEndpoint);
  }

  public removeItemFromFavorite(userName: string, id: number) {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userName+"/favoritos/remove/"+id;
    return this.http.delete(urlEndpoint);
  }
  public addItemFromFavorite(userName: string, id: number) {
    let urlEndpoint: string = "http://localhost:8080/store/users/"+userName+"/favoritos/"+id;
    return this.http.post(urlEndpoint,null);
  }

  public getListFavoriteId(usuarioNick: string): Observable<number[]>{
    let urlEndpoint: string = "http://localhost:8080/store/users/"+usuarioNick+"/favoritos-id";
    return this.http.get<number[]>(urlEndpoint);
  }

  public getAllItems(page: number,size: number, sort: string,filter?: string|undefined): Observable<Item[]> {
    let urlEndpoint: string = "http://localhost:8080/store/items?page="+page+"&size="+size+"&sort="+sort;
    if(filter){
      urlEndpoint = urlEndpoint + "&filter=" + filter;
    }
    return this.http.get<Item[]>(urlEndpoint)
  }
  public deleteItem(itemIdToDelete: number):Observable<any> {
    let urlEndpoint: string = "http://localhost:8080/store/items/" + itemIdToDelete;
    return this.http.delete<any>(urlEndpoint);
  }
  public getItemById(itemId: number): Observable<Item> {
    let urlEndpoint: string = "http://localhost:8080/store/items/" + itemId;
    return this.http.get<Item>(urlEndpoint);
  }

  public insert(item: Item): Observable<Item> {
    let urlEndpoint: string = "http://localhost:8080/store/items";
    return this.http.post<Item>(urlEndpoint,item);
  }

  public update(item: Item): Observable<Item> {
    let urlEndpoint: string = "http://localhost:8080/store/items";
    return this.http.patch<Item>(urlEndpoint,item);
  }
}
