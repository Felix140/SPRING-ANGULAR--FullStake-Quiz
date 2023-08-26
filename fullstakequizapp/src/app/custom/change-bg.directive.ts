import { Directive, ElementRef, HostListener, Input, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appChangeBg]' //? <-- Questa è la direttiva DA RICHIAMARE DENTRO ANSWERS
})
export class ChangeBgDirective {

  @Input() isCorrect: Boolean = false;

  constructor(private elemRef: ElementRef, private render: Renderer2) { }

  @HostListener('click') answer() {
    if (this.isCorrect) {
      // Background Answer
      this.render.setStyle(this.elemRef.nativeElement, 'background', 'green');
      // Color Text Answer
      this.render.setStyle(this.elemRef.nativeElement, 'color', '#fff');
      // Border Color Answer
      this.render.setStyle(this.elemRef.nativeElement, 'border', '2px solid green');
    } else {
      // Background Answer
      this.render.setStyle(this.elemRef.nativeElement, 'background', 'red');
      // Color Text Answer
      this.render.setStyle(this.elemRef.nativeElement, 'color', '#fff');
      // Border Color Answer
      this.render.setStyle(this.elemRef.nativeElement, 'border', '2px solid red');
    }
  }

}
