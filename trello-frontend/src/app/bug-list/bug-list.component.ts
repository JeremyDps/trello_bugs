import { Component, OnInit } from '@angular/core';
import { delay } from 'rxjs/operators';
import { Bug } from '../models/bug';
import {BugsService} from './../service/bugs.service';

@Component({
  selector: 'app-bug-list',
  templateUrl: './bug-list.component.html',
  styleUrls: ['./bug-list.component.css']
})
export class BugListComponent implements OnInit {

  public bugs: Bug[] = [];
  //public showSpinner: boolean;

  constructor(private bugsService: BugsService) { }

  ngOnInit(): void {
    //this.showSpinner = true;
    this.bugsService.getBugsList()
    .subscribe((bugsResponse => {
    this.bugs = bugsResponse;
    console.log(this.bugs);
    //this.showSpinner = false;
    }));
  }

  // deleteBug(id: number): void{
  //   this.bugsService.deleteBug(id).subscribe((deleteResponse) => {
  //     this.bugs = this.bugs.filter(b=>b.id !== id);
  //   },(error) => {
  //   });
  // }

  // addBug(newBug: Bug): void {
  //   this.bugs.push(newBug);
  //   }
  }
  

