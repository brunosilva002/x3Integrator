import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-edit-header-div',
  templateUrl: './edit-header-div.component.html',
  styleUrls: ['./edit-header-div.component.scss']
})
export class EditHeaderDivComponent {
  @Input()
  labelDiv : string = ''

  @Input()
  isModal: boolean = false
  
  constructor(
  ){
    
  }
}
