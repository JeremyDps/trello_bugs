import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BugListComponent } from './bug-list/bug-list.component';
import { BugDetailComponent } from './bug-detail/bug-detail.component';
import { CreateBugComponent } from './create-bug/create-bug.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DevelopersListComponent } from './developers-list/developers-list.component';
import { DevelopersDetailsComponent } from './developers-details/developers-details.component';

@NgModule({
  declarations: [
    AppComponent,
    BugListComponent,
    BugDetailComponent,
    CreateBugComponent,
    DevelopersListComponent,
    DevelopersDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
