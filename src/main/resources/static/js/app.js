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
            // .when('/voter/:id', {templateUrl: 'voter.html', controller: 'VoterDetailCtrl'})
            // .when('/create-vote', {templateUrl: 'create-vote.html', controller: 'VoterCreationCtrl'})
            .otherwise({
                redirectTo: '/error/404'
            });
        $locationProvider.html5Mode(true).hashPrefix('!');
    }]);
