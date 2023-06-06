import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemFavoritesComponent } from './item-favorites.component';

describe('ItemFavoritesComponent', () => {
  let component: ItemFavoritesComponent;
  let fixture: ComponentFixture<ItemFavoritesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemFavoritesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemFavoritesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
