import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-main-menu',
  templateUrl: './main-menu.component.html',
  styleUrls: ['./main-menu.component.scss']
})
export class MainMenuComponent implements OnInit{


  sidebarVisible1: boolean = false;
  items: MenuItem[] | undefined;

  constructor(private translate: TranslateService) {
   
  }


  async ngOnInit(): Promise<void> {
    await this.translate.get('Name').toPromise().then()
      this.items = [
        {
          label:this.translate.instant("Initial.singular",{count:2}) ,
          icon: 'pi pi-fw pi-file',
          routerLink:"/mainPage",
          command: this.onClickMenu.bind(this) 
        },
        {
          label: this.translate.instant('CommonData.plural'),
          icon: 'pi pi-fw pi-file',
          items: [
              {
                  label: this.translate.instant('Product.plural'),
                  icon: 'pi pi-building',
                  routerLink:"/productList",
                  command: this.onClickMenu.bind(this) 
              },
              {
                label: this.translate.instant('Customer.plural'),
                icon: 'pi pi-building',
                routerLink:"/customerList",
                command: this.onClickMenu.bind(this)
              },
          ]
        },
        {
          label: this.translate.instant('Sale.plural'),
          icon: 'pi pi-fw pi-file',
          items: [
              {
                  label: this.translate.instant('SaleQuote.plural'),
                  icon: 'pi pi-building',
                  routerLink:"/salesQuoteList",
                  command:this.onClickMenu.bind(this),
              },
          ]
        }, 
        {
          label:this.translate.instant('Logout.singular'),
          icon: 'pi pi-fw pi-sign-out',
          routerLink:"/login",
          command: this.onClickMenu.bind(this) 
        }, 
        {
          label:'teste Layout',
          icon: 'pi pi-fw pi-sign-out',
          routerLink:"/testeLayout",
          command: this.onClickMenu.bind(this) 
        }, 
    ];

  }

  onClickMenu(event:any): void | undefined{
    this.sidebarVisible1=false
    return event
  }

  translationMsg(key: string) {
    this.translate.get(key).subscribe((data) => {
      return data;
    });
  }

}
