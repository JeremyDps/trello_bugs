import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Developer } from '../models/developer';
import { DevelopersService } from '../service/developers.service';

@Component({
  selector: 'app-developers-details',
  templateUrl: './developers-details.component.html',
  styleUrls: ['./developers-details.component.css']
})
export class DevelopersDetailsComponent implements OnInit {

  devs : Developer | undefined;

  constructor(private developerService: DevelopersService,
                      private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id']
    this.developerService.getDevelopersById(+id)
    .subscribe((devResponse => {
      this.devs = devResponse;
      console.log(this.devs);
    }))
  }

}
