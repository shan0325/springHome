import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { MainComponent } from './main/main.component';
import { BoardComponent } from './board/board.component';
import { AlbumComponent } from './album/album.component';

const routes: Routes = [
    { path: '', component: MainComponent },
    { path: 'board', component: BoardComponent },
    { path: 'album', component: AlbumComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule {}