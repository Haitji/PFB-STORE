import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule,HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TooltipModule } from 'ngx-bootstrap/tooltip';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { CategoryListComponent } from './entities/category/category-list/category-list.component';
import { CategoryFormComponent } from './entities/category/category-form/category-form.component';
import { ItemListComponent } from './entities/item/item-list/item-list.component';
import { ItemFormComponent } from './entities/item/item-form/item-form.component';
import { HttpRequestInterceptor } from './config/interceptors/http-request-interceptor.interceptor';
import { ModalComponent } from './components/modal/modal.component';
import { LoginComponent } from './login-group/login/login.component';
import { RegisterComponent } from './login-group/register/register.component';
import { ItemFavoritesComponent } from './entities/item/item-favorites/item-favorites.component';
import { LogoutComponent } from './login-group/logout/logout.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    CategoryListComponent,
    CategoryFormComponent,
    ItemListComponent,
    ItemFormComponent,
    ModalComponent,
    LoginComponent,
    RegisterComponent,
    ItemFavoritesComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AutoCompleteModule,
    BrowserAnimationsModule,
    TooltipModule.forRoot()
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpRequestInterceptor,
    multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
