import {HeaderComponent} from './header/header.component';
import {MenuComponent} from './menu/menu.component';
import {InfoComponent} from './info/info.component';
import {GalleryComponent} from './gallery/gallery.component';
import {AppRoutingModule} from '../app-routing.module';
import {NgModule} from '@angular/core';
import { ItemComponent } from './item/item.component';
import {CommonModule} from '@angular/common';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import {FormsModule} from '@angular/forms';
import { ProfileComponent } from './profile/profile.component';
import { LoyaltyComponent } from './loyalty/loyalty.component';
import { OrdersComponent } from './orders/orders.component';
import { CartComponent } from './cart/cart.component';
import { ControlComponent } from './employee/control/control.component';
import { ReportComponent } from './admin/report/report.component';
import { EmployeeOrdersComponent } from './employee/employee-orders/employee-orders.component';
import { EmployeeOrdersDetailsComponent } from './employee/employee-orders-details/employee-orders-details.component';
import { AdminPizzaComponent } from './admin/admin-pizza/admin-pizza.component';
import { AdminEmployeeComponent } from './admin/admin-employee/admin-employee.component';
import { AdminUserComponent } from './admin/admin-user/admin-user.component';
import { AdminLoyaltyComponent } from './admin/admin-loyalty/admin-loyalty.component';
import {OfferPipe} from '../services/offer.pipe';

@NgModule({
  declarations: [
    HeaderComponent,
    MenuComponent,
    InfoComponent,
    GalleryComponent,
    ItemComponent,
    RegistrationComponent,
    LoginComponent,
    ProfileComponent,
    LoyaltyComponent,
    OrdersComponent,
    CartComponent,
    ControlComponent,
    ReportComponent,
    EmployeeOrdersComponent,
    EmployeeOrdersDetailsComponent,
    AdminPizzaComponent,
    AdminEmployeeComponent,
    AdminUserComponent,
    AdminLoyaltyComponent,
    OfferPipe
  ],
  imports: [
    AppRoutingModule,
    CommonModule,
    FormsModule
  ],
  exports: [
    AppRoutingModule,
    HeaderComponent
  ],
  providers: [

  ]
})
export class CoreModule { }
