import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { QuestionComponent } from './question/question.component';
import { TopicConfigComponent } from './topic-config/topic-config.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  // Questa riga di codice qui sotto dice al router Angular di reindirizzare l'utente al
  // componente welcome se si naviga al percorso radice dell'applicazione, che è l'URL /.
  {path:'', redirectTo: 'welcome', pathMatch: "full"},
  
  {path: "welcome", component: WelcomeComponent},
  {path: "topic-config", component: TopicConfigComponent},
  {path: "question", component: QuestionComponent},
  {path: "admin", component: AdminLoginComponent},
  {path: "admin-page", component: AdminPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
