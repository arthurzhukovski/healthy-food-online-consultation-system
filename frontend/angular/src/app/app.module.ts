import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule, JsonpModule} from '@angular/http';
import { RouterModule, Routes } from '@angular/router';


// other imports

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/index';
import { UserTableComponent } from './components/user-table/user-table.component';
import { UserDataItemComponent } from './components/user-data-item/user-data-item.component';
import { AlertComponent } from './components/alert/index';
import { AuthGuard, UserAuthGuard, CoachAuthGuard, AdminAuthGuard, GuestAuthGuard } from './services/index';
import { AlertService, AuthenticationService, UserService, ReportService, GroupService } from './services/index';
import { HomeComponent } from './components/home/index';
import { LoginComponent } from './components/login/index';
import { RegisterComponent } from './components/register/index';
import { UserReportFeedComponent } from './components/user-report-feed/index';
import { UserReportManagement } from './components/user-report-management/index';
import { UserSettingsComponent } from './components/user-settings/index';
import { GroupAssignmentComponent } from './components/group-assignment/index';
import { GroupSelectComponent } from './components/group-select/index';
import { GroupCreatorComponent } from './components/group-creator/index';
import { UserSelectComponent } from './components/user-select/index';
import { ArticleFeedComponent } from './components/article-feed/index';
import { ArticleCreatorComponent } from './components/article-creator/index';
import { DocumentComponent } from './components/documents/document.component';
import {ArticleService} from "./services/article/article.service";

import { ModalComponent } from './components/modal/index';
import { GroupEditorComponent } from './components/group-editor/index';

import {NgForFilter} from "./pipes/ngfor-filter.pipe";
import {EllipsisPipe} from "./pipes/ellipsis.pipe";
import {MessagesComponent} from "./components/messages/messages.component";
import {MessageService} from "./services/message/message.service";


const appRoutes: Routes = [
  { path: '', component: HomeComponent, canActivate: [GuestAuthGuard] },
  { path: 'edit-users', component: UserTableComponent, canActivate: [AdminAuthGuard]},

  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent, canActivate: [GuestAuthGuard]  },
  { path: 'feed', component: UserReportFeedComponent, canActivate: [UserAuthGuard] },
  { path: 'report-management', component: UserReportManagement, canActivate: [CoachAuthGuard] },
  { path: 'user-settings', component: UserSettingsComponent, canActivate: [AuthGuard] },
  { path: 'group-assignment', component: GroupAssignmentComponent, canActivate: [AdminAuthGuard] },
  { path: 'articles', component: ArticleFeedComponent, canActivate: [GuestAuthGuard]  },
  { path: 'articles/create', component: ArticleCreatorComponent, canActivate: [CoachAuthGuard]  },
  { path: 'messages', component: MessagesComponent, canActivate: [AuthGuard] },
  { path: 'documents', component: DocumentComponent, canActivate: [AdminAuthGuard] },
  { path: '**', redirectTo: '', canActivate: [GuestAuthGuard]  },
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    UserTableComponent,
    UserDataItemComponent,
    AlertComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    UserReportFeedComponent,
    UserReportManagement,
    UserSettingsComponent,
    GroupAssignmentComponent,
    GroupSelectComponent,
    GroupCreatorComponent,
    UserSelectComponent,
    ArticleFeedComponent,
    ArticleCreatorComponent,
    MessagesComponent,
    ModalComponent,
    GroupEditorComponent,
    DocumentComponent,
    NgForFilter,
      EllipsisPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    AuthGuard,
    UserAuthGuard,
    CoachAuthGuard,
    AdminAuthGuard,
    GuestAuthGuard,
    AlertService,
    AuthenticationService,
    UserService,
    ReportService,
    GroupService,
    ArticleService,
    MessageService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
