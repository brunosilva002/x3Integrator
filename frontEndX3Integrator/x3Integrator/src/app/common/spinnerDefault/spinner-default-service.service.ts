import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Injectable({
  providedIn: 'root'
})
export class SpinnerDefaultServiceService {

  constructor(
    private spinner: NgxSpinnerService,
  ) { }

  show() {
    this.spinner.show()
  }

  hide() {
    this.spinner.hide()
  }

}
