import { Component, OnInit } from '@angular/core';

import { BoardService } from './board.service';
import { Board } from './board';

@Component({
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {

  board: Board[];

  testStr: string;;

  constructor(
    private boardService: BoardService
  ) { }

  ngOnInit() {
    document.getElementById("mainNav").style.display = "none";
    document.getElementById("subNav").style.display = "block";
    window.scrollTo(0,0);

    this.testStr = "<p>안녕하세요.</p>";

    this.getBoard();
  }

  getBoard(): void {
    this.boardService.getBoard().then(board => {
      this.board = board;
    });
  }

  

}
