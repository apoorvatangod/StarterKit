import { Component } from '@angular/core';
@Component({
  selector: 'my-app',//selector used in HTML
  //{{variableName}} - "interpolation" form of one-way data binding
  //template: '<h1>{{title}}</h1><h2>{{hero.name}} details!</h2>'//template/view of HTML page to be shown
  //we can use back-ticks when we want multiline template
  template:`
  <h1>{{title}}</h1>
  <h2>{{hero.name}} details!</h2>
  <div><label>id: </label>{{hero.id}}</div>
  <div>
    <label>name: </label>
    <!-- 1-way binding
    <input value="{{hero.name}}" placeholder="name">-->
    <!-- 2-way binding-->
    <input [(ngModel)]="hero.name" placeholder="name">
  </div>
  <h2>My Heroes</h2>
  <ul class="heroes">
  <!-- FOR loop in angular -->
    <li *ngFor="let hero of heroes">
      <!-- The <span> tag is used to group inline-elements in a document. -->
      <span class="badge">{{hero.id}}</span> {{hero.name}}
    </li>
  </ul>
  `
})
export class AppComponent {
  title = 'Tour of Heroes';
  //creating object of class
  hero: Hero = {
    id: 1,
    name: 'Windstorm'
  }
  //we dont have to define tyoe, because ts can infere it from HEROES arary
  heroes = HEROES;
}
//we create const array of heroes
const HEROES: Hero[] = [
{ id: 11, name: 'Mr. Nice' },
{ id: 12, name: 'Narco' },
{ id: 13, name: 'Bombasto' },
{ id: 14, name: 'Celeritas' },
{ id: 15, name: 'Magneta' },
{ id: 16, name: 'RubberMan' },
{ id: 17, name: 'Dynama' },
{ id: 18, name: 'Dr IQ' },
{ id: 19, name: 'Magma' },
{ id: 20, name: 'Tornado' }
];

export class Hero {
  id: number;
  name: string;
}
