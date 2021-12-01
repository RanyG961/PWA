import { createStore } from 'vuex';

const axios = require('axios');

const instance = axios.create({
    baseURL: 'http://localhost:8181/api/',
    headers: {
        'Access-Control-Allow-Origin': 'http://localhost:8080',
    },
});

const store = createStore({
    state: {},
    actions: {
        createAccount: ({ commit }, userInfos) => {
            commit;
            instance
                .post('/user/register', userInfos)
                .then(function (response) {
                    console.log(response);
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
    },
});

export default store;
