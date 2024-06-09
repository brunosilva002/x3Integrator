import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { PrimeNGConfig } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'x3Integrator';

  constructor(
    private primengConfig: PrimeNGConfig, 
    private translateService: TranslateService
  ){
  }

  async ngOnInit(){
    this.translateService.setDefaultLang('pt-BR');
    const browserLang = this.translateService.getBrowserLang();
    this. translateService.use("pt-BR");

  }

  ngAfterViewInit() {
    console.log('lang ');
    this.translateService.use('pt-BR');
    this.translateService.get('primeng')
     .subscribe(res => this.primengConfig.setTranslation(res));
 }

}
