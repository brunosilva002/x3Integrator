import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { MessageService } from 'primeng/api';
import { AxiosAuthenticationResourceClient } from 'src/app/shared/java-objects';
import { UtilsService } from 'src/app/shared/utils.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  @Input()
  form:FormGroup = this.fb.group({
    "email":[""],
    "password":[""]
  })

  constructor (
    private authApi: AxiosAuthenticationResourceClient,
    private fb: FormBuilder,
    private router: Router,
    private messageService: MessageService,
    private translate: TranslateService,
    private utilService: UtilsService,
    private spinner: NgxSpinnerService,

    ){

  }

  onLogin(){

    this.spinner.show()
    this.authApi.authenticate(this.form.value).then(resp=>{
      this.utilService.showToast(resp.data.messagens!, "  ");

      this.router.navigateByUrl("/mainPage")
    
    })
    .catch(err=>{
      console.log(err)
      this.utilService.showToast(err.response.data.messagens, "error")
    })
    .finally(()=>{
      this.spinner.hide()
    })
  }
}
