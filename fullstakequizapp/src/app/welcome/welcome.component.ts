import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit{

  @ViewChild('username') nameKey!: ElementRef;// deve matchare con #username
  constructor() {

  }
  
  ngOnInit(): void { }

  startQuiz() {
    localStorage.setItem("name", this.nameKey.nativeElement.value);
  }
}
