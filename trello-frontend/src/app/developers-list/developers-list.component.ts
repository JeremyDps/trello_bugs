import { Component, OnInit } from '@angular/core';
import { Developer } from '../models/developer';
import { DevelopersService } from '../service/developers.service';

@Component({
  selector: 'app-developers-list',
  templateUrl: './developers-list.component.html',
  styleUrls: ['./developers-list.component.css']
})
export class DevelopersListComponent implements OnInit {

  public devs: Developer[] = [];

  constructor(private developersService: DevelopersService) { }

  ngOnInit(): void {
    this.developersService.getDevelopersList()
    .subscribe((devsResponse => {
      this.devs = devsResponse;
      console.log(this.devs);
    }))
  }

}
