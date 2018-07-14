import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPizzaComponent } from './admin-pizza.component';

describe('AdminPizzaComponent', () => {
  let component: AdminPizzaComponent;
  let fixture: ComponentFixture<AdminPizzaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminPizzaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPizzaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
