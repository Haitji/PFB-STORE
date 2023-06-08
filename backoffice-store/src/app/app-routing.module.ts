import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CategoryListComponent } from './entities/category/category-list/category-list.component';
import { ItemListComponent } from './entities/item/item-list/item-list.component';
import { ItemFormComponent } from './entities/item/item-form/item-form.component';
import { LoginComponent } from './login-group/login/login.component';
import { RegisterComponent } from './login-group/register/register.component';
import { ItemFavoritesComponent } from './entities/item/item-favorites/item-favorites.component';
import { LogoutComponent } from './login-group/logout/logout.component';
import { CarritoComponent } from './entities/carritoCompra/carrito/carrito.component';
import { OrderListComponent } from './entities/order/order-list/order-list.component';
import { OrderItemsComponent } from './entities/order/order-items/order-items.component';



const routes: Routes = [
  { path: '', component: HomeComponent,pathMatch:'full'},
  { path: 'categories', component: CategoryListComponent},
  { path: 'articulos', component: ItemListComponent},
  { path: 'categories/:categoryId/items', component: ItemListComponent},
  { path: 'items/:itemId', component: ItemFormComponent},
  { path: 'items/new', component: ItemFormComponent},
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'favoritesItems', component: ItemFavoritesComponent},
  { path: 'logout', component: LogoutComponent},
  { path: 'carrito', component: CarritoComponent},
  { path: 'orderList', component: OrderListComponent},
  { path: 'orderList/:orderId', component: OrderItemsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
