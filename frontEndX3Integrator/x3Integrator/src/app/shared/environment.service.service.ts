import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EnvironmentServiceService {

  public production: boolean = false;
  public rowsPerPageOptions: number[] = [15, 30, 60];
  public rowsInitial: number = 15
  public heightScreen: number=0;

  private heightScreenSubject = new Subject<number>();
  heightScreen$ = this.heightScreenSubject.asObservable();
  
  private rowsInitialSubject = new Subject<number>();
  rowsInitial$ = this.rowsInitialSubject.asObservable();

  constructor() {
    // Carregue as variáveis do environment aqui
    this.production = environment.production;

    this.initializeRowsInitial();

    window.addEventListener('resize', () => {
      if (window.innerHeight<1000){
        this.rowsInitial=15
      }else{
        this.rowsInitial=25
      }
      this.rowsInitialSubject.next(this.rowsInitial); 
    });
  }
  initializeRowsInitial(): void {
    if (window.innerHeight<1000){
      this.rowsInitial=15
    }else{
      this.rowsInitial=25
    }
  }
}
