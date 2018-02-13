import { Component, OnInit } from '@angular/core';
import { PageScrollConfig, PageScrollService, PageScrollInstance, EasingLogic } from 'ng2-page-scroll';

@Component({
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  month: number = 0;

  ngOnInit() {
    var bornDay = new Date(2016, 5, 21); //2016.06.21(태어난 일시)
    var today = new Date();

    var years = today.getFullYear() - bornDay.getFullYear();
    var months = today.getMonth() - bornDay.getMonth();
    var days = today.getDate() - bornDay.getDate();
    //console.log("years = " + years + " // months = " + months + " // days = " + days);

    this.month = years * 12 + months + (days >= 0 ? 0 : -1);
  }

  myEasing: EasingLogic = {
    ease: (t: number, b: number, c: number, d: number): number => {
        // easeInOutExpo easing
        //console.log("t : " + t + " // b : " + b + " // c : " + c + " // d : " + d);
        if (t === 0) return b;
        if (t === d) return b + c;
        if ((t /= d / 2) < 1) return c / 2 * Math.pow(2, 10 * (t - 1)) + b;
        return c / 2 * (-Math.pow(2, -10 * --t) + 2) + b;
    }
  };
  
}
