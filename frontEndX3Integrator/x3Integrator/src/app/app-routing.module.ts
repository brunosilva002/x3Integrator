import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './common/login/login.component';
import { MainPageComponent } from './common/main-page/main-page.component';
import { ListProductComponent } from './pages/product/list-product/list-product.component';
import { EditProductComponent } from './pages/product/edit-product/edit-product.component';
import { ListCustomerComponent } from './pages/customer/list-customer/list-customer.component';
import { EditCustomerComponent } from './pages/customer/edit-customer/edit-customer.component';
import { EditSalesQuoteComponent } from './pages/sales-quote/edit-sales-quote/edit-sales-quote.component';
import { ListSalesQuoteComponent } from './pages/sales-quote/list-sales-quote/list-sales-quote.component';

const routes: Routes = [

  {path: 'login'              , component:LoginComponent},
  {path: 'mainPage'           , component:MainPageComponent},

  {path: 'productList'        , component:ListProductComponent},
  {path: 'productEdit'        , component:EditProductComponent},
 
  {path: 'customerList'      , component:ListCustomerComponent},
  {path: 'customerEdit'      , component:EditCustomerComponent},

  {path: 'salesQuoteList'      , component:ListSalesQuoteComponent},
  {path: 'salesQuoteEdit'      , component:EditSalesQuoteComponent},

  {path: '**'                 , redirectTo:"/login"},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
