import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ItemService } from '../service/item.service';
import { Item } from '../model/item.model';
import { FormsModule } from '@angular/forms';
import { Category } from '../../category/model/category.model';
import { CategoryService } from '../../category/service/category.service';

@Component({
  selector: 'app-item-form',
  templateUrl: './item-form.component.html',
  styleUrls: ['./item-form.component.scss']
})
export class ItemFormComponent {
  mode: "NEW" | "UPDATE" = "NEW";
  itemId?: number;
  item?: Item;
  selectedCategory?: Category;
  categories: Category[]=[];

  constructor(private router: ActivatedRoute, private service: ItemService,private categoryService: CategoryService) { }

  ngOnInit(): void {
    const entryParam = this.router.snapshot.paramMap.get("itemId") ?? "new";
    if (entryParam !== "new") {
      this.itemId = +this.router.snapshot.paramMap.get("itemId")!;
      this.mode = "UPDATE";
      this.getItemById(this.itemId!);
    } else {
      this.mode = "NEW";
      this.initializeItem();
    }
  }
  private initializeItem() {
    this.item = new Item(undefined, "", 0);
  }

  public saveItem(): void {
    if (this.mode === "NEW") {
      this.insertItem();
    }

    if (this.mode === "UPDATE") {
      this.updateItem();
    }
  }
  private updateItem(): void {
    this.service.update(this.item!).subscribe({
      next: (itemUpdate) => {
        console.log("Insertado correctamente");
        console.log(itemUpdate);
      },
      error: (err) => { this.handleError(err) }
    })
  }

  private insertItem(): void {
    this.service.insert(this.item!).subscribe({
      next: (itemInsertado) => {
        console.log("Insertado correctamente");
        console.log(itemInsertado);
      },
      error: (err) => { this.handleError(err) }
    })
  }

  private getItemById(itemId: number) {
    this.service.getItemById(itemId).subscribe({
      next: (itemRequest) => { this.item = itemRequest;
      this.selectedCategory = new Category(itemRequest.categoryId!,itemRequest.categoryName!)},
      error: (err) => { this.handleError(err); }
    })
  }

  public getAllCategories(event?: any): void {
    let categorySearch: string | undefined;
    if(event?.query){
      categorySearch = event.query;
    }
    this.categoryService.getAllCategories(categorySearch).subscribe({
      next: (categoriesFiltered) => {this.categories = categoriesFiltered},
      error: (err) => { this.handleError(err) }
    })
  }

  public categorySelected():void {
    this.item!.categoryId = this.selectedCategory!.id;
    this.item!.categoryName = this.selectedCategory!.name;
  }

  public categoryUnselected():void {
    this.item!.categoryId = undefined;
    this.item!.categoryName = undefined;
  }
  public includeImageInItem(event: any): void {
    const inputFile = event.target as HTMLInputElement;
    const file: File|null = inputFile.files?.item(0) ?? null;

    this.readFileAsString(file!).then(
      (result) => {
        const imageType:string=this.getImageType(result);
        console.log(imageType);
        const imageBase64: string = this.getImageBase64(result);
        console.log(imageBase64);

        this.item!.image = imageBase64;
      },
      (error)=>{
        console.log("No se pudo cargar la imagen");
      }
    )
  }

  private getImageType(imageString: string): string{
    const imageStringParts: string[] = imageString.split(",");
    if(imageStringParts.length == 2){
      return imageStringParts[0];
    }else{
      return "";
    }
  }

  private getImageBase64(imageString: string): string{
    const imageStringParts: string[] = imageString.split(",");
    if(imageStringParts.length == 2){
      return imageStringParts[1];
    }else{
      return "";
    }
  }

  private readFileAsString(file: File){
    return new Promise<string>(function(resolve,reject){
      let reader: FileReader = new FileReader();
      reader.readAsDataURL(file)
      reader.onload = function(){
        resolve(this.result as string)
      }
    })
  }

  handleError(error: any): void {
    console.log(error)
  }
}
