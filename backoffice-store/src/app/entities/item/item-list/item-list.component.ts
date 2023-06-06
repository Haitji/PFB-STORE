import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from '../service/item.service';
import { Item } from '../model/item.model';
import { ItemFavorite } from '../model/itemFavorite.model';
import { GlobalClassService } from 'src/app/global-service/global-class.service';
import { forkJoin } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent {

  categoryId?: number;
  title?: string = "";
  items: Item[] = [];
  itemsTransformado: ItemFavorite[] = [];
  listFavoriteId: number[] = [];
  userName: string = '';

  page: number = 0;
  size: number = 25;
  sort: string = 'name,asc';

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  nameFilter?: string;
  priceFilter?: number;

  itemIdToDelete?: number;
  showModal = false;

  constructor(private router: ActivatedRoute, private itemService: ItemService, private globalService: GlobalClassService,private router2:Router) { }

  ngOnInit(): void {
    if (this.router.snapshot.paramMap.get("categoryId")) {
      this.categoryId = + this.router.snapshot.paramMap.get("categoryId")!;
      this.title = "Articulos de la categoria " + this.categoryId;
    } else {
      this.title = "Lista de articulos";
    }

    this.globalService.getUserName().subscribe(value => {
      this.userName = value;
    });
    this.getAllItems();
  }



  public nextPage(): void {
    this.page = this.page + 1;
    this.getAllItems();
  }

  public previousPage(): void {
    this.page = this.page - 1;
    this.getAllItems();
  }

  public searchByFilter(): void {
    this.getAllItems();
  }

  public prepareItemToDelete(itemId: number): void {
    this.itemIdToDelete = itemId;
  }

  public deleteItem(): void {
    if (this.itemIdToDelete) {
      this.itemService.deleteItem(this.itemIdToDelete!).subscribe({
        next: (data) => {
          this.getAllItems();
        },
        error: (err) => { this.handleError(err) }
      })
    }

  }

  private buildFilters(): string | undefined {
    const filters: string[] = [];



    if (this.categoryId) {
      filters.push("category.id:EQUAL:" + this.categoryId);
    }

    if (this.nameFilter) {
      filters.push("name:MATCH:" + this.nameFilter);
    }

    if (this.priceFilter) {
      filters.push("price:LESS_THAN_EQUAL:" + this.priceFilter);
    }

    if (filters.length > 0) {
      let globalFilters: string = "";
      for (let filter of filters) {
        globalFilters = globalFilters + filter + ",";
      }
      globalFilters = globalFilters.substring(0, globalFilters.length - 1);
      return globalFilters;
    } else {
      return undefined;
    }

  }

  getFavoriteItemIds() {
    if (this.userName != "") {
      this.itemService.getListFavoriteId(this.userName).subscribe({
        next: (list) => { this.listFavoriteId = list; },
        error: (error) => { this.handleError(error); }
      })
    }
  }


  private conversionItem(items: Item[]) {
    let transformado: ItemFavorite[] = new Array();
    for (let i = 0; i < items.length; i++) {
      if (this.listFavoriteId.includes(items[i].id!)) {
        transformado.push(new ItemFavorite(items[i].id, items[i].name, items[i].price, true, items[i].categoryId, items[i].categoryName, items[i].description, items[i].image));
      } else {
        transformado.push(new ItemFavorite(items[i].id, items[i].name, items[i].price, false, items[i].categoryId, items[i].categoryName, items[i].description, items[i].image));
      }
    }
    this.itemsTransformado = transformado;
  }


  getAllItems() {
    this.getFavoriteItemIds();
    const filters: string | undefined = this.buildFilters();
    this.itemService.getAllItems(this.page, this.size, this.sort, filters).subscribe({
      next: (data: any) => {
        this.items = data.content;
        this.conversionItem(data.content);
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error: (error) => { this.handleError(error); }
    })

  }

  handleError(error: any): void {
    console.log(error)
  }

  agregarAFavoritos(id: number) {
    this.itemService.addItemFromFavorite(this.userName, id).subscribe({
      next: (responseBody: any) => {
        let buscar: ItemFavorite = this.itemsTransformado.find(item => item.id === id)!;
        if (buscar) {
          buscar.favorite = true;
        }
        console.log(buscar.favorite);
      },
      error: (error) => { this.handleError(error) }
    })
  }

  quitarFavoritos(id: number) {
    this.itemService.removeItemFromFavorite(this.userName, id).subscribe({
      next: (responseBody: any) => {
        let buscar: ItemFavorite = this.itemsTransformado.find(item => item.id === id)!;
        if (buscar) {
          buscar.favorite = false;
        }
        console.log(buscar.favorite);
      },
      error: (error) => { this.handleError(error) }
    });

  }


  openModal() {
    this.showModal = true;
  }

  closeModal() {
    this.showModal = false;
  }

  irALogin(){
    this.router2.navigate(['login']);
    this.closeModal();
  }

  comprobar(){
    if(this.userName===""){
      this.openModal();
    }
  }
}
