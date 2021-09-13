import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { AuthRequest } from 'src/app/common/auth-request';
import { AuthResult } from 'src/app/common/auth-result';
import { Customer } from 'src/app/common/customer';
import { AuthService } from 'src/app/services/auth.service';
import { Luv2ShopValidators } from 'src/app/validators/luv2-shop-validators';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  closeResult = '';

  loginFormGroup?: FormGroup;
  registerFormGroup?: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router, private modalService: NgbModal) { }



  ngOnInit(): void {

    this.loginFormGroup = this.formBuilder.group({
      signInPassword: new FormControl('', [Validators.required, Validators.minLength(6),
      Luv2ShopValidators.notOnlyWhiteSpace]),
      signInEmail: new FormControl('',
        [Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])
    })

    this.registerFormGroup = this.formBuilder.group({
      firstName: new FormControl('', [Validators.required, Validators.minLength(2),
      Luv2ShopValidators.notOnlyWhiteSpace]),
      lastName: new FormControl('', [Validators.required, Validators.minLength(2),
      Luv2ShopValidators.notOnlyWhiteSpace]),
      signUpPassword: new FormControl('', [Validators.required, Validators.minLength(6),
      Luv2ShopValidators.notOnlyWhiteSpace]),
      signUpEmail: new FormControl('',
        [Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')])
    })

  }

  onLogout() {
    this.authService.logout();
  }

  onRegister(){
    if (this.registerFormGroup?.invalid) {
      this.registerFormGroup.markAllAsTouched();
      return;
    }

    let customer = new Customer();

    customer.firstName = this.registerFormGroup?.controls["firstName"].value;
    customer.lastName = this.registerFormGroup?.controls["lastName"].value;
    customer.email = this.registerFormGroup?.controls["signUpEmail"].value;
    customer.password = this.registerFormGroup?.controls["signUpPassword"].value;

    this.authService.register(customer).subscribe(
      data => {
        this.closeModal()
      }
    );
  }

  onLogin() {

    if (this.loginFormGroup?.invalid) {
      this.loginFormGroup.markAllAsTouched();
      return;
    }

    let authRequest = new AuthRequest();

    authRequest.email = this.loginFormGroup?.controls["signInEmail"].value;
    authRequest.password = this.loginFormGroup?.controls["signInPassword"].value;

    this.authService.login(authRequest).subscribe(
      data => {
        this.setSession(data)
        this.closeModal()
        // this.ngOnInit()
      }
    );
  }

  reloadComponent(): boolean {
    if (localStorage.getItem('id_token')) {
      return true
    }
    return false
  }

  closeModal() {
    this.modalService.dismissAll();
  }

  private async setSession(authResult: AuthResult) {
    // const expiresAt = moment().add(authResult.expiresIn, 'second');

    await localStorage.setItem('id_token', authResult.jwt!);
    // localStorage.setItem("expires_at", JSON.stringify(expiresAt.valueOf()));
  }

  open(content: any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
