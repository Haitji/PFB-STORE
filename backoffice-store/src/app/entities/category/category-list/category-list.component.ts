import { Component, OnInit } from '@angular/core';
import { Category } from '../model/category.model';
import { CategoryService } from '../service/category.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss']
})
export class CategoryListComponent implements OnInit {

  categories: Category[]=[];



  constructor(private categoriesService: CategoryService){}

  ngOnInit(): void{
    this.getCategories();
  }
  getCategories():void {
    this.categoriesService.getAllCategories().subscribe({
      next:(categoriesRequest)=>{this.categories = categoriesRequest;},
      error:(error)=>{this.handleError(error);}
    })
  }
  handleError(error: any):void {
    console.log(error)
  }
}
