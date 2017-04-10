/**
 * Created by sashqua on 4/6/17.
 */

'use strict';

var service = angular.module('myservices', ['ngRoute', 'ngCookies', 'ngResource']);

service.service(
    'UserService',['$http','$cookies', function($http, $cookies) {

            this.authenticate = function(name, password,
                                         remember, callback) {

                if (!name || !password) {
                    callback(false);
                    return;
                }
                var headers = {
                    authorization : "Basic "
                    + btoa(name + ":" + password)
                };

                console.log(headers);
                $http({
                    method: 'GET',
                    url: 'auth/login',
                    headers: headers
                }).then(function successCallback(response) {
                    if (response.data.username) {
                        if (remember) {
                            createRememberMeCookie(response);
                        }
                        callback(true);
                    } else {
                        callback(false);
                    }
                }, function errorCallback(response) {
                    callback(false);
                });
            };

            this.getUserInfo = function(callback) {
                $http({
                    method: 'GET',
                    url: 'auth/login'
                }).then(function successCallback(response) {
                    if (response.data.username) {
                        callback(response);
                    } else {
                        callback(null);
                    }
                }, function errorCallback(response) {
                    callback(null);
                });
            };

            this.logout = function(callback) {
                console.log("asdasdsd");
                removeRememberMeCookie();
                // return  $http({
                //     method: 'POST',
                //     url: 'auth/logout'
                // });
                $http({
                    method: 'POST',
                    url: 'auth/logout'
                }).then(function successCallback() {
                        callback(true);
                }, function errorCallback() {
                    callback(null);
                });

            };

            var createRememberMeCookie = function(userdetials) {
                var name = userdetials.username;
                var pwd = userdetials.password;
                var expireDate = new Date();
                expireDate.setDate(expireDate.getDate() + 30);

                var cookieValue = btoa(name
                    + ":"
                    + expireDate.getTime().toString()
                    + ":"
                    + md5(name
                        + ":"
                        + expireDate.getTime()
                            .toString() + ":" + pwd
                        + ":"
                        + "DEVELNOTES_REMEMBER_TOKEN"));
                $cookies.put('DEVELNOTES_REMEMBER_ME_COOKIE',
                    cookieValue, {
                        'expires' : expireDate,
                        'path' : '/'
                    });
            };

            var removeRememberMeCookie = function() {
                var expireDate = new Date();
                expireDate.setDate(expireDate.getDate() - 1);
                $cookies.put('DEVELNOTES_REMEMBER_ME_COOKIE',
                    '', {
                        'expires' : expireDate,
                        'path' : '/'
                    });
            };

        } ]);


service.factory('ProjectsFactory', function ($resource) {
    return $resource('/api/projects', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});


service.factory('ProjectFactory', function ($resource) {
    return $resource('/api/project/:id', {}, {
        show: { method: 'GET'},
        update: { method: 'POST', params: {id:'@id'} }
    })
});

service.factory('TasksFactory', function ($resource) {
    return $resource('/api/project/:id/tasks', {}, {
        show: { method: 'GET'},
        create: { method: 'POST', params: {id:'@id'} }
    })
});

service.factory('UsersFactory', function ($resource) {
    return $resource('/api/users', {}, {
        show: { method: 'GET', isArray: true},
        update: { method: 'POST' }
    })
});