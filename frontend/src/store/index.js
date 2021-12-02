import { createStore } from 'vuex';

const axios = require('axios');

const instance = axios.create({
    baseURL: 'http://localhost:8181/api/',
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080',
    },
});

const instanceLogin = axios.create({
    baseURL: 'http://localhost:8181/api/',
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080',
        'Content-Type': 'application/x-www-form-urlencoded',
    },
});

const params = new URLSearchParams();

const store = createStore({
    state: {
        status: '',
        user: {
            access_token: '',
            refresh_token: '',
            username: '',
        },
    },
    mutations: {
        setStatus: function (state, status) {
            state.status = status;
        },
        logUser: function (state, user) {
            instanceLogin.defaults.headers.common['Authorization'] =
                user.access_token;
            state.user = user;
        },
    },
    actions: {
        createAccount: ({ commit }, userInfos) => {
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                instance
                    .post('/user/register', userInfos)
                    .then(function (response) {
                        commit('setStatus', 'created');
                        resolve(response);
                    })
                    .catch(function (error) {
                        commit('setStatus', 'error_created');
                        reject(error);
                    });
            });
        },
        login: ({ commit }, userInfos) => {
            params.append('username', userInfos.username);
            params.append('password', userInfos.password);
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                instanceLogin
                    .post('/user/login', params)
                    .then(function (response) {
                        // console.log(response);
                        commit('setStatus', '');
                        commit('logUser', response.data);
                        console.log(response.data);
                        resolve(response);
                    })
                    .catch(function (error) {
                        commit('setStatus', 'error_login');
                        reject(error);
                    });
            });
        },
        // getUserInfos: ({ commit }) => {
        //     instanceLogin.post('/infos')
        //         .then(function (response)
        //     {
        //         commit('userInfos', response.data)
        //     }
        // },
    },
});

export default store;
