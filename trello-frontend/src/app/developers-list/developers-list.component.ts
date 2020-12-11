import { Component, OnInit } from '@angular/core';
import { Bug } from '../models/bug';
import { Developer } from '../models/developer';
import { BugsService } from '../service/bugs.service';
import { DevelopersService } from '../service/developers.service';

@Component({
  selector: 'app-developers-list',
  templateUrl: './developers-list.component.html',
  styleUrls: ['./developers-list.component.css']
})
export class DevelopersListComponent implements OnInit {

  public devs: Developer[] = [];
  public bugs: Bug[] = [];

  constructor(private developersService: DevelopersService,
              private bugsService: BugsService) { }

  ngOnInit(): void {
    this.developersService.getDevelopersList()
    .subscribe((devsResponse => {
      this.devs = devsResponse;
      console.log(this.devs);
    }))

    this.bugsService.getBugsList()
    .subscribe((bugsResponse => {
      this.bugs = bugsResponse;
      console.log(this.bugs);
    }))
  }

}
