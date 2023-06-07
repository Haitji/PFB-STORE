import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ItemService } from '../service/item.service';
import { GlobalClassService } from 'src/app/global-service/global-class.service';
import { Item } from '../model/item.model';

@Component({
  selector: 'app-item-favorites',
  templateUrl: './item-favorites.component.html',
  styleUrls: ['./item-favorites.component.scss']
})
export class ItemFavoritesComponent {

  items: Item[] = [];
  userName:string='';

  constructor(private router: ActivatedRoute, private itemService: ItemService, private globalService: GlobalClassService ){}

  ngOnInit(): void {
    this.globalService.getUserName().subscribe(value => {
      this.userName = value;
    });
    this.getAllFavoriteItem();
  }

  getAllFavoriteItem(){
    this.itemService.getAllFavoriteItem(this.userName).subscribe({
      next:(favoritos)=>{this.items=favoritos},
      error:(err)=>{this.handleError(err)}
    })
  }
  handleError(error: any): void {
    console.log(error)
  }
  quitarDeFavorito(id:number){
    this.itemService.removeItemFromFavorite(this.userName,id).subscribe({
      next:(hola:any)=>{this.quitar(id)},
      error:(err)=>{this.handleError(err)}
    })
  }
  quitar(id:number){
    const index = this.items.findIndex(item => item.id === id);
    if(index != -1){
      this.items.splice(index,1);
    }
  }
}
