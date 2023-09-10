import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../interface/admin';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent implements OnInit {

  //* Campi FORM
  nameInput: string = "";
  passInput: string = "";
  //* Estrapolati da DB
  public adminList: Admin[] = [];

  //? import Router per il dispatch verso admin-page
  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    this.getAdminList();
  }

  //* GET lista admin
  getAdminList(): void {
    this.adminService.getAdmins()
      .subscribe(response => {
        this.adminList = response;
        console.log(response);
      })
  }

  //* CHECK CREDENZIALI
  checkAdmin(): boolean {
    
    let check: boolean = false; // mettere una LET e non una CONST

    for (let i = 0; i < this.adminList.length; i++) {
      if (this.adminList[i].adminName === this.nameInput) {
        for (let j = 0; j < this.adminList.length; j++) {
          if (this.adminList[i].password === this.passInput) {
            if (i === j) {
              console.log("Credenziali corrette");
              return check = true;
            }
          } else {
            console.log("password errata");
            return check = false;
          }
        }
      }
    }
    return check;
  }

  //TODO rederizzare l'utente a Admin-Page (vedere come usare Guard)
  public loginAdmin(): void {

    this.checkAdmin();

    if (this.checkAdmin() === true) {
      //*Manda a /admin-page
      this.router.navigate(['/admin-page']);
    } else {
      alert('Le credenziali sono errate');
    }
  }
  
}
