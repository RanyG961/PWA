<template>
	<div>
		<h2>User :</h2>
		<p>{{ utilisateur.username }}</p>
		<h2>Tweets</h2>
		<div v-for="(tweet, index) in utilisateur.tweets" :key="index">
			<p>{{ tweet.content }}</p>
			<div>
				<p v-if="tweet.isRetweeted" @click="unRtTweet(tweet.id)">
					Vous avez retweeté le tweet
				</p>
				<button v-else @click="retweetTweet(tweet.id)">
					Vous n'avez pas retweeté le tweet
				</button>
			</div>
			<div>
				<p v-if="tweet.isFavorited" @click="unFavTweet(tweet.id)">
					Vous avez fav le tweet
				</p>
				<button v-else @click="favTweet(tweet.id)">
					Vous n'avez pas fav le tweet
				</button>
			</div>
		</div>
		<div>
			<button v-if="!utilisateur.follows" @click="followUser()">
				Follow
			</button>
			<h2 v-else>
				Abonné <button @click="unfollowUser()">UnFollow</button>
			</h2>
		</div>
	</div>
</template>

<script>
import { mapState } from "vuex";

export default {
	name: "ProfileSearch",
	mounted: function () {
		this.$store.dispatch("getAnotherUserInfo", {
			username: this.$route.params.username,
		});
	},
	computed: {
		...mapState({
			utilisateur: "anotherUser",
		}),
	},
	// updated: function () {
	// 	this.$store.dispatch("getAnotherUserInfo", {
	// 		username: this.$route.params.username,
	// 	});
	// },
	methods: {
		followUser: function () {
			this.$store.dispatch("followUser", {
				username: this.$route.params.username,
			});
		},
		unfollowUser: function () {
			this.$store.dispatch("unfollowUser", {
				username: this.$route.params.username,
			});
		},
		retweetTweet: function (tweetId) {
			this.$store.dispatch("retweetTweet", {
				tweetId: tweetId,
			});
		},
		unRtTweet: function (tweetId) {
			this.$store.dispatch("unRtTweet", {
				tweetId: tweetId,
			});
		},
		favTweet: function (tweetId) {
			this.$store.dispatch("favTweet", {
				tweetId: tweetId,
			});
		},
		unFavTweet: function (tweetId) {
			this.$store.dispatch("unFavTweet", {
				tweetId: tweetId,
			});
		},
	},
};
</script>
