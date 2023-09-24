import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../interface/admin';
import { AdminService } from '../service/admin.service';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit {

  // @Output() onSubmitLoginEvent = new EventEmitter();
  // @Output() onSubmitRegisterEvent = new EventEmitter();

  formSelected: string = "signIn";

  //* Campi FORM
  nameInput: string = "";
  passInput: string = "";
  //* SignUp
  firstName: string = "";
  lastName: string = "";
  //* Estrapolati da DB
  public adminList: Admin[] = [];

  //? import Router per il dispatch verso admin-page
  constructor(
    private adminService: AdminService,
    private router: Router,
    private authService: AuthService) { }

  ngOnInit(): void {
    // this.getAdminList();
  }

  selectForm(formSelect: string) {
    this.formSelected = formSelect;
  }

  // //* GET lista admin
  // getAdminList(): void {
  //   this.adminService.getAdmins()
  //     .subscribe(response => {
  //       this.adminList = response;
  //       console.log(response);
  //     })
  // }


  // checkAdmin(): boolean {

  //   let check: boolean = false; // mettere una LET e non una CONST

  //   for (let i = 0; i < this.adminList.length; i++) {
  //     if (this.adminList[i].adminName === this.nameInput) {
  //       for (let j = 0; j < this.adminList.length; j++) {
  //         if (this.adminList[i].password === this.passInput) {
  //           if (i === j) {
  //             console.log("Credenziali corrette");
  //             return check = true;
  //           }
  //         } else {
  //           console.log("password errata");
  //           return check = false;
  //         }
  //       }
  //     }
  //   }
  //   return check;
  // }


  // public loginAdmin(): void {

  //   this.checkAdmin();

  //   if (this.checkAdmin() === true) {
  //*Manda a /admin-page
  //     this.router.navigate(['/admin-page']);
  //   } else {
  //     alert('Le credenziali sono errate');
  //   }
  // }

  public loginAdmin() {
    this.authService.request(
      "POST",
      "/login",
      {
        adminName: this.nameInput,
				password: this.passInput
      }
    );
  }
  public registerAdmin() {
    this.authService.request(
      "POST",
      "/register",
      {
        firstName: this.firstName,
				lastName: this.lastName,
				adminName: this.nameInput,
				password: this.passInput
      }
      );
  }
}
