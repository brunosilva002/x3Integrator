import { Component, OnInit } from '@angular/core';
import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';
import { SpinnerDefaultServiceService } from 'src/app/common/spinnerDefault/spinner-default-service.service';
import { SalesQuoteProductDTO, ProductDTO, AxiosProductResourceClient, SalesQuoteX3LogDTO } from 'src/app/shared/java-objects';
import * as vkbeautify from 'vkbeautify';

@Component({
  selector: 'app-modal-view-integration-data',
  templateUrl: './modal-view-integration-data.component.html',
  styleUrls: ['./modal-view-integration-data.component.scss']
})
export class ModalViewIntegrationDataComponent implements OnInit{
onClose() {
throw new Error('Method not implemented.');
}

  activeRegister : SalesQuoteX3LogDTO = {}
  requestBody: any;
  responseBody: any;

  constructor(
    private spinner         : SpinnerDefaultServiceService,
    private dialogRef       : DynamicDialogRef,
    private dialogConfig    : DynamicDialogConfig,
    private productApi      : AxiosProductResourceClient,
  ){
  }
  
  ngOnInit(): void {
    this.spinner.show()
  
    this.activeRegister = this.dialogConfig?.data?.activeRegister || {} as SalesQuoteX3LogDTO
    this.requestBody = vkbeautify.xml(this.activeRegister.x3SendBody!, 2); // Formata o XML com identação de 2 espaços
    this.responseBody = vkbeautify.xml(this.activeRegister.x3ResponseBody!, 2); // Formata o XML com identação de 4 espaços
    this.spinner.hide()
  }

}
