import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BugDetailComponent } from './bug-detail/bug-detail.component';
import { BugListComponent } from './bug-list/bug-list.component';
import { CreateBugComponent } from './create-bug/create-bug.component';

const routes: Routes = [
  {path: 'bugs', component: BugListComponent},
  {path: 'bugs/:id', component: BugDetailComponent},
  {path: 'createBug', component: CreateBugComponent},
  {path: '', redirectTo: 'bugs', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
