import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {Bug} from '../models/bug';
import { BugsService } from './../service/bugs.service';

@Component({
  selector: 'app-create-bug',
  templateUrl: './create-bug.component.html',
  styleUrls: ['./create-bug.component.css']
})
export class CreateBugComponent implements OnInit {

  createForm!: FormGroup;

  @Output() createEvent = new EventEmitter<Bug>();

  constructor(public formBuilder: FormBuilder, 
      private bugsService: BugsService) { }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      name:'',dev:'',date:''
    });
  }

    onSubmit(bugData: { id: any; name: any; dev: any; date: any; }): void{
      this.createForm.reset();
      const bug: Bug = {
        id: bugData.id,
        name: bugData.name,
        dev: bugData.dev,
        date: bugData.date
       };
    this.bugsService.createBug(bug).subscribe((bugsResponse: any) => {
      this.createEvent.emit(bugsResponse);
    });
  }

}
