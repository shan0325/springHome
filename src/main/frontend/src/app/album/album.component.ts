import { Component, OnInit } from '@angular/core';

import { AlbumService } from './album.service';
import { Album } from './album';

declare var $: any; //jquery 사용

@Component({
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css']
})
export class AlbumComponent implements OnInit {

  album: Album[] = [];
  lastBrdid: number = 0;

  constructor(private albumService: AlbumService) { }

  ngOnInit() {
    document.getElementById("mainNav").style.display = "none";
    document.getElementById("subNav").style.display = "block";
    window.scrollTo(0,0);

    this.getAlbum();
  }

  //앨범 리스트 가져오기
  getAlbum(): void {
    this.albumService.getAlbum().then(album => {
      this.album = album;
    })
  }

  //더보기 버튼 클릭 시
  fn_moreClick(): void {
    this.album.forEach(element => {
      this.lastBrdid = element.brdid;
    });
    
    this.albumService.getAlbumMore(this.lastBrdid).then(album => {
      if(album.length == 0) {
        alert("내용이 없습니다.");
        return;
      }

      album.forEach(element => {
        this.album.push(element);
      });
    });
  }


}
