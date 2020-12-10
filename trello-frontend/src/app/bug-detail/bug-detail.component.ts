import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { delay } from 'rxjs/operators';
import { Bug } from '../models/bug';
import { Commentaire } from '../models/commentaire';
import { BugsService } from '../service/bugs.service';

@Component({
  selector: 'app-bug-detail',
  templateUrl: './bug-detail.component.html',
  styleUrls: ['./bug-detail.component.css']
})
export class BugDetailComponent implements OnInit {

  bugs: Bug | undefined;
  commentaire : Commentaire[] = [];

  constructor(private bugsService: BugsService, 
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.bugsService.getBugsById(+id)
    .subscribe((bugResponse => {
      this.bugs = bugResponse;
      console.log(this.bugs);
    }))

    this.bugsService.getCommentairesList()
    .subscribe((comResponse => {
      this.commentaire = comResponse;
      console.log(this.commentaire);
    }))
  }

}
