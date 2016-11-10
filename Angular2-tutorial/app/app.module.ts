import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';//needed to 2-way binding using forms - input [(ngModel)]="hero.name"

import { AppComponent }   from './app.component';
//npm start in cmd to start an app
@NgModule({
	imports: [
		BrowserModule,
		FormsModule		
	 ],
	declarations: [ AppComponent ],
	bootstrap: [ AppComponent ]
})
export class AppModule { }
