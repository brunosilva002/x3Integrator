import { Component, OnInit } from '@angular/core';
import { SpinnerDefaultServiceService } from './spinner-default-service.service';

@Component({
  selector: 'app-spinner-default',
  templateUrl: './spinner-default.component.html',
  styleUrls: ['./spinner-default.component.scss']
})
export class SpinnerDefaultComponent implements OnInit {

    constructor(private spinnerService: SpinnerDefaultServiceService) { }
  
    ngOnInit(): void {
    }
}
