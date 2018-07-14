import {NgModule} from '@angular/core';
import {Routes, RouterModule, PreloadAllModules} from '@angular/router';
import {MenuComponent} from './core/menu/menu.component';
import {GalleryComponent} from './core/gallery/gallery.component';
import {InfoComponent} from './core/info/info.component';
import {ItemComponent} from './core/item/item.component';
import {RegistrationComponent} from './core/registration/registration.component';
import {LoginComponent} from './core/login/login.component';
import {ProfileComponent} from './core/profile/profile.component';
import {LoyaltyComponent} from './core/loyalty/loyalty.component';
import {OrdersComponent} from './core/orders/orders.component';
import {OrderDetailsComponent} from './core/order-details/order-details.component';
import {EmployeeOrdersComponent} from './core/employee/employee-orders/employee-orders.component';
import {ReportComponent} from './core/admin/report/report.component';
import {ControlComponent} from './core/employee/control/control.component';
import {EmployeeOrdersDetailsComponent} from './core/employee/employee-orders-details/employee-orders-details.component';
import {AdminUserComponent} from './core/admin/admin-user/admin-user.component';
import {AdminEmployeeComponent} from './core/admin/admin-employee/admin-employee.component';
import {AdminPizzaComponent} from './core/admin/admin-pizza/admin-pizza.component';
import {AdminLoyaltyComponent} from './core/admin/admin-loyalty/admin-loyalty.component';

const appRoutes: Routes = [
  {path: '', component: MenuComponent},
  {path: 'gallery', component: GalleryComponent},
  {path: 'info', component: InfoComponent},
  {path: 'item/:id', component: ItemComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'login', component: LoginComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'loyalty', component: LoyaltyComponent},
  {path: 'orders', component: OrdersComponent},
  {path: 'orderDetails/:id', component: OrderDetailsComponent},
  {path: 'employeeOrderDetails/:id', component: EmployeeOrdersDetailsComponent},
  {path: 'employee', component: EmployeeOrdersComponent},
  {path: 'admin', component: ReportComponent},
  {path: 'control', component: ControlComponent},
  {path: 'adminUser', component: AdminUserComponent},
  {path: 'adminEmployee', component: AdminEmployeeComponent},
  {path: 'adminPizza', component: AdminPizzaComponent},
  {path: 'adminLoyalty', component: AdminLoyaltyComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
