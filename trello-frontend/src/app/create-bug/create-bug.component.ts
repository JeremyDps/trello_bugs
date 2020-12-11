import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {Bug} from '../models/bug';
import { BugsService } from './../service/bugs.service';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-create-bug',
  templateUrl: './create-bug.component.html',
  styleUrls: ['./create-bug.component.css']
})
export class CreateBugComponent implements OnInit {

  createForm!: FormGroup;
  current_date = formatDate(new Date(), 'yyyy-MM-dd', 'en');


  @Output() createEvent = new EventEmitter<Bug>();

  constructor(public formBuilder: FormBuilder,
              private bugsService: BugsService) {}

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      name: '',
      description: '',
      priority: '',
      progress: '',
      creation_date: null,
      developers: ''
    });
    console.log(this.current_date);
  }

  onSubmit(bugData: { name: any; description: any; priority: any; }): void {
    if(this.createForm != undefined) {
      this.createForm.reset();
      const bug: Bug = {
        name: bugData.name,
        description: bugData.description,
        priority: bugData.priority,
        progress: 'TODO',
        creation_date: new Date(this.current_date),
        developers: {
          "id" : 1,
          "nom" : "Dupuis",
          "prenom": "Jeremy",
          "avatar": ''
        }
      };
      this.bugsService.createBug(bug).subscribe((bugResponse) => {
        this.createEvent.emit(bugResponse);
      })
    }
    

  }


}
