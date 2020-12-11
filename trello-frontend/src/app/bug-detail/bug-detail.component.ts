import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { delay } from 'rxjs/operators';
import { Bug } from '../models/bug';
import { Commentaire } from '../models/commentaire';
import { Developer } from '../models/developer';
import { BugsService } from '../service/bugs.service';
import { DevelopersService } from '../service/developers.service';

@Component({
  selector: 'app-bug-detail',
  templateUrl: './bug-detail.component.html',
  styleUrls: ['./bug-detail.component.css']
})
export class BugDetailComponent implements OnInit {

  bugs !: Bug;
  commentaire : Commentaire[] = [];
  devs: Developer[] = [];
  createForm!: FormGroup;

  @Output() createEvent = new EventEmitter<Commentaire>();

  constructor(private bugsService: BugsService, 
              private developerService: DevelopersService,
              private route: ActivatedRoute,
              public formBuilder: FormBuilder) { }

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

    this.developerService.getDevelopersList()
    .subscribe((devResponse => {
      this.devs = devResponse;
      console.log(this.devs);
    }))

this.createForm = this.formBuilder.group({
  texte: '',
  bugs: '', 
  developers: ''
})

  }


  onSubmitCom(comData: { texte: any; developer: any; }): void {
    // if(this.createForm != undefined) {
    //   this.createForm.reset();
    //   console.log("comDate dev : ", comData.developer);
    //   const commentaire: Commentaire = {
    //     texte: comData.texte,
    //     bugs: this.bugs,
    //     developers: comData.developer
    //   };
    //   this.bugsService.createCommentaire(commentaire, this.bugs.id).subscribe((comResponse) => {
    //     this.createEvent.emit(comResponse);
    //   })
    // }
  }
}
