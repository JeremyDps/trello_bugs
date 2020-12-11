import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Bug } from '../models/bug';
import { Developer } from '../models/developer';
import { BugsService } from '../service/bugs.service';
import { DevelopersService } from '../service/developers.service';

@Component({
  selector: 'app-developers-details',
  templateUrl: './developers-details.component.html',
  styleUrls: ['./developers-details.component.css']
})
export class DevelopersDetailsComponent implements OnInit {

  devs : Developer | undefined;
  public bugs: Bug[] = [];
  noBug: boolean = true;

  constructor(private developerService: DevelopersService,
              private bugsService: BugsService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id']
    this.developerService.getDevelopersById(+id)
    .subscribe((devResponse => {
      this.devs = devResponse;
      console.log(this.devs);
    }))

    this.bugsService.getBugsList()
    .subscribe((bugsResponse => {
      this.bugs = bugsResponse;
      console.log(this.bugs);
    }))

  }

}
