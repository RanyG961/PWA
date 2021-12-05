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

let user = localStorage.getItem('user');

if (!user) {
    user = {
        access_token: '',
        refresh_token: '',
        username: '',
    };
} else {
    try {
        user = JSON.parse(user);
        instance.defaults.headers.common['Authorization'] = user.access_token;
    } catch (e) {
        user = {
            access_token: '',
            refresh_token: '',
            username: '',
        };
    }
}
console.log(user);

const params = new URLSearchParams();

const store = createStore({
    state: {
        status: '',
        user: user,
        userInfos: {
            userId: -1,
            firstName: '',
            lastName: '',
            username: '',
            email: '',
            // password:
            //     '$2a$10$KFTc9EWAg33dwKseOwaYw.rFmQf8VjUBwAWZJ4WpkVsnrCbp1Lc2S',
            biography: '',
            birthDate: '',
            createdTime: '',
            location: '',
            website: '',
            profilePicture: '',
            profileBanner: '',
            profileEnable: '',
            tweets: [],
            chats: [],
            roles: [],
        },
        tweetContent: {
            content: '',
            media: '',
        },
        tweets: [
            {
                tweetId: -1,
                content: '',
                media: '',
                createdTime: '',
            },
        ],
    },

    mutations: {
        setStatus: function (state, status) {
            state.status = status;
        },
        tweets: function (state, tweets) {
            state.tweets = tweets;
        },
        logUser: function (state, user) {
            instance.defaults.headers.common['Authorization'] =
                user.access_token;
            localStorage.setItem('user', JSON.stringify(user));
            state.user = user;
        },
        userInfos: function (state, userInfos) {
            state.userInfos = userInfos;
        },
        logout: function (state) {
            state.user = {
                access_token: '',
                refresh_token: '',
                username: '',
            };
            localStorage.removeItem('user');
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
        createTweet: ({ commit }, tweetInfos) => {
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                console.log(tweetInfos);
                instance
                    .post('/tweet/addTweet', tweetInfos)
                    .then(function (response) {
                        console.log(response);
                        commit('setStatus', 'created');
                        resolve(response);
                    })
                    .catch(function (error) {
                        console.log(error);
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
                        console.log();
                        resolve(response);
                    })
                    .catch(function (error) {
                        commit('setStatus', 'error_login');
                        reject(error);
                    });
            });
        },
        getUserInfos: ({ commit }) => {
            // console.log('Commit : ' + commit(getUserInfos));
            instance
                .post('/user/infos')
                .then(function (response) {
                    commit('userInfos', response.data);
                    console.log(response.data);
                })
                .catch(function () {});
        },
        getTweets: ({ commit }) => {
            instance
                .post('/tweet/allTweets')
                .then(function (response) {
                    console.log(response.data);
                    commit('tweets', response.data);
                })
                .catch(function () {});
        },
    },
});

export default store;
