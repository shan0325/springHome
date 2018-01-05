import { File } from '../file/file';

export class Board {
    brdid: number;
    menuid: number;
    parbrdid: number;
    topbrdid: number;
    depth: number;
    title: string;
    contents: string;
    regnm: string;
    regdt: string;
    topdisyn: string;
    files: File[];
    pwd: string;
}