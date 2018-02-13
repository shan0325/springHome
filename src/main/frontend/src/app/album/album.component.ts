import { Component, OnInit } from '@angular/core';

import { AlbumService } from './album.service';
import { Album } from './album';
import { Category } from '../category/category';

declare var $: any; //jquery 사용

@Component({
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css']
})
export class AlbumComponent implements OnInit {

  album: Album[] = [];
  categorys: Category[] = [];
  categoryWidthPercent: string = "0%";
  lastBrdid: number = 0;
  categorycd: number = 0;

  constructor(private albumService: AlbumService) { }

  ngOnInit() {
    document.getElementById("mainNav").style.display = "none";
    document.getElementById("subNav").style.display = "block";
    window.scrollTo(0,0);

    this.getAlbum();
    this.getCategorys();
  }

  //앨범 리스트 가져오기
  getAlbum(): void {
    this.albumService.getAlbum(this.categorycd).then(album => {
      this.album = album;
    });
  }

  getCategorys(): void {
    this.albumService.getCategorys().then(categorys => {
      this.categorys = categorys;
      this.categoryWidthPercent = (100 / (categorys.length + 1)) + "%";
    });
  }

  //더보기 버튼 클릭 시
  fn_moreClick(): void {
    this.album.forEach(element => {
      this.lastBrdid = element.brdid;
    });
    
    this.albumService.getAlbumMore(this.lastBrdid, this.categorycd).then(album => {
      if(album.length == 0) {
        alert("내용이 없습니다.");
        return;
      }

      album.forEach(element => {
        this.album.push(element);
      });
    });
  }

  fn_showImage(brdid): void {
    $("#" + brdid).modal();
  }

  fn_getCategoryAlbumList(categorycd): void {
    this.categorycd = categorycd;
    this.getAlbum();
    $(".nav li.active").removeClass("active");

    if(categorycd == 0) {
      $(".nav #cateAll").closest("li").addClass("active");
    } else {
      $(".nav #cate" + categorycd).closest("li").addClass("active");
    }
  }


}
