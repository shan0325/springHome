import { Component, OnInit } from '@angular/core';
import { PageScrollConfig, PageScrollService, PageScrollInstance, EasingLogic } from 'ng2-page-scroll';

@Component({
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  month: number = 0;

  ngOnInit() {
    var bornDay = new Date(2016, 6, 21);
    var gapTime = new Date().getTime() - bornDay.getTime();
    var curDay = 24 * 60 * 60 * 1000; //시 * 분 * 초 * 밀리세컨
    var curMonth = curDay * 30;
    var curYear = curMonth * 12;
    
    this.month = Math.floor(gapTime / curMonth);
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
