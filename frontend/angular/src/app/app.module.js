"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var platform_browser_1 = require('@angular/platform-browser');
var core_1 = require('@angular/core');
var forms_1 = require('@angular/forms');
var http_1 = require('@angular/http');
var router_1 = require('@angular/router');
// used to create fake backend
var index_1 = require('./helpers/index');
var testing_1 = require('@angular/http/testing');
var http_2 = require('@angular/http');
// other imports
var app_component_1 = require('./app.component');
var index_2 = require('./components/navbar/index');
var user_table_component_1 = require('./components/user-table/user-table.component');
var user_data_item_component_1 = require('./components/user-data-item/user-data-item.component');
var index_3 = require('./components/alert/index');
var index_4 = require('./services/index');
var index_5 = require('./services/index');
var index_6 = require('./components/home/index');
var index_7 = require('./components/login/index');
var index_8 = require('./components/register/index');
var index_9 = require('./components/user-report-feed/index');
var index_10 = require('./components/user-report-management/index');
var index_11 = require('./components/user-settings/index');
var index_12 = require('./components/group-assignment/index');
var index_13 = require('./components/group-select/index');
var index_14 = require('./components/group-creator/index');
var index_15 = require('./components/coach-select/index');
var index_16 = require('./components/article-feed/index');
var index_17 = require('./components/article-creator/index');
var ngfor_filter_pipe_1 = require("./pipes/ngfor-filter.pipe");
var appRoutes = [
    { path: 'user-table', component: user_table_component_1.UserTableComponent, canActivate: [index_4.AuthGuard] },
    { path: '', component: index_6.HomeComponent },
    { path: 'login', component: index_7.LoginComponent },
    { path: 'register', component: index_8.RegisterComponent },
    { path: 'feed', component: index_9.UserReportFeedComponent, canActivate: [index_4.AuthGuard] },
    { path: 'report-management', component: index_10.UserReportManagement, canActivate: [index_4.AuthGuard] },
    { path: 'user-settings', component: index_11.UserSettingsComponent, canActivate: [index_4.AuthGuard] },
    { path: 'group-assignment', component: index_12.GroupAssignmentComponent, canActivate: [index_4.AuthGuard] },
    { path: 'articles', component: index_16.ArticleFeedComponent },
    { path: 'create-article', component: index_17.ArticleCreatorComponent },
    // otherwise redirect to home
    { path: '*', redirectTo: '' }
];
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            declarations: [
                app_component_1.AppComponent,
                index_2.NavbarComponent,
                user_table_component_1.UserTableComponent,
                user_data_item_component_1.UserDataItemComponent,
                index_3.AlertComponent,
                index_6.HomeComponent,
                index_7.LoginComponent,
                index_8.RegisterComponent,
                index_9.UserReportFeedComponent,
                index_10.UserReportManagement,
                index_11.UserSettingsComponent,
                index_12.GroupAssignmentComponent,
                index_13.GroupSelectComponent,
                index_14.GroupCreatorComponent,
                index_15.CoachSelectComponent,
                index_16.ArticleFeedComponent,
                index_17.ArticleCreatorComponent,
                ngfor_filter_pipe_1.NgForFilter
            ],
            imports: [
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                http_1.HttpModule,
                http_1.JsonpModule,
                router_1.RouterModule.forRoot(appRoutes),
            ],
            providers: [
                index_4.AuthGuard,
                index_5.AlertService,
                index_5.AuthenticationService,
                index_5.UserService,
                index_5.ReportService,
                index_5.GroupService,
                // providers used to create fake backend
                index_1.fakeBackendProvider,
                testing_1.MockBackend,
                http_2.BaseRequestOptions
            ],
            bootstrap: [app_component_1.AppComponent]
        })
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
