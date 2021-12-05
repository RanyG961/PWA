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

let userInfo = {
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
};

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
// console.log(user);

const params = new URLSearchParams();
const paramOtherUser = new URLSearchParams();

const store = createStore({
    state: {
        status: '',
        user: user,
        userInfos: userInfo,
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
        users: [
            {
                userId: -1,
                username: '',
                profilePicture: '',
            },
        ],
        anotherUser: {
            userId: -1,
            username: '',
            profilePicture: '',
            tweeets: [],
        },
        demandeFollow: {
            username: '',
        },
        tweet: {
            tweetId: -1,
        },
    },
    mutations: {
        setStatus: function (state, status) {
            state.status = status;
        },
        tweet: function (state, tweet) {
            state.tweet = tweet;
        },
        tweets: function (state, tweets) {
            state.tweets = tweets;
        },
        users: function (state, users) {
            state.users.push(users);
        },
        anotherUser: function (state, anotherUser) {
            state.anotherUser = anotherUser;
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
        demandeFollow: function (state, demandeFollow) {
            state.demandeFollow = demandeFollow;
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
        getAllUsers: ({ commit }) => {
            instance
                .post('/user/findUsers')
                .then(function (response) {
                    // console.log(response.data);
                    commit('users', response.data);
                })
                .catch(function () {});
        },
        getAnotherUserInfo: ({ commit }, anotherUserInfo) => {
            paramOtherUser.append('username', anotherUserInfo.username);
            instance
                .post('user/findOtherUser', paramOtherUser)
                .then(function (response) {
                    console.log(response.data);
                    commit('anotherUser', response.data);
                    paramOtherUser.delete('username');
                })
                .catch(function () {});
        },
        followUser: ({ commit }, followOk) => {
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                instance
                    .post('/user/followUser', followOk)
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
        unfollowUser: ({ commit }, unfollowOk) => {
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                instance
                    .post('/user/unfollow', unfollowOk)
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
        retweetTweet: ({ commit }, retweetOk) => {
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                instance
                    .post('/tweet/rt', retweetOk)
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
        unRtTweet: ({ commit }, unRetweetOk) => {
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                instance
                    .post('/tweet/unRt', unRetweetOk)
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
        favTweet: ({ commit }, favOK) => {
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                instance
                    .post('/tweet/fav', favOK)
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
        unFavTweet: ({ commit }, unFavOk) => {
            return new Promise((resolve, reject) => {
                commit('setStatus', 'loading');
                instance
                    .post('/tweet/unFav', unFavOk)
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
    },
});

export default store;
