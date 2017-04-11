'use strict';
var app = angular.module('taskTrackerApp', ['mycontrollers', 'myservices','ngRoute']);

app.config(['$routeProvider', '$locationProvider',
    function($routeProvider,  $locationProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main.html',
                controller: 'MainController'
            })
            .when('/login', {
                templateUrl: 'login.html',
                controller: 'LoginController'
            })
            .when('/register', {
                templateUrl: 'register.html',
                controller: 'RegisterController'
            })
            .when('/developer/projects', {
                templateUrl: 'developer/projects.html',
                controller: 'ProjectsController'
            })
            .when('/developer/project-create', {
                templateUrl: 'developer/project-create.html',
                controller: 'ProjectCreateController'
            })
            .when('/developer/project/:id', {
                templateUrl: 'developer/project-info.html',
                controller: 'ProjectInfoController'
            })
            .when('/developer/project/:id/task-create', {
                templateUrl: 'developer/task-create.html',
                controller: 'TaskCreateController'
            })
            .when('/developer/project/:pid/task/:id', {
                templateUrl: 'developer/task-info.html',
                controller: 'TaskInfoController'
            })
            .otherwise({
                redirectTo: '/error/404'
            });
        $locationProvider.html5Mode(true).hashPrefix('!');
    }]);
